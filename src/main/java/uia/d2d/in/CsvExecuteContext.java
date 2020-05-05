package uia.d2d.in;

import java.sql.Connection;
import java.util.Map;
import java.util.TreeMap;

public class CsvExecuteContext {

    private final Map<String, String> globalConsts;

    private final Csv csv;

    private final int rowIndex;

    private final Connection conn;

    private final Map<String, Object> rowKeys;

    private final Map<String, Object> columnValues;

    private final Map<String, Integer> counts;

    private boolean failed;

    private String message;

    public CsvExecuteContext(Map<String, String> globalConsts, Csv csv, int rowIndex, Connection conn) {
        this.globalConsts = globalConsts;
        this.csv = csv;
        this.rowIndex = rowIndex;
        this.conn = conn;
        this.rowKeys = new TreeMap<>();
        this.columnValues = new TreeMap<>();
        this.counts = new TreeMap<>();
    }

    public CsvExecuteContext(Map<String, String> globalConsts, Csv csv, int rowIndex, Connection conn, Map<String, Integer> counts) {
        this.globalConsts = globalConsts;
        this.csv = csv;
        this.rowIndex = rowIndex;
        this.conn = conn;
        this.rowKeys = new TreeMap<>();
        this.columnValues = new TreeMap<>();
        this.counts = counts;
    }

    public CsvExecuteContext next() {
        return new CsvExecuteContext(this.globalConsts, this.csv, this.rowIndex + 1, this.conn, this.counts);
    }

    public String getConst(String key) {
        String cs = this.csv.getConst(key);
        return cs == null ? this.globalConsts.get(key) : cs;
    }

    public void putCount(String k, int v) {
        this.counts.put(k, v);
    }

    public Integer getCount(String k) {
        return this.counts.get(k);
    }

    public void addColumnValue(String name, Object value) {
        this.columnValues.put(name, value);
    }

    public Map<String, Object> getColumnValues() {
        return this.columnValues;
    }

    public Object getColumnValue(String name) {
        return this.columnValues.get(name);
    }

    public int getRowIndex() {
        return this.rowIndex;
    }

    public Connection getConnection() {
        return this.conn;
    }

    public Object getRowKey(String key) {
        return this.rowKeys.get(key);
    }

    public void putRowKey(String key, Object value) {
        this.rowKeys.put(key, value);
    }

    public boolean isFailed() {
        return this.failed;
    }

    public void setFailed(boolean failed) {
        this.failed = failed;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
