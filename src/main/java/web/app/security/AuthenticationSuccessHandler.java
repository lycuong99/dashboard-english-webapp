package web.app.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("SUCCESS --");

        AuthenticatedUser user = (AuthenticatedUser) authentication.getPrincipal();

        TokenAuthenticationService.addAuthentication(response,  user.getUsername());
        System.out.println("SUCCESS -- "+ user.getUsername());
        clearAuthenticationAttributes(request);
    }

}
