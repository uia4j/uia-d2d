package uia.d2d.in;

import java.util.List;

import org.junit.Test;

import uia.d2d.ExcelFile;
import uia.dao.Database;
import uia.dao.pg.PostgreSQL;

public class PmsMasterTest {

    private CsvSchema schema;

    public PmsMasterTest() throws Exception {
        this.schema = new CsvSchema(PmsMasterTest.class.getResourceAsStream("/pms_master.xml"));
    }

    @Test
    public void test() throws Exception {
        CsvListenerImpl l = new CsvListenerImpl();
        Csv csv = this.schema.build("STRUCT_CODE");
        csv.setListener(l);

        List<String[]> lines = ExcelFile.read(
                PmsMasterTest.class.getResourceAsStream("/pms_master.xlsx"),
                "STRUCT_CODE",
                csv.getFirstRow(),
                csv.getRowCount(),
                csv.getColumnCount());
        try (Database db = new PostgreSQL("localhost", "5432", "pmsdb", "pms", "pms")) {
            csv.run(db.getConnection(), lines);
        }
        l.println();
    }
}
