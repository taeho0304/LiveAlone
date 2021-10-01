package com.ssafy.api.Model;

import com.ssafy.db.entity.ResidenceInfo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class ResidenceModel {
    ResidenceInfo residenceInfo;
    boolean isPresent;
}
