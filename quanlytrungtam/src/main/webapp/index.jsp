<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%
    response.sendRedirect(request.getContextPath() + "/admin/courses");
<%
    response.sendRedirect(request.getContextPath() + "/login");
%>
<%--
  Created by IntelliJ IDEA.
  User: VICTUS
  Date: 4/16/2026
  Time: 7:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.quanly.model.NguoiDung" %>
<%@ include file="/header.jsp" %>

<%
    NguoiDung user = (NguoiDung) session.getAttribute("user");
%>
<style>
    .hero-section {
        background: linear-gradient(135deg, #0d6efd, #4a90ff);
        color: white;
        padding: 80px 30px;
        border-radius: 24px;
        text-align: center;
        box-shadow: 0 10px 30px rgba(0,0,0,0.10);
    }
    .hero-title {
        font-size: 52px;
        font-weight: 800;
        margin-bottom: 18px;
    }

    .hero-text {
        font-size: 22px;
        max-width: 850px;
        margin: auto;
        opacity: 0.95;
    }

    .section-title {
        font-size: 38px;
        font-weight: 800;
        margin-bottom: 30px;
    }

    .intro-card img {
        height: 340px;
        object-fit: cover;
    }

    .feature-card {
        border: none;
        border-radius: 18px;
        padding: 30px 25px;
        box-shadow: 0 8px 20px rgba(0,0,0,0.08);
        transition: 0.3s;
        height: 100%;
    }

    .feature-card:hover {
        transform: translateY(-6px);
    }

    .feature-icon {
        font-size: 42px;
        color: #0d6efd;
        margin-bottom: 16px;
    }

    .cta-box {
        background: linear-gradient(135deg, #0d6efd, #3e8cff);
        color: white;
        padding: 60px 25px;
        border-radius: 24px;
        box-shadow: 0 10px 30px rgba(0,0,0,0.10);
    }

</style>

<div class="container py-5">

    <div class="hero-section mb-5">

        <h1 class="hero-title">
            Giới thiệu trung tâm đào tạo
        </h1>

        <p class="hero-text">
            Chúng tôi cung cấp nhiều chương trình học chất lượng,
            phù hợp cho sinh viên, người đi làm và mọi đối tượng muốn nâng cao kỹ năng.
        </p>
    </div>

    <div class="row justify-content-center mb-5">

        <div class="col-lg-8">

            <div class="card intro-card border-0 shadow rounded-4 overflow-hidden">

                <img src="${pageContext.request.contextPath}/images/gioithieu.jpg"
                     alt="Trung tâm đào tạo">

                <div class="card-body text-center p-4">

                    <h3 class="fw-bold mb-3">
                        Thông tin trung tâm
                    </h3>

                    <p class="text-muted fs-5">
                        Không gian học tập hiện đại, chương trình cập nhật thực tế,
                        giảng viên tận tâm đồng hành cùng học viên.
                    </p>

                </div>

            </div>

        </div>

    </div>

    <h2 class="text-center section-title">
        Tại sao chọn chúng tôi?
    </h2>

    <div class="row g-4 mb-5">

        <div class="col-md-4">

            <div class="feature-card text-center bg-white">

                <div class="feature-icon">
                    <i class="bi bi-journal-bookmark-fill"></i>
                </div>

                <h4 class="fw-bold">
                    Chương trình đa dạng
                </h4>

                <p class="text-muted mb-0">
                    Nhiều khóa học từ cơ bản đến nâng cao phù hợp mọi nhu cầu.
                </p>

            </div>

        </div>

        <div class="col-md-4">

            <div class="feature-card text-center bg-white">

                <div class="feature-icon">
                    <i class="bi bi-person-workspace"></i>
                </div>

                <h4 class="fw-bold">
                    Giảng viên chất lượng
                </h4>

                <p class="text-muted mb-0">
                    Đội ngũ giàu kinh nghiệm thực chiến và hỗ trợ tận tình.
                </p>

            </div>

        </div>

        <div class="col-md-4">

            <div class="feature-card text-center bg-white">

                <div class="feature-icon">
                    <i class="bi bi-building-check"></i>
                </div>

                <h4 class="fw-bold">
                    Môi trường hiện đại
                </h4>

                <p class="text-muted mb-0">
                    Cơ sở vật chất tốt, thân thiện và tạo động lực học tập.
                </p>

            </div>

        </div>

    </div>

    <div class="cta-box text-center">

        <h2 class="fw-bold mb-3">
            Bắt đầu học ngay hôm nay!
        </h2>

        <p class="fs-5 mb-4">
            Tham gia các lớp học để nâng cao kỹ năng và mở rộng cơ hội tương lai.
        </p>

        <%
            if (user == null) {
        %>

        <a href="<%= request.getContextPath() %>/login"
           class="btn btn-light btn-lg fw-bold px-4">
            Đăng nhập để đăng ký
        </a>

        <%
        } else if (user.getVaiTro() != null &&
                user.getVaiTro().getMaVaiTro() == 1) {
        %>

        <a href="<%= request.getContextPath() %>/admin/dashboard.jsp"
           class="btn btn-light btn-lg fw-bold px-4">
            Vào quản trị
        </a>

        <%
        } else {
        %>
        <a href="<%= request.getContextPath() %>/hocvien/enroll"
           class="btn btn-light btn-lg fw-bold px-4">
            Đăng ký lớp học
        </a>
        <%
            }
        %>
    </div>
</div>
<%@ include file="/footer.jsp" %>
