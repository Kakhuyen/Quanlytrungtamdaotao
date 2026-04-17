<%--
  Created by IntelliJ IDEA.
  User: VICTUS
  Date: 4/16/2026
  Time: 6:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container py-5 flex-grow-1">

    <h2 class="text-center mb-4 fw-bold">Danh sách lớp học</h2>

    <div class="table-responsive">
        <table class="table table-bordered table-hover text-center align-middle">

            <thead class="table-primary">
            <tr>
                <th>Mã</th>
                <th>Tên lớp</th>
                <th>Sĩ số</th>
                <th>Khai giảng</th>
                <th>Khóa học</th>
                <th>Đăng ký</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="lop" items="${dsLop}">
                <tr>
                    <td>${lop.maLH}</td>
                    <td>${lop.tenLH}</td>
                    <td>${lop.siSoMax}</td>
                    <td>
                        <fmt:formatDate value="${lop.ngayKhaiGiang}" pattern="dd/MM/yyyy"/>
                    </td>
                    <td>${lop.tenKH}</td>
                    <td>
                        <form action="${pageContext.request.contextPath}/dangky" method="post">
                            <input type="hidden" name="maLH" value="${lop.maLH}">
                            <button class="btn btn-success">
                                Đăng ký
                            </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>

        </table>
    </div>

</div>

<%@ include file="/view/footer.jsp" %>