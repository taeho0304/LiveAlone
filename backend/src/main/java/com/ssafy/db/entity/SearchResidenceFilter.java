package com.ssafy.db.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 유저 매물 검색 필터 모델 정의.
 */
@Entity
@Getter
@Setter
@EqualsAndHashCode
public class SearchResidenceFilter extends BaseEntity implements Serializable {
    @ManyToOne( fetch = FetchType.LAZY )
    private User user;

    @ManyToMany( fetch = FetchType.LAZY )
    private List<ResidenceCategory> residenceCategory;

    @ManyToMany( fetch = FetchType.LAZY )
    private List<ResidenceType> residenceType;

    int startPrice;
    int endPrice;
    int startJPrice;
    int endJPrice;
    int startWPrice;
    int endWPrice;
    int startManagePrice;
    int endManagePrice;
    int startArea;
    int endArea;
    String si;
    String dong;
    String gugun;
}