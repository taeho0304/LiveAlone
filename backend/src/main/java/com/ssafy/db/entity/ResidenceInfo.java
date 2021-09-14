package com.ssafy.db.entity;

import com.ssafy.db.idmodel.ResidenceInfoPK;
import com.ssafy.db.idmodel.UserFavoritePK;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode
@IdClass(UserFavoritePK.class)
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
    int cost;
    int manageCost;
    int area;
    String content;
    String imgurl;
    String dong;
    String gu;
}