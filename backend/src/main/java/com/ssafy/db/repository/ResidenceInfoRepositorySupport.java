package com.ssafy.db.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.api.request.ResidenceDetailGetReq;
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

    public List<ResidenceInfo> findRooms(ResidenceDetailGetReq residenceDetailGetReq, ResidenceGetReq residenceGetReq) {
        JPAQuery<ResidenceInfo> residences = findRoomsBySiGuDong(residenceGetReq);

        BooleanBuilder builder = new BooleanBuilder();
        for (int i = 0; i< residenceDetailGetReq.getResidenceType().size(); i++)
            builder.and(qresidenceInfo.residenceType.type.eq(residenceDetailGetReq.getResidenceType().get(i)));
        for (int i = 0; i< residenceDetailGetReq.getResidenceCategory().size(); i++)
            builder.and(qresidenceInfo.residenceCategory.categoryName.eq(residenceDetailGetReq.getResidenceCategory().get(i)));
        if (residenceDetailGetReq.getStartPrice() > 0) builder.and(qresidenceInfo.cost.goe(residenceDetailGetReq.getStartPrice()));
        if (residenceDetailGetReq.getEndPrice() > 0) builder.and(qresidenceInfo.cost.loe(residenceDetailGetReq.getEndPrice()));
        if (residenceDetailGetReq.getStartJPrice() > 0) builder.and(qresidenceInfo.jeonseCost.goe(residenceDetailGetReq.getStartPrice()));
        if (residenceDetailGetReq.getEndJPrice() > 0) builder.and(qresidenceInfo.jeonseCost.loe(residenceDetailGetReq.getEndPrice()));
        if (residenceDetailGetReq.getStartWPrice() > 0) builder.and(qresidenceInfo.wolseCost.goe(residenceDetailGetReq.getStartPrice()));
        if (residenceDetailGetReq.getEndWPrice() > 0) builder.and(qresidenceInfo.wolseCost.loe(residenceDetailGetReq.getEndPrice()));
        if (residenceDetailGetReq.getStartManagePrice() > 0) builder.and(qresidenceInfo.manageCost.goe(residenceDetailGetReq.getStartManagePrice()));
        if (residenceDetailGetReq.getEndManagePrice() > 0) builder.and(qresidenceInfo.manageCost.loe(residenceDetailGetReq.getEndManagePrice()));
        if (residenceDetailGetReq.getStartArea() > 0) builder.and(qresidenceInfo.area.goe(residenceDetailGetReq.getStartArea()));
        if (residenceDetailGetReq.getEndArea() > 0) builder.and(qresidenceInfo.area.loe(residenceDetailGetReq.getEndArea()));
        residences.where(builder);

        // 정렬
//        if(residenceDetailGetReq.getSortType()!=null){
//            if(residenceDetailGetReq.getSortType().equals("cost")){
//                if(residenceDetailGetReq.getSortOrder().equals("asc")) residences.orderBy(qresidenceInfo.cost.asc());
//                if(residenceDetailGetReq.getSortOrder().equals("desc")) residences.orderBy(qresidenceInfo.cost.desc());
//            }
//            if(residenceDetailGetReq.getSortType().equals("area")){
//                if(residenceDetailGetReq.getSortOrder().equals("asc")) residences.orderBy(qresidenceInfo.area.asc());
//                if(residenceDetailGetReq.getSortOrder().equals("desc")) residences.orderBy(qresidenceInfo.area.desc());
//            }
//        }

        return residences.fetch();
    }

    public ResidenceInfo findById(Long residenceId) {
        ResidenceInfo residenceInfo = jpaQueryFactory.select(qresidenceInfo).from(qresidenceInfo)
                .where(qresidenceInfo.id.eq(residenceId)).fetchOne();
        return residenceInfo;
    }

    public JPAQuery<ResidenceInfo> findRoomsBySiGuDong(ResidenceGetReq residenceGetReq) {
        JPAQuery<ResidenceInfo> residences = jpaQueryFactory.select(qresidenceInfo).from(qresidenceInfo);
        BooleanBuilder builder = new BooleanBuilder();
        if (residenceGetReq.getSi() != null) builder.and(qresidenceInfo.dong.Gugun.Si.siName.eq(residenceGetReq.getSi()));
        if (residenceGetReq.getDong() != null) builder.and(qresidenceInfo.dong.dongName.eq(residenceGetReq.getDong()));
        if (residenceGetReq.getGugun() != null) builder.and(qresidenceInfo.dong.Gugun.gugunName.eq(residenceGetReq.getGugun()));
        residences.where(builder);

        return residences;
    }
}
