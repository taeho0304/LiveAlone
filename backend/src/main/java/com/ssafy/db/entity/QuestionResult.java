package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.io.Serializable;

/**
 * 질문 모델 정의.
 */
@Entity
@Getter
@Setter
public class QuestionResult extends BaseEntity implements Serializable {
    @Id
    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    int result;
    int resultIndex;
}