package message.vo.joo;

public class FriendsVo {
	private int friends_num;
	private String sid;
	private String rid;
	private int commit;
	public FriendsVo() {}
	public FriendsVo(int friends_num, String sid, String rid, int commit) {
		super();
		this.friends_num = friends_num;
		this.sid = sid;
		this.rid = rid;
		this.commit = commit;
	}
	public int getFriends_num() {
		return friends_num;
	}
	public void setFriends_num(int friends_num) {
		this.friends_num = friends_num;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getRid() {
		return rid;
	}
	public void setRid(String rid) {
		this.rid = rid;
	}
	public int getCommit() {
		return commit;
	}
	public void setCommit(int commit) {
		this.commit = commit;
	}
	
}
