package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * 이미지 모델 정의.
 */
@Entity
@Getter
@Setter
public class ImageUrl extends BaseEntity {
    String url;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ResidenceInfo> residenceInfos;

    public void removeResidence(ResidenceInfo residenceInfo) {
        this.residenceInfos.remove(residenceInfo);
        residenceInfo.getImageUrl().remove(this);
    }
}