import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.*;

public class Main {
	public static void main(String[] args) {
		System.out.println("==================================================");
		System.out.println("                欢迎使用保单导出工具！              ");
		System.out.println("==================================================");

		//获取需要的工具实例
		FileManager fileManager = FileManager.getInstance();
		HtmlManager htmlManager = HtmlManager.getInstance();
		BaoDanManager baoDanManager = BaoDanManager.getInstance();
		Scanner sc = new Scanner(System.in);

		//设置文件路径
		System.out.print("默认路径为./index.html - 回车继续：Enter！");
		sc.nextLine();

		//解析客户的保单号
		System.out.print("总共识别到 " + htmlManager.getIdList(fileManager.getHtml()).size() + "份保单，导入中");
		for (String s : htmlManager.getIdList(fileManager.getHtml())) {
			System.out.print(".");
			BaoDan bd = null;
			while (bd == null) {
				try {
					bd = htmlManager.getBasicInfo(htmlManager.downloadBasicInfo(s));
					bd.setBaoDanInfoList(htmlManager.getDetailInfo(htmlManager.downloadDetailInfo(s)));
				} catch (IOException e) {
					System.err.println("\n发生错误：" + e.getMessage() + "\n输入新的sessio:");
					String session = sc.nextLine();
					fileManager.writeCookie("PASESSION", session);
					fileManager.writeCookie("PORTALSESSION", session);
				}
			}
			baoDanManager.addBaoDan(bd);
		}

		baoDanManager.showAllBaoDanInfo();

		//数据转换 表1
		System.out.println("正在生成表1...");
		List<TableFirst> table1 = new ArrayList<TableFirst>();
		for (BaoDan b : baoDanManager.getBaoDanList()) {
			TableFirst t = new TableFirst();
			Map<String, String> data = t.getData();

			data.put("险种名称", b.getBaoDanInfoList().get(0).getMingcheng());
			data.put("被保人", b.getBaoDanInfoList().get(0).getBeibaoren());
			data.put("保单号", b.getBaodanhao());
			data.put("缴费日期", b.getBaoDanInfoList().get(0).getDate());
			data.put("期交保费", b.getQijiaobaofei());
			data.put("缴费年期", b.getBaoDanInfoList().get(0).getNianqi());
			data.put("开户银行", b.getKaihuyinghang());
			data.put("缴费账号", b.getYinghangzhanghao());
			data.put("备注", "无");

			table1.add(t);
		}

		//存储 表1
		fileManager.saveFirstCSV(table1);

		//数据转换 表2
		/*
		System.out.println("正在生成表2...");
		ArrayList<TableSecond> table2 = new ArrayList<TableSecond>();
		Gson gson = new Gson();

		for (BaoDan b : baoDanManager.getBaoDanList()) {
			TableSecond t = new TableSecond();

			List<BaoDanInfo> infos = b.getBaoDanInfoList();
			Map<String, String> data = t.getData();

			System.out.println("-------------------- " + b.getBaodanhao() + " --------------------");

			//必须录入
			data.put("被保人", infos.get(0).getBeibaoren());
			data.put("保单名称", infos.get(0).getMingcheng());
			data.put("保单号", b.getBaodanhao());

			//主险
			data.put("身价保障", infos.get(0).getBaofeiheji());

			//副险
			ArrayList<RepoEntity> repoList = gson.fromJson(fileManager.readRepo(), new TypeToken<List<RepoEntity>>() {
			}.getType());

			//处理数据
			for (BaoDanInfo info : infos) {
				//跳过第一个主险
				if (info == infos.get(0)) continue;

				//需要用到的变量
				int type = 0;

				//搜索保单是否在数据库有记录
				boolean isRecorded = false;
				for (RepoEntity repo : repoList) {
					if (info.getDaima().equals(repo.getId())) {
						//搜到记录
						if (repo.getType().equals("重疾保障")) type = 1;
						else if (repo.getType().equals("恶性肿瘤")) type = 2;
						else if (repo.getType().equals("轻度重疾")) type = 3;
						else if (repo.getType().equals("陪护金")) type = 4;
						else if (repo.getType().equals("意外伤残")) type = 5;
						else if (repo.getType().equals("意外医疗")) type = 6;
						else if (repo.getType().equals("住院+海外医疗")) type = 7;
						else if (repo.getType().equals("住院日额")) type = 8;
						isRecorded = true;
						break;
					}
				}

				//如果无记录手动添加
				if (!isRecorded) {
					System.out.println(info.getMingcheng() + "没有记录！");
					new TableSecond().showMenu();

					char check = 'n';
					while (check == 'n' || check == 'N') {
						System.out.print("请输入类型选项（0为备注）：");
						type = sc.nextInt();
						sc.nextLine();
						if (type > 8) {
							System.out.println("非法输入，请输入正确的值!");
							continue;
						}
						System.out.print("确定类型是 *" + type + "* 吗？输入任意字符确认，n重新选择：");
						String temp = sc.nextLine();
						if (temp.equals("")) break;
						else check = temp.charAt(0);
					}
				}

				//根据不同类型的处理
				switch (type) {
					case 0:
						System.out.print("请输入备注：");
						String temp = sc.next();
						data.put("备注", temp);
						System.out.println("添加了数据：" + info.getMingcheng() + "-->备注----" + temp);
						break;
					case 1:
						data.put("重疾保障", data.get("重疾保障") + "+" + info.getBaoe());
						repoList.add(new RepoEntity(info.getDaima(), info.getMingcheng(), "重疾保障"));
						System.out.println("添加了数据：" + info.getMingcheng() + "-->重疾保障----" + info.getBaoe());
						break;
					case 2:
						data.put("恶性肿瘤", data.get("恶性肿瘤") + "+" + info.getBaoe());
						repoList.add(new RepoEntity(info.getDaima(), info.getMingcheng(), "恶性肿瘤"));
						System.out.println("添加了数据：" + info.getMingcheng() + "-->恶性肿瘤----" + info.getBaoe());
						break;
					case 3:
						data.put("轻度重疾", data.get("轻度重疾") + "+" + info.getBaoe());
						repoList.add(new RepoEntity(info.getDaima(), info.getMingcheng(), "轻度重疾"));
						System.out.println("添加了数据：" + info.getMingcheng() + "-->轻度重疾----" + info.getBaoe());
						break;
					case 4:
						data.put("陪护金", data.get("陪护金") + "+" + info.getBaoe());
						repoList.add(new RepoEntity(info.getDaima(), info.getMingcheng(), "陪护金"));
						System.out.println("添加了数据：" + info.getMingcheng() + "-->陪护金----" + info.getBaoe());
						break;
					case 5:
						data.put("意外伤残", data.get("意外伤残") + "+" + info.getBaoe());
						repoList.add(new RepoEntity(info.getDaima(), info.getMingcheng(), "意外伤残"));
						System.out.println("添加了数据：" + info.getMingcheng() + "-->意外伤残----" + info.getBaoe());
						break;
					case 6:
						data.put("意外医疗", data.get("意外医疗") + "+" + info.getBaoe());
						repoList.add(new RepoEntity(info.getDaima(), info.getMingcheng(), "意外医疗"));
						System.out.println("添加了数据：" + info.getMingcheng() + "-->意外医疗----" + info.getBaoe());
						break;
					case 7:
						data.put("住院+海外医疗", data.get("住院+海外医疗") + "+" + info.getBaoe());
						repoList.add(new RepoEntity(info.getDaima(), info.getMingcheng(), "住院+海外医疗"));
						System.out.println("添加了数据：" + info.getMingcheng() + "-->住院+海外医疗----" + info.getBaoe());
						break;
					case 8:
						data.put("住院日额", data.get("住院日额") + "+" + info.getBaoe());
						repoList.add(new RepoEntity(info.getDaima(), info.getMingcheng(), "住院日额"));
						System.out.println("添加了数据：" + info.getMingcheng() + "-->住院日额----" + info.getBaoe());
						break;
					default:
						System.err.println("系统没有你输入的类型，本条记录已经被跳过！");
				}
			}

			//存储刚刚修改的记录
			table2.add(t);
			fileManager.writeRepo(gson.toJson(repoList));
		}

		//存储 表2
		fileManager.saveSecondCSV(table2);
		 */
	}
}
