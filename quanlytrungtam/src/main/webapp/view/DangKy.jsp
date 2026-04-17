<%--
  Created by IntelliJ IDEA.
  User: VICTUS
  Date: 4/17/2026
  Time: 3:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container mt-5">

    <div class="row justify-content-center">
        <div class="col-md-7">

            <div class="card shadow-lg border-0">
                <div class="card-header bg-primary text-white text-center">
                    <h4>Đăng ký lớp học</h4>
                </div>

                <div class="card-body">

                    <form action="${pageContext.request.contextPath}/dangky" method="post">

                        <div class="mb-3">
                            <label class="form-label fw-bold">Chọn lớp học</label>

                            <select name="maLH" class="form-select" required>
                                <option value="">-- Chọn lớp --</option>

                                <c:forEach var="lop" items="${dsLop}">
                                    <option value="${lop.maLH}">
                                            ${lop.tenLH} | ${lop.tenKH}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="mb-3">
                            <label class="form-label fw-bold">Họ tên</label>
                            <input type="text" name="hoTen" class="form-control" required>
                        </div>

                        <div class="mb-3">
                            <label class="form-label fw-bold">Email</label>
                            <input type="email" name="email" class="form-control">
                        </div>

                        <div class="mb-3">
                            <label class="form-label fw-bold">Số điện thoại</label>
                            <input type="text" name="sdt" class="form-control" required>
                        </div>

                        <div class="mb-3">
                            <label class="form-label fw-bold">Địa chỉ</label>
                            <input type="text" name="diaChi" class="form-control">
                        </div>

                        <button type="submit" class="btn btn-success w-100 fw-bold">
                            Xác nhận đăng ký
                        </button>

                    </form>

                </div>
            </div>

        </div>
    </div>

</div>

<%@ include file="footer.jsp" %>