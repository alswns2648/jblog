package kr.co.itcen.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.jblog.vo.CategoryVo;

@Repository
public class CategoryDao {

	@Autowired
	private SqlSession sqlSession;
	
	//default 카테고리 생성
	public Boolean insert(String id) {
		int count = sqlSession.insert("category.insertdefault",id);		
		return count ==1;

	}
	//카테고리 count
	public List<CategoryVo> getCount(String id) {
		List<CategoryVo> list = sqlSession.selectList("category.count",id);
		return list;
	}
	

	//카테고리 list 가져오기
	public List<CategoryVo> getList(String id) {
		List<CategoryVo> list = sqlSession.selectList("category.getlist",id);
		return list;
	}

	//카테고리 title 가져오기
	public List<CategoryVo> getTitle(String id) {
		List<CategoryVo> list = sqlSession.selectList("category.titlelist",id);
		return list;
	}

	// 카테고리 추가
	public Long insert(CategoryVo vo) {
		int count =sqlSession.insert("category.insert",vo); 
		return vo.getNo();
	}
	
	//카테고리 가져오기
	public CategoryVo get(Long no) {
		CategoryVo vo = sqlSession.selectOne("category.get",no);
		return vo;
	}
	public Boolean delete(Long no) {
		int count = sqlSession.delete("category.delete",no);
		return count==1;
	}

}