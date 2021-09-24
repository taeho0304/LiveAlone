package com.ssafy.db.repository;

import com.ssafy.db.entity.SearchResidenceFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 방 검색 옵션 저장 모델 관련 디비 쿼리 생성을 위한 JPA Query Method 인터페이스 정의.
 */
@Repository
public interface SearchResidenceFilterRepository extends JpaRepository<SearchResidenceFilter, Long> {
}