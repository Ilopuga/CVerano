package controlador;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/admin/*")
public class Filtro implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Aquí puedes inicializar recursos si es necesario.
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false); // Obtén la sesión sin crear una nueva.

        if (session != null && session.getAttribute("usuario") != null) {
            chain.doFilter(request, response); // Usuario autenticado, permite el acceso.
        } else {
            res.sendRedirect(req.getContextPath() + "/login.html"); // No autenticado, redirige al login.
        }
    }

    @Override
    public void destroy() {
        // Limpiar recursos si es necesario.
    }
}