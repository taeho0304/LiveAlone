package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.BargainType;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 거래 타입 조회 API ([GET] /api/v1/search/bargaintypes) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("BargainTypeRes")
public class BargainTypeRes extends BaseResponseBody {
	List<BargainType> bargainType = new ArrayList<>();

	public static BargainTypeRes of(List<BargainType> bargainTypes) {
		BargainTypeRes res = new BargainTypeRes();
		res.setStatusCode(200);
		res.setMessage("success");
		res.setBargainType(bargainTypes);
		return res;
	}

	public static BargainTypeRes of(Integer setStatusCode, String message) {
		BargainTypeRes res = new BargainTypeRes();
		res.setStatusCode(setStatusCode);
		res.setMessage(message);
		return res;
	}

}
