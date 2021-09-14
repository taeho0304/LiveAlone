package com.ssafy.api.service;

import com.ssafy.api.request.ResidenceGetReq;
import com.ssafy.db.entity.ResidenceCategory;
import com.ssafy.db.entity.ResidenceInfo;
import com.ssafy.db.entity.ResidenceType;
import com.ssafy.db.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *	방 검색 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("roomSearchService")
public class RoomSearchServiceImpl implements RoomSearchService {
	@Autowired
	ResidenceCategoryRepository residenceCategoryRepository;

	@Autowired
	ResidenceTypeRepository residenceTypeRepository;

	@Autowired
	RoomSearchService roomSearchService;

	@Autowired
	ResidenceInfoRepositorySupport residenceInfoRepositorySupport;

	@Override
	public void createResidenceCategory(String type) {
		ResidenceCategory residenceCategory = new ResidenceCategory();
		residenceCategory.setCategoryName(type);
		residenceCategoryRepository.save(residenceCategory);
	}

	@Override
	public List<ResidenceCategory> getResidenceCategory() {
		return residenceCategoryRepository.findAll();
	}

	@Override
	public void deleteResidenceCategory(List<Long> residenceCategory) {
		for (Long id:residenceCategory) residenceCategoryRepository.deleteById(id);
	}

	@Override
	public void createResidenceType(String type) {
		ResidenceType residenceType = new ResidenceType();
		residenceType.setType(type);
		residenceTypeRepository.save(residenceType);
	}

	@Override
	public List<ResidenceType> getResidenceType() {
		return residenceTypeRepository.findAll();
	}

	@Override
	public void deleteResidenceType(List<Long> residenceType) {
		for (Long id:residenceType) residenceTypeRepository.deleteById(id);
	}

	@Override
	public List<ResidenceInfo> getResidenceInfos(ResidenceGetReq residenceGetReq) {
		return residenceInfoRepositorySupport.findRooms(residenceGetReq);
	}
}