package com.ssafy.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.QQuestionOption;
import com.ssafy.db.entity.QUser;
import com.ssafy.db.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 질문 옵션 모델 관련 디비 쿼리 생성을 위한 구현 정의.
 */
@Repository
public class QuestionOptionRepositorySupport {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QQuestionOption qQuestionOption = QQuestionOption.questionOption;

    public int countQuestionOption(Long questionId) {
        Long count = jpaQueryFactory.select(qQuestionOption).from(qQuestionOption)
                .where(qQuestionOption.question.id.eq(questionId)).fetchCount();
        return Long.valueOf(count).intValue();
    }
}
