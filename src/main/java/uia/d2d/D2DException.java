package uia.d2d;

/**
 * D2D Exception.
 *
 * @author Kyle K. Lin
 *
 */
public class D2DException extends Exception {

    private static final long serialVersionUID = -5236114766782201917L;

    private final String csv;

    private final String plan;

    private final String column;

    private int rowIndex;

    private String postName;

    /**
     * Constructor.
     *
     * @param csv The csv name.
     * @param plan The plan name.
     * @param column The column name.
     * @param rowIndex The row index.
     * @param ex The exception.
     */
    public D2DException(String csv, String plan, String column, int rowIndex, Exception ex) {
        super(ex);
        this.csv = csv;
        this.plan = plan;
        this.column = column;
        this.rowIndex = rowIndex;
    }

    /**
     * Constructor.
     *
     * @param csv The csv name.
     * @param plan The plan name.
     * @param column The column name.
     * @param rowIndex The row index.
     * @param message The exception message.
     */
    public D2DException(String csv, String plan, String column, int rowIndex, String message) {
        super(message);
        this.csv = csv;
        this.plan = plan;
        this.column = column;
        this.rowIndex = rowIndex;
    }

    /**
     * Returns CSV name.
     *
     * @return The CSV name.
     */
    public String getCsv() {
        return this.csv;
    }

    /**
     * Returns plan name.
     *
     * @return The plan name.
     */
    public String getPlan() {
        return this.plan;
    }

    /**
     * Returns column name.
     *
     * @return The column name.
     */
    public String getColumn() {
        return this.column;
    }

    /**
     * Sets row index.
     *
     * @param rowIndex The row index.
     */
    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    /**
     * Return row index.
     *
     * @return The row index.
     */
    public int getRowIndex() {
        return this.rowIndex;
    }

    /**
     * Returns name of post action.
     *
     * @return The name of post action.
     */
    public String getPostName() {
        return this.postName;
    }

    /**
     * Sets name of post action.
     *
     * @param postName The name of post action.
     */
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
