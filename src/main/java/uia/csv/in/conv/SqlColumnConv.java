package uia.csv.in.conv;


import uia.csv.in.CsvExecuteContext;
import uia.csv.in.SqlColumn;

public interface SqlColumnConv {
	
	public Object toObject(SqlColumn column, Object[] values, CsvExecuteContext ctx) throws Exception;
}
