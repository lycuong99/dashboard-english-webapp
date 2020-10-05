package web.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/500").setViewName("error/500");
        registry.addViewController("/401").setViewName("error/401");
        registry.addViewController("/403").setViewName("error/403");
        registry.addViewController("/405").setViewName("error/405");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
}
