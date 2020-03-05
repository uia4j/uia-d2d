package uia.csv.in.conv.cc;

import uia.csv.in.CsvColumn;
import uia.csv.in.CsvExecuteContext;
import uia.csv.in.conv.CsvColumnConv;

public class StringConv implements CsvColumnConv {

	@Override
	public Object toObject(CsvColumn column, String value, CsvExecuteContext ctx) {
		if(value == null || value.trim().isEmpty()) {
			value = column.getArgument("null");
		}
		return value;
	}

}
