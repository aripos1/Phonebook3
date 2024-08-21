package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PhonebookDao;
import com.javaex.service.PhonebookService;
import com.javaex.vo.PersonVo;

@Controller
public class PhonebookController {

	// dao를 메모리에 올린다
	@Autowired
	private PhonebookDao phonebookDao;
	@Autowired
	private PhonebookService phonebookService;

	// 리스트
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(Model model) {

		List<PersonVo> exePersonList = phonebookService.exePersonList();
		model.addAttribute("personList", exePersonList);

		phonebookService.exePersonList();

		return "list";
	}

	// 등록 폼
	@RequestMapping(value = "/writeform", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeform() {

		System.out.println("dddd");
		return "writeForm";
	}

	// 등록
	@RequestMapping(value = "write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@ModelAttribute PersonVo personVo) {
		System.out.println("write");
		System.out.println(personVo);

//		int count = phonebookDao.insertPerson(personVo);
		phonebookService.exeWritePerson(personVo);
//		System.out.println(count);

		return "redirect:/list";
	}

//	@RequestMapping(value = "/write2", method = { RequestMethod.GET, RequestMethod.POST })
//	public String write2(@RequestParam String name, @RequestParam String hp, @RequestParam String company) {
//		System.out.println("wirte2");
//
//		PersonVo personVo = new PersonVo(name, hp, company);
//		System.out.println(personVo);
//
//		return "redirect:/list";
//	}

	// 삭제
	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@RequestParam(value = "no") int no) {
		System.out.println("delete");
		phonebookService.exeDeletePerson(no);

		return "redirect:/list";
	}

	// 수정 폼
	@RequestMapping(value = "editform", method = { RequestMethod.GET, RequestMethod.POST })
	public String editform(@RequestParam(value = "no") int personId, Model model) {
		// DAO를 사용해 DB에서 데이터 
		System.out.println("editform");
		PersonVo person = phonebookService.exeEditForm(personId);

		// 가져온 데이터를 모델에 추가
		model.addAttribute("personVo", person);
		phonebookService.exeEditForm(personId);
		// 뷰 이름 반환
		return "editForm"; // editForm.jsp 또는 다른 뷰 파일로 이동
	}

	// 수정
	@RequestMapping(value = "edit", method = { RequestMethod.GET, RequestMethod.POST })
	public String edit(@ModelAttribute PersonVo personVo) {
		// personVo 객체에 이미 수정된 데이터가 담겨있음

		// DB 업데이트 처리
		int count = phonebookDao.updatePerson(personVo);

		System.out.println("수정된 행의 수: " + count);

		// 수정 후 목록 페이지로 리다이렉트
		return "redirect:/list";
	}

}
