package model.bo;

import java.util.ArrayList;

import model.bean.SANPHAM;
import model.dao.SanPhamDAO;

public class SanPhamBO {

	SanPhamDAO sp = new SanPhamDAO();

	public ArrayList<SANPHAM> getDanhSachSanPham(String timkiem,
			String idDanhMuc, int nRecord, int page) {
		// TODO Auto-generated method stub
		if (KiemTraHopLe(timkiem))
			return sp.getDanhSachSanPham(timkiem, idDanhMuc, nRecord, page);
		else
			return sp.getDanhSachSanPham("", idDanhMuc, nRecord, page);
	}

	public boolean KiemTraHopLe(String x, String regex) {
		if (x == null)
			return false;
		return x.trim().matches(regex);
	}

	public boolean KiemTraHopLe(String x) {
		// TODO Auto-generated method stub
		if (x == null)
			return false;
		if (x.trim().equals(""))
			return false;
		return true;
	}

	public SANPHAM getSanPham(String id) {
		// TODO Auto-generated method stub
		if (KiemTraHopLe(id, "[\\s\\w]*"))
			return sp.getSanPham(id);
		else
			return sp.getSanPham("");
	}

	public boolean addSanPham(String id, String IdDanhMuc, String TenSanPham,
			String MoTa, String GiaBan, String SoLuong, String HinhAnh) {
		// TODO Auto-generated method stub
		return sp.addSanPham(id, IdDanhMuc, TenSanPham, MoTa, GiaBan, SoLuong,
				HinhAnh);
	}

	public boolean updateSanPham(String id, String IdDanhMuc,
			String TenSanPham, String MoTa, String GiaBan, String SoLuong,
			String HinhAnh) {
		// TODO Auto-generated method stub
		return sp.updateSanPham(id, IdDanhMuc, TenSanPham, MoTa, GiaBan,
				SoLuong, HinhAnh);
	}

	public boolean deleteSanPham(String id) {
		// TODO Auto-generated method stub
		return sp.deleteSanPham(id);
	}

	public ArrayList<SANPHAM> getDanhSachBanChay(int i) {
		return sp.getDanhSachBanChay(i);
	}

	public String getIdSanPham() {
		return sp.getIdSanPham();
	}

	public ArrayList<SANPHAM> getTopSanPhamMoi(int i) {
		return sp.getTopSanPhamMoi(i);
	}

	public ArrayList<SANPHAM> getTopSanPhamRandom(int i) {
		return sp.getTopSanPhamRandom(i);
	}

	public String getPageNav() {
		return sp.getPageNav();
	}
}
