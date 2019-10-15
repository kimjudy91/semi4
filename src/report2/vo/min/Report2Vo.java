package report2.vo.min;

public class Report2Vo {
	private int rnum;
	private String id;
	private int write_num;
	private String report_content;
	private String comments;
	
	public Report2Vo() {}

	public Report2Vo(int rnum, String id, int write_num, String report_content, String comments) {
		super();
		this.rnum = rnum;
		this.id = id;
		this.write_num = write_num;
		this.report_content = report_content;
		this.comments = comments;
	}

	public int getRnum() {
		return rnum;
	}

	public void setRnum(int rnum) {
		this.rnum = rnum;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getWrite_num() {
		return write_num;
	}

	public void setWrite_num(int write_num) {
		this.write_num = write_num;
	}

	public String getReport_content() {
		return report_content;
	}

	public void setReport_content(String report_content) {
		this.report_content = report_content;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
	


}

