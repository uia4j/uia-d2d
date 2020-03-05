package uia.csv.in;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import uia.csv.in.conv.CsvColumnConv;
import uia.csv.in.conv.SqlColumnConv;
import uia.csv.in.conv.cc.ArgumentConv;
import uia.csv.in.conv.cc.DateConv;
import uia.csv.in.conv.cc.IntConv;
import uia.csv.in.conv.cc.IntOrStringConv;
import uia.csv.in.conv.cc.IntStringConv;
import uia.csv.in.conv.cc.StringConv;
import uia.csv.in.conv.sc.NowConv;
import uia.csv.in.conv.sc.ColumnValueConv;
import uia.csv.in.conv.sc.RowKeyConv;
import uia.csv.in.conv.sc.SQLConv;
import uia.csv.in.conv.sc.SQLxConv;
import uia.csv.in.conv.sc.VarcharConv;
import uia.csv.in.conv.sc.VarcharIncrementConv;
import uia.csv.in.conv.sc.VarcharOrConv;
import uia.csv.in.conv.sc.UUIDConv;
import uia.csv.in.conv.sc.VarcharAndConv;
import uia.csv.in.xml.CCConvType;
import uia.csv.in.xml.CsvType;
import uia.csv.in.xml.ParameterType;
import uia.csv.in.xml.SCConvType;
import uia.csv.in.xml.WorkspaceType;
import uia.utils.JaxbUtils;

public class CsvBuilder {

	private final TreeMap<String, CsvType> csvs ;
	
	private final TreeMap<String, SqlColumnConv> scConvs;
	
	private final TreeMap<String, CsvColumnConv> ccConvs;
	
	private final TreeMap<String, String> globalConsts;
	
	public CsvBuilder(File file) throws Exception {
		if(!file.exists()) {
			throw new IOException(file.getName() + " NOT FOUND");
		}
		this.csvs = new TreeMap<>();
		this.scConvs = new TreeMap<>();
		this.ccConvs = new TreeMap<>();
		this.globalConsts = new TreeMap<>();
		
		JaxbUtils<WorkspaceType> jaxb = new JaxbUtils<>(
				WorkspaceType.class,
				"workspace",
				"http://csv.uia/in/xml", 
				"uia.csv.in.xml");
		
		WorkspaceType wsType = jaxb.fromXml(file);
		for(CsvType csv : wsType.getCsvSpace().getCsv()) {
			this.csvs.put(csv.getName(), csv);
		}
		
		this.scConvs.put("Content", new uia.csv.in.conv.sc.ContentConv());
		this.scConvs.put("Const", new uia.csv.in.conv.sc.ConstConv());
		this.scConvs.put("Now", new NowConv());
		this.scConvs.put("ColumnValue", new ColumnValueConv());
		this.scConvs.put("RowKey", new RowKeyConv());
		this.scConvs.put("SQL", new SQLConv());
		this.scConvs.put("SQLx", new SQLxConv());
		this.scConvs.put("UUID", new UUIDConv());
		this.scConvs.put("Varchar", new VarcharConv());
		this.scConvs.put("Increment", new VarcharIncrementConv());
		this.scConvs.put("VarcharOr", new VarcharOrConv());
		this.scConvs.put("VarcharAnd", new VarcharAndConv());
		for(SCConvType sccType : wsType.getScConvSpace().getScConv()) {
			this.scConvs.put(sccType.getId(), (SqlColumnConv)Class.forName(sccType.getDriver()).newInstance());
		}

		this.ccConvs.put("Argument", new ArgumentConv());
		this.ccConvs.put("Content", new uia.csv.in.conv.cc.ContentConv());
		this.ccConvs.put("Const", new uia.csv.in.conv.cc.ConstConv());
		this.ccConvs.put("Date", new DateConv());
		this.ccConvs.put("Int", new IntConv());
		this.ccConvs.put("IntString", new IntStringConv());
		this.ccConvs.put("IntOrString", new IntOrStringConv());
		this.ccConvs.put("String", new StringConv());
		for(CCConvType cccType : wsType.getCcConvSpace().getCcConv()) {
			this.ccConvs.put(cccType.getId(), (CsvColumnConv)Class.forName(cccType.getDriver()).newInstance());
		}
		
		for(ParameterType pType : wsType.getConstSpace().getConst()) {
			this.globalConsts.put(pType.getName(), pType.getValue());
		}
	}
	
	public Csv build(String id) throws Exception {
		CsvType csvType = this.csvs.get(id);
		if(csvType == null) {
			throw new Exception(id + " NOT FOUND in the xml");
		}
		return new Csv(csvType, this);
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
