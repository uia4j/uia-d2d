package uia.csv.in;

import java.util.TreeMap;

import uia.csv.in.conv.CsvColumnConv;
import uia.csv.in.xml.CsvColumnType;
import uia.csv.in.xml.ParameterType;

public class CsvColumn {
	
	private final Csv csv;
	
	private final SqlColumn sqlColumn;
	
	private final CsvColumnType columnType;
	
	private final CsvColumnConv conv;
	
	private final TreeMap<String, String> args;
	
	public CsvColumn(Csv csv, SqlColumn sqlColumn, CsvColumnType columnType, CsvColumnConv conv) {
		this.csv = csv;
		this.sqlColumn = sqlColumn;
		this.columnType = columnType;
		this.conv = conv;
		this.args = new TreeMap<>();
		for(ParameterType arg : columnType.getArgument()) {
			this.args.put(arg.getName(), arg.getValue());
		}
	}
	
	public CsvColumnType getType() {
		return this.columnType;
	}
	
	public String getArgument(String name) {
		return this.args.get(name);
	}
	
	public Object toObject(String[] csvValues, CsvExecuteContext ctx) throws Exception {
		try {
			if(columnType.getIndex() >= 0) {
				return this.conv.toObject(this, csvValues[columnType.getIndex()], ctx);
			}
			else {
				return this.conv.toObject(this, null, ctx);
			}
		}
		catch(Exception ex) {
			this.csv.getListener().failed(this, ctx.getRowIndex(), this.columnType.getIndex());
			throw ex;
		}
	}
	
	@Override
	public String toString() {
		return String.format("CsvColumn> csv:%s, %s, col:%s>", 
				this.csv.getName(), 
				this.sqlColumn.getName(), 
				this.columnType.getIndex());
	}

}
