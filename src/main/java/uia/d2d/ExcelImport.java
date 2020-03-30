package uia.d2d;

import java.io.File;
import java.util.List;

import uia.d2d.in.Csv;
import uia.d2d.in.CsvListener;
import uia.d2d.in.CsvSchema;
import uia.dao.Database;

public class ExcelImport {

    private final CsvSchema schema;

    private final CsvListener listener;

    public ExcelImport(File schemaXML, CsvListener listener) throws Exception {
        this.schema = new CsvSchema(schemaXML);
        this.listener = listener;
    }

    public void run(Database database, String csvName, String xlsFile, int sheetIndex) throws Exception {
        Csv csv = this.schema.build(csvName);
        csv.setListener(this.listener);

        List<String[]> lines = ExcelFile.read(
                xlsFile,
                sheetIndex,
                csv.getFirstRow(),
                csv.getColumnCount());
        csv.run(database, lines);
    }

    public void run(Database database, String csvName, String xlsFile, int sheetIndex, int rowCount) throws Exception {
        Csv csv = this.schema.build(csvName);
        csv.setListener(this.listener);

        List<String[]> lines = ExcelFile.read(
                xlsFile,
                sheetIndex,
                csv.getFirstRow(),
                rowCount,
                csv.getColumnCount());
        csv.run(database, lines);
    }
}
