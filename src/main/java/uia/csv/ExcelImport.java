package uia.csv;

import java.io.File;
import java.util.List;

import uia.csv.in.Csv;
import uia.csv.in.CsvBuilder;
import uia.csv.in.CsvListener;
import uia.dao.Database;

public class ExcelImport {

	private final CsvBuilder builder;

	private final CsvListener listener;
	
	public ExcelImport(File workspaceFile, CsvListener listener) throws Exception {
		this.builder = new CsvBuilder(workspaceFile);
		this.listener = listener;
	}
	
	public void run(Database database, String csvName, String xlsFile, int sheetIndex) throws Exception {
		Csv csv = this.builder.build(csvName);

		List<String[]> lines = ExcelFile.read(
				xlsFile, 
				sheetIndex,
				csv.getFirstRow(), 
				csv.getColumnCount());
		csv.setListener(this.listener);
		csv.run(database, lines);
	}
	
	public void run(Database database, String csvName, String xlsFile, int sheetIndex, int rowCount) throws Exception {
		Csv csv = this.builder.build(csvName);
		csv.setListener(this.listener);

		List<String[]> lines = ExcelFile.read(
				xlsFile, 
				sheetIndex,
				csv.getFirstRow(), 
				csv.getFirstRow() + rowCount, 
				csv.getColumnCount());
		csv.run(database, lines);
	}
}
