import java.util.LinkedHashMap;
import java.util.Map;

public class TableFirst {

	Map<String, String> data = new LinkedHashMap<String, String>();

	public TableFirst() {
		data.put("险种名称","");
		data.put("被保人","");
		data.put("保单号","");
		data.put("缴费日期","");
		data.put("期交保费","");
		data.put("缴费年期","");
		data.put("开户银行","");
		data.put("缴费账号","");
		data.put("备注","");
	}

	public Map<String, String> getData() {
		return data;
	}

	public void setData(Map<String, String> data) {
		this.data = data;
	}

	public String keyToCSV() {
		StringBuilder sb = new StringBuilder();
		for (String key : data.keySet()) {
			if (!data.keySet().iterator().next().equals(key))
				sb.append(",");
			sb.append(key);
		}
		sb.append("\r\n");
		return sb.toString();
	}

	public String valueToCSV() {
		StringBuilder sb = new StringBuilder();
		for (String val : data.values()) {
			if (!data.values().iterator().next().equals(val))
				sb.append(",");
			sb.append(val);
		}
		sb.append("\r\n");
		return sb.toString();
	}
}
