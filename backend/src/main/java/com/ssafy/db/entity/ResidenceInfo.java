package com.ssafy.db.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * 매물 모델 정의.
 */
@Entity
@Getter
@Setter
@EqualsAndHashCode
public class ResidenceInfo extends BaseEntity{
    @ManyToOne(fetch = FetchType.LAZY)
    private ResidenceType residenceType;

    @ManyToOne(fetch = FetchType.LAZY)
    private EstateInfo estateInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    private ResidenceCategory residenceCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    private Dong dong;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<ImageUrl> imageUrl;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Feature> feature;

    @OneToOne
    private ResidenceWeight residenceWeight;

    String name;
    String lat;
    String lon;
    int cost;
    int wolseCost;
    int jeonseCost;
    double manageCost;
    int area;
    String content;
    String myFloor; // 층
    String structure; // 방 구조
    String buildingFloor; // 층
    String direction;
}