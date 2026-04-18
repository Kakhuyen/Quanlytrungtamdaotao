<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.quanly.model.NguoiDung" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bảng điều khiển quản trị</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<%
    NguoiDung user = (NguoiDung) session.getAttribute("user");
%>
<div class="container py-5">
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h2 class="m-0">Trang quan tri</h2>
        <a class="btn btn-outline-danger" href="<%= request.getContextPath() %>/logout">Đăng xuất</a>
    </div>
    <div class="alert alert-success">
        Đăng nhập thành công với vai trò <strong>Quản trị viên</strong>.
    </div>
    <p><strong>Tài khoản:</strong> <%= user != null ? user.getTenDangNhap() : "Không xác định" %></p>
    <p>Đây là trang bảng điều khiển tạm thời để test phân quyền.</p>
</div>
</body>
</html>