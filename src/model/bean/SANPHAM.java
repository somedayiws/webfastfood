package model.bean;

public class SANPHAM {
	String IdSanPham;
	String IdKhuyenMai;
	String TenSanPham;
	String MoTa;
	int GiaBan;
	int SoLuong;
	String HinhAnh;
	
	DANHMUC DanhMuc;
	
	public DANHMUC getDanhMuc() {
		return DanhMuc;
	}

	public void setDanhMuc(DANHMUC danhMuc) {
		DanhMuc = danhMuc;
	}

	public SANPHAM(String idSanPham, String tenSanPham,
			String moTa, int giaBan, int soLuong, String hinhAnh) {
		super();
		IdSanPham = idSanPham;
		TenSanPham = tenSanPham;
		MoTa = moTa;
		GiaBan = giaBan;
		SoLuong = soLuong;
		HinhAnh = hinhAnh;
	}
	
	public SANPHAM(String idSanPham, String idKhuyenMai,
			String tenSanPham, String moTa, int giaBan, int soLuong,
			String hinhAnh, DANHMUC dm) {
		super();
		IdSanPham = idSanPham;
		IdKhuyenMai = idKhuyenMai;
		TenSanPham = tenSanPham;
		MoTa = moTa;
		GiaBan = giaBan;
		SoLuong = soLuong;
		HinhAnh = hinhAnh;
		DanhMuc = dm;
	}

	public String getIdSanPham() {
		return IdSanPham;
	}

	public void setIdSanPham(String idSanPham) {
		IdSanPham = idSanPham;
	}

	public String getIdKhuyenMai() {
		return IdKhuyenMai;
	}

	public void setIdKhuyenMai(String idKhuyenMai) {
		IdKhuyenMai = idKhuyenMai;
	}

	public String getTenSanPham() {
		return TenSanPham;
	}

	public void setTenSanPham(String tenSanPham) {
		TenSanPham = tenSanPham;
	}

	public String getMoTa() {
		return MoTa;
	}

	public void setMoTa(String moTa) {
		MoTa = moTa;
	}

	public int getGiaBan() {
		return GiaBan;
	}

	public void setGiaBan(int giaBan) {
		GiaBan = giaBan;
	}

	public int getSoLuong() {
		return SoLuong;
	}

	public void setSoLuong(int soLuong) {
		SoLuong = soLuong;
	}

	public String getHinhAnh() {
		return HinhAnh;
	}

	public void setHinhAnh(String hinhAnh) {
		HinhAnh = hinhAnh;
	}
}
