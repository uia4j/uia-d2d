package uia.d2d;

public class D2DException extends Exception {

	private static final long serialVersionUID = -5236114766782201917L;

	private final String csv;

	private final String plan;

	private final String column;

	private int rowIndex;

	private String postName;

	public D2DException(String csv, String plan, String column, int rowIndex, Exception ex) {
		super(ex);
		this.csv = csv;
		this.plan = plan;
		this.column = column;
		this.rowIndex  = rowIndex;
	}

	public D2DException(String csv, String plan, String column, int rowIndex, String message) {
		super(message);
		this.csv = csv;
		this.plan = plan;
		this.column = column;
		this.rowIndex  = rowIndex;
	}

	public String getCsv() {
		return csv;
	}

	public String getPlan() {
		return plan;
	}

	public String getColumn() {
		return column;
	}
	
	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}

	public int getRowIndex() {
		return rowIndex;
	}

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}
	
	@Override
	public String toString() {
		return String.format("CSV:%s, PLAN:%s, COL:%s, ROW:%s, POST:%s, %s",
				this.csv,
				this.plan,
				this.column,
				this.rowIndex,
				this.postName,
				getMessage());
	}
	
}
