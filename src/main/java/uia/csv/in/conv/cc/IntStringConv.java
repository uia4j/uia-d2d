package uia.csv.in.conv.cc;

import uia.csv.in.CsvColumn;
import uia.csv.in.CsvExecuteContext;
import uia.csv.in.conv.CsvColumnConv;

public class IntStringConv implements CsvColumnConv {

	@Override
	public Object toObject(CsvColumn column, String value, CsvExecuteContext ctx) {
		try {
			return value == null || value.isEmpty() 
					? null : 
					"" + (int)Double.parseDouble(value);
		}
		catch(Exception ex) {
			return value;
		}
	}

}
