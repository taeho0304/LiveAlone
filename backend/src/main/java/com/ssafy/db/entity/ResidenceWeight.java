package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * 매물 가중치 모델 정의.
 */
@Entity
@Getter
@Setter
public class ResidenceWeight extends BaseEntity implements Serializable {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    private CommercialCategory commercialCategory;

    String weight;
}