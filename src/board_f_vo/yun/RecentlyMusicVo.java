package board_f_vo.yun;

import java.sql.Date;

public class RecentlyMusicVo {
	private int write_num;
	private String id;
	private String p_title;
	private Date r_date;
	public RecentlyMusicVo() {}
	public RecentlyMusicVo(int write_num, String id, String p_title, Date r_date) {
		super();
		this.write_num = write_num;
		this.id = id;
		this.p_title = p_title;
		this.r_date = r_date;
	}
	public int getWrite_num() {
		return write_num;
	}
	public void setWrite_num(int write_num) {
		this.write_num = write_num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getP_title() {
		return p_title;
	}
	public void setP_title(String p_title) {
		this.p_title = p_title;
	}
	public Date getR_date() {
		return r_date;
	}
	public void setR_date(Date r_date) {
		this.r_date = r_date;
	}
	
}
