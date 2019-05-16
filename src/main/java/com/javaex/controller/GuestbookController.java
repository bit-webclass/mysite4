package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping(value="/gb")
public class GuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	@RequestMapping(value= {"", "/addlist"}, method=RequestMethod.GET)
	public String addList(Model model) {
		List<GuestbookVo> list = guestbookService.getList();
		model.addAttribute("list", list);
		return "guestbook/addlist";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String add(@ModelAttribute GuestbookVo vo) {
		guestbookService.add(vo);
		return "redirect:/gb/addlist";
	}
	

	@RequestMapping(value="/deleteform", method=RequestMethod.GET)
	public String deleteForm() {
		return "guestbook/deleteform";
	}
	
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(@ModelAttribute GuestbookVo vo) {
		System.out.println(vo.toString());
		guestbookService.delete(vo);
		return "redirect:/gb/addlist";
	}
	
	@RequestMapping(value="/ajaxlist", method=RequestMethod.GET)
	public String ajaxList() {
		return "guestbook/list-ajax";
	}
}
