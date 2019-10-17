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
public class AdminController {
	@Autowired
	private BlogService blogService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private PostService postService;
	
	//기본 관리 페이지 
	@RequestMapping("/admin/basic")
	public String blogBasic(@PathVariable String id, Model model) {
		BlogVo vo = blogService.get(id);
		model.addAttribute("blogvo",vo);
		return "blog/blog-admin-basic";
	}
	
	//기본 제목, 파일 갱신
	@RequestMapping(value="/admin/basic", method = RequestMethod.POST)
	public String blogBasic(@RequestParam(value = "logo-file", required = false) MultipartFile multipartFile,
							@PathVariable String id, BlogVo vo) {
		vo.setBlog_id(id);
		blogService.update(multipartFile, vo);
		return "redirect:/"+id;
	}
	
	//글쓰기 페이지
	@RequestMapping("/admin/write")
	public String blogWrite(@PathVariable String id, Model model) {
		List<CategoryVo> list= categoryService.getTitle(id);
		BlogVo vo = blogService.get(id);
		model.addAttribute("list",list);
		model.addAttribute("blogvo",vo);
		return "blog/blog-admin-write";
	}
	
	//카테고리, 제목, 내용 갱신
	@RequestMapping(value = "/admin/write", method = RequestMethod.POST)
	public String write(@PathVariable String id, PostVo vo, @RequestParam("category") Long no) {
		vo.setCategory_no(no);
		postService.insertPost(vo);
		return "redirect:/"+id;
	}
	
	//카테고리 페이지
		@RequestMapping("/admin/category")
		public String blogCategory(@PathVariable String id, Model model) {
			List<CategoryVo> list= categoryService.getCountList(id);
			model.addAttribute("list",list);
			BlogVo vo = blogService.get(id);
			model.addAttribute("blogvo",vo);
			return "blog/blog-admin-category";
		}
}