package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

/**
 * 매물 타입 모델 정의.
 */
@Entity
@Getter
@Setter
public class ResidenceType extends BaseEntity{
    String type;
}