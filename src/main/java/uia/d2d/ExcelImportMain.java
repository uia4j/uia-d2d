package uia.d2d;

import java.io.File;
import java.util.List;
import java.util.UUID;

import uia.d2d.in.Csv;
import uia.d2d.in.CsvColumn;
import uia.d2d.in.CsvListener;
import uia.d2d.in.CsvSchema;
import uia.d2d.in.SqlColumn;
import uia.dao.pg.PostgreSQL;

public class ExcelImportMain implements CsvListener {

    private PostgreSQL pg;

    private CsvSchema schema;

    private String dir;

    public static void main(String[] args) throws Exception {
        ExcelImportMain pg = new ExcelImportMain(args[0], args[1]);
        pg.run(args[2]);
    }

    public ExcelImportMain(String xml, String excelFile) throws Exception {
        UUID.randomUUID();
        this.dir = excelFile;

        this.pg = new PostgreSQL("10.10.2.169", "5432", "scmdb", "scm", "scmAdmin");
        this.schema = new CsvSchema(new File(xml));
    }

    public void run(String csvName) throws Exception {
        Csv csv = this.schema.build(csvName);
        csv.setListener(this);

        List<String[]> lines = ExcelFile.read(this.dir, 0, csv.getFirstRow(), csv.getColumnCount());
        csv.run(this.pg, lines);
    }

    @Override
    public void done(String csvName, String plan, int index) {
        System.out.println(String.format("csv:%s, plan:%s, index:%4s done",
                csvName,
                plan,
                index));
    }

    @Override
    public void abort(SqlColumn column, String message) {
        System.out.println(String.format("%s %s abort",
                column,
                message));

    }

    @Override
    public void failed(SqlColumn column) {
        System.out.println(String.format("%s failed",
                column));
    }

    @Override
    public void failed(CsvColumn column, int rowIndex, int columnIndex) {
        System.out.println(String.format("%s (%s,%s) failed",
                column,
                rowIndex,
                columnIndex));
    }
}
