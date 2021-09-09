package com.ssafy.api.service;

import com.ssafy.db.entity.Question;
import com.ssafy.db.entity.QuestionOption;
import com.ssafy.db.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *	질문 옵션 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("questionOptionService")
public class QuestionOptionServiceImpl implements QuestionOptionService {
	@Autowired
	QuestionRepository questionRepository;

	@Autowired
	QuestionOptionService questionOptionService;

	QuestionOption questionOption = new QuestionOption();


}
