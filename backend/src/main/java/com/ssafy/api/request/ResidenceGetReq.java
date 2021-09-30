package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 방 검색 ([Get] /api/v1/search/rooms) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
//@ApiModel("residenceGetReq")
public class ResidenceGetReq {
//	@ApiModelProperty(name="시", example="서울특별시")
	String si;
//	@ApiModelProperty(name="동", example="가양동")
	String dong;
//	@ApiModelProperty(name="동", example="가양동")
	String gugun;
}