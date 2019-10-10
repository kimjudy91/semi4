package board.vo.yun;

import java.sql.Date;

public class BoardVo {
	private int write_num;
	private String id;
	private String p_title;
	private String contents;
	private Date r_date;
	private int views;
	private String genre;
	public BoardVo() {}
	public BoardVo(int write_num, String id, String p_title, String contents, Date r_date, int views,
			String genre) {
		super();
		this.write_num = write_num;
		this.id = id;
		this.p_title = p_title;
		this.contents = contents;
		this.r_date = r_date;
		this.views = views;
		this.genre = genre;
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
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public Date getR_date() {
		return r_date;
	}
	public void setR_date(Date r_date) {
		this.r_date = r_date;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
}
