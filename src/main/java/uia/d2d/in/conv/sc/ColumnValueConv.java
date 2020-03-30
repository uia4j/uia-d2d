package uia.d2d.in.conv.sc;

import uia.d2d.in.CsvExecuteContext;
import uia.d2d.in.SqlColumn;
import uia.d2d.in.conv.SqlColumnConv;

public class ColumnValueConv implements SqlColumnConv {

    @Override
    public Object toObject(SqlColumn column, Object[] values, CsvExecuteContext ctx) {
        return ctx.getColumnValue(column.getArgument("ref"));
    }
}
