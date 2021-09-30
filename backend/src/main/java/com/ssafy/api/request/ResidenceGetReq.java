package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 방 검색 ([Get] /api/v1/search/rooms) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("ResidenceDetailGetReq")
public class ResidenceGetReq {
	String si;
	String dong;
	String gugun;
}