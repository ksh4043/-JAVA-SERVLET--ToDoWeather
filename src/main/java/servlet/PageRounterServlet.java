package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 페이지 이동 경로를 지정하는 서블릿 클래스
@WebServlet("/page")
public class PageRounterServlet extends HttpServlet {
    public PageRounterServlet() {
        super();
    }

    // 페이지 라우팅 로직
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String target = request.getParameter("go"); // page 경로의 go 라는 키워드의 파라미터 값을 저장 >> page?go={parameter}
		
		if (target == null) {
			target = "main"; // 아무런 값도 없을 때 main 으로 이동시킴
		}
		
		String path = "/WEB-INF/views/" + target + ".jsp"; // ex >> /WEB-INF/views/login.jsp
		request.getRequestDispatcher(path).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
