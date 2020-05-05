package uia.d2d;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * Excel file reader.
 *
 * @author Kyle K. Lin
 *
 */
public final class ExcelFile {

    private ExcelFile() {
    }

    /**
     * Read the excel file.
     *
     * @param file The excel file.
     * @param sheetName The sheet name.
     * @param firstRow Index of the first row.
     * @param columnCount Count of columns.
     * @return Result.
     * @throws Exception read failed.
     */
    public static List<String[]> read(String file, String sheetName, int firstRow, int columnCount) throws Exception {
        try (InputStream is = new FileInputStream(file)) {
            return read(is, sheetName, firstRow, columnCount);
        }
    }

    /**
     * Read the excel file.
     *
     * @param file The excel file stream.
     * @param sheetName The sheet name.
     * @param firstRow Index of the first row.
     * @param columnCount Count of columns.
     * @return Result.
     * @throws Exception read failed.
     */
    public static List<String[]> read(InputStream file, String sheetName, int firstRow, int columnCount) throws Exception {
        return read(file, sheetName, firstRow, 0, columnCount);
    }

    /**
     * Read the excel file.
     *
     * @param file The excel file.
     * @param sheetIndex The index of sheet.
     * @param firstRow Index of the first row.
     * @param columnCount Count of columns.
     * @return Result.
     * @throws Exception read failed.
     */
    public static List<String[]> read(String file, int sheetIndex, int firstRow, int columnCount) throws Exception {
        try (InputStream is = new FileInputStream(file)) {
            return read(is, sheetIndex, firstRow, columnCount);
        }
    }

    /**
     * Read the excel file.
     *
     * @param file The excel file stream.
     * @param sheetIndex The index of sheet.
     * @param firstRow Index of the first row.
     * @param columnCount Count of columns.
     * @return Result.
     * @throws Exception read failed.
     */
    public static List<String[]> read(InputStream file, int sheetIndex, int firstRow, int columnCount) throws Exception {
        return read(file, sheetIndex, firstRow, 0, columnCount);
    }

    /**
     * Read the excel file.
     *
     * @param file The excel file.
     * @param sheetName The sheet name.
     * @param firstRow Index of the first row.
     * @param rowCount Count of rows. Detect automatically if value is 0.
     * @param columnCount Count of columns.
     * @return Result.
     * @throws Exception read failed.
     */
    public static List<String[]> read(String file, String sheetName, int firstRow, int rowCount, int columnCount) throws Exception {
        try (InputStream is = new FileInputStream(file)) {
            return read(is, sheetName, firstRow, rowCount, columnCount);
        }
    }

    /**
     * Read the excel file.
     *
     * @param file The excel file stream.
     * @param sheetName The sheet name.
     * @param firstRow Index of the first row.
     * @param rowCount Count of rows. Detect automatically if value is 0.
     * @param columnCount Count of columns.
     * @return Result.
     * @throws Exception read failed.
     */
    public static List<String[]> read(InputStream file, String sheetName, int firstRow, int rowCount, int columnCount) throws Exception {
        try(Workbook wb = WorkbookFactory.create(file)) {
	        Sheet sheet = wb.getSheet(sheetName);
	        if (sheet == null) {
	            throw new Exception("Sheet:" + sheetName + " NOT FOUND in Excel File");
	        }
	
	        return read(sheet, firstRow, rowCount > 0 ? rowCount : sheet.getLastRowNum(), columnCount);
        }
    }

    /**
     * Read the excel file.
     *
     * @param file The excel file.
     * @param sheetIndex The index of sheet.
     * @param firstRow Index of the first row.
     * @param rowCount Count of rows. Detect automatically if value is 0.
     * @param columnCount Count of columns.
     * @return Result.
     * @throws Exception read failed.
     */
    public static List<String[]> read(String file, int sheetIndex, int firstRow, int rowCount, int columnCount) throws Exception {
        try (InputStream is = new FileInputStream(file)) {
            return read(is, sheetIndex, firstRow, rowCount, columnCount);
        }
    }

    /**
     * Read the excel file.
     *
     * @param file The excel file stream.
     * @param sheetIndex The index of sheet.
     * @param firstRow Index of the first row.
     * @param rowCount Count of rows. Detect automatically if value is 0.
     * @param columnCount Count of columns.
     * @return Result.
     * @throws Exception read failed.
     */
    public static List<String[]> read(InputStream file, int sheetIndex, int firstRow, int rowCount, int columnCount) throws Exception {
        try (Workbook wb = WorkbookFactory.create(file)) {
            Sheet sheet = wb.getSheetAt(sheetIndex);
            if (sheet == null) {
                throw new Exception("Sheet:" + sheetIndex + " NOT FOUND");
            }

            return read(sheet, firstRow, rowCount > 0 ? rowCount : sheet.getLastRowNum(), columnCount);
        }
    }

    private static List<String[]> read(Sheet sheet, int firstRow, int rowCount, int columnCount) throws Exception {
        ArrayList<String[]> lines = new ArrayList<>();

        for (int r = firstRow; r <= rowCount; r++) {
            // check
            if (sheet.getRow(r) == null || sheet.getRow(r).getCell(0) == null || sheet.getRow(r).getCell(0).toString().trim().isEmpty()) {
                return lines;
            }

            String[] cols = new String[columnCount];
            for (int c = 0; c < cols.length; c++) {
                try {
                    Cell cell = sheet.getRow(r).getCell(c);
                    if (cell == null) {
                        cols[c] = "";
                    }
                    else if (cell.getCellTypeEnum() == CellType.STRING || cell.getCellTypeEnum() == CellType.FORMULA) {
                        cols[c] = cell.getStringCellValue().trim();
                    }
                    else {
                        cols[c] = "" + cell.toString();
                    }
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    throw new Exception(String.format("Excel Sheet:%s at %s,%s failed", sheet.getSheetName(), r, c));
                }
            }
            lines.add(cols);
        }

        return lines;
    }

}
