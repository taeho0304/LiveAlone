package com.ssafy.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.QSearchResidenceFilter;
import com.ssafy.db.entity.QUserFavorite;
import com.ssafy.db.entity.SearchResidenceFilter;
import com.ssafy.db.entity.UserFavorite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * 유저 검색 필터 관련 디비 쿼리 생성을 위한 구현 정의.
 */
@Repository
public class SearchResidenceFilterRepositorySupport {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QSearchResidenceFilter qSearchResidenceFilter = QSearchResidenceFilter.searchResidenceFilter;

    public SearchResidenceFilter findSearchFilterByUserId(Long id) {
        SearchResidenceFilter searchResidenceFilter = jpaQueryFactory.select(qSearchResidenceFilter).from(qSearchResidenceFilter)
                .where(qSearchResidenceFilter.user.id.eq(id)).fetchOne();
        return searchResidenceFilter;
    }
}