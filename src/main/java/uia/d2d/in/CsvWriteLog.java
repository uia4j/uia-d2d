package uia.d2d.in;

public class CsvWriteLog {
	
	private String csvName;

    private int row;

    private int column;

    private int level;

    private String message;

    public CsvWriteLog(String csvName) {
       	this.csvName = csvName;
                this.level = 0;
        this.message = "Unknown";
    }

    public CsvWriteLog(String csvName, int row, int column) {
       	this.csvName = csvName;
               this.row = row;
        this.column = column;
        this.level = 0;
        this.message = "OK";
    }

    public CsvWriteLog(String csvName, int row, int column, int level, String message) {
       	this.csvName = csvName;
        this.row = row;
        this.column = column;
        this.level = level;
        this.message = message;
    }

    public String getCsvName() {
		return csvName;
	}

	public void setCsvName(String csvName) {
		this.csvName = csvName;
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
