<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Khóa học của chúng tôi</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <style>
        .course-card {
            transition: transform .2s, box-shadow .2s;
        }

        .course-card:hover {
            transform: translateY(-4px);
            box-shadow: 0 8px 24px #0002;
        }

        .course-img {
            height: 180px;
            object-fit: cover;
        }

        .badge-tuition {
            background: #0d6efd22;
            color: #0d6efd;
            font-weight: 600;
        }
    </style>
</head>
<body class="bg-light">

<%-- Navbar --%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand fw-bold" href="#">
            <i class="bi bi-mortarboard-fill me-2"></i>Training Center
        </a>
    </div>
</nav>

<%-- Hero --%>
<div class="bg-primary text-white py-5 mb-4">
    <div class="container text-center">
        <h1 class="fw-bold">Khóa học của chúng tôi</h1>
        <p class="lead">Nâng cao kỹ năng – Phát triển sự nghiệp</p>
        <%-- Search --%>
        <form class="d-flex justify-content-center mt-3 gap-2"
              action="${pageContext.request.contextPath}/admin/courses" method="get">
            <input type="hidden" name="action" value="search">
            <input type="search" name="keyword" class="form-control" style="max-width:360px"
                   placeholder="Tìm khóa học..." value="${keyword}">
            <button class="btn btn-light text-primary fw-semibold">
                <i class="bi bi-search me-1"></i>Tìm
            </button>
        </form>
    </div>
</div>

<div class="container pb-5">
    <c:if test="${not empty keyword}">
        <p class="text-muted mb-3">
            Kết quả cho "<strong>${keyword}</strong>" — ${courses.size()} khóa học
            <a href="${pageContext.request.contextPath}/admin/courses" class="ms-2 btn btn-sm btn-outline-secondary">
                Xem tất cả
            </a>
        </p>
    </c:if>

    <div class="row row-cols-1 row-cols-md-3 g-4">
        <c:choose>
            <c:when test="${empty courses}">
                <div class="col-12 text-center py-5 text-muted">
                    <i class="bi bi-search fs-1 d-block mb-3"></i>
                    Không tìm thấy khóa học phù hợp.
                </div>
            </c:when>

            <c:otherwise>
                <c:forEach items="${courses}" var="c">
                    <div class="col">
                        <div class="card course-card h-100 border-0 shadow-sm">
                            <img src="${pageContext.request.contextPath}/view/${empty c.image ? 'fullstackweb.jpg' : c.image}"
                                 class="card-img-top course-img"
                                 alt="${c.courseName}"
                                 onerror="this.src='${pageContext.request.contextPath}/view/fullstackweb.jpg'">
                            <div class="card-body d-flex flex-column">
                                <h5 class="card-title fw-bold">${c.courseName}</h5>
                                <p class="card-text text-muted flex-grow-1">${c.description}</p>
                                <div class="d-flex justify-content-between align-items-center mt-3">
                                    <span class="badge badge-tuition fs-6 px-3 py-2 rounded-pill">
                                        <fmt:formatNumber value="${c.tuition}" type="number"/>đ
                                    </span>
                                    <a href="${pageContext.request.contextPath}/dangky" class="btn btn-primary btn-sm">
                                        <i class="bi bi-arrow-right me-1"></i>Đăng ký
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </div>
</div>
<footer class="bg-dark text-white text-center py-3 mt-4">
    <small>&copy; 2024 Training Center. All rights reserved.</small>
</footer>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>