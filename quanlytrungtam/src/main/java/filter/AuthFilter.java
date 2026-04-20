package filter;

import model.NguoiDung;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/admin/*", "/course", "/course/*"})
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);
        NguoiDung user = session != null ? (NguoiDung) session.getAttribute("user") : null;

        if (user == null || user.getVaiTro() == null) {
            resp.sendRedirect(req.getContextPath() + "/login?error=unauthorized");
            return;
        }

        String uri = req.getRequestURI();
        String contextPath = req.getContextPath();
        String path = uri.substring(contextPath.length());

        // Legacy URL compatibility after route migration.
        if (uri.endsWith("/admin/dashboard.jsp")) {
            resp.sendRedirect(req.getContextPath() + "/admin/course");
            return;
        }
        if (uri.endsWith("/hocvien/home.jsp")) {
            resp.sendRedirect(req.getContextPath() + "/course");
            return;
        }

        int maVaiTro = user.getVaiTro().getMaVaiTro();

        boolean isAdminRoute = path.startsWith("/admin/");
        if (isAdminRoute && maVaiTro != 1) {
            resp.sendRedirect(req.getContextPath() + "/login?error=forbidden");
            return;
        }

        boolean isHocVienRoute = path.equals("/course") || path.startsWith("/course/");
        if (isHocVienRoute && maVaiTro != 2) {
            resp.sendRedirect(req.getContextPath() + "/login?error=forbidden");
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}