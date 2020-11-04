package uia.d2d.sheet;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import uia.d2d.sheet.fmt.SimpleFormatter;
import uia.d2d.sheet.xml.CellType;
import uia.d2d.sheet.xml.CustomType;
import uia.d2d.sheet.xml.ListType;
import uia.d2d.sheet.xml.ObjectType;
import uia.d2d.sheet.xml.PropertyType;
import uia.d2d.sheet.xml.SheetType;
import uia.d2d.sheet.xml.WorkspaceType;
import uia.utils.JaxbUtils;
import uia.utils.PropertyBeanUtils;

public class SheetWriter {

    private final WorkspaceType workspace;

    private final Map<String, SheetType> sheets;

    private final Map<String, CustomFormatter> formatters;

    private final TreeMap<String, Handler> handlers;

    public SheetWriter(File file) throws Exception {
        this(new FileInputStream(file));
    }

    public SheetWriter(InputStream file) throws Exception {
        JaxbUtils<WorkspaceType> jaxb = new JaxbUtils<>(
                WorkspaceType.class,
                "workspace",
                "http://d2d.uia/sheet/xml",
                "uia.d2d.sheet.xml");

        this.workspace = jaxb.fromXml(file);

        this.sheets = this.workspace.getSheetSpace().getSheet().stream()
                .collect(Collectors.toMap(s -> s.getName(), s -> s));
        this.formatters = this.workspace.getFormatterSpace().getFormatter().stream()
                .collect(Collectors.toMap(f -> f.getName(), f -> {
                    try {
                        return (CustomFormatter) Class.forName(f.getDriver()).newInstance();
                    }
                    catch (Exception ex) {
                        return null;
                    }
                }));

        this.handlers = new TreeMap<>();
        this.handlers.put(PropertyType.class.getSimpleName(), this::handleProperty);
        this.handlers.put(ObjectType.class.getSimpleName(), this::handleObject);
        this.handlers.put(ListType.class.getSimpleName(), this::handleList);
        this.handlers.put(CustomType.class.getSimpleName(), this::handleCustom);
    }

    public boolean write(String sheetName, Object data) {
        SheetType sheetType = this.sheets.get(sheetName);
        if (sheetType == null) {
            return false;
        }

        if (data instanceof List) {
            handleList(sheetType.getPropertyOrObjectOrList().get(0), data, 0, 0);

        }
        else {
            for (CellType cellType : sheetType.getPropertyOrObjectOrList()) {
                this.handlers.get(cellType.getClass().getSimpleName())
                        .accept(cellType, data, 0, 0);
            }
        }
        return true;
    }

    private Object handleProperty(CellType cellType, Object data, int refRow, int refCol) {
        try {
            PropertyType propertyType = (PropertyType) cellType;
            Object value = PropertyBeanUtils.read(data, propertyType.getName());
            println(refRow + propertyType.getRow(), refCol + propertyType.getCol(), value);
        }
        catch (Exception e) {

        }
        return data;

    }

    private Object handleObject(CellType cellType, Object data, int refRow, int refCol) {
        try {
            ObjectType objectType = (ObjectType) cellType;
            Object value = PropertyBeanUtils.read(data, objectType.getName());
            for (CellType next : objectType.getPropertyOrObjectOrList()) {
                this.handlers.get(next.getClass().getSimpleName())
                        .accept(next, value, refRow, refCol);
            }
        }
        catch (Exception e) {

        }
        return data;
    }

    @SuppressWarnings("unchecked")
    private Object handleList(CellType cellType, Object data, int refRow, int refCol) {
        try {
            ListType listType = (ListType) cellType;
            List<Object> values = (List<Object>) PropertyBeanUtils.read(data, listType.getName());
            if ("rows".equalsIgnoreCase(listType.getMode())) {
                int r = 0;
                for (Object value : values) {
                    for (CellType next : listType.getPropertyOrObjectOrList()) {
                        this.handlers.get(next.getClass().getSimpleName())
                                .accept(next, value, listType.getRow() + r, listType.getCol());
                    }
                    r++;
                }
            }
            else {
                int c = 0;
                for (Object value : values) {
                    for (CellType next : listType.getPropertyOrObjectOrList()) {
                        this.handlers.get(next.getClass().getSimpleName())
                                .accept(next, value, listType.getRow(), listType.getCol() + c);
                    }
                    c++;
                }
            }
        }
        catch (Exception e) {

        }
        return data;
    }

    private Object handleCustom(CellType cellType, Object data, int refRow, int refCol) {
        try {
            CustomType customType = (CustomType) cellType;
            ArrayList<Object> values = new ArrayList<>();
            if (customType.getPropertyRef().isEmpty()) {
                values.add(PropertyBeanUtils.read(data, customType.getName()));
            }
            else {
                for (String name : customType.getPropertyRef()) {
                    values.add(PropertyBeanUtils.read(data, name));
                }
            }
            CustomFormatter fmt = this.formatters.get(customType.getFormatter());
            if (fmt == null) {
                fmt = new SimpleFormatter();
            }
            String result = fmt.format(values);
            println(refRow + customType.getRow(), refCol + customType.getCol(), result);

        }
        catch (Exception ex) {

        }
        return refCol;
    }

    private void println(int row, int col, Object data) {
        System.out.println(String.format("%2d,%2d - %s", row, col, data));
    }

    interface Handler {

        public Object accept(CellType cellType, Object data, int refRow, int refCol);
    }
}
