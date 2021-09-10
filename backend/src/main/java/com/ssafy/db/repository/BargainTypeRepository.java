package com.ssafy.db.repository;

import com.ssafy.db.entity.BargainType;
import com.ssafy.db.entity.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 거래 종류 옵션 모델 관련 디비 쿼리 생성을 위한 JPA Query Method 인터페이스 정의.
 */
@Repository
public interface BargainTypeRepository extends JpaRepository<BargainType, Long> {
}