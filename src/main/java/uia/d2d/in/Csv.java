package uia.d2d.in;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import uia.d2d.D2DException;
import uia.d2d.in.xml.CsvColumnType;
import uia.d2d.in.xml.CsvType;
import uia.d2d.in.xml.ParameterType;
import uia.d2d.in.xml.PlanType;
import uia.d2d.in.xml.SqlColumnType;

public class Csv {

    private final CsvSchema schema;

    private final CsvType csvType;

    private final Map<String, String> globalConsts;

    private final Map<String, String> csvConsts;

    private final Map<String, SqlColumn> rowKeys;

    private final ArrayList<CsvPlan> plans;

    private CsvListener listener;

    public Csv(CsvSchema schema, CsvType csvType) throws D2DException {
        this.schema = schema;
        this.globalConsts = schema.getGlobalConsts();
        this.csvType = csvType;
        this.csvConsts = csvType.getConst().stream()
                .collect(Collectors.toMap(
                        ParameterType::getName,
                        ParameterType::getValue));

        this.rowKeys = new TreeMap<>();
        List<SqlColumnType> rowKeyTypes = csvType.getRowKey();
        for (SqlColumnType rowKeyType : rowKeyTypes) {
            SqlColumn rowKey = new SqlColumn(this, null, rowKeyType, schema.getSqlColumnConv(rowKeyType.getConv()));
            for (CsvColumnType ccType : rowKeyType.getCsvColumn()) {
                CsvColumn cc = new CsvColumn(this, rowKey, ccType, schema.getCsvColumnConv(ccType.getConv()));
                rowKey.addCsvColumn(cc);
            }
            this.rowKeys.put(rowKey.getName(), rowKey);
        }

        this.plans = new ArrayList<>();
        for (PlanType planType : csvType.getPlan()) {
            CsvPlan plan = new CsvPlan(this, planType, schema);
            this.plans.add(plan);
        }
    }

    public CsvSchema getSchema() {
        return this.schema;
    }

    public String getName() {
        return this.csvType.getName();
    }

    public int getFirstRow() {
        return this.csvType.getFirstRow();
    }

    public int getRowCount() {
        return this.csvType.getRowCount();
    }

    public int getColumnCount() {
        return this.csvType.getColumnCount();
    }

    public CsvListener getListener() {
        return this.listener;
    }

    public void setListener(CsvListener listener) {
        this.listener = listener;
    }

    public String getConst(String name) {
        return this.csvConsts.get(name);
    }

    public void run(final Connection conn, final List<String[]> rows) throws Exception {
        run(conn, rows, new TreeMap<>());
    }

    public void run(final Connection conn, final List<String[]> rows, Map<String, Object> rowKeys) throws D2DException {
        CsvExecuteContext ctx = new CsvExecuteContext(this.globalConsts, this, 1, conn);
        // copy rowKyes into ctx
        for (Map.Entry<String, Object> e : rowKeys.entrySet()) {
            ctx.putRowKey(e.getKey(), e.getValue());
        }

        int rowIndex = 0;
        try {
            conn.setAutoCommit(false);
            for (String[] row : rows) {
                ctx.setFailed(false);
                for (Map.Entry<String, SqlColumn> e : this.rowKeys.entrySet()) {
                    Object value = e.getValue().toObject(row, ctx);
                    if (ctx.isFailed()) {
                        break;
                    }

                    if (value != null) {
                        ctx.putRowKey(e.getKey(), value);
                    }
                    else {
                        ctx.setFailed(true);
                        ctx.setMessage("rowKey:" + e.getKey() + " can not be null");
                        break;
                    }
                }
                if (ctx.isFailed()) {
                    if (this.getListener() != null) {
                        this.getListener().rowIgnore(
                                getName(),
                                null,
                                rowIndex,
                                ctx.getMessage());
                    }
                    continue;
                }

                for (CsvPlan plan : this.plans) {
                    plan.execute(conn, row, ctx);
                }
                rowIndex++;

                //if (ctx.isFailed()) {
                //    System.out.println(ctx.getMessage());
                //}
                ctx = ctx.next();
            }
            conn.commit();
        }
        catch (D2DException ex1) {
            ex1.setRowIndex(rowIndex);
            throw ex1;
        }
        catch (Exception ex2) {
            D2DException d2dex = new D2DException(
                    getName(),
                    null,
                    null,
                    rowIndex,
                    ex2);
            throw d2dex;
        }
    }
}
