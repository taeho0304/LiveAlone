package com.ssafy.api.service;

import com.ssafy.api.request.ResidenceGetReq;
import com.ssafy.db.entity.ResidenceCategory;
import com.ssafy.db.entity.ResidenceInfo;
import com.ssafy.db.entity.ResidenceType;

import java.util.List;

/**
 *	방 검색 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface RoomSearchService {
	void createResidenceCategory(String type);

	List<ResidenceCategory> getResidenceCategory();

	void deleteResidenceCategory(List<Long> roomType);

	void createResidenceType(String type);

	List<ResidenceType> getResidenceType();

	void deleteResidenceType(List<Long> residenceType);

	List<ResidenceInfo> getResidenceInfos(ResidenceGetReq residenceGetReq);
}
