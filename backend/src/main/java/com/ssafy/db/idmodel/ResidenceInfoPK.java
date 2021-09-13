package com.ssafy.db.idmodel;

import com.ssafy.db.entity.*;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@RequiredArgsConstructor
@EqualsAndHashCode
public class ResidenceInfoPK implements Serializable {
    private Long id;
    private ResidenceType residenceType;
    private ResidenceDetail residenceDetail;
    private ResidenceEstate residenceEstate;
    private ResidenceCategory residenceCategory;
    private ResidenceWeight residenceWeight;
}
