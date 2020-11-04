package uia.d2d.in;

import java.util.ArrayList;
import java.util.List;

public class CsvListenerImpl implements CsvListener {

    private final ArrayList<CsvWriteLog> results;

    public CsvListenerImpl() {
        this.results = new ArrayList<CsvWriteLog>();
    }

    public List<CsvWriteLog> getResults() {
        return this.results;
    }

    public void println() {
        for (CsvWriteLog r : this.results) {
            System.out.println(String.format("(%3s,%2s): %s", r.getRow(), r.getColumn(), r.getMessage()));
        }
    }

    @Override
    public void cellFailed(String csvName, CsvColumn column, int rowIndex, int columnIndex) {
        this.results.add(new CsvWriteLog(csvName, rowIndex, columnIndex, 2, "Ignore"));
        System.out.println(String.format("cell(%3s,%2s) failed, %s",
                rowIndex,
                columnIndex,
                column));
    }

    @Override
    public void rowDone(String csvName, String plan, int rowIndex) {
        this.results.add(new CsvWriteLog(csvName, rowIndex, 0, 0, "OK"));
        System.out.println(String.format("row(%3s): csv:%s, plan:%s, done",
                rowIndex,
                csvName,
                plan));
    }

    @Override
    public void rowIgnore(String csvName, String plan, int rowIndex, String message) {
        this.results.add(new CsvWriteLog(csvName, rowIndex, 0, 1, "ignore: " + message));
        System.out.println(String.format("row(%3s): csv:%s, plan:%s, ignore",
                rowIndex,
                csvName,
                plan));
    }

    @Override
    public void rowFailed(String csvName, String plan, int rowIndex, String message) {
        this.results.add(new CsvWriteLog(csvName, rowIndex, 0, 2, "failed: " + message));
        System.out.println(String.format("row(%3s): csv:%s, plan:%s, failed, %s",
                rowIndex,
                csvName,
                plan,
                message));
    }
}
