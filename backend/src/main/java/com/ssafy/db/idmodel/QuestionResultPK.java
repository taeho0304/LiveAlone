package com.ssafy.db.idmodel;

import com.ssafy.db.entity.*;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@RequiredArgsConstructor
@EqualsAndHashCode
public class QuestionResultPK implements Serializable {
    private Long id;
    private User user;
}
