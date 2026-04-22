<%--
  Created by IntelliJ IDEA.
  User: VICTUS
  Date: 4/19/2026
  Time: 4:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Classroom" %>

<%
    List<Classroom> ds =
            (List<Classroom>) request.getAttribute("dsLop");

    Classroom lop =
            (Classroom) request.getAttribute("lop");

    if (lop == null) {
        lop = new Classroom();
    }

    String msg =
            request.getAttribute("msg") == null
                    ? ""
                    : request.getAttribute("msg").toString();

    String keyword =
            request.getAttribute("keyword") == null
                    ? ""
                    : request.getAttribute("keyword").toString();
%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Quản lý lớp học</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body{
            background:#f4f6f9;
        }

        .box{
            background:#ffffff;
            border-radius:16px;
            box-shadow:0 10px 25px rgba(0,0,0,.08);
        }

        .title{
            color:#0d6efd;
            font-weight:700;
        }

        .table td, .table th{
            vertical-align:middle;
        }
    </style>
</head>
<body>

<div class="container py-5">

    <div class="d-flex justify-content-between align-items-center mb-4">

        <h2 class="title m-0">Quản lý lớp học</h2>

        <a href="${pageContext.request.contextPath}/admin/dashboard.jsp"
           class="btn btn-secondary">
            Quay lại
        </a>

    </div>

    <% if(!msg.isEmpty()){ %>
    <div class="alert alert-warning">
        <%= msg %>
    </div>
    <% } %>

    <div class="row g-4">

        <div class="col-lg-4">

            <div class="box p-4">

                <h4 class="fw-bold mb-3">
                    <%= lop.getMaLH() > 0 ? "Cập nhật lớp học" : "Thêm lớp học" %>
                </h4>

                <form action="${pageContext.request.contextPath}/admin/class"
                      method="post">

                    <input type="hidden"
                           name="maLH"
                           value="<%= lop.getMaLH() %>">

                    <div class="mb-3">
                        <label class="form-label">Mã khóa học</label>
                        <input type="number"
                               name="maKH"
                               class="form-control"
                               min="1"
                               value="<%= lop.getMaKH() %>"
                               required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Mã trung tâm</label>
                        <input type="number"
                               name="maTT"
                               class="form-control"
                               min="1"
                               value="<%= lop.getMaTT() %>"
                               required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Tên lớp học</label>
                        <input type="text"
                               name="tenLH"
                               class="form-control"
                               maxlength="100"
                               value="<%= lop.getTenLH() == null ? "" : lop.getTenLH() %>"
                               required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Sĩ số tối đa</label>
                        <input type="number"
                               name="siSoMax"
                               class="form-control"
                               min="1"
                               max="30"
                               value="<%= lop.getSiSoMax() == 0 ? 30 : lop.getSiSoMax() %>"
                               required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Ngày khai giảng</label>
                        <input type="date"
                               name="ngayKhaiGiang"
                               class="form-control"
                               value="<%= lop.getNgayKhaiGiang() == null ? "" : lop.getNgayKhaiGiang() %>"
                               required>
                    </div>

                    <% if(lop.getMaLH() > 0){ %>

                    <button type="submit"
                            name="action"
                            value="update"
                            class="btn btn-primary w-100 mb-2">
                        Cập nhật
                    </button>

                    <a href="${pageContext.request.contextPath}/admin/class"
                       class="btn btn-secondary w-100">
                        Thêm mới
                    </a>

                    <% } else { %>

                    <button type="submit"
                            name="action"
                            value="add"
                            class="btn btn-success w-100">
                        Thêm lớp học
                    </button>

                    <% } %>

                </form>

            </div>

        </div>

        <div class="col-lg-8">

            <div class="box p-4">

                <div class="d-flex justify-content-between align-items-center mb-3">

                    <h4 class="fw-bold m-0">Danh sách lớp học</h4>

                    <form action="${pageContext.request.contextPath}/admin/class"
                          method="get"
                          class="d-flex">

                        <input type="text"
                               name="keyword"
                               class="form-control me-2"
                               placeholder="Tìm tên lớp"
                               value="<%= keyword %>">

                        <button class="btn btn-primary">
                            Tìm
                        </button>

                    </form>

                </div>

                <div class="table-responsive">

                    <table class="table table-bordered table-hover text-center">

                        <thead class="table-primary">
                        <tr>
                            <th>Mã</th>
                            <th>Tên lớp</th>
                            <th>Mã KH</th>
                            <th>Mã TT</th>
                            <th>Sĩ số</th>
                            <th>Khai giảng</th>
                            <th width="180">Thao tác</th>
                        </tr>
                        </thead>

                        <tbody>

                        <%
                            if(ds != null && !ds.isEmpty()){
                                for(Classroom c : ds){
                        %>

                        <tr>
                            <td><%= c.getMaLH() %></td>
                            <td><%= c.getTenLH() %></td>
                            <td><%= c.getMaKH() %></td>
                            <td><%= c.getMaTT() %></td>
                            <td><%= c.getSiSoMax() %></td>
                            <td><%= c.getNgayKhaiGiang() %></td>

                            <td>

                                <a href="${pageContext.request.contextPath}/admin/class?action=edit&id=<%= c.getMaLH() %>"
                                   class="btn btn-warning btn-sm">
                                    Sửa
                                </a>

                                <a href="${pageContext.request.contextPath}/admin/class?action=delete&id=<%= c.getMaLH() %>"
                                   class="btn btn-danger btn-sm"
                                   onclick="return confirm('Bạn chắc chắn muốn xóa?')">
                                    Xóa
                                </a>

                            </td>
                        </tr>

                        <%
                            }
                        } else {
                        %>

                        <tr>
                            <td colspan="7" class="text-danger fw-bold">
                                Không có dữ liệu lớp học trong database
                            </td>
                        </tr>

                        <% } %>

                        </tbody>

                    </table>

                </div>

            </div>

        </div>

    </div>

</div>

</body>
</html>