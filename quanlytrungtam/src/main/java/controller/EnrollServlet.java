package controller;

import dao.ClassDAO;
import dao.EnrollDAO;
import model.NguoiDung;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/web/enroll")
public class EnrollServlet extends HttpServlet {

    private final ClassDAO classDAO = new ClassDAO();
    private final EnrollDAO enrollDAO = new EnrollDAO();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        try {

            request.setAttribute(
                    "dsLop",
                    classDAO.getAll()
            );

        } catch (Exception e) {

            request.setAttribute(
                    "error",
                    "Không thể tải danh sách lớp học."
            );
        }

        request.getRequestDispatcher("/web/enroll.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session =
                request.getSession(false);

        if (session == null ||
                session.getAttribute("user") == null) {

            response.sendRedirect(
                    request.getContextPath() + "/login"
            );
            return;
        }

        try {

            NguoiDung user =
                    (NguoiDung) session.getAttribute("user");

            int maND = user.getMaND();

            String maLHRaw =
                    request.getParameter("maLH");

            String hoTen =
                    safe(request.getParameter("hoTen"));

            String email =
                    safe(request.getParameter("email"));

            String sdt =
                    safe(request.getParameter("sdt"));

            String diaChi =
                    safe(request.getParameter("diaChi"));

            if (maLHRaw.isEmpty()) {

                request.setAttribute(
                        "error",
                        "Vui lòng chọn lớp học."
                );

                reload(request, response);
                return;
            }

            int maLH =
                    Integer.parseInt(maLHRaw);

            if (maLH <= 0) {

                request.setAttribute(
                        "error",
                        "Mã lớp không hợp lệ."
                );

                reload(request, response);
                return;
            }

            if (hoTen.isEmpty() ||
                    email.isEmpty() ||
                    sdt.isEmpty() ||
                    diaChi.isEmpty()) {

                request.setAttribute(
                        "error",
                        "Vui lòng nhập đầy đủ thông tin."
                );

                reload(request, response);
                return;
            }

            if (!email.matches(
                    "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {

                request.setAttribute(
                        "error",
                        "Email không hợp lệ."
                );

                reload(request, response);
                return;
            }

            if (!sdt.matches("^0\\d{9}$")) {

                request.setAttribute(
                        "error",
                        "Số điện thoại phải 10 số."
                );

                reload(request, response);
                return;
            }

            boolean ok =
                    enrollDAO.register(
                            maND,
                            maLH,
                            hoTen,
                            email,
                            sdt,
                            diaChi
                    );

            if (ok) {

                response.sendRedirect(
                        request.getContextPath()
                                + "/web/class-list?success=1"
                );
                return;
            }

            request.setAttribute(
                    "error",
                    "Đăng ký thất bại hoặc đã đăng ký."
            );

        } catch (NumberFormatException e) {

            request.setAttribute(
                    "error",
                    "Mã lớp không hợp lệ."
            );

        } catch (Exception e) {

            request.setAttribute(
                    "error",
                    "Có lỗi xảy ra khi đăng ký."
            );
        }

        try {
            reload(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void reload(HttpServletRequest request,
                        HttpServletResponse response)
            throws Exception {

        request.setAttribute(
                "dsLop",
                classDAO.getAll()
        );

        request.getRequestDispatcher("/web/enroll.jsp")
                .forward(request, response);
    }

    private String safe(String s) {

        if (s == null) {
            return "";
        }

        return s.trim();
    }
}