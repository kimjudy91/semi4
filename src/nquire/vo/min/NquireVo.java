package nquire.vo.min;

import java.sql.Date;

public class NquireVo {
	private int nquire_num;
	private String id;
	private String title;
	private String contents;
	private Date r_date;
	private String comments;
	
	public NquireVo() {}

	public NquireVo(int nquire_num, String id, String title, String contents, Date r_date, String comments) {
		super();
		this.nquire_num = nquire_num;
		this.id=id;
		this.title = title;
		this.contents = contents;
		this.r_date = r_date;
		this.comments = comments;
	}

	public int getNquire_num() {
		return nquire_num;
	}

	public void setNquire_num(int nquire_num) {
		this.nquire_num = nquire_num;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id=id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
}
