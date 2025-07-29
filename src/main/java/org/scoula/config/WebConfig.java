package org.scoula.config;

import org.scoula.security.config.SecurityConfig;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    //DispatcherServlet의 동작을 커스터마이징하는 핵심 코드
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        //요청한 URL에 매핑된 컨트롤러가 없으면 → 404.html 같은 기본 에러 페이지 반환
        registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{RootConfig.class, SecurityConfig.class,MailConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {ServletConfig.class};
    }

    //스프링의 FrontController인 DispatcherServlet이 담당할 Url 매핑 패턴,  / : 모든 요청에 대해 매핑
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    // POSTbody문자인코딩필터설정-UTF-8설정
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter= new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return new Filter[] {characterEncodingFilter};
    }

}
