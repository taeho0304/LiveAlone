package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 방 상세 검색 ([Get] /api/v1/search/rooms) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
//@ApiModel("ResidenceDetailGetReq")
public class ResidenceDetailGetReq {
//	@ApiModelProperty(name="방 종류", example="원룸")
	List<String> residenceCategory = new ArrayList<>();
//	@ApiModelProperty(name="거래 타입", example="월세")
	List<String> residenceType = new ArrayList<>();
//	@ApiModelProperty(name="최저 매매 가격대(만원)", example="40")
	int startPrice;
//	@ApiModelProperty(name="최고 매매 가격대(만원)", example="100")
	int endPrice;
//	@ApiModelProperty(name="최저 전세 가격대(만원)", example="40")
	int startJPrice;
//	@ApiModelProperty(name="최고 전세 가격대(만원)", example="100")
	int endJPrice;
//	@ApiModelProperty(name="최저 월세 가격대(만원)", example="40")
	int startWPrice;
//	@ApiModelProperty(name="최고 월세 가격대(만원)", example="100")
	int endWPrice;
//	@ApiModelProperty(name="관리비 최저 가격대(만원)", example="5")
	int startManagePrice;
//	@ApiModelProperty(name="관리비 최고 가격대(만원)", example="10")
	int endManagePrice;
//	@ApiModelProperty(name="최저 면적(평수)", example="8")
	int startArea;
//	@ApiModelProperty(name="최고 면적(평수)", example="10")
	int endArea;
//	@ApiModelProperty(name="정렬 기준", example="cost")
	String sortType;
//	@ApiModelProperty(name="정렬 방식", example="asc")
	String sortOrder;
}