package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Question;
import com.ssafy.db.entity.RoomType;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 방 종류 조회 API ([GET] /api/v1/search/roomtypes) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("RoomTypeRes")
public class RoomTypeRes extends BaseResponseBody {
	List<RoomType> roomTypes = new ArrayList<>();

	public static RoomTypeRes of(List<RoomType> roomTypes) {
		RoomTypeRes res = new RoomTypeRes();
		res.setStatusCode(200);
		res.setMessage("success");
		res.setRoomTypes(roomTypes);
		return res;
	}

	public static RoomTypeRes of(Integer setStatusCode, String message) {
		RoomTypeRes res = new RoomTypeRes();
		res.setStatusCode(setStatusCode);
		res.setMessage(message);
		return res;
	}
}
