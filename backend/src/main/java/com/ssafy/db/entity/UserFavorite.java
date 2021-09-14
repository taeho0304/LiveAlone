package com.ssafy.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.db.idmodel.ResidenceInfoPK;
import com.ssafy.db.idmodel.UserFavoritePK;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 관심매물 모델 정의.
 */
@Entity
@Getter
@Setter
@EqualsAndHashCode
@IdClass(UserFavoritePK.class)
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