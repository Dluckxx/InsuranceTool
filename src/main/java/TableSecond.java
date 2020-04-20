import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class TableSecond {

	private LinkedHashMap<String, String> data = new LinkedHashMap<String, String>();

	public TableSecond() {
		data.put("被保人", "");
		data.put("保单名称", "");
		data.put("保单号", "");
		data.put("身价保障", "");
		data.put("重疾保障", "0");//1
		data.put("恶性肿瘤", "0");//2
		data.put("轻度重疾", "0");//3
		data.put("陪护金", "0");//4
		data.put("意外伤残", "0");//5
		data.put("意外医疗", "0");//6
		data.put("住院+海外医疗", "0");//7
		data.put("住院日额", "0");//8
		data.put("备注", "");//0
	}

	public LinkedHashMap<String, String> getData() {
		return data;
	}

	public void setData(LinkedHashMap<String, String> data) {
		this.data = data;
	}

	public void showMenu() {
		int i = 1;
		boolean flag = false;
		for (String s : data.keySet()) {
			if (flag) {
				System.out.print(i + ". ");
				System.out.println(s);
				i++;
			}
			if (s.equals("身价保障")) {
				flag = true;
			} else if (s.equals("住院日额")){
				flag = false;
			}
		}
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
