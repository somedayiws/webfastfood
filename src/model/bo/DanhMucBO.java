package model.bo;

import java.util.ArrayList;

import model.bean.DANHMUC;
import model.dao.DanhMucDAO;

public class DanhMucBO {

	DanhMucDAO dm = new DanhMucDAO();

	public ArrayList<DANHMUC> getDanhSachDanhMuc(String timkiem) {
		// TODO Auto-generated method stub
		return dm.getDanhSachDanhMuc(timkiem.trim());
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

	public DANHMUC getDanhMuc(String id) {
		// TODO Auto-generated method stub
		if (KiemTraHopLe(id, "[\\s\\w]*"))
			return dm.getDanhMuc(id);
		else
			return dm.getDanhMuc("");
	}

	public boolean addDanhMuc(String id, String danhmuc) {
		// TODO Auto-generated method stub
		return dm.addDanhMuc(id, danhmuc);
	}

	public boolean updateDanhMuc(String id, String danhmuc) {
		// TODO Auto-generated method stub
		return dm.updateDanhMuc(id, danhmuc);
	}

	public boolean deleteDanhMuc(String id) {
		// TODO Auto-generated method stub
		return dm.deleteDanhMuc(id);
	}
}
