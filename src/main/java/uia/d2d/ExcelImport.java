package uia.d2d;

import java.io.File;
import java.sql.Connection;
import java.util.List;

import uia.d2d.in.Csv;
import uia.d2d.in.CsvListener;
import uia.d2d.in.CsvSchema;

/**
 * Execl data importer.
 *
 * @author Kyle K. Lin
 *
 */
public class ExcelImport {

    private final CsvSchema schema;

    private final CsvListener listener;

    /**
     * Constructor.
     *
     * @param schemaXML The XML file of schema.
     * @param listener The listener.
     * @throws Exception Failed to read schema file.
     */
    public ExcelImport(File schemaXML, CsvListener listener) throws Exception {
        this.schema = new CsvSchema(schemaXML);
        this.listener = listener;
    }

    /**
     * Run.
     *
     * @param conn The database connection.
     * @param csvName The CSV name.
     * @param xlsFile The Excel file.
     * @param sheetIndex The sheet index.
     * @throws Exception Failed to import.
     */
    public void run(Connection conn, String csvName, String xlsFile, int sheetIndex) throws Exception {
        Csv csv = this.schema.build(csvName);
        csv.setListener(this.listener);

        List<String[]> lines = ExcelFile.read(
                xlsFile,
                sheetIndex,
                csv.getFirstRow(),
                csv.getColumnCount());
        csv.run(conn, lines);
    }

    /**
     * Run.
     *
     * @param conn The database connection.
     * @param csvName The CSV name.
     * @param xlsFile The Excel file.
     * @param sheetIndex The sheet index.
     * @param rowCount The row count.
     * @throws Exception Failed to import.
     */
    public void run(Connection conn, String csvName, String xlsFile, int sheetIndex, int rowCount) throws Exception {
        Csv csv = this.schema.build(csvName);
        csv.setListener(this.listener);

        List<String[]> lines = ExcelFile.read(
                xlsFile,
                sheetIndex,
                csv.getFirstRow(),
                rowCount,
                csv.getColumnCount());
        csv.run(conn, lines);
    }
}
