package Ex0414;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/seme/subject/insert.bit")
@SuppressWarnings("serial")
public class subjectServletInsert extends HttpServlet {
	static SubjectDao dao;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {

		DBConnectionPool dbConnectionPool = new DBConnectionPool();
		dao = new MysqlSubjectDao();
		((MysqlSubjectDao) dao).setDBConnectionPool(dbConnectionPool);

		// 요청 정보에서 파라미터 값 꺼내기
		String title = request.getParameter("title");
		String description = request.getParameter("description");

		SubjectVo subject = new SubjectVo();
		subject.setTitle(request.getParameter("title"));
		subject.setDescription(request.getParameter("description"));
		boolean isOk = false;

		try {
			dao.insert(subject);
			isOk = true;
			System.out.println("등록성공!");
		} catch (Throwable e) {
			isOk = false;
			e.printStackTrace();
		}
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>Subject Insert</title></head>");
		if (isOk) {
			out.println("<body><h1>등록 성공!</h1>");
		} else {
			out.println("<body><h1>등록 실패!</h1>");
		}
		out.println("</body></html>");
	}

}
