package uia.d2d.in.conv.sc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import uia.d2d.in.CsvExecuteContext;
import uia.d2d.in.SqlColumn;

/**
 * Get the value by executing a SQL.<br>
 * 
 * <pre>{@code
 * <sqlColumn name="user_id" length="50" conv="SQL">
 *     <csvColumn index="9" />
 * 	   <argument name="sql">SELECT id FROM user WHERE user_id=?</argument>
 * 	   <argument name="nullable">true</argument>
 * </sqlColumn>
 * }</pre>
 * 
 * @author Kyle K. Lin
 *
 */
public class SQLConv extends VarcharConv {

    @Override
    public Object toObject(SqlColumn column, Object[] values, CsvExecuteContext ctx) throws Exception {
        Object result = super.toObject(column, values, ctx);
        String sql = column.getArgument("sql");
        String nullable = column.getArgument("nullable", "false");
        try (PreparedStatement ps = ctx.getConnection().prepareStatement(sql)) {
            ps.setObject(1, result);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    result = rs.getString(1);
                }
                else if (!"true".equalsIgnoreCase(nullable)) {
                    ctx.setAbort(true);
                    ctx.raiseAbort(column, String.format("%s no result of '%s'", sql, result));
                }
                else {
                    result = null;
                }
            }
        }
        return result;
    }
}
