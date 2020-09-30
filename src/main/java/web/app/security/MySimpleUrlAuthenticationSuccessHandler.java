package web.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import web.app.entity.Campus;
import web.app.repos.CampusRepo;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Component
public class MySimpleUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler{
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    @Autowired
    CampusRepo campusRepo;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        handle(request, response, authentication);
        clearAuthenticationAttributes(request);

    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {

    }

    protected void handle(HttpServletRequest request,
                          HttpServletResponse response, Authentication authentication)
            throws IOException {
        AuthenticatedUser user = (AuthenticatedUser) authentication.getPrincipal();

        System.out.println("SUCCESS LOGIN"+ user.getUsername() +"\nRole:"+user.getRole());
        HttpSession session = request.getSession(true);
        session.setAttribute("ROLE", user.getRole());
        if(user.getRole().equalsIgnoreCase("ADMIN"))
        {
            List<Campus> campusList = campusRepo.findAll();
            session.setAttribute("campuses", campusList);
        }

        TokenAuthenticationService.addAuthentication(response,  user.getUsername());
        System.out.println("Authorization: "+ authentication.getAuthorities());

        redirectStrategy.sendRedirect(request, response, "/admin");
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }
    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }
}
