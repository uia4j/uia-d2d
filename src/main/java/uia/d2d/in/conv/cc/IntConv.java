package uia.d2d.in.conv.cc;

import java.math.BigDecimal;

import uia.d2d.in.CsvColumn;
import uia.d2d.in.CsvExecuteContext;
import uia.d2d.in.conv.CsvColumnConv;

public class IntConv implements CsvColumnConv {

    @Override
    public Object toObject(CsvColumn column, String value, CsvExecuteContext ctx) {
        return value == null ? null : new BigDecimal(value).intValue();
    }

}
