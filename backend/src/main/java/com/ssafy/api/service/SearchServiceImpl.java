package com.ssafy.api.service;

import com.ssafy.api.model.DongModel;
import com.ssafy.api.request.ResidenceDetailGetReq;
import com.ssafy.api.request.ResidenceGetReq;
import com.ssafy.common.auth.UserDetail;
import com.ssafy.db.entity.*;
import com.ssafy.db.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *	방 검색 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("residenceSearchService")
public class SearchServiceImpl implements SearchService {
	@Autowired
	ResidenceCategoryRepository residenceCategoryRepository;

	@Autowired
	ResidenceTypeRepository residenceTypeRepository;

	@Autowired
	SearchService searchService;

	@Autowired
	UserService userService;

	@Autowired
	SearchResidenceFilterRepository searchResidenceFilterRepository;

	@Autowired
	SearchResidenceFilterRepositorySupport searchResidenceFilterRepositorySupport;

	@Autowired
	ResidenceCategoryRepositorySupport residenceCategoryRepositorySupport;

	@Autowired
	ResidenceTypeRepositorySupport residenceTypeRepositorySupport;

	@Autowired
	SiRepository siRepository;

	@Autowired
	GuGunRepositorySupport guGunRepositorySupport;

	@Autowired
	DongRepositorySupport dongRepositorySupport;

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
	public void createSearchResidenceFilter(ResidenceDetailGetReq residenceDetailGetReq, Authentication authentication, ResidenceGetReq residenceGetReq) {
		UserDetail userDetail = (UserDetail) authentication.getDetails();
		User user = userService.getUserByUserId(userDetail.getUsername());

		try{
			SearchResidenceFilter searchResidenceFilter = searchResidenceFilterRepositorySupport.findSearchFilterByUserId(user.getId());
			saveFilter(searchResidenceFilter, residenceDetailGetReq, user, residenceGetReq);
		}catch (Exception e) {
			SearchResidenceFilter searchResidenceFilter = new SearchResidenceFilter();
			saveFilter(searchResidenceFilter, residenceDetailGetReq, user, residenceGetReq);
		}
	}

	@Override
	public List<Si> getSi() {
		return siRepository.findAll();
	}

	@Override
	public List<String> getGuGun(String siName) {
		List<Gugun> guGuns = guGunRepositorySupport.getGuGunBySiName(siName);
		List<String> guGunList = new ArrayList<>();
		for(int i=0; i<guGuns.size(); i++)
			guGunList.add(guGuns.get(i).getGugunName());
		return guGunList;
	}

	@Override
	public List<DongModel> getDong(String guGunName) {
		List<Dong> dongs = dongRepositorySupport.getDongByGuGunName(guGunName);
		List<DongModel> dongModels = new ArrayList<>();

		for(int i=0; i<dongs.size(); i++){
			DongModel dongModel = new DongModel();
			dongModel.setDongName(dongs.get(i).getDongName());
			dongModel.setLat(dongs.get(i).getLat());
			dongModel.setLon(dongs.get(i).getLon());
			dongModels.add(dongModel);
		}

		return dongModels;
	}

	private void saveFilter(SearchResidenceFilter searchResidenceFilter, ResidenceDetailGetReq residenceDetailGetReq, User user, ResidenceGetReq residenceGetReq) {
		List<ResidenceCategory> residenceCategoryList =  new ArrayList<>();
		for (int i=0; i<residenceDetailGetReq.getResidenceCategory().size(); i++)
			residenceCategoryList.add(residenceCategoryRepositorySupport.getResidenceCategoryByCategory(residenceDetailGetReq.getResidenceCategory().get(i)));
		searchResidenceFilter.setResidenceCategory(residenceCategoryList);

		List<ResidenceType> residenceTypeList =  new ArrayList<>();
		for (int i=0; i<residenceDetailGetReq.getResidenceType().size(); i++)
			residenceTypeList.add(residenceTypeRepositorySupport.getResidenceTypeByTypeName(residenceDetailGetReq.getResidenceType().get(i)));

		searchResidenceFilter.setDong(residenceGetReq.getDong());
		searchResidenceFilter.setSi(residenceGetReq.getSi());
		searchResidenceFilter.setGugun(residenceGetReq.getGugun());
		searchResidenceFilter.setResidenceType(residenceTypeList);
		searchResidenceFilter.setUser(user);
		searchResidenceFilter.setStartPrice(residenceDetailGetReq.getStartPrice());
		searchResidenceFilter.setEndPrice(residenceDetailGetReq.getEndPrice());
		searchResidenceFilter.setStartJPrice(residenceDetailGetReq.getStartJPrice());
		searchResidenceFilter.setEndJPrice(residenceDetailGetReq.getEndJPrice());
		searchResidenceFilter.setStartWPrice(residenceDetailGetReq.getStartWPrice());
		searchResidenceFilter.setEndWPrice(residenceDetailGetReq.getEndWPrice());
		searchResidenceFilter.setStartManagePrice(residenceDetailGetReq.getStartManagePrice());
		searchResidenceFilter.setEndManagePrice(residenceDetailGetReq.getEndManagePrice());
		searchResidenceFilter.setStartArea(residenceDetailGetReq.getStartArea());
		searchResidenceFilter.setEndArea(residenceDetailGetReq.getEndArea());
		searchResidenceFilterRepository.save(searchResidenceFilter);
	}
}