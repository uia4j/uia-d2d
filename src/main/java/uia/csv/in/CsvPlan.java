package uia.csv.in;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import uia.csv.in.xml.CsvColumnType;
import uia.csv.in.xml.FilterType;
import uia.csv.in.xml.JobType;
import uia.csv.in.xml.ParameterType;
import uia.csv.in.xml.PlanType;
import uia.csv.in.xml.SqlColumnType;
import uia.dao.Database;

public class CsvPlan {
	
	private final Csv csv;
	
	private final PlanType planType;
	
	private final ArrayList<SqlColumn> sqlColumns;
	
	CsvPlan(Csv csv, PlanType planType, CsvBuilder builder) {
		this.csv = csv;
		this.planType = planType;
		this.sqlColumns = new ArrayList<>();
		for(SqlColumnType scType : planType.getSqlColumns().getSqlColumn()) {
			SqlColumn sc = new SqlColumn(this.csv, this, scType, builder.getSqlColumnConv(scType.getConv()));
			for(CsvColumnType ccType : scType.getCsvColumn()) {
				CsvColumn cc = new CsvColumn(this.csv, sc, ccType, builder.getCsvColumnConv(ccType.getConv()));
				sc.addCsvColumn(cc);
			}
			this.sqlColumns.add(sc);
		}
	}
	
	public String getName() {
		return this.planType.getName();
	}
	
	public void execute(Database database, String[] oneRow, CsvExecuteContext ctx) throws Exception {
		FilterType ft = this.planType.getFilter();
		if(ft != null) {
			String check = oneRow[ft.getCsvColumnIndex()];
			if(check == null) {
				check = "";
			}
			if("eq".equals(ft.getOp()) && !check.equals(ft.getValue())) {
				return;
			}
			if("neq".equals(ft.getOp()) && check.equals(ft.getValue())) {
				return;
			}
		}

		ctx.setAbort(false);

		ArrayList<Object> columnValues = new ArrayList<>();
		for(SqlColumn sc : this.sqlColumns) {
			try {
				Object value = sc.toObject(oneRow, ctx);
				ctx.addColumnValue(sc.getName(), value);
				columnValues.add(value);
			}
			catch(Exception ex) {
				String message = ex.getClass().getName() + ":" + ex.getMessage();
				throw new Exception(String.format("plan:%s sqlColumn:%s %s", planType.getName(), sc.getName(), message), ex);
			}
		}
		
		if(ctx.isAbort()) {
			return;
		}

		Connection conn = database.getConnection();
		try(PreparedStatement ps = conn.prepareStatement(this.planType.getSql())) {
			int i = 1;
			for(Object columnValue : columnValues) {
				if(columnValue instanceof Date) {
					ps.setObject(i, new Timestamp(((Date)columnValue).getTime()));
				}
				else {
					ps.setObject(i, columnValue);
				}
				i++;
			}
			ps.execute();
		}
		
		if(this.planType.getPost() != null) {
			for(JobType jobType : this.planType.getPost().getJob()) {
				try(PreparedStatement ps = conn.prepareStatement(jobType.getSql())) {
					int i =1;
					for(ParameterType pt : jobType.getParameter()) {
						Object value = ctx.getColumnValue(pt.getName());
						if(value == null) {
							value = ctx.getConst(pt.getName());
						}
						
						try {
							if(value instanceof Date) {
								ps.setObject(i, new Timestamp(((Date)value).getTime()));
							}
							else {
								ps.setObject(i, value);
							}
						}
						catch(Exception ex) {
							throw new Exception(String.format("job:%s parameter:%s failed", jobType.getName(), pt.getName()));
						}
						i++;
					}
					ps.execute();
				}
				catch(SQLException ex2) {
					throw new Exception(jobType.getSql(), ex2);
				}
			}
		}
		if(this.csv.getListener() != null) {
			this.csv.getListener().done(this.csv.getName(), this.planType.getName(), ctx.getRowIndex());
		}
	}
}
