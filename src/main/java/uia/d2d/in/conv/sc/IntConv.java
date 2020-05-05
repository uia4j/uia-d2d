package uia.d2d.in.conv.sc;

import uia.d2d.in.CsvExecuteContext;
import uia.d2d.in.SqlColumn;
import uia.d2d.in.conv.SqlColumnConv;

public class IntConv implements SqlColumnConv {

    @Override
    public Object toObject(SqlColumn column, Object[] values, CsvExecuteContext ctx) {
        String value = "";
        for (Object v : values) {
            if (v != null) {
                value = v.toString().trim();
                if (!value.isEmpty()) {
                    break;
                }
            }
        }

        if (column.getType().getLength() > 0 && value.length() > column.getType().getLength()) {
            if (!"true".equals(column.getArgument("trim", "false"))) {
                ctx.setFailed(true);
                ctx.setMessage(String.format("%s:%s length>%s", column.getName(), value, value.length()));
                return value;
            }
            value = value.substring(0, column.getType().getLength());
        }

        if (column.getType().isEmpty2Null() && value.isEmpty()) {
            value = null;
        }
        if (!column.getType().isNullable() && value == null) {
            ctx.setFailed(true);
            ctx.setMessage(String.format("%s can not be null", column.getName()));
            return value;
        }

        try {
            return value == null ? null : Integer.parseInt(value);
        }
        catch (Exception ex) {
            ctx.setFailed(true);
            ctx.setMessage(String.format("%s failed, %s", column.getName(), ex.getMessage()));
            return null;
        }
    }
}
