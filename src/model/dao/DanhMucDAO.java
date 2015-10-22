package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.bean.DANHMUC;
import model.bean.DANHMUC;

public class DanhMucDAO {

	DataBaseDAO db = new DataBaseDAO();
	
	public ArrayList<DANHMUC> getDanhSachDanhMuc(String timkiem) {
		// TODO Auto-generated method stub
		ResultSet rs = db.getResultSet("select * from DANHMUC where IdDanhMuc like N'%"
				+ timkiem+"%' or DanhMuc like N'%"+timkiem+"%'");
		ArrayList<DANHMUC> list = new ArrayList<DANHMUC>();
		try {
			while(rs.next()){
				list.add(new DANHMUC(rs.getString(1), rs.getString(2)));
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		return list;
	}

	public DANHMUC getDanhMuc(String id) {
		// TODO Auto-generated method stub
		ResultSet rs = db.getResultSet("select * from DANHMUC where IdDanhMuc=N'" + id + "'");
		try {
			if(rs.next()){
				return new DANHMUC(rs.getString(1), rs.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		}
		return null;
	}

	public boolean addDanhMuc(String id, String danhmuc) {
		// TODO Auto-generated method stub
		return db.updateData("insert into DANHMUC values (N'"+id+"',N'"+danhmuc+"')");
	}

	public boolean updateDanhMuc(String id, String danhmuc) {
		// TODO Auto-generated method stub
		return db.updateData("update DANHMUC set DanhMuc=N'"+danhmuc+"' where IdDanhMuc=N'"+id+"'");
	}

	public boolean deleteDanhMuc(String id) {
		// TODO Auto-generated method stub
		return db.updateData("delete from DANHMUC where IdDanhMuc=N'"+id+"'");
	}
}
