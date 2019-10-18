package kr.co.itcen.jblog.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import kr.co.itcen.jblog.vo.BlogVo;


@Repository
public class BlogDao {

	@Autowired
	private SqlSession sqlSession;
	
	//default 블로그 생성
	public BlogVo insert(String id, String name) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id",id);
		map.put("name",name);
		BlogVo blogvo = sqlSession.selectOne("blog.insert",map);
		return blogvo;
	}
	
	//블로그 정보 
	public BlogVo get(String id) {
		BlogVo vo = sqlSession.selectOne("blog.get", id);
		return vo;
	}
	
	//파일 업로드
	public Boolean update(BlogVo vo) {
		int count = sqlSession.update("blog.update",vo);
		return count ==1;
	}
}