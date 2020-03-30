package uia.d2d.in;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import uia.d2d.D2DException;
import uia.d2d.in.conv.CsvColumnConv;
import uia.d2d.in.conv.SqlColumnConv;
import uia.d2d.in.conv.cc.ArgumentConv;
import uia.d2d.in.conv.cc.DateConv;
import uia.d2d.in.conv.cc.IntConv;
import uia.d2d.in.conv.cc.IntOrStringConv;
import uia.d2d.in.conv.cc.IntStringConv;
import uia.d2d.in.conv.cc.StringConv;
import uia.d2d.in.conv.sc.ColumnValueConv;
import uia.d2d.in.conv.sc.NowConv;
import uia.d2d.in.conv.sc.RowKeyConv;
import uia.d2d.in.conv.sc.SQLConv;
import uia.d2d.in.conv.sc.SQLxConv;
import uia.d2d.in.conv.sc.UUIDConv;
import uia.d2d.in.conv.sc.VarcharAndConv;
import uia.d2d.in.conv.sc.VarcharConv;
import uia.d2d.in.conv.sc.VarcharIncrementConv;
import uia.d2d.in.conv.sc.VarcharOrConv;
import uia.d2d.in.xml.CCConvType;
import uia.d2d.in.xml.CsvType;
import uia.d2d.in.xml.ParameterType;
import uia.d2d.in.xml.SCConvType;
import uia.d2d.in.xml.WorkspaceType;
import uia.utils.JaxbUtils;

public class CsvSchema {

    private final TreeMap<String, CsvType> csvs;

    private final TreeMap<String, SqlColumnConv> scConvs;

    private final TreeMap<String, CsvColumnConv> ccConvs;

    private final TreeMap<String, String> globalConsts;

    public CsvSchema(File file) throws Exception {
        if (!file.exists()) {
            throw new IOException(file.getName() + " NOT FOUND");
        }
        this.csvs = new TreeMap<>();
        this.scConvs = new TreeMap<>();
        this.ccConvs = new TreeMap<>();
        this.globalConsts = new TreeMap<>();

        JaxbUtils<WorkspaceType> jaxb = new JaxbUtils<>(
                WorkspaceType.class,
                "workspace",
                "http://d2d.uia/in/xml",
                "uia.d2d.in.xml");

        WorkspaceType wsType = jaxb.fromXml(file);
        for (CsvType csv : wsType.getCsvSpace().getCsv()) {
            this.csvs.put(csv.getName(), csv);
        }

        this.scConvs.put("Content", new uia.d2d.in.conv.sc.ContentConv());
        this.scConvs.put("Const", new uia.d2d.in.conv.sc.ConstConv());
        this.scConvs.put("Now", new NowConv());
        this.scConvs.put("Int", new uia.d2d.in.conv.sc.IntConv());
        this.scConvs.put("ColumnValue", new ColumnValueConv());
        this.scConvs.put("RowKey", new RowKeyConv());
        this.scConvs.put("SQL", new SQLConv());
        this.scConvs.put("SQLx", new SQLxConv());
        this.scConvs.put("UUID", new UUIDConv());
        this.scConvs.put("Varchar", new VarcharConv());
        this.scConvs.put("Increment", new VarcharIncrementConv());
        this.scConvs.put("VarcharOr", new VarcharOrConv());
        this.scConvs.put("VarcharAnd", new VarcharAndConv());
        if (wsType.getScConvSpace() != null) {
            for (SCConvType sccType : wsType.getScConvSpace().getScConv()) {
                this.scConvs.put(sccType.getId(), (SqlColumnConv) Class.forName(sccType.getDriver()).newInstance());
            }
        }

        this.ccConvs.put("Argument", new ArgumentConv());
        this.ccConvs.put("Content", new uia.d2d.in.conv.cc.ContentConv());
        this.ccConvs.put("Const", new uia.d2d.in.conv.cc.ConstConv());
        this.ccConvs.put("Date", new DateConv());
        this.ccConvs.put("Int", new IntConv());
        this.ccConvs.put("IntString", new IntStringConv());
        this.ccConvs.put("IntOrString", new IntOrStringConv());
        this.ccConvs.put("String", new StringConv());
        if (wsType.getCcConvSpace() != null) {
            for (CCConvType cccType : wsType.getCcConvSpace().getCcConv()) {
                this.ccConvs.put(cccType.getId(), (CsvColumnConv) Class.forName(cccType.getDriver()).newInstance());
            }
        }

        if (wsType.getConstSpace() != null) {
            for (ParameterType pType : wsType.getConstSpace().getConst()) {
                this.globalConsts.put(pType.getName(), pType.getValue());
            }
        }
    }

    public Csv build(String csvName) throws D2DException {
        CsvType csvType = this.csvs.get(csvName);
        if (csvType == null) {
            throw new D2DException(
            		csvName,
            		null, 
            		null,
            		0,
            		"CsvName NOT FOUND(xml)");
        }
        return new Csv(this, csvType);
    }

    public SqlColumnConv getSqlColumnConv(String id) {
        return this.scConvs.get(id);
    }

    public CsvColumnConv getCsvColumnConv(String id) {
        return this.ccConvs.get(id);
    }

    public Map<String, String> getGlobalConsts() {
        return this.globalConsts;
    }
}
