package kr.co.itcen.jblog.controller.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.itcen.jblog.dto.JSONResult;
import kr.co.itcen.jblog.security.AuthUser;
import kr.co.itcen.jblog.service.CategoryService;
import kr.co.itcen.jblog.vo.CategoryVo;
import kr.co.itcen.jblog.vo.UserVo;

@Controller("categoryApiController")
@RequestMapping("/api/admin")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@ResponseBody
	@RequestMapping("/insertCategory")
	public JSONResult insertCategory(@RequestBody CategoryVo categoryVo, @AuthUser UserVo vo) {
		categoryVo.setBlog_id(vo.getId());
		Long no = categoryService.insert(categoryVo);
		
		//insert의 no는 null이어서 그 값을 가져와서 category 모든 값은 cartvo에 넣는다
		CategoryVo cartvo = categoryService.get(no);
		cartvo.setCount(0L);
		return JSONResult.success(cartvo);
	}
	@ResponseBody
	@RequestMapping(value = "/deletecategory")
	public JSONResult deleteCategory(@RequestBody CategoryVo categoryVo, @AuthUser UserVo vo) {
		Boolean exist=categoryService.delete(categoryVo.getNo());
		System.out.println(categoryVo);
		
		return JSONResult.success(exist);
	}
}