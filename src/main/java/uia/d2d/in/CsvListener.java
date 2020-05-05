package uia.d2d.in;

public interface CsvListener {

    public void cellFailed(CsvColumn column, int rowIndex, int columnIndex);

    public void rowIgnore(String csvName, String plan, int rowIndex, String message);

    public void rowFailed(String csvName, String plan, int rowIndex, String message);

    public void rowDone(String csvName, String plan, int rowIndex);
}
