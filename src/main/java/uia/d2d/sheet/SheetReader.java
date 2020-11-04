package uia.d2d.sheet;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import uia.d2d.sheet.xml.CellType;
import uia.d2d.sheet.xml.ListType;
import uia.d2d.sheet.xml.ObjectType;
import uia.d2d.sheet.xml.PropertyType;
import uia.d2d.sheet.xml.SheetType;
import uia.d2d.sheet.xml.WorkspaceType;
import uia.utils.JaxbUtils;
import uia.utils.PropertyBeanUtils;

public class SheetReader {

    private final WorkspaceType workspace;

    private final Map<String, SheetType> sheets;

    private final Map<String, CustomFormatter> formatters;

    private final TreeMap<String, Handler> handlers;

    public SheetReader(File file) throws Exception {
        this(new FileInputStream(file));
    }

    public SheetReader(InputStream file) throws Exception {
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
    }

    public <T> T read(String sheetName, Class<T> clz) {
        SheetType sheetType = this.sheets.get(sheetName);
        if (sheetType == null) {
            return null;
        }
        return null;
    }

    private Object handleProperty(CellType cellType, Object data, int refRow, int refCol) {
        try {
            PropertyType propertyType = (PropertyType) cellType;
            Object value = null;    // TODO: read from excel
            PropertyBeanUtils.write(data, propertyType.getName(), value);
            println(refRow + propertyType.getRow(), refCol + propertyType.getCol(), value);
        }
        catch (Exception e) {

        }
        return data;

    }

    private Object handleObject(CellType cellType, Object data, int refRow, int refCol) {
        try {
            ObjectType objectType = (ObjectType) cellType;
            Object value = null;  // TODO: new an instance
            for (CellType next : objectType.getPropertyOrObjectOrList()) {
                this.handlers.get(next.getClass().getSimpleName())
                        .accept(next, value, refRow, refCol);
            }
            PropertyBeanUtils.write(data, objectType.getName(), value);
        }
        catch (Exception e) {

        }
        return data;
    }

    @SuppressWarnings("unchecked")
    private Object handleList(CellType cellType, Object data, int refRow, int refCol) {
        try {
            ListType listType = (ListType) cellType;
            ArrayList<Object> value = new ArrayList<>();
            if ("rows".equalsIgnoreCase(listType.getMode())) {
                int rc = 0;    // TODO: find row count
                for (int r = 0; r < rc; r++) {
                    Object row = null;  // TODO: new an instance
                    for (CellType next : listType.getPropertyOrObjectOrList()) {
                        this.handlers.get(next.getClass().getSimpleName())
                                .accept(next, row, listType.getRow() + r, listType.getCol());
                    }
                    value.add(row);
                }
            }
            else {
                int cc = 0;    // TODO: find column count
                for (int c = 0; c < cc; c++) {
                    Object col = null;  // TODO: new an instance
                    for (CellType next : listType.getPropertyOrObjectOrList()) {
                        this.handlers.get(next.getClass().getSimpleName())
                                .accept(next, col, listType.getRow(), listType.getCol() + c);
                    }
                    value.add(col);
                }
            }
            PropertyBeanUtils.write(data, listType.getName(), value);
        }
        catch (Exception e) {

        }
        return data;
    }

    private void println(int row, int col, Object data) {
        System.out.println(String.format("%2d,%2d - %s", row, col, data));
    }

    interface Handler {

        public Object accept(CellType cellType, Object data, int refRow, int refCol);
    }
}
