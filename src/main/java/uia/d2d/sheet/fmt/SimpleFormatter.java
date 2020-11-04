package uia.d2d.sheet.fmt;

import java.util.ArrayList;
import java.util.List;

import uia.d2d.sheet.CustomFormatter;

public class SimpleFormatter implements CustomFormatter {

    @Override
    public String format(List<Object> values) {
        String result = values.get(0).toString();
        for (int i = 1; i < values.size(); i++) {
            result += "," + values.get(i);
        }
        return result;
    }

    @Override
    public List<Object> parse(Object value) {
        ArrayList<Object> result = new ArrayList<>();
        for (String v : value.toString().split(",")) {
            result.add(v);
        }
        return result;
    }
}
