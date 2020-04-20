public class BaoDanInfo {
	//被保人
	String beibaoren;
	//险种类型
	String leixing;
	//险种代码
	String daima;
	//险种名称
	String mingcheng;
	//险种状态
	String zhuangtai;
	//保额
	String baoe;
	//份数
	String number;
	//生效日期
	String date;
	//保费合计
	String baofeiheji;
	//缴费年期
	String nianqi;
	//保险期限
	String qixian;

	@Override
	public String toString() {
		return beibaoren + " " +
				leixing + " " +
				daima + " " +
				mingcheng + " " +
				zhuangtai + " " +
				baoe + " " +
				number + " " +
				date + " " +
				baofeiheji + " " +
				nianqi + " " +
				qixian;
	}

	public BaoDanInfo(String beibaoren, String leixing, String daima, String mingcheng, String zhuangtai, String baoe, String number, String date, String baofeiheji, String nianqi, String qixian) {
		this.beibaoren = beibaoren;
		this.leixing = leixing;
		this.daima = daima;
		this.mingcheng = mingcheng;
		this.zhuangtai = zhuangtai;
		this.baoe = baoe;
		this.number = number;
		this.date = date;
		this.baofeiheji = baofeiheji;
		this.nianqi = nianqi;
		this.qixian = qixian;
	}

	public String getBeibaoren() {
		return beibaoren;
	}

	public void setBeibaoren(String beibaoren) {
		this.beibaoren = beibaoren;
	}

	public String getLeixing() {
		return leixing;
	}

	public void setLeixing(String leixing) {
		this.leixing = leixing;
	}

	public String getDaima() {
		return daima;
	}

	public void setDaima(String daima) {
		this.daima = daima;
	}

	public String getMingcheng() {
		return mingcheng;
	}

	public void setMingcheng(String mingcheng) {
		this.mingcheng = mingcheng;
	}

	public String getZhuangtai() {
		return zhuangtai;
	}

	public void setZhuangtai(String zhuangtai) {
		this.zhuangtai = zhuangtai;
	}

	public String getBaoe() {
		return baoe;
	}

	public void setBaoe(String baoe) {
		this.baoe = baoe;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getBaofeiheji() {
		return baofeiheji;
	}

	public void setBaofeiheji(String baofeiheji) {
		this.baofeiheji = baofeiheji;
	}

	public String getNianqi() {
		return nianqi;
	}

	public void setNianqi(String nianqi) {
		this.nianqi = nianqi;
	}

	public String getQixian() {
		return qixian;
	}

	public void setQixian(String qixian) {
		this.qixian = qixian;
	}
}
