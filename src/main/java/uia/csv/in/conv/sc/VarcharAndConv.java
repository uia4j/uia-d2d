package uia.csv.in.conv.sc;

import uia.csv.in.CsvExecuteContext;
import uia.csv.in.SqlColumn;
import uia.csv.in.conv.SqlColumnConv;

public class VarcharAndConv implements SqlColumnConv {

	@Override
	public Object toObject(SqlColumn column, Object[] values, CsvExecuteContext ctx) throws Exception {
		String value = "";
		for(Object v : values) {
			if(v == null || v.toString().trim().isEmpty()) {
				value = "";
				break;
			}
			value = v.toString();
		}
		if(column.getType().getLength() > 0 && value.length() > column.getType().getLength()) {
			if(!"true".equals(column.getArgument("trim", "false"))) {
				throw new Exception(String.format("%s:%s length>%s", column.getName(), value, value.length()));
			}
			value = value.substring(0, column.getType().getLength());
		}
		if(column.getType().isEmpty2Null() && value.isEmpty()) {
			value = null;
		}
		if(!column.getType().isNullable() && value == null) {
			throw new Exception(column.getName() + ": null");
		}
	
		return value;
	}
}
