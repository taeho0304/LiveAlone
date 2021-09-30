package com.ssafy.api.service;

import com.ssafy.db.entity.ResidenceInfo;
import com.ssafy.db.entity.UserFavorite;
import org.springframework.security.core.Authentication;

import java.util.List;

/**
 *	유저 관심 매물 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface UserFavoriteService {

	void createFavoriteResidence(Long residenceId, Authentication authentication);

	List<UserFavorite> getFavoriteResidence(Authentication authentication);

	void deleteFavoriteResidence(List<Long> userFavoriteIds);

	UserFavorite checkDuplicated(Authentication authentication, Long residenceId);
}
