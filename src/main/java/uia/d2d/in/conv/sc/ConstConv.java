package uia.d2d.in.conv.sc;

import uia.d2d.in.CsvExecuteContext;
import uia.d2d.in.SqlColumn;
import uia.d2d.in.conv.SqlColumnConv;

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
public class ConstConv implements SqlColumnConv {

    @Override
    public Object toObject(SqlColumn column, Object[] values, CsvExecuteContext ctx) {
        String key = column.getArgument("name");
        return ctx.getConst(key);
    }
}
