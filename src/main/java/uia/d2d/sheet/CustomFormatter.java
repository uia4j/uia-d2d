package uia.d2d.sheet;

import java.util.List;

public interface CustomFormatter {

    public String format(List<Object> values);

    public List<Object> parse(Object value);
}
