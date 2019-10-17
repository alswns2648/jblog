package kr.co.itcen.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.jblog.exception.UserDaoException;
import kr.co.itcen.jblog.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public Boolean insert(UserVo vo) throws UserDaoException{
		int count = sqlSession.insert("user.insert", vo);
		return count == 1;
	}
	
	//로그인
	public UserVo get(UserVo vo) {
		UserVo result = sqlSession.selectOne("user.getByIdAndPassword1", vo);
		return result; 
	}
	
	//ID 중복검사
	public UserVo get(String id) { 
		UserVo result = sqlSession.selectOne("user.getById", id);
		return result;
	}
}