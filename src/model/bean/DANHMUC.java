package model.bean;

public class DANHMUC {
	String IdDanhMuc;
	String DanhMuc;
	public DANHMUC(String idDanhMuc, String danhMuc) {
		super();
		IdDanhMuc = idDanhMuc;
		DanhMuc = danhMuc;
	}
	public String getIdDanhMuc() {
		return IdDanhMuc;
	}
	public void setIdDanhMuc(String idDanhMuc) {
		IdDanhMuc = idDanhMuc;
	}
	public String getDanhMuc() {
		return DanhMuc;
	}
	public void setDanhMuc(String danhMuc) {
		DanhMuc = danhMuc;
	}
	
}
