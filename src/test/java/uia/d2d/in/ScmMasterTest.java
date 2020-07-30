package uia.d2d.in;

import java.util.List;

import org.junit.Test;

import uia.d2d.ExcelFile;
import uia.dao.Database;
import uia.dao.pg.PostgreSQL;

public class ScmMasterTest {

    private CsvSchema schema;

    public ScmMasterTest() throws Exception {
        this.schema = new CsvSchema(ScmMasterTest.class.getResourceAsStream("/scm_master.xml"));
    }

    @Test
    public void testCountry() throws Exception {
        CsvListenerImpl l = new CsvListenerImpl();
        Csv csv = this.schema.build("country");
        csv.setListener(l);

        List<String[]> lines = ExcelFile.read(
                ScmMasterTest.class.getResourceAsStream("/country_codes.xlsx"),
                0,
                csv.getFirstRow(),
                csv.getRowCount(),
                csv.getColumnCount());
        try (Database db = new PostgreSQL("localhost", "5432", "scmdb", "scm", "scmAdmin")) {
            csv.run(db.getConnection(), lines);
        }
        l.println();
    }

    @Test
    public void testPartSpec() throws Exception {
        CsvListenerImpl l = new CsvListenerImpl();
        Csv csv = this.schema.build("part_spec");
        csv.setListener(l);

        List<String[]> lines = ExcelFile.read(
                "D:/google-drive/giant-soft/scm/02.sasd/part_spec.xlsx",
                "part_spec",
                1,
                10);
        try (Database db = new PostgreSQL("localhost", "5432", "scmdb", "scm", "scmAdmin", "po")) {
            csv.run(db.getConnection(), lines);
        }
        l.println();
    }

    @Test
    public void testPartUom() throws Exception {
        CsvListenerImpl l = new CsvListenerImpl();
        Csv csv = this.schema.build("part_uom");
        csv.setListener(l);

        List<String[]> lines = ExcelFile.read(
                "D:/google-drive/giant-soft/scm/02.sasd/part_spec.xlsx",
                "part_spec",
                1,
                10);
        try (Database db = new PostgreSQL("localhost", "5432", "scmdb", "scm", "scmAdmin", "po")) {
            csv.run(db.getConnection(), lines);
        }
        l.println();
    }

    @Test
    public void testPart() throws Exception {
        CsvListenerImpl l = new CsvListenerImpl();
        Csv csv = this.schema.build("part");
        csv.setListener(l);

        List<String[]> lines = ExcelFile.read(
                "D:/google-drive/giant-soft/scm/02.sasd/part_spec.xlsx",
                "part_spec",
                1,
                10);
        try (Database db = new PostgreSQL("localhost", "5432", "scmdb", "scm", "scmAdmin", "po")) {
            csv.run(db.getConnection(), lines);
        }
        l.println();
    }
}
