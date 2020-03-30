package uia.d2d.in;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.junit.Test;

import uia.d2d.ExcelFile;
import uia.dao.pg.PostgreSQL;

public class CountryCodesTest implements CsvListener {

    private CsvSchema schema;

    public CountryCodesTest() throws Exception {
        UUID.randomUUID();
        this.schema = new CsvSchema(new File("test/country_codes.xml"));
    }

    @Test
    public void test() throws Exception {
        Csv csv = this.schema.build("country");
        csv.setListener(this);

        List<String[]> lines = ExcelFile.read("test/country_codes.xlsx", 0, csv.getFirstRow(), csv.getRowCount(), csv.getColumnCount());
        csv.run(new PostgreSQL("localhost", "5432", "scmdb", "scm", "scmAdmin"), lines);
    }

    @Override
    public void done(String csvName, String plan, int index) {
        System.out.println(String.format("csv:%s, row:%4s plan:%s done",
                csvName,
                index,
                plan));
    }

    @Override
    public void abort(SqlColumn column, String message) {
        System.out.println(String.format("sql:%s ignore: %s",
                column,
                message));

    }

    @Override
    public void failed(SqlColumn column) {
        System.out.println(String.format("sql:%s failed",
                column));
    }

    @Override
    public void failed(CsvColumn column, int rowIndex, int columnIndex) {
        System.out.println(String.format("csv:%s (%s,%s) failed",
                column,
                rowIndex,
                columnIndex));
    }
}
