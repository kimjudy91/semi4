package message.vo.joo;

public class MessageVo {
	private int message_num;
	private String sid;
	private String rid;
	private String contents;
	private int checking;
	public MessageVo() {}
	public MessageVo(int message_num, String sid, String rid, String contents, int checking) {
		super();
		this.message_num = message_num;
		this.sid = sid;
		this.rid = rid;
		this.contents = contents;
		this.checking = checking;
	}
	public int getMessage_num() {
		return message_num;
	}
	public void setMessage_num(int message_num) {
		this.message_num = message_num;
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
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public int getChecking() {
		return checking;
	}
	public void setChecking(int cheking) {
		this.checking = cheking;
	}
	
}
