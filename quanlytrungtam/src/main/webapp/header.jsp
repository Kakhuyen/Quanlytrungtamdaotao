<%--
  Created by IntelliJ IDEA.
  User: VICTUS
  Date: 4/16/2026
  Time: 6:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Quản lý trung tâm đào tạo</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">

    <style>

        body {
            font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
        }

        .header-custom {
            background: linear-gradient(135deg, #0d6efd, #3f8cff);
            padding: 16px 0;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
        }

        .logo-text {
            font-size: 30px;
            font-weight: 800;
            color: #ffffff !important;
            letter-spacing: 1px;
        }

        .navbar-nav .nav-link {
            color: #ffffff !important;
            font-size: 18px;
            font-weight: 600;
            margin: 0 10px;
            transition: 0.3s;
        }

        .navbar-nav .nav-link:hover {
            color: #ffe082 !important;
            transform: translateY(-1px);
        }

        .search-box input {
            border-radius: 30px 0 0 30px;
            min-width: 220px;
            border: none;
            padding-left: 16px;
        }

        .search-box button {
            border-radius: 0 30px 30px 0;
            font-weight: 600;
            padding: 0 18px;
        }

        .login-group a {
            color: #ffffff;
            font-size: 28px;
            margin-left: 14px;
            transition: 0.3s;
        }

        .login-group a:hover {
            color: #ffe082;
            transform: scale(1.08);
        }

        @media (max-width: 991px) {

            .navbar-nav {
                margin-top: 15px;
                text-align: center;
            }

            .search-box {
                margin-top: 15px;
                justify-content: center;
            }

            .login-group {
                margin-top: 15px;
                text-align: center;
                width: 100%;
            }
        }

    </style>

</head>

<body class="d-flex flex-column min-vh-100">

<nav class="navbar navbar-expand-lg header-custom">

    <div class="container">

        <a class="navbar-brand logo-text"
           href="${pageContext.request.contextPath}/index.jsp">
            TRUNG TÂM ĐÀO TẠO
        </a>

        <button class="navbar-toggler bg-light"
                type="button"
                data-bs-toggle="collapse"
                data-bs-target="#menuHeader">

            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="menuHeader">

            <ul class="navbar-nav mx-auto">

                <li class="nav-item">
                    <a class="nav-link"
                       href="${pageContext.request.contextPath}/index.jsp">
                        Trang chủ
                    </a>
                </li>

                <li class="nav-item">
                    <a class="nav-link"
                       href="${pageContext.request.contextPath}/hocvien/class-list">
                        Lớp học
                    </a>
                </li>

                <li class="nav-item">
                    <a class="nav-link"
                       href="#">
                        Lịch khai giảng
                    </a>
                </li>

            </ul>

            <form class="d-flex search-box me-3">

                <input type="search"
                       class="form-control"
                       placeholder="Tìm kiếm...">

                <button type="submit"
                        class="btn btn-light">
                    Tìm
                </button>

            </form>
            <div class="login-group">

                <a href="${pageContext.request.contextPath}/login"
                   title="Đăng nhập">
                    <i class="bi bi-person-circle"></i>
                </a>
            </div>

        </div>

    </div>
</nav>