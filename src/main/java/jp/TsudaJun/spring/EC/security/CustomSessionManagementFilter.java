package jp.TsudaJun.spring.EC.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.session.SessionManagementFilter;

public class CustomSessionManagementFilter extends SessionManagementFilter{
	
	public CustomSessionManagementFilter(
            SecurityContextRepository securityContextRepository) {
        super(securityContextRepository);
    }
 
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
 
        System.out.println("リクエスト毎に実行されます。");
        chain.doFilter(request, response);
    }

}
