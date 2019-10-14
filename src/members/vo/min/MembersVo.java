package members.vo.min;

public class MembersVo {
	private String id;
	private String pwd;
	private String name;
	private int jumin;
	private String email;
	private String address;
	private String phone;
	private int write_count;
	private int reply_count;
	private int grade;
	private int warning;
	private int genre_num;
	
	private MembersVo() {}
	public MembersVo(String id, String pwd, String name, int jumin, String email, String address, String phone,
			int write_count, int reply_count, int grade, int warning, int genre_num) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.jumin = jumin;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.write_count = write_count;
		this.reply_count = reply_count;
		this.grade = grade;
		this.warning = warning;
		this.genre_num=genre_num;
	}
	public int getGenre_num() {return genre_num;}
	public void setGenre_num(int genre_num) {this.genre_num = genre_num;}
	
	public String getId() {return id;}
	public void setId(String id) {this.id = id;}
	
	public String getPwd() {return pwd;}
	public void setPwd(String pwd) {this.pwd = pwd;}
	
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	
	public int getJumin() {return jumin;}
	public void setJumin(int jumin) {this.jumin = jumin;}
	
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
	
	public String getAddress() {return address;}
	public void setAddress(String address) {this.address = address;}
	
	public String getPhone() {return phone;}
	public void setPhone(String phone) {this.phone = phone;}
	
	public int getWrite_count() {return write_count;}
	public void setWrite_count(int write_count) {this.write_count = write_count;}
	
	public int getReply_count() {return reply_count;}
	public void setReply_count(int reply_count) {this.reply_count = reply_count;}
	
	public int getGrade() {return grade;}
	public void setGrade(int grade) {this.grade = grade;}
	
	public int getWarning() {return warning;}
	public void setWarning(int warning) {this.warning = warning;}
}
