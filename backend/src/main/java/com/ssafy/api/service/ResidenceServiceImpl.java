package com.ssafy.api.service;

import com.ssafy.api.request.ResidenceDetailGetReq;
import com.ssafy.api.request.ResidenceGetReq;
import com.ssafy.api.request.ResidencePostReq;
import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.db.entity.*;
import com.ssafy.db.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *	매물 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("residenceService")
public class ResidenceServiceImpl implements ResidenceService {
	@Autowired
	ResidenceInfoRepositorySupport residenceInfoRepositorySupport;

	@Autowired
	ResidenceInfoRepository residenceInfoRepository;

	@Autowired
	ResidenceTypeRepositorySupport residenceTypeRepositorySupport;

	@Autowired
	ResidenceCategoryRepositorySupport residenceCategoryRepositorySupport;

	@Autowired
	EstateInfoRepositorySupport estateInfoRepositorySupport;

	@Autowired
	DongRepositorySupport dongRepositorySupport;

	@Override
	public List<ResidenceInfo> getResidenceDetails(ResidenceDetailGetReq residenceDetailGetReq, ResidenceGetReq residenceGetReq) {
		return residenceInfoRepositorySupport.findRooms(residenceDetailGetReq, residenceGetReq);
	}

	@Override
	public List<ResidenceInfo> getResidencesBySiGuDong(ResidenceGetReq residenceGetReq) {
		return residenceInfoRepositorySupport.findRoomsBySiGuDong(residenceGetReq).fetch();
	}

	@Override
	public void deleteResidence(Long residenceId) {
		residenceInfoRepository.deleteById(residenceId);
	}

	@Override
	public void createResidence(ResidencePostReq residence) {
		ResidenceInfo residenceInfo = new ResidenceInfo();
//		residenceInfo.setDong(dongRepositorySupport.getDongByDongName(residence.getName()));
//		residenceInfo.setImageUrl();
//		residenceInfo.setFeature(residence.getFeature());
		residenceInfo.setEstateInfo(estateInfoRepositorySupport.getEstateInfoByRegistrationNumber(residence.getEstateNumber()).get());
		residenceInfo.setResidenceType(residenceTypeRepositorySupport.getResidenceTypeByTypeName(residence.getResidenceType()));
		residenceInfo.setResidenceCategory(residenceCategoryRepositorySupport.getResidenceCategoryByCategory(residence.getResidenceCategory()));
		residenceInfo.setArea(residence.getArea());
		residenceInfo.setBuildingFloor(residence.getBuildingFloor());
		residenceInfo.setContent(residence.getContent());
		residenceInfo.setCost(residence.getCost());
		residenceInfo.setJeonseCost(residence.getJeonseCost());
		residenceInfo.setWolseCost(residence.getWolseCost());
		residenceInfo.setManageCost(residence.getManageCost());
		residenceInfo.setDirection(residence.getDirection());
		residenceInfo.setLat(residence.getLat());
		residenceInfo.setLon(residence.getLon());
		residenceInfo.setMyFloor(residence.getMyFloor());
		residenceInfo.setName(residence.getName());
		residenceInfo.setStructure(residence.getStructure());
		residenceInfoRepository.save(residenceInfo);
	}
}
