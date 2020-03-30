package uia.d2d.in.conv.cc;

import uia.d2d.in.CsvColumn;
import uia.d2d.in.CsvExecuteContext;
import uia.d2d.in.conv.CsvColumnConv;

public class IntStringConv implements CsvColumnConv {

    @Override
    public Object toObject(CsvColumn column, String value, CsvExecuteContext ctx) {
        try {
            return value == null || value.isEmpty()
                    ? null
                    : "" + (int) Double.parseDouble(value);
        }
        catch (Exception ex) {
            return value;
        }
    }

}
