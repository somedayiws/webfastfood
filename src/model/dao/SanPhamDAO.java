package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.bean.DANHMUC;
import model.bean.SANPHAM;

public class SanPhamDAO {

	DataBaseDAO db = new DataBaseDAO();

	public ArrayList<SANPHAM> getDanhSachSanPham(String timkiem,
			String idDanhMuc, int nRecord, int page) {
		// TODO Auto-generated method stub
		String sql = "select IdSanPham, IdKhuyenMai, TenSanPham, MoTa, GiaBan, SoLuong, HinhAnh, DANHMUC.IdDanhMuc, DanhMuc"
				+ " from SANPHAM join DANHMUC on SANPHAM.IdDanhMuc=DANHMUC.IdDanhMuc ";
		// Nếu có xử dụng lọc theo iddanh mục thì chỉ hiển thị theo danh mục
		if (!idDanhMuc.trim().equals("")) {
			sql += "and DANHMUC.IdDanhMuc=N'" + idDanhMuc + "'";
		}
		sql += " and (IdSanPham like N'%" + timkiem
				+ "%' or TenSanPham like N'%" + timkiem + "%' or MoTa like N'%"
				+ timkiem + "%' or DanhMuc like N'%" + timkiem + "%')";
		ResultSet rs = db.getResultSet(sql);
		System.out.println(sql);
		ArrayList<SANPHAM> list = new ArrayList<SANPHAM>();
		try {
			if (nRecord != -1) {
				// Paging các record trả về một resultset chứa nRecord và tạo
				// đường link
				db.paging(rs, nRecord, page, "SanPham");
				for (int i = 0; i < nRecord; i++) {
					rs.next();
					list.add(new SANPHAM(rs.getString(1), rs.getString(2), rs
							.getString(3), rs.getString(4), rs.getInt(5), rs
							.getInt(6), rs.getString(7), new DANHMUC(rs
							.getString(8), rs.getString(9))));
				}
			} else {
				while (rs.next()) {
					list.add(new SANPHAM(rs.getString(1), rs.getString(2), rs
							.getString(3), rs.getString(4), rs.getInt(5), rs
							.getInt(6), rs.getString(7), new DANHMUC(rs
							.getString(8), rs.getString(9))));
				}
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		return list;
	}

	public SANPHAM getSanPham(String id) {
		// TODO Auto-generated method stub
		ResultSet rs = db
				.getResultSet("select IdSanPham, IdKhuyenMai, TenSanPham, MoTa, GiaBan, SoLuong, HinhAnh, DANHMUC.IdDanhMuc, DanhMuc"
						+ " from SANPHAM join DANHMUC on SANPHAM.IdDanhMuc=DANHMUC.IdDanhMuc and IdSanPham = N'"
						+ id + "'");
		try {
			if (rs.next()) {
				return new SANPHAM(rs.getString(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getInt(5),
						rs.getInt(6), rs.getString(7), new DANHMUC(
								rs.getString(8), rs.getString(9)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		}
		return null;
	}

	public String getIdSanPham() {
		ResultSet rs = db
				.getResultSet("select top 1 IdSanPham from SANPHAM order by IdSanPham desc");
		String idSanPham = "SP00000000";
		try {
			if (rs.next()) {
				idSanPham = rs.getString(1);
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		int so = Integer.parseInt(idSanPham.substring(2)) + 1;
		idSanPham = idSanPham.substring(0, 10 - (so + "").length()) + so;
		System.out.println("Xem : " + idSanPham);
		return idSanPham;
	}

	public boolean addSanPham(String id, String IdDanhMuc, String TenSanPham,
			String MoTa, String GiaBan, String SoLuong, String HinhAnh) {
		// TODO Auto-generated method stub
		return db.updateData("insert into SANPHAM values (N'" + getIdSanPham()
				+ "',N'" + IdDanhMuc + "',NULL,N'" + TenSanPham + "',N'" + MoTa
				+ "','" + GiaBan + "','" + SoLuong + "',N'" + HinhAnh + "')");
	}

	public boolean updateSanPham(String id, String IdDanhMuc,
			String TenSanPham, String MoTa, String GiaBan, String SoLuong,
			String HinhAnh) {
		// TODO Auto-generated method stub
		return db
				.updateData("update SANPHAM set IdDanhMuc=N'" + IdDanhMuc
						+ "', TenSanPham=N'" + TenSanPham + "', MoTa=N'" + MoTa
						+ "', GiaBan='" + GiaBan + "', SoLuong='" + SoLuong
						+ "', HinhAnh=N'" + HinhAnh + "' where IdSanPham=N'"
						+ id + "'");
	}

	public boolean deleteSanPham(String id) {
		return db.updateData("delete from CHITIETDONHANG where IdSanPham=N'"
				+ id + "'")
				&& db.updateData("delete from SANPHAM where IdSanPham=N'" + id
						+ "'");
	}

	public ArrayList<SANPHAM> getDanhSachBanChay(int i) {
		ResultSet rs = db
				.getResultSet("select SANPHAM.IdSanPham, IdKhuyenMai, TenSanPham, MoTa, GiaBan, SoLuong, "
						+ "HinhAnh, DANHMUC.IdDanhMuc, DanhMuc,TongSo.TongSoLuong from SANPHAM join DANHMUC "
						+ "on SANPHAM.IdDanhMuc=DANHMUC.IdDanhMuc inner join "
						+ "(select top 10 CHITIETDONHANG.IdSanPham, SUM(CHITIETDONHANG.SoLuong) as TongSoLuong "
						+ "from CHITIETDONHANG inner join SANPHAM on CHITIETDONHANG.IdSanPham = SANPHAM.IdSanPham "
						+ "group by CHITIETDONHANG.IdSanPham order by TongSoLuong desc) TongSo "
						+ "on SANPHAM.IdSanPham=TongSo.IdSanPham");
		ArrayList<SANPHAM> list = new ArrayList<SANPHAM>();
		try {
			while (rs.next()) {
				list.add(new SANPHAM(rs.getString(1), rs.getString(2), rs
						.getString(3), rs.getString(4), rs.getInt(5), rs
						.getInt(6), rs.getString(7), new DANHMUC(rs
						.getString(8), rs.getString(9))));
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		return list;
	}

	public ArrayList<SANPHAM> getTopSanPhamMoi(int i) {
		ResultSet rs = db
				.getResultSet("select TOP "
						+ i
						+ " SANPHAM.IdSanPham, IdKhuyenMai, TenSanPham, MoTa, GiaBan, SoLuong, "
						+ "HinhAnh, DANHMUC.IdDanhMuc, DanhMuc from SANPHAM join DANHMUC "
						+ "on SANPHAM.IdDanhMuc=DANHMUC.IdDanhMuc order by SANPHAM.IdSanPham DESC");
		ArrayList<SANPHAM> list = new ArrayList<SANPHAM>();
		try {
			while (rs.next()) {
				list.add(new SANPHAM(rs.getString(1), rs.getString(2), rs
						.getString(3), rs.getString(4), rs.getInt(5), rs
						.getInt(6), rs.getString(7), new DANHMUC(rs
						.getString(8), rs.getString(9))));
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		return list;
	}

	public ArrayList<SANPHAM> getTopSanPhamRandom(int i) {
		ResultSet rs = db
				.getResultSet("select TOP "
						+ i
						+ " SANPHAM.IdSanPham, IdKhuyenMai, TenSanPham, MoTa, GiaBan, SoLuong, "
						+ "HinhAnh, DANHMUC.IdDanhMuc, DanhMuc from SANPHAM join DANHMUC "
						+ "on SANPHAM.IdDanhMuc=DANHMUC.IdDanhMuc order by NEWID()");
		ArrayList<SANPHAM> list = new ArrayList<SANPHAM>();
		try {
			while (rs.next()) {
				list.add(new SANPHAM(rs.getString(1), rs.getString(2), rs
						.getString(3), rs.getString(4), rs.getInt(5), rs
						.getInt(6), rs.getString(7), new DANHMUC(rs
						.getString(8), rs.getString(9))));
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		return list;
	}

	public String getPageNav() {
		return db.getPageNav();
	}
}
