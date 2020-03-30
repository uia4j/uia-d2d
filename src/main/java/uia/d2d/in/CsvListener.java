package uia.d2d.in;

public interface CsvListener {

    public void done(String csvName, String plan, int index);

    public void abort(SqlColumn column, String message);

    public void failed(SqlColumn column);

    public void failed(CsvColumn column, int rowIndex, int columnIndex);
}
