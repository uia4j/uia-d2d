package uia.csv.in.conv.cc;

import uia.csv.in.CsvColumn;
import uia.csv.in.CsvExecuteContext;
import uia.csv.in.conv.CsvColumnConv;

public class IntConv implements CsvColumnConv {

	@Override
	public Object toObject(CsvColumn column, String value, CsvExecuteContext ctx) {
		return value == null ? null : Integer.parseInt(value);
	}

}
