package kr.co.itcen.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.itcen.jblog.repository.PostDao;
import kr.co.itcen.jblog.vo.PostVo;

@Service
public class PostService {
	@Autowired
	private PostDao postDao;
	
	//post list 불러오기
	public List<PostVo> getList(Long categoryno) {
		return postDao.getList(categoryno);
	}
	
	//Post 선택하여 내용 보기
	public PostVo getPost(Long postno, Long categoryno) {
		return postDao.getPost(postno, categoryno);
	}
	
	//게시물 작성
	public Boolean insertPost(PostVo vo) {
		return postDao.insert(vo);
	}
}