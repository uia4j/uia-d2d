package uia.d2d.in.conv.cc;

import uia.d2d.in.CsvColumn;
import uia.d2d.in.CsvExecuteContext;
import uia.d2d.in.conv.CsvColumnConv;

public class StringConv implements CsvColumnConv {

    @Override
    public Object toObject(CsvColumn column, String value, CsvExecuteContext ctx) {
        if (value == null || value.trim().isEmpty()) {
            value = column.getArgument("null");
        }
        return value;
    }

}
