package Ex0414;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/seme/subject/delete.bit")
@SuppressWarnings("serial")
public class subjectServletDelete extends HttpServlet{
	static SubjectDao dao;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		
		DBConnectionPool dbConnectionPool = new DBConnectionPool();
		dao = new MysqlSubjectDao();
		((MysqlSubjectDao)dao).setDBConnectionPool(dbConnectionPool);
		
		//요청 정보에서 파라미터 값 꺼내기
		String no = request.getParameter("no");
		SubjectVo subject=null;
		
		try {
			dao.delete( Integer.parseInt(no) );
        } catch (Throwable e) {
        	e.printStackTrace();
        }
		boolean isOk = false;
		
		try {
	         dao.delete(Integer.parseInt(no));
	         isOk = true;
	        System.out.println("삭제성공!");
        } catch (Throwable e) {
        	isOk = false;
	        e.printStackTrace();
        }
		response.setContentType("text/html;charset=UTF-8");	
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>Subject Delete</title></head>");
		if(isOk){
			out.println("<body><h1>삭제 성공!</h1>");
		}else{
			out.println("<body><h1>삭제 실패!</h1>");
		}
		out.println("</body></html>");
	}
	
	public static void testDelete(int no) throws Throwable {
		dao.delete(no);
	}
}
