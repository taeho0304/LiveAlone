package com.ssafy.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * 관심매물 모델 정의.
 */
@Entity
@Getter
@Setter
@EqualsAndHashCode
public class UserFavorite extends BaseEntity implements Serializable {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    private ResidenceInfo residenceInfo;

    public static UserFavorite of (ResidenceInfo residenceInfo, User user){
        UserFavorite userFavorite= new UserFavorite();
        userFavorite.setResidenceInfo(residenceInfo);
        userFavorite.setUser(user);
        return userFavorite;
    }
}