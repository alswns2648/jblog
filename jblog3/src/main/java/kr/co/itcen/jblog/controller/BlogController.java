package kr.co.itcen.jblog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.co.itcen.jblog.security.AuthUser;
import kr.co.itcen.jblog.service.BlogService;
import kr.co.itcen.jblog.service.CategoryService;
import kr.co.itcen.jblog.service.PostService;
import kr.co.itcen.jblog.vo.BlogVo;
import kr.co.itcen.jblog.vo.CategoryVo;
import kr.co.itcen.jblog.vo.PostVo;
import kr.co.itcen.jblog.vo.UserVo;

@Controller
@RequestMapping("/{id:(?!assets)(?!images).*}")//assets밑에 있는 파일을 제외하고 모두 받는다
public class BlogController {

	@Autowired
	private BlogService blogService;
	@Autowired
	private PostService postService;
	@Autowired
	private CategoryService categoryService;
	
	// 블로그 메인
	@RequestMapping({"","/{path1}","/{path1}/{path2}" })
	public String blogMain(@PathVariable String id, @PathVariable Optional<Long> path1, @PathVariable Optional<Long> path2,@AuthUser UserVo vo,  Model model ) {
		
		//category 내용 가져오기
		List<CategoryVo> list = categoryService.getList(id);
		Long categoryno= 0L;
		if(!list.isEmpty()) {
			categoryno= list.get(0).getNo();
		}
		model.addAttribute("list",list);

		
		Long postno = 0L;
		
		if(path2.isPresent()) {
			categoryno=path1.get();
			postno=path2.get();
		}else if(path1.isPresent()) {
			categoryno=path1.get();
		}	
		
		//post 가져오기
		List<PostVo> postList= postService.getList(categoryno);
		model.addAttribute("postList",postList);
		PostVo postvo;
		
		if(postno==0L && (!postList.isEmpty())) {
			postno=postList.get(0).getNo();
			postvo=postService.getPost(postno,categoryno);
		}else {
			postvo= postService.getPost(postno,categoryno);
		}
		
		BlogVo blogvo = blogService.get(id);
		model.addAttribute("blogvo",blogvo);
		model.addAttribute("post",postvo);
		model.addAttribute("id",id);
		
		if(id.equals(vo.getId()))
			model.addAttribute("isMe",true);
		
		return "blog/blog-main";
	}
}