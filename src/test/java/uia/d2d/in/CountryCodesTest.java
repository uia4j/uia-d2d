package uia.d2d.in;

import java.util.List;

import org.junit.Test;

import uia.d2d.ExcelFile;
import uia.dao.Database;
import uia.dao.pg.PostgreSQL;

public class CountryCodesTest {

    private CsvSchema schema;

    public CountryCodesTest() throws Exception {
        this.schema = new CsvSchema(CountryCodesTest.class.getResourceAsStream("/country_codes.xml"));
    }

    @Test
    public void test() throws Exception {
        CsvListenerImpl l = new CsvListenerImpl();
        Csv csv = this.schema.build("country");
        csv.setListener(l);

        List<String[]> lines = ExcelFile.read(
                CountryCodesTest.class.getResourceAsStream("/country_codes.xlsx"),
                0,
                csv.getFirstRow(),
                csv.getRowCount(),
                csv.getColumnCount());
        try (Database db = new PostgreSQL("localhost", "5432", "scmdb", "scm", "scmAdmin")) {
            csv.run(db.getConnection(), lines);
        }
        l.println();
    }
}
