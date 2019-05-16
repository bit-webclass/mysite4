package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value="/ga")
public class GalleryController {
	
	@Autowired
	private GalleryService galleryService;
	
	
	@RequestMapping(value="/list")
	public String list(Model model){
		List<GalleryVo> galleryList = galleryService.getList();
		System.out.println(galleryList.toString());
		
		model.addAttribute("galleryList", galleryList);
		return "gallery/list";
	}
	
	@RequestMapping("/upload")
	public String upload(@ModelAttribute GalleryVo galleryVo, @RequestParam("file") MultipartFile file, HttpSession session, Model model) {
		System.out.println(file.getOriginalFilename());
		System.out.println(galleryVo.toString());
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		galleryVo.setUser_no(authUser.getNo());
		
		
		//파일정보vo 파일복사
		galleryService.restore(galleryVo, file);
		
		return "redirect:/ga/list";
	}
	
	@ResponseBody
	@RequestMapping("/view")
	public GalleryVo view(@RequestParam("no") int no) {
		
		//UserVo authUser = (UserVo)session.getAttribute("authUser");
		//galleryVo.setUser_no(authUser.getNo());
		
		return galleryService.getGallery(no);
	}
	

	@RequestMapping("/remove")
	public String remove(@RequestParam("no") int no) {
		galleryService.remoceGallery(no);
		return "redirect:/ga/list";
	}
	
	
}
