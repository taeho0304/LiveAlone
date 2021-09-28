package com.ssafy.api.service;

import com.ssafy.api.model.CountModel;
import com.ssafy.api.request.ResidenceDetailGetReq;
import com.ssafy.api.request.ResidenceGetReq;
import com.ssafy.api.request.ResidencePostReq;
import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.db.entity.EstateInfo;
import com.ssafy.db.entity.ResidenceInfo;
import com.ssafy.db.entity.User;

import java.util.List;

/**
 *	매물 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface ResidenceService {
	List<ResidenceInfo> getResidenceDetails(ResidenceDetailGetReq residenceDetailGetReq, ResidenceGetReq residenceGetReq);

	List<ResidenceInfo> getResidencesBySiGuDong(ResidenceGetReq residenceGetReq);

	void deleteResidence(Long residenceId);

	void createResidence(ResidencePostReq residence);

    List<CountModel> getGetGuCount();

    List<CountModel> getGetDongCount();
}
