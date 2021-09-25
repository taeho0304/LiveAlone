package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

/**
 * 매물 상세 모델 정의.
 */
@Entity
@Getter
@Setter
public class ResidenceDetail extends BaseEntity {
    String floor; // 층
    String structure; // 방 구조
}