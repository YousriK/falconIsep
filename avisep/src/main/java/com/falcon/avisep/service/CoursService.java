package com.falcon.avisep.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.falcon.avisep.model.Cours;
import com.falcon.avisep.repository.CoursRepository;

@Service
public class CoursService {
	@Autowired
	private CoursRepository coursRepository;
	
	public Cours findACours(Long id){
		Cours cours=coursRepository.getOne(id);
		return cours;
	}
}
