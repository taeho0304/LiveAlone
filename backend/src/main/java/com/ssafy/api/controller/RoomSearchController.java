package com.ssafy.api.controller;

import com.ssafy.api.request.ResidenceGetReq;
import com.ssafy.api.response.*;
import com.ssafy.api.service.RoomSearchService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.ResidenceCategory;
import com.ssafy.db.entity.ResidenceInfo;
import com.ssafy.db.entity.ResidenceType;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * 방 검색 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "방 검색 API", tags = {"search"})
@RestController
@RequestMapping("/api/v1/search")
public class RoomSearchController {

    @Autowired
    RoomSearchService roomSearchService;

    @PostMapping("/roomtypes")
    @ApiOperation(value = "방 종류 생성", notes = "방 종류를 생성 한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "성공"),
            @ApiResponse(code = 500, message = "실패")
    })
    public ResponseEntity<? extends BaseResponseBody> createRoomType(
            @RequestBody @ApiParam(value = "방 종류", required = true) String roomtype) {
        try {
            roomSearchService.createResidenceCategory(roomtype);
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
    public ResponseEntity<ResidenceCategoryRes> getRoomType() {
        try {
            List<ResidenceCategory> ResidenceCategories = roomSearchService.getResidenceCategory();
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
    public ResponseEntity<BaseResponseBody> deleteRoomType(
            @RequestBody @ApiParam(value = "삭제 질문 리스트", required = true) List<Long> roomtype) {
        try {
            roomSearchService.deleteResidenceCategory(roomtype);
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
            @RequestBody @ApiParam(value = "거래 타입", required = true) String bargaintype) {
        try {
            roomSearchService.createResidenceType(bargaintype);
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
            List<ResidenceType> residenceType = roomSearchService.getResidenceType();
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
            roomSearchService.deleteResidenceType(bargainId);
            return ResponseEntity.status(201).body(BaseResponseBody.of(201, "Success"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(500).body(BaseResponseBody.of(500, "fail"));
        }
    }

    @GetMapping("/rooms")
    @ApiOperation(value = "거래 타입 조회", notes = "거래 타입을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 500, message = "실패")
    })
    public ResponseEntity<ResidenceRes> getRooms(
            @RequestBody @ApiParam(value = "매물 검색", required = false) ResidenceGetReq residenceGetReq
            ) {
        try {
            List<ResidenceInfo> rooms = roomSearchService.getResidenceInfos(residenceGetReq);
            return ResponseEntity.status(200).body(ResidenceRes.of(rooms));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(500).body(ResidenceRes.of(500, "fail"));
        }
    }
}