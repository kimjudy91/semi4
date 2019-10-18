package board.filecontroller.vo;

import java.sql.Date;

public class FileBoardCommentsVo {
	private int comments_num;
	private int write_num;
	private String id;
	private String comments_contents;
	private Date comments_date;
	private int ref;
	private int lev;
	private int step;
	public FileBoardCommentsVo() {}
	public FileBoardCommentsVo(int comments_num, int write_num, String id, String comments_contents, Date comments_date,
			int ref, int lev, int step) {
		super();
		this.comments_num = comments_num;
		this.write_num = write_num;
		this.id = id;
		this.comments_contents = comments_contents;
		this.comments_date = comments_date;
		this.ref = ref;
		this.lev = lev;
		this.step = step;
	}
	public int getComments_num() {
		return comments_num;
	}
	public void setComments_num(int comments_num) {
		this.comments_num = comments_num;
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
	public String getComments_contents() {
		return comments_contents;
	}
	public void setComments_contents(String comments_contents) {
		this.comments_contents = comments_contents;
	}
	public Date getComments_date() {
		return comments_date;
	}
	public void setComments_date(Date comments_date) {
		this.comments_date = comments_date;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public int getLev() {
		return lev;
	}
	public void setLev(int lev) {
		this.lev = lev;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	
 } 