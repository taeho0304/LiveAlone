package com.ssafy.db.entity;

import com.ssafy.db.idmodel.QuestionResultPK;
import com.ssafy.db.idmodel.ResidenceInfoPK;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 질문 모델 정의.
 */
@Entity
@Getter
@Setter
@EqualsAndHashCode
@IdClass(QuestionResultPK.class)
public class QuestionResult extends BaseEntity implements Serializable {
    @Id
    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    int result;
    int resultIndex;
}