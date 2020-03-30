package uia.d2d.in.conv.cc;

import uia.d2d.in.CsvColumn;
import uia.d2d.in.CsvExecuteContext;
import uia.d2d.in.conv.CsvColumnConv;

public class ArgumentConv implements CsvColumnConv {

    @Override
    public Object toObject(CsvColumn column, String value, CsvExecuteContext ctx) {
        String dv = column.getArgument("__default__");
        String result = value == null || value.isEmpty() ? dv : column.getArgument(value);
        return result == null ? dv : result;
    }

}
