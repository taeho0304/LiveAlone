package com.ssafy.api.service;

import com.ssafy.api.request.QuestionPatchReq;
import com.ssafy.api.request.QuestionResultPostReq;
import com.ssafy.common.auth.SsafyUserDetails;
import com.ssafy.db.entity.Question;
import com.ssafy.db.entity.QuestionResult;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.QuestionRepository;
import com.ssafy.db.repository.QuestionResultRepository;
import com.ssafy.db.repository.QuestionResultRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.ssafy.db.entity.QQuestionResult.questionResult;

/**
 *	질문 답변 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("questionResultService")
public class QuestionResultServiceImpl implements QuestionResultService {
	@Autowired
	QuestionResultRepository questionResultRepository;

	@Autowired
	QuestionResultRepositorySupport questionResultRepositorySupport;

	@Autowired
	QuestionResultService questionResultService;

	@Autowired
	UserService userService;

	@Override
	public void createQuestionResult(Authentication authentication, QuestionResultPostReq questionResultPostReq) {
		SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
		User user = userService.getUserByUserId(userDetails.getUsername());

		try{
			QuestionResult questionResult = questionResultRepositorySupport.findQuestionResultByUesrIdAndResult(questionResultPostReq.getResultIndex(), user);
			createResult(questionResult, questionResultPostReq, user);
		}catch (Exception e) {
			QuestionResult questionResult = new QuestionResult();
			createResult(questionResult, questionResultPostReq, user);
		}
	}

	private void createResult(QuestionResult questionResult, QuestionResultPostReq questionResultPostReq, User user) {
		questionResult.setResult(questionResultPostReq.getResult());
		questionResult.setResultIndex(questionResultPostReq.getResultIndex());
		questionResult.setUser(user);
		questionResultRepository.save(questionResult);
	}
}