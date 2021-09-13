package com.ssafy.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

/**
 * 거래 타입 모델 정의.
 */
@Entity
@Getter
@Setter
public class BargainType extends BaseEntity{
    String type;
}