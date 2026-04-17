<%--
  Created by IntelliJ IDEA.
  User: VICTUS
  Date: 4/16/2026
  Time: 7:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="view/header.jsp" %>
<style>
    .container-custom {
        max-width: 1200px;
        margin: auto;
    }

    .title-main {
        font-size: 48px;
        font-weight: bold;
    }

    .text-main {
        font-size: 36px;
    }

    .card img {
        height: 300px;
        object-fit: cover;
    }

    .section-box {
        padding: 40px;
    }

    .feature-box {
        padding: 40px;
        font-size: 36px;
    }

    .cta-box {
        padding: 60px;
        font-size: 36px;
    }
</style>

<div class="container container-custom py-5">

    <div class="text-center mb-5">
        <h1 class="title-main">Giới thiệu trung tâm đào tạo</h1>
        <p class="text-main text-muted">
            Trung tâm đào tạo cung cấp các chương trình học đa dạng,
            phù hợp với nhiều đối tượng học viên khác nhau.
        </p>
    </div>

    <div class="row justify-content-center mb-5">
        <div class="col-md-6">
            <div class="card shadow-lg border-0">

                <img src="${pageContext.request.contextPath}/images/gioithieu.jpg"
                     class="card-img-top">

                <div class="card-body text-center section-box">
                    <h4 class="fw-bold">Thông tin trung tâm</h4>
                    <p class="text-muted">
                        Xem các lớp học đang mở tại trung tâm
                    </p>
                    <a href="DSLop" class="btn btn-primary px-4">
                        Xem lớp học
                    </a>
                </div>

            </div>
        </div>
    </div>

    <h2 class="text-center fw-bold mb-4 title-main">Tại sao chọn chúng tôi?</h2>

    <div class="row text-center g-4 mb-5">

        <div class="col-md-4">
            <div class="shadow rounded bg-light h-100 feature-box">
                <h5 class="fw-bold">Chương trình đa dạng</h5>
                <p class="text-muted">Nhiều khóa học phù hợp với mọi đối tượng.</p>
            </div>
        </div>

        <div class="col-md-4">
            <div class="shadow rounded bg-light h-100 feature-box">
                <h5 class="fw-bold">Giảng viên chất lượng</h5>
                <p class="text-muted">Đội ngũ giàu kinh nghiệm.</p>
            </div>
        </div>

        <div class="col-md-4">
            <div class="shadow rounded bg-light h-100 feature-box">
                <h5 class="fw-bold">Môi trường học tập</h5>
                <p class="text-muted">Hiện đại và thân thiện.</p>
            </div>
        </div>

    </div>

    <div class="text-center bg-primary text-white rounded cta-box">
        <h2 class="fw-bold">Bắt đầu học ngay hôm nay!</h2>
        <p>Tham gia các lớp học để nâng cao kỹ năng của bạn</p>
        <a href="${pageContext.request.contextPath}/view/DangKy.jsp"
           class="btn btn-light mt-3 px-4">
            Đăng ký ngay
        </a>
    </div>

</div>

<%@ include file="view/footer.jsp" %>