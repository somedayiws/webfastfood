<%@page import="model.bean.SANPHAM"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- Phần mở đầu gôm các file thư viện cần cho trang web --%>
<%@include file="FILE\HEAD.jsp"%>
<%-- Định nghĩa lại title, đoạn javascript dùng cho chính trang web --%>




<%-- Phần header bao gồm logo thanh top menu --%>
<%@include file="FILE\header.jsp"%>


<div id="primary">
	<%@include file="FILE\sidebar.jsp"%>
	<%-- Trình bày nội dung web đây --%>
	<div id="content">
		<div id="title">
			<p>Danh sách các sản phẩm</p>
		</div>
		<div id="contentPage">
			<form action="SanPham" method="post">
				<table border="1" class="bang">
					<tr>
						<th width="100px">Tìm kiếm :</th>
						<td colspan="6"><input type="text" name="txttimkiem"
							placeholder="Nhập từ khóa cần tìm..." style="width: 91%">
							<input type="submit" value="Lọc" style="width: 50px"></td>
					</tr>
					<tr>
						<td colspan="6"><br></td>
					</tr>
					<tr>
						<th width="100px">Mã SP</th>
						<th>Danh mục</th>
						<th>Tên SP</th>
						<th>Mô tả</th>
						<th>Giá bán</th>
						<th><a href="ThemSanPhamServlet"> Thêm mới </a></th>
					</tr>
					<%
						ArrayList<SANPHAM> list = (ArrayList<SANPHAM>) request
								.getAttribute("list");
						int i = 0;
						while (i < list.size()) {
					%>
					<tr class="<%=(i % 2 == 0) ? "odd" : "even"%>">
						<td width="100px"><%=list.get(i).getIdSanPham()%></td>
						<td><%=(list.get(i).getDanhMuc()).getDanhMuc()%></td>
						<td><%=list.get(i).getTenSanPham()%></td>
						<td><%=list.get(i).getMoTa()%></td>
						<td><%=list.get(i).getGiaBan()%></td>
						<td><a
							href="SuaSanPhamServlet?id=<%=list.get(i).getIdSanPham()%>">
								Sửa </a> - <a
							href="XoaSanPhamServlet?id=<%=list.get(i).getIdSanPham()%>">
								Xóa </a>
						</th>
					</tr>
					<%
						i++;
						}
					%>
				</table>
			</form>
			<div id="Navigation" align="center">
				<%=request.getAttribute("pageNav")%>
			</div>
		</div>
	</div>
</div>
<%-- Kết thúc quá trình code --%>
<%@include file="FILE\footer.jsp"%>
