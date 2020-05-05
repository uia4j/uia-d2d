package uia.d2d.in;

public class CsvWriteLog {

    private int row;

    private int column;

    private int level;

    private String message;

    public CsvWriteLog() {
        this.level = 0;
        this.message = "Unknown";
    }

    public CsvWriteLog(int row, int column) {
        this.row = row;
        this.column = column;
        this.level = 0;
        this.message = "OK";
    }

    public CsvWriteLog(int row, int column, int level, String message) {
        this.row = row;
        this.column = column;
        this.level = level;
        this.message = message;
    }

    public int getRow() {
        return this.row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return this.column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
