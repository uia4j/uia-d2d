package uia.csv.in.conv.cc;

import uia.csv.in.CsvColumn;
import uia.csv.in.CsvExecuteContext;
import uia.csv.in.conv.CsvColumnConv;

public class IntOrStringConv implements CsvColumnConv {

	@Override
	public Object toObject(CsvColumn column, String value, CsvExecuteContext ctx) {
		if(value == null) {
			return null;
		}
		try {
			return "" + (int)Double.parseDouble(value);
		}
		catch(Exception ex) {
			return value;
		}
	}

}
