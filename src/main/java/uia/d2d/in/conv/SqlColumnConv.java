package uia.d2d.in.conv;

import uia.d2d.in.CsvExecuteContext;
import uia.d2d.in.SqlColumn;

public interface SqlColumnConv {

    public Object toObject(SqlColumn column, Object[] values, CsvExecuteContext ctx);
}
