package Ex0414;

import java.util.List;

public interface SubjectDao {
	void insert(SubjectVo subject) throws Throwable;
	List<SubjectVo> list(int pageNo, int pageSize) throws Throwable;
	SubjectVo detail(int no) throws Throwable;
	int update(SubjectVo subject) throws Throwable;
	void delete(int no) throws Throwable;
}









