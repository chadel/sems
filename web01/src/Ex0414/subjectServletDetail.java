package Ex0414;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/seme/subject/detail.bit")
@SuppressWarnings("serial")
public class subjectServletDetail extends HttpServlet{
	static SubjectDao dao;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		
		System.out.println("11111");
		DBConnectionPool dbConnectionPool = new DBConnectionPool();
		dao = new MysqlSubjectDao();
		((MysqlSubjectDao)dao).setDBConnectionPool(dbConnectionPool);
		
		System.out.println("2222");
		//요청 정보에서 파라미터 값 꺼내기
		String no = request.getParameter("no");
		SubjectVo subject=null;
		try {
			subject = testDetail(Integer.parseInt(no));
		
        } catch (Throwable e) {
        	e.printStackTrace();
        }
		System.out.println(subject);
		
		System.out.println("3333");
		
		//클라이언트에게 출력하기
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		System.out.println(subject.getNo());
		System.out.println(subject.getTitle());
		System.out.println(subject.getDescription());
		
		out.println("<html><head><title>과목상세 정보</title></head>");
		out.println("<body><h1>과목상세 정보</h1>");
		out.print("<table border=1><tr><th>번 호</th><td>" +subject.getNo()+"</td></tr>");
		out.print("<tr><th>과목명</th><td>" +subject.getTitle()+"</td></tr>");
		out.print("<tr><th>설명</th><td><textarea readonly=readonly cols=40 rows=10>");
		out.print(""+subject.getDescription() +"</textarea></td></tr>");
		out.println("</table></body></html>");

	}
	
	public static SubjectVo testDetail(int no) throws Throwable {
		return dao.detail(no);
	}
}