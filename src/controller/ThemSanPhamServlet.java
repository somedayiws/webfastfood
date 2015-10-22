package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.DANHMUC;
import model.bean.QUANTRI;
import model.bo.DanhMucBO;
import model.bo.SanPhamBO;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.FileCleanerCleanup;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class ThemSanPhamServlet
 */
@WebServlet("/admin/ThemSanPhamServlet")
public class ThemSanPhamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ThemSanPhamServlet() {
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
		QUANTRI quanTri = (QUANTRI) session.getAttribute("quanTri");
		try {
			SanPhamBO sp = new SanPhamBO();
			DanhMucBO dm = new DanhMucBO();
			ArrayList<DANHMUC> danhmuc = dm.getDanhSachDanhMuc("");
			request.setAttribute("danhmuc", danhmuc);

			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if (!isMultipart) {
				RequestDispatcher requestDispatcher = request
						.getRequestDispatcher("ThemSanPham.jsp");
				requestDispatcher.forward(request, response);
			} else {

				FileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				List items = null;

				String id;
				String iddanhmuc;
				String tensanpham;
				String mota;
				String giaban;
				String hinhanh;

				try {
					items = upload.parseRequest(request);
				} catch (FileUploadException e) {
					e.printStackTrace();
				}

				Iterator iter = items.iterator();
				Hashtable params = new Hashtable();
				String filename = null;

				while (iter.hasNext()) {

					FileItem item = (FileItem) iter.next();
					if (item.isFormField()) {
						// GetFieldName -> Parameter
						// GetString -> Value of Form
						// GetName -> file name of file component
						// out.println(item.getFieldName()+"@@@@@@"+item.getName()+
						// "@@@@@@@@@@@"+ item.getString());
						params.put(item.getFieldName(), item.getString("UTF-8"));
						System.out.println("Du lieu: " + item.getString()
								+ "       " + item.getFieldName());
					} else {
						try {
							// out.println(item.getFieldName() + "@@@@@@"
							// + item.getName() + "@@@@@@@@@@@"
							// + item.getString());
							String itemName = item.getName();

							filename = itemName.substring(itemName
									.lastIndexOf("\\") + 1);
							// out.println("item name = " + itemName
							// + "   File name =" + filename);
							SanPhamBO sanPhamBO = new SanPhamBO();
							id = sanPhamBO.getIdSanPham();
							filename = id
									+ filename.substring(filename.indexOf("."));
							String realPath = getServletContext().getRealPath(
									"/")
									+ "images\\products\\" + filename;

							System.out.println(realPath);

							File savedFile = new File(realPath);
							FileCleanerCleanup item2 = new FileCleanerCleanup();
							// Upload file l�n server
							item.write(savedFile);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}

				id = (String) params.get("id");
				iddanhmuc = (String) params.get("iddanhmuc");
				tensanpham = (String) params.get("tensanpham");
				mota = (String) params.get("mota");
				giaban = (String) params.get("giaban");
				hinhanh = filename;

				if (mota != null && tensanpham != null && giaban != null) {
					if (sp.KiemTraHopLe(tensanpham) && sp.KiemTraHopLe(mota)
							&& sp.KiemTraHopLe(giaban, "\\d+")) {
						if (sp.addSanPham(id, iddanhmuc, tensanpham, mota,
								giaban, "0", hinhanh)) {
							response.sendRedirect("SanPham");
						} else {
							request.setAttribute("loi", "Lỗi cập nhật csdl");
							RequestDispatcher pc = request
									.getRequestDispatcher("ThemSanPham.jsp");
							pc.forward(request, response);
						}
					} else {
						request.setAttribute("loi", "Hãy nhập đầy đủ thông tin");
						RequestDispatcher pc = request
								.getRequestDispatcher("ThemSanPham.jsp");
						pc.forward(request, response);
					}
				} else {
					RequestDispatcher pc = request
							.getRequestDispatcher("ThemSanPham.jsp");
					pc.forward(request, response);
				}
			}

		} catch (Exception e) {
			System.out.println("Throw exception upload file!");
		}
	}
}
