<%--
  Created by IntelliJ IDEA.
  User: VICTUS
  Date: 4/16/2026
  Time: 6:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Quản lý trung tâm đào tạo</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
    <style>
        .header-custom {
            min-height: 200px;
            background: linear-gradient(135deg, #1e6be3, #4f8df5);
            padding: 20px 40px;
        }

        .logo {
            font-size: 48px;
            font-weight: bold;
            color: white;
        }

        .navbar-nav {
            gap: 40px;
        }

        .nav-link {
            font-size: 28px;
            color: white !important;
            font-weight: 500;
        }

        .nav-link:hover {
            color: #ffd700 !important;
        }

        .search-box {
            display: flex;
            align-items: center;
            margin-left: 40px;
        }

        .search-box input {
            height: 60px;
            width: 300px;
            border-radius: 30px 0 0 30px;
            font-size: 18px;
            padding-left: 20px;
        }

        .search-box button {
            height: 60px;
            font-size: 18px;
            padding: 0 25px;
            border-radius: 0 30px 30px 0;
        }

        .login-icon i {
            font-size: 40px;
            color: white;
            margin-left: 20px;
            transition: 0.3s;
        }

        .login-icon i:hover {
            color: #ffd700;
            transform: scale(1.2);
        }
    </style>
</head>

<body class="d-flex flex-column min-vh-100">
<nav class="navbar navbar-expand-lg header-custom">
    <div class="container-fluid">

        <a class="navbar-brand logo" href="${pageContext.request.contextPath}/index.jsp">
            TRUNG TÂM ĐÀO TẠO
        </a>

        <button class="navbar-toggler bg-light" type="button" data-bs-toggle="collapse" data-bs-target="#menu">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse justify-content-center" id="menu">

            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/index.jsp">Trang chủ</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/DSLop">Lớp học</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Lịch khai giảng</a>
                </li>
            </ul>

            <form class="d-flex search-box">
                <input class="form-control" type="search" placeholder="Tìm kiếm...">
                <button class="btn btn-light">Tìm</button>
            </form>

        </div>

        <div class="login-icon">
            <a href="login-hocvien.jsp"><i class="bi bi-person-circle"></i></a>
            <a href="login-admin.jsp"><i class="bi bi-person-gear"></i></a>
        </div>

    </div>
</nav>