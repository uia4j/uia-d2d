package uia.d2d;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public final class ExcelFile {

    private ExcelFile() {
    }

    public static List<String[]> read(String file, String sheetName, int firstRow, int columnCount) throws Exception {
        ArrayList<String[]> lines = new ArrayList<>();

        try (InputStream inp = new FileInputStream(file)) {
            try (Workbook wb = WorkbookFactory.create(inp)) {
                Sheet sheet = wb.getSheet(sheetName);
                if(sheet == null) {
                	throw new Exception("Sheet:" + sheetName + " NOT FOUND");
                }
                int num = sheet.getLastRowNum();
                for (int r = firstRow; r <= num; r++) {
            		// check
            		if(sheet.getRow(r) == null || sheet.getRow(r).getCell(0) == null || sheet.getRow(r).getCell(0).toString().trim().isEmpty()) {
            			return lines;
            		}

            		String[] cols = new String[columnCount];
                    for (int c = 0; c < cols.length; c++) {
                    	try {
	                        Cell cell = sheet.getRow(r).getCell(c);
	                        cols[c] = cell == null ? "" : cell.toString().trim();
                    	}
                    	catch(Exception ex) {
                    		throw new Exception(String.format("Excel Sheet:%s at %s,%s failed", sheetName, r, c));
                    	}
                    }
                    lines.add(cols);
                }
            }
        }

        return lines;
    }

    public static List<String[]> read(String file, String sheetName, int firstRow, int rowCount, int columnCount) throws Exception {
        if (rowCount <= 0) {
            return read(file, sheetName, firstRow, columnCount);
        }

        ArrayList<String[]> lines = new ArrayList<>();

        try (InputStream inp = new FileInputStream(file)) {
            try (Workbook wb = WorkbookFactory.create(inp)) {
                Sheet sheet = wb.getSheet(sheetName);
                if(sheet == null) {
                	throw new Exception("SheetName:" + sheetName + " NOT FOUND");
                }
                for (int r = firstRow; r < (firstRow + rowCount); r++) {
            		// check
            		if(sheet.getRow(r) == null || sheet.getRow(r).getCell(0) == null || sheet.getRow(r).getCell(0).toString().trim().isEmpty()) {
            			return lines;
            		}

            		String[] cols = new String[columnCount];
                    for (int c = 0; c < cols.length; c++) {
                		Object v = sheet.getRow(r).getCell(c);
                        cols[c] = v == null ? null : v.toString().trim();
                    }
                    lines.add(cols);
                }
            }
        }

        return lines;
    }

    public static List<String[]> read(String file, int sheetIndex, int firstRow, int columnCount) throws Exception {
        ArrayList<String[]> lines = new ArrayList<>();

        try (InputStream inp = new FileInputStream(file)) {
            try (Workbook wb = WorkbookFactory.create(inp)) {
                Sheet sheet = wb.getSheetAt(sheetIndex);
                int num = sheet.getLastRowNum();
                for (int r = firstRow; r <= num; r++) {
            		// check
            		if(sheet.getRow(r) == null || sheet.getRow(r).getCell(0) == null || sheet.getRow(r).getCell(0).toString().trim().isEmpty()) {
            			return lines;
            		}

            		String[] cols = new String[columnCount];
                    for (int c = 0; c < cols.length; c++) {
                		Cell cell = sheet.getRow(r).getCell(c);
                        cols[c] = cell == null ? "" : cell.toString().trim();
                    }
                    lines.add(cols);
                }
            }
        }

        return lines;
    }

    public static List<String[]> read(String file, int sheetIndex, int firstRow, int rowCount, int columnCount) throws Exception {
        if (rowCount <= 0) {
            return read(file, sheetIndex, firstRow, columnCount);
        }

        ArrayList<String[]> lines = new ArrayList<>();

        try (InputStream inp = new FileInputStream(file)) {
            try (Workbook wb = WorkbookFactory.create(inp)) {
                Sheet sheet = wb.getSheetAt(sheetIndex);
                for (int r = firstRow; r < (firstRow + rowCount); r++) {
            		// check
            		if(sheet.getRow(r) == null || sheet.getRow(r).getCell(0) == null || sheet.getRow(r).getCell(0).toString().trim().isEmpty()) {
            			return lines;
            		}

            		String[] cols = new String[columnCount];
                    for (int c = 0; c < cols.length; c++) {
                        Object v = sheet.getRow(r).getCell(c);
                        cols[c] = v == null ? null : v.toString().trim();
                    }
                    lines.add(cols);
                }
            }
        }

        return lines;
    }
}
