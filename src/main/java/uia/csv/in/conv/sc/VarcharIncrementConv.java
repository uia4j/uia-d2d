package uia.csv.in.conv.sc;

import uia.csv.in.CsvExecuteContext;
import uia.csv.in.SqlColumn;

public class VarcharIncrementConv extends VarcharConv {

	@Override
	public Object toObject(SqlColumn column, Object[] values, CsvExecuteContext ctx) throws Exception {
		String k = super.toObject(column, values, ctx).toString();
		String value = column.getArgument("value");
		Integer c = ctx.getCount(k);
		if(c == null) {
			c = 1;
		}
		else {
			c = ((Integer)c) + 1;
		}
		ctx.putCount(k, c);
	
		return value != null ? String.format("%s-%02d", value, c) : String.format("%s-%02d", k, c);
	}
}
