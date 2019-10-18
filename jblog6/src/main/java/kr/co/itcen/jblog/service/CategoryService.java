package kr.co.itcen.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.itcen.jblog.repository.CategoryDao;
import kr.co.itcen.jblog.repository.PostDao;
import kr.co.itcen.jblog.vo.CategoryVo;

@Service
public class CategoryService {
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private PostDao postDao;
	
	//category 목록 불러오기
	public List<CategoryVo> getList(String id) {
		return categoryDao.getList(id);
	}

	//category 제목 불러오기
	public List<CategoryVo> getTitle(String id) {
		List<CategoryVo> list = categoryDao.getTitle(id);
		return list;
	}

	//카테고리 추가
	public Long insert(CategoryVo categoryvo){
		return categoryDao.insert(categoryvo);
	}
	
	//카테고리 no 값 받아옴
	public CategoryVo get(Long no) {
		return categoryDao.get(no);
	}

	public Boolean delete(Long no) {
		postDao.deleteAll(no);
		return categoryDao.delete(no);
	}

	public List<CategoryVo> getCountList(String id) {
		List<CategoryVo> list = categoryDao.getCount(id);
		return list;
	}
	
}