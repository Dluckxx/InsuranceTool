import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class HtmlManager {
	private static final HtmlManager instance = new HtmlManager();
	private final Map<String, String> cookies = new HashMap<String, String>();

	FileManager fileManager = FileManager.getInstance();

	private HtmlManager() {
	}

	public static HtmlManager getInstance() {
		return instance;
	}

	private void updateCookie() {
		Properties properties = fileManager.readCookie();

		for (Map.Entry<Object, Object> entry : properties.entrySet()) {
			cookies.put(entry.getKey().toString(), entry.getValue().toString());
		}
	}

	public List<String> getIdList(String html) {
		List<String> idList = new ArrayList<String>();
		Document doc = Jsoup.parse(html);
		Elements elements = doc.getElementsByAttributeValue("onclick", "toQueryPolicyInfor(this)");
		for (Element e : elements) {
			if (doc.getElementsByAttributeValue("onclick", "toQueryPolicyInfor(this)") == null) break;
			String id = e.attr("value");
			idList.add(id);
		}
		return idList;
	}

	public BaoDan getBasicInfo(String html) {
		Document doc = Jsoup.parse(html);

		//保单号
		String id = doc.getElementsByAttributeValue("name", "textfield").first().attr("value");
		//保单生效日
		String baodanshengxiaori = doc.getElementsByAttributeValue("name", "textfield3").first().attr("value");
		//开户行
		String kaihuyinghang = doc.getElementsByAttributeValue("name", "textfield9").first().attr("value");
		//银行账号
		String yinghangzhanghao = doc.getElementsByAttributeValue("name", "textfield10").first().attr("value");
		//期交保费
		String qijiaobaofei = doc.getElementsByAttributeValue("name", "textfield5").get(1).attr("value");

		return new BaoDan(id, baodanshengxiaori, kaihuyinghang, yinghangzhanghao, qijiaobaofei);
	}

	public List<BaoDanInfo> getDetailInfo(String html) {
		List<BaoDanInfo> infoList = new ArrayList<BaoDanInfo>();
		Document doc = Jsoup.parse(html);

		while (!doc.getElementsByAttributeValue("name", "textfield").isEmpty()) {
			//被保人
			String beibaoren = doc.getElementsByAttributeValue("name", "textfield").first().attr("value");
			doc.getElementsByAttributeValue("name", "textfield").first().remove();
			//险种类型
			String leixing = doc.getElementsByAttributeValue("name", "textfield2").first().attr("value");
			doc.getElementsByAttributeValue("name", "textfield2").first().remove();
			//险种代码
			String daima = doc.getElementsByAttributeValue("name", "textfield3").first().attr("value");
			doc.getElementsByAttributeValue("name", "textfield3").first().remove();
			//险种名称
			String mingcheng = doc.getElementsByAttributeValue("name", "textfield4").first().attr("value");
			doc.getElementsByAttributeValue("name", "textfield4").first().remove();
			//险种状态
			String zhuangtai = doc.getElementsByAttributeValue("name", "textfield32").first().attr("value");
			doc.getElementsByAttributeValue("name", "textfield32").first().remove();
			//保额
			String baoe = doc.getElementsByAttributeValue("name", "textfield5").first().attr("value");
			doc.getElementsByAttributeValue("name", "textfield5").first().remove();
			baoe = baoe.replace(".0","");
			//份数
			String number = doc.getElementsByAttributeValue("name", "textfield322").first().attr("value");
			doc.getElementsByAttributeValue("name", "textfield322").first().remove();
			//生效日期
			String date = doc.getElementsByAttributeValue("name", "textfield323").first().attr("value");
			doc.getElementsByAttributeValue("name", "textfield323").first().remove();
			//保费合计
			String total = doc.getElementsByAttributeValue("name", "textfield7").first().attr("value");
			doc.getElementsByAttributeValue("name", "textfield7").first().remove();
			//缴费年期
			String nianqi = doc.getElementsByAttributeValue("name", "textfield8").first().attr("value");
			doc.getElementsByAttributeValue("name", "textfield8").first().remove();
			//保险期限
			String qixian = doc.getElementsByAttributeValue("name", "textfield9").first().attr("value");
			doc.getElementsByAttributeValue("name", "textfield9").first().remove();

			BaoDanInfo info = new BaoDanInfo(beibaoren, leixing, daima, mingcheng, zhuangtai, baoe, number, date, total, nianqi, qixian);
			infoList.add(info);
		}
		return infoList;
	}

	public String downloadBasicInfo(String id) throws IOException {
		updateCookie();
		Map<String, String> data = new HashMap<String, String>();
		data.put("polno", id);

		Connection.Response res;
		res = Jsoup.connect(fileManager.readConfig().getProperty("basic_info_url"))
				.data(data)
				.cookies(cookies)
				.timeout(30000)
				.execute();
		for (Map.Entry<String, String> entry : res.cookies().entrySet()) {
			fileManager.writeCookie(entry.getKey(), entry.getValue());
		}
		return res.body();
	}

	public String downloadDetailInfo(String id) throws IOException {
		updateCookie();
		Map<String, String> data = new HashMap<String, String>();
		data.put("polno", id);

		Connection.Response res;
		res = Jsoup.connect(fileManager.readConfig().getProperty("detail_info_url"))
				.data(data)
				.cookies(cookies)
				.timeout(30000)
				.execute();

		for (Map.Entry<String, String> entry : res.cookies().entrySet()) {
			fileManager.writeCookie(entry.getKey(), entry.getValue());
		}
		return res.body();

	}

}
