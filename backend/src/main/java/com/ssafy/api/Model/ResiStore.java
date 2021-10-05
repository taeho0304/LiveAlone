package com.ssafy.api.Model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class ResiStore extends ResidenceModel{
    int score;
    long categoryId;
}
