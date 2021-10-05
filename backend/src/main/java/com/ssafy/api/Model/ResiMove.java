package com.ssafy.api.Model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class ResiMove extends ResidenceModel{
    int score;
    long categoryId;
}
