package uia.csv.in;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.junit.Test;

import uia.csv.ExcelFile;
import uia.dao.pg.PostgreSQL;

public class CountryCodesTest implements CsvListener {

	private PostgreSQL pg;
	
	private CsvBuilder builder;

	public CountryCodesTest() throws Exception {
		UUID.randomUUID();
		
		this.pg = new PostgreSQL("localhost", "5432", "scmdb", "scm", "scmAdmin");
		this.builder = new CsvBuilder(new File("test/country_codes.xml"));
	}

	@Test
	public void test() throws Exception {
		Csv csv = this.builder.build("country");
		csv.setListener(this);

		List<String[]> lines = ExcelFile.read("test/country_codes.xlsx", 0, csv.getFirstRow(), csv.getColumnCount());
		csv.run(pg, lines);
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
