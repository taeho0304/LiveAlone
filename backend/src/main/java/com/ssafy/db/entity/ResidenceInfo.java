package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 매물 모델 정의.
 */
@Entity
@Getter
@Setter
public class ResidenceInfo extends BaseEntity implements Serializable {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    private ResidenceType residenceType;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    private ResidenceDetail residenceDetail;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    private ResidenceEstate residenceEstate;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    private ResidenceCategory residenceCategory;

    @Id
    @OneToOne
//    @JoinColumn(name = "id", unique = true, nullable = false )
    private ResidenceWeight residenceWeight;

    String name;
    String lat;
    String lon;
    String cost;
    String area;
    String content;
    String imgurl;
}