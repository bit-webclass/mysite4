package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping(value = "/api/gb")
public class ApiGuestbookController {

	@Autowired
	private GuestbookService guestbookService;
	
	/* 방명록가져오기 마지막번호에서 10개 */
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public List<GuestbookVo> list(@RequestParam("lastNo") int lastNo) {
		System.out.println(lastNo);
		List<GuestbookVo> guestbookList = guestbookService.getList(lastNo);
		System.out.println(guestbookList.toString());
		return guestbookList;
	}
	
	
	/* 방명록추가 */
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public GuestbookVo add(@ModelAttribute GuestbookVo guestbookVo) {

		return guestbookService.addSelectVo(guestbookVo);
	}

	/* 삭제 */
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public int delete(@ModelAttribute GuestbookVo guestbookVo) {
		
		int count = guestbookService.delete(guestbookVo);
		return count;
	}

	
}
