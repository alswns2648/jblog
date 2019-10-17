package kr.co.itcen.jblog.service;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.itcen.jblog.exception.FileUploadException;
import kr.co.itcen.jblog.repository.BlogDao;
import kr.co.itcen.jblog.repository.PostDao;
import kr.co.itcen.jblog.vo.BlogVo;
import kr.co.itcen.jblog.vo.PostVo;

@Service
public class BlogService {
	private static final String URL_PREFIX = "/images";
	private static final String SAVE_PATH = "/uploads";
	
	@Autowired
	private BlogDao blogDao;
	
	
	//블로그 메인정보 
	public BlogVo get(String id) {
		return blogDao.get(id);
	}
	
	//파일 업로드
	public Boolean update(MultipartFile multipartFile, BlogVo vo){
		String url ="";
		if (multipartFile == null) {
			return false;
		}
		//원래 파일 이름 
		String originalFilename = multipartFile.getOriginalFilename();
		//확장자명 제거
		String extName = originalFilename.substring(originalFilename.lastIndexOf('.') + 1);
		//milli초 까지 계산(generateSaveFilename) 하여 이름 저장
		String saveFileName = generateSaveFilename(extName);
		try {
			//파일의 byte를 읽어옴
			byte[] fileDate = multipartFile.getBytes();
			//os에 넣어줌(byte 형식으로)
			OutputStream os = new FileOutputStream(SAVE_PATH + "/" + saveFileName);
			os.write(fileDate); // 저장
			os.close(); // os 닫아줌
			url = URL_PREFIX + "/" + saveFileName; // URL 저장 됨 : .../images/파일이름
		} catch (IOException e) {
			throw new FileUploadException();
		}
		vo.setLogo(url);
		return blogDao.update(vo); // DB에 url 형식으로 담김
	}

	private String generateSaveFilename(String extName) {
		String filename = "";
		Calendar calendar = Calendar.getInstance();
		filename += calendar.get(Calendar.YEAR);
		filename += calendar.get(Calendar.MONTH);
		filename += calendar.get(Calendar.DATE);
		filename += calendar.get(Calendar.HOUR);
		filename += calendar.get(Calendar.MINUTE);
		filename += calendar.get(Calendar.SECOND);
		filename += calendar.get(Calendar.MILLISECOND);
		filename += (".") + extName;
		return filename;
	}
	
	
	
	
	
}