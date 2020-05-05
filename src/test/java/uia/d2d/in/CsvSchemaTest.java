package uia.d2d.in;

import org.junit.Test;

public class CsvSchemaTest {

    @Test
    public void test1() throws Exception {
        new CsvSchema(CsvSchemaTest.class.getResourceAsStream("/country_codes.xml")).println();
    }

    @Test
    public void test2() throws Exception {
        new CsvSchema(CsvSchemaTest.class.getResourceAsStream("/pms_master.xml")).println();
    }
}
