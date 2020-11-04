package uia.d2d.in.conv.sc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import uia.d2d.in.CsvExecuteContext;
import uia.d2d.in.SqlColumn;
import uia.d2d.in.conv.SqlColumnConv;

/**
 * Get the value by executing a SQL.<br>
 *
 * <pre>{@code
 * <sqlColumn name="user_id" length="50" conv="SQLx">
 *     <csvColumn index="2" />
 *     <csvColumn index="9" />
 * 	   <argument name="sql">SELECT id FROM user WHERE key1=? AND key=?</argument>
 * </sqlColumn>
 * }</pre>
 *
 * @author Kyle K. Lin
 *
 */
public class SQLxConv implements SqlColumnConv {

    @Override
    public Object toObject(SqlColumn column, Object[] values, CsvExecuteContext ctx) {
        String sql = column.getArgument("sql");
        String nullable = column.getArgument("nullable", "false");
        Object result = null;
        try (PreparedStatement ps = ctx.getConnection().prepareStatement(sql)) {
            for (int i = 0; i < values.length; i++) {
                ps.setObject(i + 1, values[i]);
            }
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    result = rs.getString(1);
                }
                else if("true".equalsIgnoreCase(nullable)) {
                    result = null;
                }
                else {
                	throw new Exception("NOT FOUND");
                }
            }
        }
        catch (Exception ex) {
            ctx.setFailed(true);
            ctx.setMessage(column.getName() + " failed, " + ex.getMessage());
        }
        return result;
    }
}
