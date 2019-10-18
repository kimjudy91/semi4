package board_f_vo.yun;

public class Recommended_folk_Vo {
	private int write_num;
	private int genre_num;
	private int likes;
	public Recommended_folk_Vo() {}
	public Recommended_folk_Vo(int write_num, int genre_num, int likes) {
		super();
		this.write_num = write_num;
		this.genre_num = genre_num;
		this.likes = likes;
	}
	public int getWrite_num() {
		return write_num;
	}
	public void setWrite_num(int write_num) {
		this.write_num = write_num;
	}
	public int getGenre_num() {
		return genre_num;
	}
	public void setGenre_num(int genre_num) {
		this.genre_num = genre_num;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	
}