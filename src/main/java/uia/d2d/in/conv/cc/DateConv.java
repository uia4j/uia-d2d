package uia.d2d.in.conv.cc;

import java.text.SimpleDateFormat;
import java.util.Date;

import uia.d2d.in.CsvColumn;
import uia.d2d.in.CsvExecuteContext;
import uia.d2d.in.conv.CsvColumnConv;

public class DateConv implements CsvColumnConv {

    @Override
    public Object toObject(CsvColumn column, String value, CsvExecuteContext ctx) throws Exception {
        if (value == null || value.isEmpty()) {
            return "y".equals(column.getArgument("now4null")) ? new Date() : null;
        }
        else {
            return new SimpleDateFormat(column.getArgument("format")).parse(value);
        }
    }
}
