package com.ssafy.api.service;

import com.ssafy.api.Model.ResidencePaging;
import com.ssafy.api.Model.ResidenceSearchPaging;
import com.ssafy.api.Model.ResidenceModel;
import com.ssafy.api.model.CountModel;
import com.ssafy.api.model.PositionModel;
import com.ssafy.api.request.*;
import com.ssafy.db.entity.ResidenceInfo;
import org.springframework.security.core.Authentication;

import java.io.IOException;
import java.util.List;

/**
 *	매물 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface ResidenceService {
	ResidenceSearchPaging getResidenceDetails(ResidenceDetailGetReq residenceDetailGetReq, Authentication authentication);

	ResidencePaging getResidencesBySiGuDong(ResidenceGetReq residenceGetReq);

	void deleteResidence(Long residenceId);

	void createResidence(ResidencePostReq residence) throws IOException;

    List<CountModel> getGetGuCount();

    List<CountModel> getGetDongCount();

	List<PositionModel> getPosition(String dongName);

	ResidenceSearchPaging getResidencesById(ResidenceIdsPostReq residenceIdsPostReq, Authentication authentication);

	ResidencePaging getResidencesByEstateId(ResidenceEstateIdsPostReq residenceId);

	void patchResidence(ResidencePostReq residence, long residenceId) throws IOException;
}
