package uia.d2d.in;

import java.util.ArrayList;
import java.util.TreeMap;

import uia.d2d.D2DException;
import uia.d2d.in.conv.SqlColumnConv;
import uia.d2d.in.xml.ParameterType;
import uia.d2d.in.xml.SqlColumnType;

public class SqlColumn {

    private final Csv csv;

    private final CsvPlan plan;

    private final SqlColumnType columnType;

    private final SqlColumnConv conv;

    private final ArrayList<CsvColumn> csvColumns;

    private final TreeMap<String, String> args;

    public SqlColumn(Csv csv, CsvPlan plan, SqlColumnType columnType, SqlColumnConv conv) throws D2DException {
        this.csv = csv;
        this.plan = plan;
        this.columnType = columnType;
        this.conv = conv;
        this.csvColumns = new ArrayList<>();
        this.args = new TreeMap<>();
        for (ParameterType arg : columnType.getArgument()) {
            this.args.put(arg.getName(), arg.getValue());
        }
        if(this.conv == null) {
        	throw new D2DException(
        			csv.getName(), 
        			plan.getName(),
        			columnType.getName(),
        			0,
        			"conv:" + columnType.getConv() + " NOT FOUND");
        }
    }

    public String getName() {
        return this.columnType.getName();
    }

    public String getArgument(String name) {
        return this.args.get(name);
    }

    public String getArgument(String name, String defaultValue) {
        String value = this.args.get(name);
        return value == null ? defaultValue : value;
    }

    public SqlColumnType getType() {
        return this.columnType;
    }

    public void addCsvColumn(CsvColumn cc) {
        this.csvColumns.add(cc);
    }

    public Object toObject(String[] csvValues, CsvExecuteContext ctx) throws Exception {
        try {
            Object[] values = new Object[this.csvColumns.size()];
            for (int i = 0; i < values.length; i++) {
                values[i] = this.csvColumns.get(i).toObject(csvValues, ctx);
            }
            return this.conv.toObject(this, values, ctx);
        }
        catch (Exception ex) {
            this.csv.getListener().failed(this);
            throw ex;
        }
    }

    @Override
    public String toString() {
        return this.plan == null ? String.format("SqlColumn> csv:%s, %s>", this.csv.getName(), this.columnType.getName()) : String.format("SqlColumn> csv:%s, plan:%s, %s>", this.csv.getName(), this.plan.getName(), this.columnType.getName());
    }
}
