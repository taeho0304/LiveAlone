package com.ssafy.api.response;

import com.ssafy.api.Model.QnA;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Question;
import com.ssafy.db.entity.QuestionOption;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 질문 옵션 조회 API ([GET] /api/v1/questions/options) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("QuestionOptionRes")
public class QuestionOptionRes extends BaseResponseBody {
	@ApiModelProperty(name="Question ID")
	List<QnA> qna = new ArrayList<>();
	
	public static QuestionOptionRes of(List<QnA> questionOptions) {
		QuestionOptionRes res = new QuestionOptionRes();
		res.setQna(questionOptions);
		return res;
	}

	public static QuestionOptionRes of(Integer setStatusCode, String message) {
		QuestionOptionRes res = new QuestionOptionRes();
		res.setStatusCode(setStatusCode);
		res.setMessage(message);
		return res;
	}
}
