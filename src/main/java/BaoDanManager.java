import java.util.ArrayList;
import java.util.List;

public class BaoDanManager {
	private static final BaoDanManager instance = new BaoDanManager();
	private final List<BaoDan> baoDanList = new ArrayList<BaoDan>();

	private BaoDanManager() {
	}

	public static BaoDanManager getInstance() {
		return instance;
	}

	public List<BaoDan> getBaoDanList() {
		return baoDanList;
	}

	public void addBaoDan(BaoDan bd) {
		baoDanList.add(bd);
	}

	public void showAllBaoDanInfo() {
		System.out.println();
		System.out.println("===============目前已经录入如下保单===============");
		for (BaoDan b : baoDanList) {
			System.out.println("保单号：" + b.getBaodanhao() + " ---- 解析到 " + b.getBaoDanInfoList().size() + " 份险种！");
		}
	}
}
