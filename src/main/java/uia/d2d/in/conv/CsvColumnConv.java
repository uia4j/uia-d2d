package uia.d2d.in.conv;

import uia.d2d.in.CsvColumn;
import uia.d2d.in.CsvExecuteContext;

public interface CsvColumnConv {

    public Object toObject(CsvColumn column, String value, CsvExecuteContext ctx) throws Exception;
}
