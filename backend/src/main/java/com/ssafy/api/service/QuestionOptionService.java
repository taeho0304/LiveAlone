package com.ssafy.api.service;

import com.ssafy.api.Model.QnA;
import com.ssafy.api.request.QuestionOptionPostReq;
import com.ssafy.db.entity.QuestionOption;

import java.util.List;

/**
 *	질문 옵션 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface QuestionOptionService {
    void createQuestionOption(QuestionOptionPostReq questionOptionInfo);

    List<QnA> getQuestionOption();
}
