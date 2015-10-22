package model.dao;

import java.sql.*;
import java.util.Properties;

public class DataBaseDAO {

	Connection con;
	Statement stm;
	private String pageNav = "";

	public String getPageNav() {
		return pageNav;
	}

	public void setPageNav(String pageNav) {
		this.pageNav = pageNav;
	}

	public void createPageNav(int totalRecords, int totalPages, int nRecord,
			int page, String strURL) {
		String pageNav = "";

		System.out.println("Total = " + totalRecords + " Number record = "
				+ nRecord + " page = " + page + " String url = " + strURL
				+ "  Total Pages = " + totalPages);
		if (page != 1) {
			// Button prev - Xét không phải trang đầu thì hiển thị thêm button
			// prev
			pageNav += "<a href='" + strURL + (page - 1) + "'>«</a>";
		}

		if (totalPages <= 5) {
			for (int i = 1; i <= totalPages; i++) {
				if (i == page) {
					// Đánh dấu button đang được chọn để đặt css
					pageNav += "<a id='selected' href='#'>" + i + "</a>";
				} else {
					pageNav += "<a href='" + strURL + i + "'>" + i + "</a>";
				}
			}
		} else {

			if (page <= 3) {
				for (int i = 1; i <= 4; i++) {
					if (i == page) {
						// Đánh dấu button đang được chọn để đặt css
						pageNav += "<a id='selected' href='#'>" + i + "</a>";
					} else {
						pageNav += "<a href='" + strURL + i + "'>" + i + "</a>";
					}
				}
				pageNav += "<a href='#'>...</a>";
				pageNav += "<a href='" + strURL + totalPages + "'>"
						+ totalPages + "</a>";
			} else {
				pageNav += "<a href='" + strURL + 1 + "'>" + 1 + "</a>";
				pageNav += "<a href='#'>...</a>";
				if (page >= totalPages - 2) {
					for (int i = totalPages - 3; i <= totalPages; i++) {
						if (i == page) {
							pageNav += "<a id='selected' href='" + strURL + i
									+ "'>" + i + "</a>";
						} else {
							pageNav += "<a href='" + strURL + i + "'>" + i
									+ "</a>";
						}
					}
				} else {
					pageNav += "<a href='" + strURL + (page - 1) + "'>"
							+ (page - 1) + "</a>";
					pageNav += "<a id='selected' href='" + strURL + (page)
							+ "'>" + (page) + "</a>";
					pageNav += "<a href='" + strURL + (page + 1) + "'>"
							+ (page + 1) + "</a>";
					pageNav += "<a href='#'>...</a>";
					pageNav += "<a href='" + strURL + totalPages + "'>"
							+ totalPages + "</a>";
				}

			}
		}
		// Button next - Xét đến trang cuối không được cộng thêm page lên
		pageNav += "<a href='" + strURL
				+ (page + 1 > totalPages ? (page + "#") : (page + 1))
				+ "'>»</a>";
		setPageNav(pageNav);
	}

	public void paging(ResultSet records, int nRecord, int page, String url)
			throws SQLException {
		// Get string page navigation
		records.last();
		int totalRecords = records.getRow();
		int totalPages = (int) (totalRecords / nRecord)
				+ (totalRecords % nRecord != 0 ? 1 : 0);
		records.beforeFirst();
		if (page < 1) {
			page = 1;
		}
		if (page > totalPages) {
			page = totalPages;
		}
		createPageNav(totalRecords, totalPages, nRecord, page,
				url+"?page=");

		if (page > 1) {
			int row = (page - 1) * nRecord;
			records.absolute(row);
			System.out.println("Row = " + row);
		}
	}

	public DataBaseDAO() {
		// Khởi tạo
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(
					"jdbc:sqlserver://localhost:1433;databaseName=DAMANGDB",
					"useradmin", "zxcvbnm1233");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (con != null) {
			System.out.println("Connection is established !");
		} else
			System.out.println("Connection isn't established !");
		try {
			stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			System.out.println("Statement is established !");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return con;
	}

	public void closeConnection() {
		try {
			stm.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Statement getStatement() {
		return stm;
	}

	public ResultSet getResultSet(String sql) {
		try {
			return stm.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println("Lỗi truy vấn");
			return null;
		}
	}

	public boolean updateData(String sql) {
		try {
			return stm.executeUpdate(sql) != 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("Execute update error!");
			return false;
		}
	}
	/*
	 * public static void main(String[] args) { DataBaseDAO db = new
	 * DataBaseDAO();
	 * 
	 * db.updateData("insert into DANHMUC values ('2',N'Đấy là danh mục 2')");
	 * db.updateData("insert into DANHMUC values ('3',N'Ồ đây là 4')");
	 * 
	 * ResultSet rs = db.getResultSet("select * from DANHMUC"); try {
	 * while(rs.next()){ System.out.println("Trả về kết quả : " +
	 * rs.getString(2)); } } catch (SQLException e) { // TODO Auto-generated
	 * catch block e.printStackTrace(); } }
	 */
}
