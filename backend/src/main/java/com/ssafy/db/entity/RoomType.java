package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

/**
 * 거래 타입 모델 정의.
 */
@Entity
@Getter
@Setter
public class RoomType extends BaseEntity{
    String type;
}