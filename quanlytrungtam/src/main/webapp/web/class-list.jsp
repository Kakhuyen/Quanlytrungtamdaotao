<%--
  Created by IntelliJ IDEA.
  User: VICTUS
  Date: 4/16/2026
  Time: 6:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%--
  Danh sách lớp học học viên
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/header.jsp" %>

<div class="container py-5">

    <h2 class="text-center fw-bold mb-4 text-primary">
        Danh sách lớp học
    </h2>

    <div class="row justify-content-center mb-4">
        <div class="col-md-6">

            <form action="${pageContext.request.contextPath}/web/class-list"
                  method="get"
                  class="d-flex">

                <input type="text"
                       name="keyword"
                       class="form-control me-2"
                       placeholder="Tìm tên lớp học..."
                       value="${keyword}">

                <button class="btn btn-primary">
                    Tìm
                </button>

            </form>

        </div>
    </div>

    <c:if test="${not empty msg}">
        <div class="alert alert-warning text-center">
                ${msg}
        </div>
    </c:if>

    <div class="table-responsive">

        <table class="table table-bordered table-hover text-center shadow">

            <thead class="table-primary">
            <tr>
                <th>Mã lớp</th>
                <th>Tên lớp</th>
                <th>Khóa học</th>
                <th>Sĩ số</th>
                <th>Khai giảng</th>
            </tr>
            </thead>

            <tbody>

            <c:choose>

                <c:when test="${not empty dsLop}">

                    <c:forEach var="lop" items="${dsLop}">

                        <tr>
                            <td>${lop.maLH}</td>
                            <td>${lop.tenLH}</td>
                            <td>${lop.tenKH}</td>
                            <td>${lop.siSoMax}</td>
                            <td>${lop.ngayKhaiGiang}</td>

                        </tr>

                    </c:forEach>

                </c:when>

                <c:otherwise>

                    <tr>
                        <td colspan="6" class="text-danger fw-bold">
                            Không có dữ liệu lớp học trong database
                        </td>
                    </tr>

                </c:otherwise>

            </c:choose>

            </tbody>

        </table>

    </div>

</div>

<%@ include file="/footer.jsp" %>