package com.ssafy.api.service;

import com.ssafy.db.entity.BargainType;
import com.ssafy.db.entity.RoomType;

import java.util.List;

/**
 *	방 검색 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface RoomSearchService {
	void createRoomType(String type);

	List<RoomType> getRoomType();

	void deleteRoomType(List<Long> roomType);

	void createBargainType(String type);

	List<BargainType> getBargainType();

	void deleteBargainType(List<Long> bargainType);
}
