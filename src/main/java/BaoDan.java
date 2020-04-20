import java.util.ArrayList;
import java.util.List;

public class BaoDan {
	//保单号
	String baodanhao;
	//保单生效日
	String baodanshengxiaori;
	//开户行
	String kaihuyinghang;
	//银行账号
	String yinghangzhanghao;
	//期交保费
	String qijiaobaofei;

	//保单信息
	List<BaoDanInfo> baoDanInfoList = new ArrayList<BaoDanInfo>();

	public BaoDan(String baodanhao, String baodanshengxiaori, String kaihuyinghang, String yinghangzhanghao, String qijiaobaofei) {
		this.baodanhao = baodanhao;
		this.baodanshengxiaori = baodanshengxiaori;
		this.kaihuyinghang = kaihuyinghang;
		this.yinghangzhanghao = yinghangzhanghao;
		this.qijiaobaofei = qijiaobaofei;
	}

	public String getBaodanhao() {
		return baodanhao;
	}

	public void setBaodanhao(String baodanhao) {
		this.baodanhao = baodanhao;
	}

	public String getBaodanshengxiaori() {
		return baodanshengxiaori;
	}

	public void setBaodanshengxiaori(String baodanshengxiaori) {
		this.baodanshengxiaori = baodanshengxiaori;
	}

	public String getKaihuyinghang() {
		return kaihuyinghang;
	}

	public void setKaihuyinghang(String kaihuyinghang) {
		this.kaihuyinghang = kaihuyinghang;
	}

	public String getYinghangzhanghao() {
		return yinghangzhanghao;
	}

	public void setYinghangzhanghao(String yinghangzhanghao) {
		this.yinghangzhanghao = yinghangzhanghao;
	}

	public String getQijiaobaofei() {
		return qijiaobaofei;
	}

	public void setQijiaobaofei(String qijiaobaofei) {
		this.qijiaobaofei = qijiaobaofei;
	}

	public List<BaoDanInfo> getBaoDanInfoList() {
		return baoDanInfoList;
	}

	public void setBaoDanInfoList(List<BaoDanInfo> baoDanInfoList) {
		this.baoDanInfoList = baoDanInfoList;
	}

	@Override
	public String toString() {
		return "保单号：" + baodanhao;
	}

	public void show() {
		System.out.println("保单号：" + baodanhao);
		System.out.println(baodanshengxiaori + " " + kaihuyinghang + " " + yinghangzhanghao + " " + qijiaobaofei);
		for (BaoDanInfo b : baoDanInfoList) {
			System.out.println(b.toString());
		}
		System.out.println();
	}
}
