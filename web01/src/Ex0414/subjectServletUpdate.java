package Ex0414;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/seme/subject/update.bit")
@SuppressWarnings("serial")
public class subjectServletUpdate extends HttpServlet{
	static SubjectDao dao;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		
		DBConnectionPool dbConnectionPool = new DBConnectionPool();
		dao = new MysqlSubjectDao();
		((MysqlSubjectDao)dao).setDBConnectionPool(dbConnectionPool);
		
		//요청 정보에서 파라미터 값 꺼내기
		String no = request.getParameter("no");
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		
		SubjectVo subject = new SubjectVo();
		subject.setNo(Integer.parseInt(request.getParameter("no")));
		subject.setTitle(request.getParameter("title"));
		subject.setDescription(request.getParameter("description"));
		boolean isOk = false;
		int result=0;
		try {
			result = dao.update(subject);
	         isOk = true;
        } catch (Throwable e) {
        	isOk = false;
	        e.printStackTrace();
        }
		
		response.setContentType("text/html;charset=UTF-8");	
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>Subject Update</title></head>");
		if(isOk && result==0){
			out.println("<body><h1>변경 성공!</h1>");
		}else{
			out.println("<body><h1>변경 실패!@@@@</h1>");
		}
		out.println("</body></html>");
	}
	
}
