package uia.csv.in.conv.cc;

import uia.csv.in.CsvColumn;
import uia.csv.in.CsvExecuteContext;
import uia.csv.in.conv.CsvColumnConv;

public class ContentConv implements CsvColumnConv {

	@Override
	public Object toObject(CsvColumn column, String value, CsvExecuteContext ctx) throws Exception {
		return column.getArgument("value");
	}
}
