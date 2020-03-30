package uia.d2d.in.conv.cc;

import uia.d2d.in.CsvColumn;
import uia.d2d.in.CsvExecuteContext;
import uia.d2d.in.conv.CsvColumnConv;

public class IntOrStringConv implements CsvColumnConv {

    @Override
    public Object toObject(CsvColumn column, String value, CsvExecuteContext ctx) {
        if (value == null) {
            return null;
        }
        try {
            return "" + (int) Double.parseDouble(value);
        }
        catch (Exception ex) {
            return value;
        }
    }

}
