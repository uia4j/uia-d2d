package uia.d2d.sheet.fmt;

import java.util.Arrays;
import java.util.List;

import uia.d2d.sheet.CustomFormatter;

public class SexFormatter implements CustomFormatter {

    @Override
    public String format(List<Object> values) {
        if ("f".equalsIgnoreCase(values.get(0).toString())) {
            return "女";
        }
        else {
            return "男";
        }
    }

    @Override
    public List<Object> parse(Object value) {
        if ("女".equals(value)) {
            return Arrays.asList("f");
        }
        else {
            return Arrays.asList("m");
        }
    }

}
