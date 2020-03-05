package uia.csv.in.conv.cc;

import uia.csv.in.CsvColumn;
import uia.csv.in.CsvExecuteContext;
import uia.csv.in.conv.CsvColumnConv;

public class ArgumentConv implements CsvColumnConv {

	@Override
	public Object toObject(CsvColumn column, String value, CsvExecuteContext ctx) {
		String dv = column.getArgument("__default__");
		String result = value == null || value.isEmpty() ? dv : column.getArgument(value);
		return result == null ? dv : result;
	}	

}
