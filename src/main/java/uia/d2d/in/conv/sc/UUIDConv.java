package uia.d2d.in.conv.sc;

import java.util.UUID;

import uia.d2d.in.CsvExecuteContext;
import uia.d2d.in.SqlColumn;
import uia.d2d.in.conv.SqlColumnConv;

public class UUIDConv implements SqlColumnConv {

    @Override
    public Object toObject(SqlColumn column, Object[] values, CsvExecuteContext ctx) {
        String id = UUID.randomUUID().toString();
        String len = column.getArgument("len");
        if (len != null) {
            try {
                id = id.substring(0, Integer.parseInt(len));
            }
            catch (Exception ex) {
            }
        }

        String pre = column.getArgument("prefix", "");
        String post = column.getArgument("postfix", "");
        return pre + id + post;

    }
}
