package uia.csv.in.conv.sc;

import uia.csv.in.CsvExecuteContext;
import uia.csv.in.SqlColumn;
import uia.csv.in.conv.SqlColumnConv;

public class ColumnValueConv implements SqlColumnConv {

	@Override
	public Object toObject(SqlColumn column, Object[] values, CsvExecuteContext ctx) {
		return ctx.getColumnValue(column.getArgument("ref"));
	}
}
