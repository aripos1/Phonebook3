package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.PhonebookDao;
import com.javaex.vo.PersonVo;

@Service
public class PhonebookService {

	@Autowired
	private PhonebookDao phonebookdao;

	public int exeWritePerson(PersonVo personVo) {
		System.out.println("service");

		int count = phonebookdao.insertPerson(personVo);

		return count;
	}

	public List<PersonVo> exePersonList() {

		List<PersonVo> personList = phonebookdao.getPersonList();

		System.out.println("exePersonList");
		return personList;
	}

	public int exeDeletePerson(int no) {
		System.out.println("exeDeletePerson");

		int count = phonebookdao.deletePerson(no);

		return count;
	}

	public PersonVo exeEditForm(int personId) {
		System.out.println("exeEditForm");
		PersonVo person = phonebookdao.getPersonOne(personId);

		return person;
	}
	public int exeEditPerson(PersonVo personVo) {
		System.out.println("PhonebookService.exeEdit()");
		
		int count = phonebookdao.updatePerson(personVo);
		
		return count;
	}
}
