package newmessage.vo.joo;

public class NewMessageVo {
	private String id;
	private int count;
	public NewMessageVo() {}
	public NewMessageVo(String id, int count) {
		super();
		this.id = id;
		this.count = count;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
