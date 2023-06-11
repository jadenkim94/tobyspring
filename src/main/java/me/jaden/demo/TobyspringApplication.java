package me.jaden.demo;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TobyspringApplication {
    public static void main(String[] args) {
        GenericApplicationContext applicationContext = new GenericApplicationContext();
        applicationContext.registerBean(HelloController.class);
        applicationContext.refresh();
        // tomcat servletContainer 생성
        ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
		// frontController 등록
        WebServer webServer = serverFactory.getWebServer(servletContext -> {
            servletContext.addServlet("frontcontroller"
                    , new HttpServlet() {
                        @Override
                        protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
                                IOException {
                            // 공통적으로 해야할 인증, 보안, 다국어처리를 하는 코드 삽입

                            HelloController helloController = applicationContext.getBean(HelloController.class);

                            // hello 요청 매핑
                            if (req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())) {
                                String name = req.getParameter("name");

                                String ret = helloController.hello(name);
                                resp.setContentType(MediaType.TEXT_PLAIN_VALUE);
                                resp.getWriter().print(ret);
                            } else {
                                resp.setStatus(HttpStatus.NOT_FOUND.value());
                            }
                        }
                        // 모든 요청을 mapping
                    }).addMapping("/*");
        });
        webServer.start();
    }

}
