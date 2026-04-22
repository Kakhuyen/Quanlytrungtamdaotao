<%--
  Created by IntelliJ IDEA.
  User: VICTUS
  Date: 4/17/2026
  Time: 3:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/header.jsp" %>

<div class="container py-5">

    <div class="row justify-content-center">
        <div class="col-lg-8 col-md-10">

            <div class="card shadow border-0">

                <div class="card-header bg-primary text-white text-center">
                    <h3 class="mb-0">Đăng ký lớp học</h3>
                </div>

                <div class="card-body p-4">

                    <c:if test="${not empty error}">
                        <div class="alert alert-danger">
                                ${error}
                        </div>
                    </c:if>

                    <c:if test="${not empty success}">
                        <div class="alert alert-success">
                                ${success}
                        </div>
                    </c:if>

                    <form method="post"
                          action="${pageContext.request.contextPath}/hocvien/enroll">

                        <div class="mb-3">
                            <label class="form-label fw-bold">
                                Chọn lớp học
                            </label>

                            <select name="maLH"
                                    class="form-select"
                                    required>

                                <option value="">
                                    -- Chọn lớp học --
                                </option>

                                <c:forEach var="lop" items="${dsLop}">
                                    <option value="${lop.maLH}">
                                            ${lop.tenLH} - ${lop.tenKH} - ${lop.ngayKhaiGiang}
                                    </option>
                                </c:forEach>

                            </select>
                        </div>

                        <div class="mb-3">
                            <label class="form-label fw-bold">
                                Họ tên
                            </label>

                            <input type="text"
                                   name="hoTen"
                                   class="form-control"
                                   maxlength="100"
                                   value="${param.hoTen}"
                                   required>
                        </div>

                        <div class="mb-3">
                            <label class="form-label fw-bold">
                                Email
                            </label>

                            <input type="email"
                                   name="email"
                                   class="form-control"
                                   maxlength="100"
                                   value="${param.email}"
                                   required>
                        </div>

                        <div class="mb-3">
                            <label class="form-label fw-bold">
                                Số điện thoại
                            </label>

                            <input type="text"
                                   name="sdt"
                                   class="form-control"
                                   maxlength="10"
                                   value="${param.sdt}"
                                   required>
                        </div>

                        <div class="mb-3">
                            <label class="form-label fw-bold">
                                Địa chỉ
                            </label>

                            <input type="text"
                                   name="diaChi"
                                   class="form-control"
                                   maxlength="200"
                                   value="${param.diaChi}"
                                   required>
                        </div>

                        <button type="submit"
                                class="btn btn-success w-100">
                            Xác nhận đăng ký
                        </button>

                    </form>

                </div>

            </div>

        </div>
    </div>

</div>

<%@ include file="/footer.jsp" %>