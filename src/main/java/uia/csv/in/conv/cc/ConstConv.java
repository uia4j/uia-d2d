package uia.csv.in.conv.cc;

import uia.csv.in.CsvColumn;
import uia.csv.in.CsvExecuteContext;
import uia.csv.in.conv.CsvColumnConv;

/**
 * Get the static content.<br>
 * 
 * <pre>{@code
 * <sqlColumn name="user_id" length="50" conv="Content">
 * 	   <argument name="name">CONST VALUE</argument>
 * </sqlColumn>
 * }</pre>
 * 
 * @author Kyle K. Lin
 *
 */
public class ConstConv implements CsvColumnConv {

	@Override
	public Object toObject(CsvColumn column, String value, CsvExecuteContext ctx) throws Exception {
		String key = column.getArgument("name");
		return ctx.getConst(key);
	}
}
