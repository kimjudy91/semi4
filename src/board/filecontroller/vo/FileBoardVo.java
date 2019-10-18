package board.filecontroller.vo;

import java.util.Date;

public class FileBoardVo {
	private  int  write_num;
	private String  id ;
	private int f_num;
	private String p_title; 
	private String contents;
	private Date r_date;
	private  int views; 
	private int likes;
	private int genre_num;
	
	public FileBoardVo () {}

	public FileBoardVo(int write_num, String id, int f_num, String p_title, String contents, Date r_date, int views,
			int likes, int genre_num) {
		super();
		this.write_num = write_num;
		this.id = id;
		this.f_num = f_num;
		this.p_title = p_title;
		this.contents = contents;
		this.r_date = r_date;
		this.views = views;
		this.likes = likes;
		this.genre_num = genre_num;
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

	public int getF_num() {
		return f_num;
	}

	public void setF_num(int f_num) {
		this.f_num = f_num;
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

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getGenre_num() {
		return genre_num;
	}

	public void setGenre_num(int genre_num) {
		this.genre_num = genre_num;
	}
	
	
	
	

}
