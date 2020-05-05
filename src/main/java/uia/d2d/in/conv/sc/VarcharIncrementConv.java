package uia.d2d.in.conv.sc;

import uia.d2d.in.CsvExecuteContext;
import uia.d2d.in.SqlColumn;

public class VarcharIncrementConv extends VarcharConv {

    @Override
    public Object toObject(SqlColumn column, Object[] values, CsvExecuteContext ctx) {
        String k = super.toObject(column, values, ctx).toString();
        String value = column.getArgument("value");
        Integer c = ctx.getCount(k);
        if (c == null) {
            c = 1;
        }
        else {
            c = (c) + 1;
        }
        ctx.putCount(k, c);

        return value != null ? String.format("%s-%02d", value, c) : String.format("%s-%02d", k, c);
    }
}
