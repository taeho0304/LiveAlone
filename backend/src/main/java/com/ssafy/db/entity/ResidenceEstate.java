package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

/**
 * 매물 부동산 모델 정의.
 */
@Entity
@Getter
@Setter
public class ResidenceEstate extends BaseEntity{
    String name;
    String phone;
    String address;
    String tel;
}