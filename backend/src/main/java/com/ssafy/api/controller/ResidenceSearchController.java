package com.ssafy.api.controller;

import com.ssafy.api.model.DongModel;
import com.ssafy.api.request.ResidenceDetailGetReq;
import com.ssafy.api.request.ResidenceGetReq;
import com.ssafy.api.response.*;
import com.ssafy.api.service.ResidenceSearchService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.*;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * 방 검색 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "방 검색 API", tags = {"Search"})
@RestController
@RequestMapping("/api/v1/search")
public class ResidenceSearchController {

    @Autowired
    ResidenceSearchService residenceSearchService;

    @PostMapping("/roomtypes")
    @ApiOperation(value = "방 종류 생성", notes = "방 종류를 생성 한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "성공"),
            @ApiResponse(code = 500, message = "실패")
    })
    public ResponseEntity<? extends BaseResponseBody> createResidenceType(
            @RequestBody @ApiParam(value = "방 종류", required = true) String residenceType) {
        try {
            residenceSearchService.createResidenceCategory(residenceType);
            return ResponseEntity.status(201).body(BaseResponseBody.of(201, "Success"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(500).body(BaseResponseBody.of(500, "fail"));
        }
    }

    @GetMapping("/roomtypes")
    @ApiOperation(value = "방 종류 조회", notes = "방 종류를 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<ResidenceCategoryRes> getResidenceType() {
        try {
            List<ResidenceCategory> ResidenceCategories = residenceSearchService.getResidenceCategory();
            return ResponseEntity.status(200).body(ResidenceCategoryRes.of(ResidenceCategories));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(500).body(ResidenceCategoryRes.of(500, "fail"));
        }
    }

    @DeleteMapping("roomtypes")
    @ApiOperation(value = "방 종류 삭제", notes = "방 종류를 삭제한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "삭제 성공"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<BaseResponseBody> deleteResidenceType(
            @RequestBody @ApiParam(value = "삭제 질문 리스트", required = true) List<Long> residenceType) {
        try {
            residenceSearchService.deleteResidenceCategory(residenceType);
            return ResponseEntity.status(200).body(UserLoginPostRes.of(201, "Success"));
        }catch (Exception e){
            return ResponseEntity.status(500).body(UserLoginPostRes.of(500, "Fail"));
        }
    }

    @PostMapping("/bargaintypes")
    @ApiOperation(value = "거래 타입 생성", notes = "거래 타입을 생성한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "성공"),
            @ApiResponse(code = 500, message = "실패")
    })
    public ResponseEntity<? extends BaseResponseBody> createBargainType(
            @RequestBody @ApiParam(value = "거래 타입", required = true) String bargainType) {
        try {
            residenceSearchService.createResidenceType(bargainType);
            return ResponseEntity.status(201).body(BaseResponseBody.of(201, "Success"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(500).body(BaseResponseBody.of(500, "fail"));
        }
    }

    @GetMapping("/bargaintypes")
    @ApiOperation(value = "거래 타입 조회", notes = "거래 타입을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 500, message = "실패")
    })
    public ResponseEntity<ResidenceTypeRes> getBargainType() {
        try {
            List<ResidenceType> residenceType = residenceSearchService.getResidenceType();
            return ResponseEntity.status(200).body(ResidenceTypeRes.of(residenceType));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(500).body(ResidenceTypeRes.of(500, "fail"));
        }
    }

    @DeleteMapping("/bargaintypes")
    @ApiOperation(value = "거래 타입 삭제", notes = "거래 타입을 삭제한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "성공"),
            @ApiResponse(code = 500, message = "실패")
    })
    public ResponseEntity<? extends BaseResponseBody> deleteBargainType(
            @RequestBody @ApiParam(value = "질문 옵션 리스트 삭제", required = true)List<Long> bargainId) {
        try {
            residenceSearchService.deleteResidenceType(bargainId);
            return ResponseEntity.status(201).body(BaseResponseBody.of(201, "Success"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(500).body(BaseResponseBody.of(500, "fail"));
        }
    }

    @GetMapping("/si")
    @ApiOperation(value = "시 목록 조회", notes = "시 목록을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 500, message = "실패")
    })
    public ResponseEntity<SiRes> getSi() {
        try {
            List<Si> siList = residenceSearchService.getSi();
            return ResponseEntity.status(200).body(SiRes.of(siList));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(500).body(SiRes.of(500, "fail"));
        }
    }

    @GetMapping("/gugun")
    @ApiOperation(value = "구군 목록 조회", notes = "구군 목록을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 500, message = "실패")
    })
    public ResponseEntity<GuGunRes> getGuGun( @RequestParam String siName ) {
        try {
            List<String> guGunList = residenceSearchService.getGuGun(siName);
            return ResponseEntity.status(200).body(GuGunRes.of(guGunList));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(500).body(GuGunRes.of(500, "fail"));
        }
    }

    @GetMapping("/dong")
    @ApiOperation(value = "동 목록 조회", notes = "동 목록을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 500, message = "실패")
    })
    public ResponseEntity<DongRes> getDong( @RequestParam String dong ) {
        try {
            List<DongModel> dongModelList = residenceSearchService.getDong(dong);
            return ResponseEntity.status(200).body(DongRes.of(dongModelList));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(500).body(DongRes.of(500, "fail"));
        }
    }

    @GetMapping("/residences")
    @ApiOperation(value = "매물 구/군/동으로 조회", notes = "매물을 상세 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 500, message = "실패")
    })
    public ResponseEntity<ResidenceRes> getResidences( @ModelAttribute ResidenceGetReq residenceGetReq) {
        try {
            List<ResidenceInfo> rooms = residenceSearchService.getResidencesBySiGuDong(residenceGetReq);
            return ResponseEntity.status(200).body(ResidenceRes.of(rooms));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(500).body(ResidenceRes.of(500, "fail"));
        }
    }

    @GetMapping("/residences/detail")
    @ApiOperation(value = "매물 상세필터로 조회", notes = "매물을 상세 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 500, message = "실패")
    })
    public ResponseEntity<ResidenceRes> getResidencesDetail(
            @ModelAttribute ResidenceDetailGetReq residenceDetailGetReq, @ModelAttribute ResidenceGetReq residenceGetReq) {
        try {
            List<ResidenceInfo> rooms = residenceSearchService.getResidenceDetails(residenceDetailGetReq, residenceGetReq);
            return ResponseEntity.status(200).body(ResidenceRes.of(rooms));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(500).body(ResidenceRes.of(500, "fail"));
        }
    }

    @PostMapping("/save")
    @ApiOperation(value = "유저 검색 필터 저장", notes = "유저 검색 필터를 저장한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 500, message = "실패")
    })
    public ResponseEntity<? extends BaseResponseBody> createUserResidenceSearchFilter(
            @ModelAttribute ResidenceDetailGetReq residenceDetailGetReq, @ApiIgnore Authentication authentication,
            @ModelAttribute ResidenceGetReq residenceGetReq) {
        try {
            residenceSearchService.createSearchResidenceFilter(residenceDetailGetReq, authentication, residenceGetReq);
            return ResponseEntity.status(200).body(BaseResponseBody.of(201, "success"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(500).body(BaseResponseBody.of(500, "fail"));
        }
    }
}
