package uia.csv.in.conv;

import uia.csv.in.CsvColumn;
import uia.csv.in.CsvExecuteContext;

public interface CsvColumnConv {

	public Object toObject(CsvColumn column, String value, CsvExecuteContext ctx) throws Exception;
}
