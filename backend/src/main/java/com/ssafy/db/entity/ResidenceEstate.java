package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * 매물 부동산 모델 정의.
 */
@Entity
@Getter
@Setter
public class ResidenceEstate extends BaseEntity{
    @ManyToOne(fetch = FetchType.LAZY)
    private EstateInfo estateInfo;

    String phone;
    String address;
    String tel;
}