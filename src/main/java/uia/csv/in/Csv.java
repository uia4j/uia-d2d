package uia.csv.in;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import uia.csv.in.xml.CsvColumnType;
import uia.csv.in.xml.CsvType;
import uia.csv.in.xml.ParameterType;
import uia.csv.in.xml.PlanType;
import uia.csv.in.xml.SqlColumnType;
import uia.dao.Database;

public class Csv {
	
	private final CsvType csvType;
	
	private final Map<String, String> globalConsts;

	private final Map<String, String> csvConsts;

	private final Map<String, SqlColumn> rowKeys ;
	
	private final ArrayList<CsvPlan> plans;
	
	private CsvListener listener;
	
	public Csv(CsvType csvType, CsvBuilder builder) {
		this.globalConsts = builder.getGlobalConsts();
		this.csvType = csvType;
		this.csvConsts = csvType.getConst().stream()
				.collect(Collectors.toMap(
						ParameterType::getName, 
						ParameterType::getValue));

		this.rowKeys = new TreeMap<>();
		List<SqlColumnType> rowKeyTypes = csvType.getRowKey();
		for(SqlColumnType rowKeyType : rowKeyTypes) {
			SqlColumn rowKey = new SqlColumn(this, null, rowKeyType, builder.getSqlColumnConv(rowKeyType.getConv()));
			for(CsvColumnType ccType : rowKeyType.getCsvColumn()) {
				CsvColumn cc = new CsvColumn(this, rowKey, ccType, builder.getCsvColumnConv(ccType.getConv()));
				rowKey.addCsvColumn(cc);
			}
			this.rowKeys.put(rowKey.getName(), rowKey);
		}

		this.plans = new ArrayList<>();
		for(PlanType planType : csvType.getPlan()) {
			CsvPlan plan = new CsvPlan(this, planType, builder);
			this.plans.add(plan);
		}
	}
	
	public String getName() {
		return this.csvType.getName();
	}
	
	public int getFirstRow() {
		return this.csvType.getFirstRow();
	}
	
	public int getColumnCount() {
		return this.csvType.getColumnCount();
	}
	
	public CsvListener getListener() {
		return listener;
	}

	public void setListener(CsvListener listener) {
		this.listener = listener;
	}
	
	public String getConst(String name) {
		return this.csvConsts.get(name);
	}
	
	public void run(final Database database, final List<String[]> rows) throws Exception {
		Connection conn = database.getConnection();

		CsvExecuteContext ctx = new CsvExecuteContext(this.globalConsts, this, 1, conn);
		for(String[] row : rows) {
			ctx.setAbort(false);
			for(Map.Entry<String, SqlColumn> e : this.rowKeys.entrySet()) {
				Object value = e.getValue().toObject(row, ctx);
				if(value != null) {
					ctx.putRowKey(e.getKey(), value);
				}
				else {
					ctx.setAbort(true);
					break;
				}
			}
			if(ctx.isAbort()) {
				continue;
			}

			conn.setAutoCommit(false);
			for(CsvPlan plan : this.plans) {
				plan.execute(database, row, ctx);
			}
			conn.commit();
			
			ctx = ctx.next();
		}
	}
}
