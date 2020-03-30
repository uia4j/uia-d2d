package uia.d2d.in.conv.cc;

import uia.d2d.in.CsvColumn;
import uia.d2d.in.CsvExecuteContext;
import uia.d2d.in.conv.CsvColumnConv;

public class ContentConv implements CsvColumnConv {

    @Override
    public Object toObject(CsvColumn column, String value, CsvExecuteContext ctx) throws Exception {
        return column.getArgument("value");
    }
}
