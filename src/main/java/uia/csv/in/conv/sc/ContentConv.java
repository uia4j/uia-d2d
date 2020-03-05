package uia.csv.in.conv.sc;

import uia.csv.in.CsvExecuteContext;
import uia.csv.in.SqlColumn;
import uia.csv.in.conv.SqlColumnConv;

/**
 * Get the static content.<br>
 * 
 * <pre>{@code
 * <sqlColumn name="user_id" length="50" conv="Content">
 * 	   <argument name="value">FIX VALUE</argument>
 * </sqlColumn>
 * }</pre>
 * 
 * @author Kyle K. Lin
 *
 */
public class ContentConv implements SqlColumnConv {

	@Override
	public Object toObject(SqlColumn column, Object[] values, CsvExecuteContext ctx) throws Exception {
		return column.getArgument("value");
	}
}
