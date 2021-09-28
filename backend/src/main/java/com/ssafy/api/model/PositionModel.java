package com.ssafy.api.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class PositionModel {
    long id;
    double lat;
    double lon;
}
