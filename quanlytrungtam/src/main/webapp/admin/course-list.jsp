<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý Khóa học</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <style>
        body {
            background: #f5f6fa;
        }

        .sidebar {
            min-height: 100vh;
            background: #1e2a3a;
        }

        .sidebar .nav-link {
            color: #adb5bd;
        }

        .sidebar .nav-link:hover, .sidebar .nav-link.active {
            color: #fff;
            background: #0d6efd22;
        }

        .card-stat {
            border-left: 4px solid #0d6efd;
        }

        .table-img {
            width: 60px;
            height: 45px;
            object-fit: cover;
            border-radius: 4px;
            display: block;
        }

        .form-panel {
            background: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 8px #0001;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <div class="row">

        <%-- SIDEBAR --%>
        <nav class="col-md-2 sidebar py-3 d-none d-md-block">
            <div class="text-white fw-bold fs-5 mb-4 px-3">
                <i class="bi bi-mortarboard-fill me-2"></i>Training
            </div>
            <ul class="nav flex-column">
                <li class="nav-item">
                    <a class="nav-link active" href="${pageContext.request.contextPath}/admin/courses">
                        <i class="bi bi-journals me-2"></i>Khóa học
                    </a>
                </li>
            </ul>
        </nav>

        <%-- MAIN --%>
        <main class="col-md-10 ms-sm-auto py-4 px-4">

            <%-- Breadcrumb --%>
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="#">Trang chủ</a></li>
                    <li class="breadcrumb-item active">Quản lý Khóa học</li>
                </ol>
            </nav>

            <%-- Alert --%>
            <c:if test="${not empty successMsg}">
                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    <i class="bi bi-check-circle me-2"></i>${successMsg}
                    <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                </div>
            </c:if>
            <c:if test="${not empty errorMsg}">
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    <i class="bi bi-exclamation-triangle me-2"></i>${errorMsg}
                    <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                </div>
            </c:if>

            <%-- Stat card --%>
            <div class="row mb-4">
                <div class="col-md-3">
                    <div class="card card-stat shadow-sm">
                        <div class="card-body">
                            <div class="text-muted small">Tổng khóa học</div>
                            <div class="fs-3 fw-bold text-primary">${totalCourse}</div>
                        </div>
                    </div>
                </div>
            </div>

            <%-- Form Add/Edit --%>
            <div class="form-panel p-4 mb-4">
                <h5 class="mb-3">
                    <i class="bi bi-${editMode ? 'pencil-square' : 'plus-circle'} me-2 text-primary"></i>
                    ${editMode ? 'Cập nhật khóa học' : 'Thêm khóa học mới'}
                </h5>

                <form action="${pageContext.request.contextPath}/admin/courses" method="post">
                    <input type="hidden" name="action" value="${editMode ? 'update' : 'insert'}">
                    <c:if test="${editMode}">
                        <input type="hidden" name="courseId" value="${course.courseId}">
                    </c:if>

                    <div class="row g-3">
                        <div class="col-md-4">
                            <label class="form-label fw-semibold">Tên khóa học <span
                                    class="text-danger">*</span></label>
                            <input type="text" name="courseName" class="form-control"
                                   placeholder="VD: Java Web Development"
                                   value="${course.courseName}" required>
                        </div>
                        <div class="col-md-2">
                            <label class="form-label fw-semibold">Học phí (VNĐ) <span
                                    class="text-danger">*</span></label>
                            <input type="number" name="tuition" class="form-control" min="0" step="100000"
                                   placeholder="5.000.000"
                                   value="${course.tuition}" required>
                        </div>
                        <div class="col-md-3">
                            <label class="form-label fw-semibold">Tên file ảnh</label>
                            <input type="text" name="image" class="form-control"
                                   placeholder="VD: java-web.jpg"
                                   value="${course.image}">
                        </div>
                        <div class="col-md-3">
                            <label class="form-label fw-semibold">Mô tả</label>
                            <input type="text" name="description" class="form-control"
                                   placeholder="Mô tả ngắn về khóa học"
                                   value="${course.description}">
                        </div>
                    </div>

                    <div class="mt-3">
                        <button type="submit" class="btn btn-${editMode ? 'warning' : 'primary'}">
                            <i class="bi bi-${editMode ? 'save' : 'plus-lg'} me-1"></i>
                            ${editMode ? 'Cập nhật' : 'Thêm mới'}
                        </button>
                        <c:if test="${editMode}">
                            <a href="${pageContext.request.contextPath}/admin/courses"
                               class="btn btn-secondary ms-2">
                                <i class="bi bi-x-lg me-1"></i>Hủy
                            </a>
                        </c:if>
                    </div>
                </form>
            </div>

            <%-- Search + Table --%>
            <div class="card shadow-sm">
                <div class="card-header d-flex justify-content-between align-items-center">
                    <h6 class="mb-0"><i class="bi bi-table me-2"></i>Danh sách khóa học</h6>
                    <form class="d-flex gap-2"
                          action="${pageContext.request.contextPath}/admin/courses" method="get">
                        <input type="hidden" name="action" value="search">
                        <input type="search" name="keyword" class="form-control form-control-sm"
                               style="width:220px" placeholder="Tìm tên khóa học..."
                               value="${keyword}">
                        <button class="btn btn-sm btn-outline-primary">
                            <i class="bi bi-search"></i>
                        </button>
                        <c:if test="${not empty keyword}">
                            <a href="${pageContext.request.contextPath}/admin/courses"
                               class="btn btn-sm btn-outline-secondary">Xóa lọc</a>
                        </c:if>
                    </form>
                </div>

                <div class="card-body p-0">
                    <div class="table-responsive">
                        <table class="table table-hover table-striped align-middle mb-0">
                            <thead class="table-dark">
                            <tr>
                                <th>#</th>
                                <th>Ảnh</th>
                                <th>Tên khóa học</th>
                                <th>Mô tả</th>
                                <th class="text-end">Học phí</th>
                                <th class="text-center">Thao tác</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:choose>
                                <c:when test="${empty courses}">
                                    <tr>
                                        <td colspan="6" class="text-center py-4 text-muted">
                                            <i class="bi bi-inbox fs-3 d-block mb-2"></i>
                                            Không có khóa học nào.
                                        </td>
                                    </tr>
                                </c:when>
                                <c:otherwise>
                                    <c:forEach items="${courses}" var="c" varStatus="st">
                                        <tr>
                                            <td>${st.count}</td>
                                            <td>
                                                <img src="${pageContext.request.contextPath}/view/${empty c.image ? 'fullstackweb.jpg' : c.image}"
                                                     alt="${c.courseName}" class="table-img"
                                                     onerror="this.src='${pageContext.request.contextPath}/view/fullstackweb.jpg'">
                                            </td>
                                            <td class="fw-semibold">${c.courseName}</td>
                                            <td class="text-muted" style="max-width:200px">
                        <span class="d-inline-block text-truncate" style="max-width:180px"
                              title="${c.description}">${c.description}</span>
                                            </td>
                                            <td class="text-end">
                                                <fmt:formatNumber value="${c.tuition}" type="number"
                                                                  groupingUsed="true"/>đ
                                            </td>
                                            <td class="text-center">
                                                <a href="${pageContext.request.contextPath}/admin/courses?action=edit&id=${c.courseId}"
                                                   class="btn btn-sm btn-outline-warning me-1"
                                                   title="Sửa">
                                                    <i class="bi bi-pencil"></i>
                                                </a>
                                                <button class="btn btn-sm btn-outline-danger"
                                                        title="Xóa"
                                                        onclick="confirmDelete(${c.courseId}, '${c.courseName}')">
                                                    <i class="bi bi-trash"></i>
                                                </button>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>
                            </tbody>
                        </table>
                    </div>
                </div>

                <div class="card-footer text-muted small">
                    Hiển thị ${empty courses ? 0 : courses.size()} khóa học
                    <c:if test="${not empty keyword}"> — kết quả tìm kiếm cho "<strong>${keyword}</strong>"</c:if>
                </div>
            </div>

        </main>
    </div>
</div>

<%-- Modal Confirm to delete --%>
<div class="modal fade" id="deleteModal" tabindex="-1">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title text-danger">
                    <i class="bi bi-exclamation-triangle me-2"></i>Xác nhận xóa
                </h5>
                <button class="btn-close" data-bs-dismiss="modal"></button>
            </div>
            <div class="modal-body">
                Bạn có chắc muốn xóa khóa học <strong id="deleteName"></strong>?
                <br><small class="text-danger">Hành động này không thể hoàn tác.</small>
            </div>
            <div class="modal-footer">
                <button class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                <a id="deleteLink" href="#" class="btn btn-danger">
                    <i class="bi bi-trash me-1"></i>Xóa
                </a>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
    function confirmDelete(id, name) {
        document.getElementById('deleteName').textContent = name;
        document.getElementById('deleteLink').href =
            '${pageContext.request.contextPath}/admin/courses?action=delete&id=' + id;
        new bootstrap.Modal(document.getElementById('deleteModal')).show();
    }
</script>
</body>
</html>