package com.ssafy.db.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.api.request.ResidenceGetReq;
import com.ssafy.db.entity.QResidenceInfo;
import com.ssafy.db.entity.ResidenceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 방 검색 관련 디비 쿼리 생성을 위한 구현 정의.
 */
@Repository
public class ResidenceInfoRepositorySupport {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QResidenceInfo qresidenceInfo = QResidenceInfo.residenceInfo;

    public List<ResidenceInfo> findRooms(ResidenceGetReq residenceGetReq) {
        JPAQuery<ResidenceInfo> rooms = jpaQueryFactory.select(qresidenceInfo).from(qresidenceInfo);

        // where 절
        BooleanBuilder builder = new BooleanBuilder();
        if (residenceGetReq.getRoomType() != null) builder.and(qresidenceInfo.residenceType.type.eq(residenceGetReq.getBargainType()));
        if (residenceGetReq.getBargainType() != null) builder.and(qresidenceInfo.residenceCategory.categoryName.eq(residenceGetReq.getRoomType()));
        if (residenceGetReq.getStartPrice() > 0) builder.and(qresidenceInfo.cost.goe(residenceGetReq.getStartPrice()));
        if (residenceGetReq.getEndPrice() > 0) builder.and(qresidenceInfo.cost.loe(residenceGetReq.getEndPrice()));
        if (residenceGetReq.getStartManagePrice() > 0) builder.and(qresidenceInfo.manageCost.goe(residenceGetReq.getStartManagePrice()));
        if (residenceGetReq.getEndManagePrice() > 0) builder.and(qresidenceInfo.manageCost.loe(residenceGetReq.getEndManagePrice()));
        if (residenceGetReq.getStartArea() > 0) builder.and(qresidenceInfo.area.goe(residenceGetReq.getStartArea()));
        if (residenceGetReq.getEndArea() > 0) builder.and(qresidenceInfo.area.loe(residenceGetReq.getEndArea()));
        rooms.where(builder);

        // 정렬
        if(residenceGetReq.getSortType().equals("cost")){
            if(residenceGetReq.getSortOrder().equals("asc")) rooms.orderBy(qresidenceInfo.cost.asc());
            if(residenceGetReq.getSortOrder().equals("desc")) rooms.orderBy(qresidenceInfo.cost.desc());
        }
        if(residenceGetReq.getSortType().equals("area")){
            if(residenceGetReq.getSortOrder().equals("asc")) rooms.orderBy(qresidenceInfo.area.asc());
            if(residenceGetReq.getSortOrder().equals("desc")) rooms.orderBy(qresidenceInfo.area.desc());
        }

        return rooms.fetch();
    }

}
