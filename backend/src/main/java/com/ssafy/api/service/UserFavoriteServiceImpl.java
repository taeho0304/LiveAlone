package com.ssafy.api.service;

import com.ssafy.common.auth.UserDetail;
import com.ssafy.db.entity.UserFavorite;
import com.ssafy.db.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *	유저 관심 매물 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("userFavoriteService")
public class UserFavoriteServiceImpl implements UserFavoriteService {
	@Autowired
	UserFavoriteRepository userFavoriteRepository;

	@Autowired
	UserFavoriteRepositorySupport userFavoriteRepositorySupport;

	@Autowired
	UserFavoriteService userFavoriteService;

	@Autowired
	ResidenceInfoRepository residenceInfoRepository;

	@Autowired
	ResidenceInfoRepositorySupport residenceInfoRepositorySupport;
	
	@Autowired
	UserService userService;

	@Override
	public void createFavoriteResidence(Long residenceId, Authentication authentication) {
		UserDetail userDetail = (UserDetail) authentication.getDetails();
		UserFavorite userFavorite = new UserFavorite();
		userFavorite.setResidenceInfo(residenceInfoRepository.findById(residenceId).get());
		userFavorite.setUser(userService.getUserByUserId(userDetail.getUsername()));
		userFavoriteRepository.save(userFavorite);
	}

	@Override
	public List<UserFavorite> checkDuplicate(Authentication authentication) {
		UserDetail userDetail = (UserDetail) authentication.getDetails();
		List<UserFavorite> userFavorites = userFavoriteRepositorySupport.findByUserId(userService.getUserByUserId(userDetail.getUsername()).getId());
		return userFavorites;
	}

	@Override
	public void deleteFavoriteResidence(List<Long> userFavoriteIds) {
		for (Long userFavoriteId:userFavoriteIds){
			userFavoriteRepository.deleteById(userFavoriteId);
		}
	}

	@Override
	public Boolean getFavoriteResidences(Authentication authentication, Long residenceId) {
		UserDetail userDetail = (UserDetail) authentication.getDetails();
		Boolean isFavorite = userFavoriteRepositorySupport.checkIsFavorite(userDetail.getUser().getId(), residenceId);
		return isFavorite;
	}
}
