<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Đăng ký học viên</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            min-height: 100vh;
            background: linear-gradient(135deg, #f5f7fa 0%, #d7e7ff 100%);
            display: flex;
            align-items: center;
            justify-content: center;
            font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
            padding: 24px 0;
        }

        .register-card {
            width: 100%;
            max-width: 620px;
            border: 0;
            border-radius: 16px;
            box-shadow: 0 14px 35px rgba(0, 0, 0, 0.14);
        }
    </style>
</head>
<body>
<div class="card register-card p-4">
    <div class="card-body">
        <h3 class="card-title mb-2 text-center">Đăng ký học viên</h3>
        <p class="text-muted text-center mb-4">Tạo tài khoản mới cho hệ thống quản lý trung tâm</p>

        <% String error = (String) request.getAttribute("error"); %>
        <% if (error != null) { %>
        <div class="alert alert-danger" role="alert"><%= error %></div>
        <% } %>

        <% String usernameError = (String) request.getAttribute("usernameError"); %>
        <% String hoTenError = (String) request.getAttribute("hoTenError"); %>
        <% String passwordError = (String) request.getAttribute("passwordError"); %>
        <% String confirmPasswordError = (String) request.getAttribute("confirmPasswordError"); %>
        <% String emailError = (String) request.getAttribute("emailError"); %>
        <% String sdtError = (String) request.getAttribute("sdtError"); %>
        <% String diaChiError = (String) request.getAttribute("diaChiError"); %>

        <form action="<%= request.getContextPath() %>/register" method="post">
            <div class="row g-3">
                <div class="col-md-6">
                    <label for="username" class="form-label">Tên đăng nhập *</label>
                    <input type="text" class="form-control <%= usernameError != null ? "is-invalid" : "" %>" id="username" name="username"
                           value="<%= request.getAttribute("username") != null ? request.getAttribute("username") : "" %>"
                           maxlength="50" minlength="4" pattern="[A-Za-z0-9_]{4,50}"
                           title="Tên đăng nhập từ 4 đến 50 ký tự, chỉ gồm chữ, số và dấu gạch dưới"
                           required>
                    <% if (usernameError != null) { %>
                    <div class="invalid-feedback d-block"><%= usernameError %></div>
                    <% } %>
                </div>
                <div class="col-md-6">
                    <label for="hoTen" class="form-label">Họ tên *</label>
                    <input type="text" class="form-control <%= hoTenError != null ? "is-invalid" : "" %>" id="hoTen" name="hoTen"
                           value="<%= request.getAttribute("hoTen") != null ? request.getAttribute("hoTen") : "" %>"
                           maxlength="100" minlength="2"
                           title="Họ tên từ 2 đến 100 ký tự"
                           required>
                    <% if (hoTenError != null) { %>
                    <div class="invalid-feedback d-block"><%= hoTenError %></div>
                    <% } %>
                </div>

                <div class="col-md-6">
                    <label for="password" class="form-label">Mật khẩu *</label>
                    <input type="password" class="form-control <%= passwordError != null ? "is-invalid" : "" %>" id="password" name="password"
                           minlength="6" maxlength="50"
                           title="Mật khẩu từ 6 đến 50 ký tự"
                           required>
                    <% if (passwordError != null) { %>
                    <div class="invalid-feedback d-block"><%= passwordError %></div>
                    <% } %>
                </div>
                <div class="col-md-6">
                    <label for="confirmPassword" class="form-label">Xác nhận mật khẩu *</label>
                    <input type="password" class="form-control <%= confirmPasswordError != null ? "is-invalid" : "" %>" id="confirmPassword" name="confirmPassword"
                           minlength="6" maxlength="50"
                           title="Xác nhận mật khẩu phải giống mật khẩu"
                           required>
                    <% if (confirmPasswordError != null) { %>
                    <div class="invalid-feedback d-block"><%= confirmPasswordError %></div>
                    <% } %>
                </div>

                <div class="col-md-6">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" class="form-control <%= emailError != null ? "is-invalid" : "" %>" id="email" name="email"
                           value="<%= request.getAttribute("email") != null ? request.getAttribute("email") : "" %>"
                           maxlength="100">
                    <% if (emailError != null) { %>
                    <div class="invalid-feedback d-block"><%= emailError %></div>
                    <% } %>
                </div>
                <div class="col-md-6">
                    <label for="sdt" class="form-label">Số điện thoại *</label>
                    <input type="text" class="form-control <%= sdtError != null ? "is-invalid" : "" %>" id="sdt" name="sdt" maxlength="10"
                           inputmode="numeric" pattern="\d{10}"
                           oninput="this.value = this.value.replace(/\D/g, '').slice(0, 10);"
                           title="Số điện thoại phải gồm đúng 10 chữ số"
                           value="<%= request.getAttribute("sdt") != null ? request.getAttribute("sdt") : "" %>" required>
                    <% if (sdtError != null) { %>
                    <div class="invalid-feedback d-block"><%= sdtError %></div>
                    <% } %>
                </div>

                <div class="col-12">
                    <label for="diaChi" class="form-label">Địa chỉ</label>
                    <textarea class="form-control <%= diaChiError != null ? "is-invalid" : "" %>" id="diaChi" name="diaChi" rows="2" maxlength="200"><%= request.getAttribute("diaChi") != null ? request.getAttribute("diaChi") : "" %></textarea>
                    <% if (diaChiError != null) { %>
                    <div class="invalid-feedback d-block"><%= diaChiError %></div>
                    <% } %>
                </div>
            </div>

            <div class="d-grid mt-4 mb-2">
                <button type="submit" class="btn btn-primary">Đăng ký</button>
            </div>
        </form>

        <p class="text-center mb-0">
            Đã có tài khoản?
            <a href="<%= request.getContextPath() %>/login">Đăng nhập ngay</a>
        </p>
    </div>
</div>
</body>
</html>