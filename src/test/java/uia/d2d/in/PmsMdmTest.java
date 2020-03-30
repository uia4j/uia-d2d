package uia.d2d.in;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.junit.Test;

import uia.d2d.ExcelFile;
import uia.dao.pg.PostgreSQL;

public class PmsMdmTest implements CsvListener {

    private CsvSchema schema;

    public PmsMdmTest() throws Exception {
        UUID.randomUUID();
        this.schema = new CsvSchema(new File("test/pms_mdm.xml"));
    }

    @Test
    public void testEquip() throws Exception {
    	String csvName = "equip";
        Csv csv = this.schema.build(csvName);
        csv.setListener(this);

        List<String[]> lines = ExcelFile.read("test/pms_mdm.xlsx", csvName, csv.getFirstRow(), csv.getRowCount(), csv.getColumnCount());
        csv.run(new PostgreSQL("localhost", "5432", "pmsdb", "pms", "pms"), lines);
    }

    @Test
    public void testEquipGroup() throws Exception {
    	String csvName = "設備群組";
        Csv csv = this.schema.build(csvName);
        csv.setListener(this);

        List<String[]> lines = ExcelFile.read("test/pms_mdm.xlsx", csvName, csv.getFirstRow(), csv.getRowCount(), csv.getColumnCount());
        csv.run(new PostgreSQL("localhost", "5432", "pmsdb", "pms", "pms"), lines);
    }

    @Test
    public void testEquipGroupEquip() throws Exception {
    	String csvName = "設備群組-設備";
        Csv csv = this.schema.build(csvName);
        csv.setListener(this);

        List<String[]> lines = ExcelFile.read("test/pms_mdm.xlsx", csvName, csv.getFirstRow(), csv.getRowCount(), csv.getColumnCount());
        csv.run(new PostgreSQL("localhost", "5432", "pmsdb", "pms", "pms"), lines);
    }

    @Test
    public void testPartCategory() throws Exception {
    	String csvName = "備品類別基本檔";
        Csv csv = this.schema.build(csvName);
        csv.setListener(this);

        List<String[]> lines = ExcelFile.read("test/pms_mdm.xlsx", csvName, csv.getFirstRow(), csv.getRowCount(), csv.getColumnCount());
        csv.run(new PostgreSQL("localhost", "5432", "pmsdb", "pms", "pms"), lines);
    }

    @Test
    public void testPart() throws Exception {
    	String csvName = "備品基本檔";
        Csv csv = this.schema.build(csvName);
        csv.setListener(this);

        List<String[]> lines = ExcelFile.read("test/pms_mdm.xlsx", csvName, csv.getFirstRow(), csv.getRowCount(), csv.getColumnCount());
        csv.run(new PostgreSQL("localhost", "5432", "pmsdb", "pms", "pms"), lines);
    }


    @Test
    public void testStructCode() throws Exception {
    	String csvName = "結構碼基本檔";
        Csv csv = this.schema.build(csvName);
        csv.setListener(this);

        List<String[]> lines = ExcelFile.read("test/pms_mdm.xlsx", csvName, csv.getFirstRow(), csv.getRowCount(), csv.getColumnCount());
        csv.run(new PostgreSQL("localhost", "5432", "pmsdb", "pms", "pms"), lines);
    }

    @Test
    public void testBrokenCode() throws Exception {
    	String csvName = "故障碼基本檔";
        Csv csv = this.schema.build(csvName);
        csv.setListener(this);

        List<String[]> lines = ExcelFile.read("test/pms_mdm.xlsx", csvName, csv.getFirstRow(), csv.getRowCount(), csv.getColumnCount());
        csv.run(new PostgreSQL("localhost", "5432", "pmsdb", "pms", "pms"), lines);
    }

    @Test
    public void testWorkGroup() throws Exception {
    	String csvName = "工作群組基本檔";
        Csv csv = this.schema.build(csvName);
        csv.setListener(this);

        List<String[]> lines = ExcelFile.read("test/pms_mdm.xlsx", csvName, csv.getFirstRow(), csv.getRowCount(), csv.getColumnCount());
        csv.run(new PostgreSQL("localhost", "5432", "pmsdb", "pms", "pms"), lines);
    }

    @Test
    public void testEquipGroupStruct() throws Exception {
    	String csvName = "設備群組-結構碼";
        Csv csv = this.schema.build(csvName);
        csv.setListener(this);

        List<String[]> lines = ExcelFile.read("test/pms_mdm.xlsx", csvName, csv.getFirstRow(), csv.getRowCount(), csv.getColumnCount());
        csv.run(new PostgreSQL("localhost", "5432", "pmsdb", "pms", "pms"), lines);
    }

    @Test
    public void testMaItem() throws Exception {
    	String csvName = "設備群組-保養項目";
        Csv csv = this.schema.build(csvName);
        csv.setListener(this);

        List<String[]> lines = ExcelFile.read("test/pms_mdm.xlsx", csvName, csv.getFirstRow(), csv.getRowCount(), csv.getColumnCount());
        csv.run(new PostgreSQL("localhost", "5432", "pmsdb", "pms", "pms"), lines);
    }
    
    @Test
    public void testEquipGroupStructCode() throws Exception {
    	String csvName = "設備群組-故障碼";
        Csv csv = this.schema.build(csvName);
        csv.setListener(this);

        List<String[]> lines = ExcelFile.read("test/pms_mdm.xlsx", csvName, csv.getFirstRow(), csv.getRowCount(), csv.getColumnCount());
        csv.run(new PostgreSQL("localhost", "5432", "pmsdb", "pms", "pms"), lines);
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
