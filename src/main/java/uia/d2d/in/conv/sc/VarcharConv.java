package uia.d2d.in.conv.sc;

import uia.d2d.in.CsvExecuteContext;
import uia.d2d.in.SqlColumn;
import uia.d2d.in.conv.SqlColumnConv;

/**
 * Link all values.<br>
 *
 * ex: [ "A", "1", "z" ] will be "A1z".
 *
 * <pre>{@code
 * <sqlColumn name="addr_line1" length="50" conv="VarcharOr">
 *     <csvColumn index="9" />
 * 	   <csvColumn index="10" />
 * 	   <csvColumn index="11" />
 * </sqlColumn>
 * }</pre>
 *
 * @author Kyle K. Lin
 *
 */
public class VarcharConv implements SqlColumnConv {

    @Override
    public Object toObject(SqlColumn column, Object[] values, CsvExecuteContext ctx) {
        StringBuilder b = new StringBuilder();
        for (Object v : values) {
            if (v != null) {
                b.append(v);
            }
        }
        String value = b.toString();
        if (column.getType().getLength() > 0 && value.length() > column.getType().getLength()) {
            if ("true".equals(column.getArgument("null4ofs", "false"))) {	// out of size
                value = null;
            }
            else if ("true".equals(column.getArgument("trim", "false"))) {
                value = value.substring(0, column.getType().getLength());
            }
            else {
                ctx.setFailed(true);
                ctx.setMessage(String.format("%s:%s length>%s", column.getName(), value, value.length()));
                return value;
            }
        }

        if (value != null && column.getType().isEmpty2Null() && value.isEmpty()) {
            value = null;
        }
        if (!column.getType().isNullable() && value == null) {
            ctx.setFailed(true);
            ctx.setMessage(String.format("%s can not be null", column.getName()));
            return value;
        }

        if (value != null && column.getArgument("prefix") != null) {
            value = column.getArgument("prefix") + value;
        }
        return value;
    }
}
