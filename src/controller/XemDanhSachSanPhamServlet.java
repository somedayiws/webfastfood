package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.SANPHAM;
import model.bo.SanPhamBO;

/**
 * Servlet implementation class XemDanhSachSanPhamServlet
 */
@WebServlet("/admin/SanPham")
public class XemDanhSachSanPhamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public XemDanhSachSanPhamServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		int page = 1;
		int nRecord = 14;
		try {
			page = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
			page = 1;
		}
		System.out.println("nRecord= " + nRecord + " page= " + page);

		String txttimkiem = request.getParameter("txttimkiem");
		String id = request.getParameter("id");
		if (txttimkiem == null)
			txttimkiem = "";
		if (id == null)
			id = "";
		SanPhamBO sanPhamBO = new SanPhamBO();
		ArrayList<SANPHAM> list = new ArrayList<SANPHAM>();
		list = sanPhamBO.getDanhSachSanPham(txttimkiem, id, nRecord, page);

		String pageNav = sanPhamBO.getPageNav();
		request.setAttribute("pageNav", pageNav);
		request.setAttribute("list", list);
		System.out.println("Số lượng record = " + list.size());
		RequestDispatcher pc = request
				.getRequestDispatcher("XemDanhSachSanPham.jsp");
		pc.forward(request, response);
	}

}
