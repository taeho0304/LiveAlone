package com.ssafy.api.service;

import com.ssafy.common.auth.SsafyUserDetails;
import com.ssafy.db.entity.ResidenceInfo;
import com.ssafy.db.entity.UserFavorite;
import com.ssafy.db.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

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
		SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
		UserFavorite userFavorite = new UserFavorite();
		userFavorite.setResidenceInfo(residenceInfoRepositorySupport.findById(residenceId));
		userFavorite.setUser(userService.getUserByUserId(userDetails.getUsername()));
		userFavoriteRepository.save(userFavorite);
	}

	@Override
	public List<UserFavorite> getFavoriteResidence(Authentication authentication) {
		SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
		List<UserFavorite> userFavorites = userFavoriteRepositorySupport.findByUserId(userService.getUserByUserId(userDetails.getUsername()).getId());
		return userFavorites;
	}

	@Override
	public void deleteFavoriteResidence(List<Long> userFavoriteIds, Authentication authentication) {
		for (Long userFavoriteId:userFavoriteIds)
			userFavoriteRepositorySupport.deleteByUserId(userFavoriteId);
	}

	@Override
	public UserFavorite checkDuplicated(Authentication authentication, Long residenceId) {
		SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
		return userFavoriteRepositorySupport.checkDuplicate(userDetails.getUser().getId(), residenceId);
	}
}
