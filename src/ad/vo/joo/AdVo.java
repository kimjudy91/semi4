package ad.vo.joo;

import java.sql.Date;

public class AdVo {
	private int ad_num;
	private int ad_money;
	private String ad_com;
	private String ad_image;
	private String ad_start_date;
	private String ad_end_date;
	public AdVo () {}
	public AdVo(int ad_num, int ad_money, String ad_com, String ad_image, String ad_start_date, String ad_end_date) {
		super();
		this.ad_num = ad_num;
		this.ad_money = ad_money;
		this.ad_com = ad_com;
		this.ad_image = ad_image;
		this.ad_start_date = ad_start_date;
		this.ad_end_date = ad_end_date;
	}
	public int getAd_num() {
		return ad_num;
	}
	public void setAd_num(int ad_num) {
		this.ad_num = ad_num;
	}
	public int getAd_money() {
		return ad_money;
	}
	public void setAd_money(int ad_money) {
		this.ad_money = ad_money;
	}
	public String getAd_com() {
		return ad_com;
	}
	public void setAd_com(String ad_com) {
		this.ad_com = ad_com;
	}
	public String getAd_image() {
		return ad_image;
	}
	public void setAd_image(String ad_image) {
		this.ad_image = ad_image;
	}
	public String getAd_start_date() {
		return ad_start_date;
	}
	public void setAd_start_date(String ad_start_date) {
		this.ad_start_date = ad_start_date;
	}
	public String getAd_end_date() {
		return ad_end_date;
	}
	public void setAd_end_date(String ad_end_date) {
		this.ad_end_date = ad_end_date;
	}
	
	
	
}
