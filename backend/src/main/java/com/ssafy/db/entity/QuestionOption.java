package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 질문 모델 정의.
 */
@Entity
@Getter
@Setter
public class QuestionOption extends BaseEntity{
<<<<<<< HEAD

=======
>>>>>>> c0ebc8e9ae59c1b82f017c027fb2a51f5d38c7a3
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Question question;

    String optionContent;
    int optionContentIndex;
<<<<<<< HEAD

=======
>>>>>>> c0ebc8e9ae59c1b82f017c027fb2a51f5d38c7a3
}