package uia.d2d.in;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import uia.d2d.D2DException;
import uia.d2d.ExcelFile;
import uia.d2d.in.xml.CsvColumnType;
import uia.d2d.in.xml.FilterType;
import uia.d2d.in.xml.JobType;
import uia.d2d.in.xml.ParameterType;
import uia.d2d.in.xml.PlanType;
import uia.d2d.in.xml.SheetJobType;
import uia.d2d.in.xml.SimpleJobType;
import uia.d2d.in.xml.SqlColumnType;
import uia.dao.Database;

public class CsvPlan {

    private final Csv csv;

    private final PlanType planType;

    private final ArrayList<SqlColumn> sqlColumns;

    CsvPlan(Csv csv, PlanType planType, CsvSchema builder) throws D2DException {
        this.csv = csv;
        this.planType = planType;
        this.sqlColumns = new ArrayList<>();
        for (SqlColumnType scType : planType.getSqlColumns().getSqlColumn()) {
            SqlColumn sc = new SqlColumn(this.csv, this, scType, builder.getSqlColumnConv(scType.getConv()));
            for (CsvColumnType ccType : scType.getCsvColumn()) {
                CsvColumn cc = new CsvColumn(this.csv, sc, ccType, builder.getCsvColumnConv(ccType.getConv()));
                sc.addCsvColumn(cc);
            }
            this.sqlColumns.add(sc);
        }
    }

    public String getName() {
        return this.planType.getName();
    }

    public void execute(Database database, String[] oneRow, CsvExecuteContext ctx) throws D2DException {
        FilterType ft = this.planType.getFilter();
        if (ft != null) {
            String check = oneRow[ft.getCsvColumnIndex()];
            if (check == null) {
                check = "";
            }
            if ("eq".equals(ft.getOp()) && !check.equals(ft.getValue())) {
                return;
            }
            if ("neq".equals(ft.getOp()) && check.equals(ft.getValue())) {
                return;
            }
        }

        ctx.setAbort(false);

        ArrayList<Object> columnValues = new ArrayList<>();
        for (SqlColumn sc : this.sqlColumns) {
            try {
                Object value = sc.toObject(oneRow, ctx);
                ctx.addColumnValue(sc.getName(), value);
                columnValues.add(value);
            }
            catch (Exception ex) {
                String message = ex.getClass().getName() + ":" + ex.getMessage();
            	throw new D2DException(
            			this.csv.getName(),
            			this.getName(),
            			sc.getName(),
            			0,
            			message);
            }
        }

        if (ctx.isAbort()) {
            return;
        }

        Connection conn = database.getConnection();
        try (PreparedStatement ps = conn.prepareStatement(this.planType.getSql())) {
            int i = 1;
            for (Object columnValue : columnValues) {
                if (columnValue instanceof Date) {
                    ps.setObject(i, new Timestamp(((Date) columnValue).getTime()));
                }
                else {
                    ps.setObject(i, columnValue);
                }
                i++;
            }
            ps.execute();
        }
        catch(Exception ex) {
        	throw new D2DException(
        			this.csv.getName(),
        			this.getName(),
        			null,
        			0,
        			ex);
        }

        if (this.planType.getPost() != null) {
            for (JobType jobType : this.planType.getPost().getSimpleJobOrSheetJob()) {
                if (jobType instanceof SimpleJobType) {
                    SimpleJobType sjType = (SimpleJobType) jobType;
                    try (PreparedStatement ps = conn.prepareStatement(sjType.getSql())) {
                        int i = 1;
                        for (ParameterType pt : sjType.getParameter()) {
                            Object value = ctx.getColumnValue(pt.getName());
                            if (value == null) {
                                value = ctx.getConst(pt.getName());
                            }

                            if (value instanceof Date) {
                                ps.setObject(i, new Timestamp(((Date) value).getTime()));
                            }
                            else {
                                ps.setObject(i, value);
                            }
                            i++;
                        }
                        ps.execute();
                    }
                    catch (SQLException ex2) {
                    	D2DException d2dex = new D2DException(
                    			this.csv.getName(),
                    			this.getName(),
                    			null,
                    			0,
                    			ex2);
                    	d2dex.setPostName(jobType.getName());
                    	throw d2dex;
                    }
                }
                else {
                    SheetJobType sjType = (SheetJobType) jobType;
                    Csv childCsv = this.csv.getSchema().build(sjType.getCsvType());

                    try {
	                    List<String[]> childLines = ExcelFile.read(
	                            sjType.getFilePath(),
	                            sjType.getSheetName(),
	                            childCsv.getFirstRow(),
	                            childCsv.getRowCount(),
	                            childCsv.getColumnCount());
	                    childCsv.run(database, childLines, ctx.getColumnValues());
                    }
                    catch(D2DException ex1) {
                    	ex1.setPostName(sjType.getName());
                    	throw ex1;
                    }
                    catch(Exception ex2) {
                    	throw new D2DException(
                    			this.csv.getName(),
                    			this.getName(),
                    			null,
                    			0,
                    			ex2);
                    }
                }
            }
        }
        if (this.csv.getListener() != null) {
            this.csv.getListener().done(this.csv.getName(), this.planType.getName(), ctx.getRowIndex());
        }
    }
}
