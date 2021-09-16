package com.ssafy.api.request;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 방 검색 ([Get] /api/v1/search/rooms) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
//@ApiModel("ResidenceDetailGetReq")
public class ResidenceGetReq {
//	@ApiModelProperty(name="시", example="asc")
	String si;
//	@ApiModelProperty(name="동", example="asc")
	String dong;
//	@ApiModelProperty(name="구", example="asc")
	String gugun;
}