package loginIds.vo.joo;

public class LoginIdVO {
	private int login_num;
	private String ids;
	public LoginIdVO() {}
	public LoginIdVO(int login_num, String ids) {
		super();
		this.login_num = login_num;
		this.ids = ids;
	}
	public int getLogin_num() {
		return login_num;
	}
	public void setLogin_num(int login_num) {
		this.login_num = login_num;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	
}
