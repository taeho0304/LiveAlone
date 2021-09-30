package com.ssafy.api.service;

import com.querydsl.core.Tuple;
import com.ssafy.api.model.CountModel;
import com.ssafy.api.model.PositionModel;
import com.ssafy.api.request.ResidenceDetailGetReq;
import com.ssafy.api.request.ResidenceGetReq;
import com.ssafy.api.request.ResidencePostReq;
import com.ssafy.db.entity.*;
import com.ssafy.db.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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
	ResidenceTypeRepository residenceTypeRepository;

	@Autowired
	ResidenceCategoryRepository residenceCategoryRepository;

	@Autowired
	EstateInfoRepository estateInfoRepository;

	@Autowired
	DongRepositorySupport dongRepositorySupport;

	@Autowired
	DongRepository dongRepository;

	@Autowired
	GuGunRepository guGunRepository;

	@Autowired
	FeatureRepositorySupport featureRepositorySupport;

	@Autowired
	FeatureRepository featureRepository;

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
	public void createResidence(ResidencePostReq residence) throws IOException {
		List<ImageUrl> imageUrls = new ArrayList<>();
		for (MultipartFile thumbnail:residence.getThumbnails()) {
			ImageUrl imageUrl = new ImageUrl();
			imageUrl.setUrl(saveThumbnail(thumbnail));
			imageUrls.add(imageUrl);
		}
		ResidenceInfo residenceInfo = new ResidenceInfo();
		residenceInfo.setDong(dongRepository.findById(residence.getDong()).get());
		System.out.println(residenceInfo.getDong().getDongName());
		System.out.println(residenceInfo.getDong().getId());
		residenceInfo.setImageUrl(imageUrls);
		residenceInfo.setFeature(setFeature(residence.getFeature()));
		residenceInfo.setEstateInfo(estateInfoRepository.findById(residence.getEstateId()).get());
		residenceInfo.setResidenceType(residenceTypeRepository.findById(residence.getResidenceType()).get());
		residenceInfo.setResidenceCategory(residenceCategoryRepository.findById(residence.getResidenceCategory()).get());
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

	@Override
	public List<CountModel> getGetGuCount() {
		List<CountModel> countModelList = new ArrayList<>();
		List<Gugun> gugunList = guGunRepository.findAll();

		for (Gugun gugun : gugunList) {
			CountModel countModel = new CountModel();
			countModel.setLon(gugun.getLon());
			countModel.setLat(gugun.getLat());
			countModel.setName(gugun.getGugunName());
			countModel.setCount(residenceInfoRepositorySupport.findGuGunCount(gugun.getId()));
			countModelList.add(countModel);
		}
		return countModelList;
	}

	@Override
	public List<CountModel> getGetDongCount() {
		List<CountModel> countModelList = new ArrayList<>();
		List<Dong> dongList = dongRepository.findAll();

		for (Dong dong : dongList) {
			CountModel countModel = new CountModel();
			countModel.setLon(dong.getLon());
			countModel.setLat(dong.getLat());
			countModel.setName(dong.getDongName());
			countModel.setCount(residenceInfoRepositorySupport.findDongCount(dong.getId()));
			countModelList.add(countModel);
		}
		return countModelList;
	}

	@Override
	public List<PositionModel> getPosition(String dongName) {
		List<PositionModel> positionModels = residenceInfoRepositorySupport.findPositionsByDongName(dongName);
		return positionModels;
	}

	@Override
	public List<ResidenceInfo> getResidencesById(List<Long> residenceIds) {
		List<ResidenceInfo> residenceInfos = new ArrayList<>();
		for(Long id : residenceIds){
			ResidenceInfo residenceInfo = residenceInfoRepository.findById(id).get();
			residenceInfos.add(residenceInfo);
		}

		return residenceInfos;
	}

	private List<Feature> setFeature(List<String> featureList) {
		List<Feature> features = new ArrayList<>();
		for(int i=0; i<featureList.size(); i++){
			Optional<Feature> featureOptional = featureRepositorySupport.findFeatureByFeatureName(featureList.get(i));
			Feature feature = new Feature();
			if(!featureOptional.isPresent()){
				feature.setFeatureName(featureList.get(i));
				featureRepository.save(feature);
				feature = featureRepositorySupport.findFeatureByFeatureName(featureList.get(i)).get();
			}else feature = featureOptional.get();
			features.add(feature);
		}
		return features;
	}

	private String saveThumbnail(MultipartFile thumbnail) throws IOException {
		String path = "images/";
		File file = new File(path);
		if (!file.exists())
			file.mkdirs();

		String url = "";
		if (thumbnail != null) {
			String originalFileExtension = thumbnail.getOriginalFilename().substring(thumbnail.getOriginalFilename().lastIndexOf("."));
			String new_file_name = Long.toString(System.nanoTime()) + originalFileExtension;

			url = "images" + File.separator + new_file_name;
			Path pathAbs = Paths.get(url).toAbsolutePath();
			thumbnail.transferTo(pathAbs.toFile());
		}
		return url;
	}
}

