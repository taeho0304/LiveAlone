package com.ssafy.api.controller;

import com.ssafy.api.Model.ResidenceModel;
import com.ssafy.api.model.CountModel;
import com.ssafy.api.model.PositionModel;
import com.ssafy.api.request.ResidenceDetailGetReq;
import com.ssafy.api.request.ResidenceGetReq;
import com.ssafy.api.request.ResidencePostReq;
import com.ssafy.api.response.*;
import com.ssafy.api.service.ResidenceService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.ResidenceInfo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.swing.text.Position;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * 방 검색 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "방 생성, 삭제 API", tags = {"Residence"})
@RestController
@RequestMapping("/api/v1/residences")
public class ResidenceController {

    @Autowired
    ResidenceService residenceService;

    @GetMapping()
    @ApiOperation(value = "매물 구/군/동으로 조회", notes = "매물을 상세 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 500, message = "실패")
    })
    public ResponseEntity<ResidenceRes> getResidences( @ModelAttribute ResidenceGetReq residenceGetReq) {
        try {
            List<ResidenceInfo> rooms = residenceService.getResidencesBySiGuDong(residenceGetReq);
            return ResponseEntity.status(200).body(ResidenceRes.of(rooms));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(500).body(ResidenceRes.of(500, "fail"));
        }
    }

    @PostMapping("/ids")
    @ApiOperation(value = "매물 id로 조회", notes = "매물을 id로 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 500, message = "실패")
    })
    public ResponseEntity<ResidenceDetailRes> getResidences( @RequestBody List<Long> residenceIds, @ApiIgnore Authentication authentication) {
        try {
            List<ResidenceModel> rooms = residenceService.getResidencesById(residenceIds, authentication);
            return ResponseEntity.status(200).body(ResidenceDetailRes.of(rooms));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(500).body(ResidenceDetailRes.of(500, "fail"));
        }
    }

    @GetMapping("/positions")
    @ApiOperation(value = "전체 동 좌표 조회", notes = "전체 동 좌표를 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 500, message = "실패")
    })
    public ResponseEntity<PositionRes> getPositions( @RequestParam(value = "동이름") String dongName ) {
        try {
            List<PositionModel> positionModels = residenceService.getPosition(dongName);
            return ResponseEntity.status(200).body(PositionRes.of(positionModels));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(500).body(PositionRes.of(500, "fail"));
        }
    }

    @PostMapping("/detail")
    @ApiOperation(value = "매물 상세필터로 조회", notes = "매물을 상세 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 500, message = "실패")
    })
    public ResponseEntity<ResidenceDetailRes> getResidencesDetail(
            @RequestBody @ApiParam(value = "매물 상세", required = true) ResidenceDetailGetReq residenceDetailGetReq, @ApiIgnore Authentication authentication
    ) {
        try {
            List<ResidenceModel> rooms = residenceService.getResidenceDetails(residenceDetailGetReq, authentication);
            return ResponseEntity.status(200).body(ResidenceDetailRes.of(rooms));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(500).body(ResidenceDetailRes.of(500, "fail"));
        }
    }

    @GetMapping("/gucount")
    @ApiOperation(value = "구 매물 개수 조회", notes = "구 매물 개수를 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 500, message = "실패")
    })
    public ResponseEntity<CountRes> getGuCount() {
        try {
            List<CountModel> guCounts = residenceService.getGetGuCount();
            return ResponseEntity.status(200).body(CountRes.of(guCounts));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(500).body(CountRes.of(500, "fail"));
        }
    }

    @GetMapping("/dongcount")
    @ApiOperation(value = "동 매물 개수 조회", notes = "구 매물 개수를 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 500, message = "실패")
    })
    public ResponseEntity<CountRes> getDongCount() {
        try {
            List<CountModel> dongCounts = residenceService.getGetDongCount();
            return ResponseEntity.status(200).body(CountRes.of(dongCounts));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(500).body(CountRes.of(500, "fail"));
        }
    }

    @PostMapping()
    @ApiOperation(value = "매물 생성", notes = "방 종류를 생성 한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "성공"),
            @ApiResponse(code = 500, message = "실패")
    })
    public ResponseEntity<? extends BaseResponseBody> createResidenceType(
            ResidencePostReq residence
    ) {
        try {
            residenceService.createResidence(residence);
            return ResponseEntity.status(201).body(BaseResponseBody.of(201, "Success"));
        } catch (NoSuchElementException | IOException e) {
            return ResponseEntity.status(500).body(BaseResponseBody.of(500, "fail"));
        }
    }

    @DeleteMapping()
    @ApiOperation(value = "방 종류 삭제", notes = "방 종류를 삭제한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "삭제 성공"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<BaseResponseBody> deleteResidenceType(
            @RequestBody @ApiParam(value = "매물 삭제", required = true) Long residenceId) {
        try {
            residenceService.deleteResidence(residenceId);
            return ResponseEntity.status(200).body(UserLoginPostRes.of(201, "Success"));
        }catch (Exception e){
            return ResponseEntity.status(500).body(UserLoginPostRes.of(500, "fail"));
        }
    }
}
