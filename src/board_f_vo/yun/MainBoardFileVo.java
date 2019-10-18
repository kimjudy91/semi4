package board_f_vo.yun;

public class MainBoardFileVo {
	private int write_num;
	private String id;
	private String p_title;
	private int likes;
	public MainBoardFileVo() {}
	public MainBoardFileVo(int write_num, String id, String p_title, int likes) {
		super();
		this.write_num = write_num;
		this.id = id;
		this.p_title = p_title;
		this.likes = likes;
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
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	
}
