package site.metacoding.ds;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 톰켓이 req, resp 만들어서 디스패처 서블릿에게 던져줌

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}

	private void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doProcess 요청됨"); 
		String httpMethod = req.getMethod();
//		System.out.println(httpMethod);
		String identifier = req.getRequestURI();
//		System.out.println(identifier);
		
		// 공통 로직 처리
		UserController c = new UserController();
		
		// run 타임시에 분석하는걸 리플레젝션
		// 어노테이션의 value 값을  찾는게 목적
		Method[] methodList = c.getClass().getDeclaredMethods();
		for (Method method : methodList) {
//			System.out.println(method.getName());
			Annotation annotation = method.getDeclaredAnnotation(RequestMapping.class);
			RequestMapping requestMapping = (RequestMapping) annotation;
			try {
//				System.out.println(requestMapping.value());
				if(identifier.equals(requestMapping.value())) {
					method.invoke(c); // 메서드 이름을 몰라도 된다 
				}
			} catch (Exception e) {
				System.out.println(method.getName()+"은 어노테이션이 없습니다.");
			}
			
		}
	}
}
