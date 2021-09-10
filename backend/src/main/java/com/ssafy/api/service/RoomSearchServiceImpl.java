package com.ssafy.api.service;

import com.ssafy.db.entity.BargainType;
import com.ssafy.db.entity.RoomType;
import com.ssafy.db.repository.BargainTypeRepository;
import com.ssafy.db.repository.QuestionRepository;
import com.ssafy.db.repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *	방 검색 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("roomSearchService")
public class RoomSearchServiceImpl implements RoomSearchService {
	@Autowired
	RoomTypeRepository roomTypeRepository;

	@Autowired
	BargainTypeRepository bargainTypeRepository;

	@Autowired
	RoomSearchService roomSearchService;

	@Override
	public void createRoomType(String type) {
		RoomType roomType = new RoomType();
		roomType.setType(type);
		roomTypeRepository.save(roomType);
	}

	@Override
	public List<RoomType> getRoomType() {
		return roomTypeRepository.findAll();
	}

	@Override
	public void deleteRoomType(List<Long> roomType) {
		for (Long id:roomType) roomTypeRepository.deleteById(id);
	}

	@Override
	public void createBargainType(String type) {
		BargainType bargainType = new BargainType();
		bargainType.setType(type);
		bargainTypeRepository.save(bargainType);
	}

	@Override
	public List<BargainType> getBargainType() {
		return bargainTypeRepository.findAll();
	}

	@Override
	public void deleteBargainType(List<Long> bargainType) {
		for (Long id:bargainType) bargainTypeRepository.deleteById(id);
	}
}