package board.filecontroller.vo;

public class FileUpLoadVo {
	private int f_num;
	private String orgfilename;
	private String savefilename;
	private int filesize;
	
	
	public FileUpLoadVo() {}


	public FileUpLoadVo(int f_num, String orgfilename, String savefilename, int filesize) {
		super();
		this.f_num = f_num;
		this.orgfilename = orgfilename;
		this.savefilename = savefilename;
		this.filesize = filesize;
	}


	public int getF_num() {
		return f_num;
	}


	public void setF_num(int f_num) {
		this.f_num = f_num;
	}


	public String getOrgfilename() {
		return orgfilename;
	}


	public void setOrgfilename(String orgfilename) {
		this.orgfilename = orgfilename;
	}


	public String getSavefilename() {
		return savefilename;
	}


	public void setSavefilename(String savefilename) {
		this.savefilename = savefilename;
	}


	public int getFilesize() {
		return filesize;
	}


	public void setFilesize(int filesize) {
		this.filesize = filesize;
	}
	
	
	
	
	
}
