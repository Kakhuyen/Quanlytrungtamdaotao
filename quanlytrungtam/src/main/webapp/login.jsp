<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Đăng nhập hệ thống</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            min-height: 100vh;
            background: linear-gradient(135deg, #f7f7f7 0%, #d9e9ff 100%);
            display: flex;
            align-items: center;
            justify-content: center;
            font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
        }

        .login-card {
            width: 100%;
            max-width: 420px;
            border: 0;
            border-radius: 16px;
            box-shadow: 0 12px 30px rgba(0, 0, 0, 0.12);
        }
    </style>
</head>
<body>
<div class="card login-card p-4">
    <div class="card-body">
        <h3 class="card-title mb-3 text-center">Đăng nhập</h3>
        <p class="text-muted text-center mb-4">Quản lý trung tâm đào tạo</p>

        <% String error = (String) request.getAttribute("error"); %>
        <% String errorCode = request.getParameter("error"); %>
        <% String messageCode = request.getParameter("message"); %>
        <% if (error != null) { %>
        <div class="alert alert-danger" role="alert"><%= error %></div>
        <% } %>
        <% if ("unauthorized".equals(errorCode)) { %>
        <div class="alert alert-warning" role="alert">Vui lòng đăng nhập để tiếp tục.</div>
        <% } %>
        <% if ("forbidden".equals(errorCode)) { %>
        <div class="alert alert-danger" role="alert">Bạn không có quyền truy cập trang này.</div>
        <% } %>
        <% if ("logout".equals(messageCode)) { %>
        <div class="alert alert-success" role="alert">Đăng xuất thành công.</div>
        <% } %>
        <% if ("registered".equals(messageCode)) { %>
        <div class="alert alert-success" role="alert">Đăng ký thành công. Vui lòng đăng nhập.</div>
        <% } %>

        <form action="<%= request.getContextPath() %>/login" method="post">
            <div class="mb-3">
                <label for="username" class="form-label">Tên đăng nhập</label>
                <input type="text" class="form-control" id="username" name="username" required>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Mật khẩu</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>
            <button type="submit" class="btn btn-primary w-100">Đăng nhập</button>
        </form>

        <p class="text-center mt-3 mb-0">
            Chưa có tài khoản?
            <a href="<%= request.getContextPath() %>/register">Đăng ký ngay</a>
        </p>
    </div>
</div>
</body>
</html>