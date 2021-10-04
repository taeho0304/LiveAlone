package com.ssafy.db.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.api.Model.ResidencePaging;
import com.ssafy.api.model.PositionModel;
import com.ssafy.api.request.ResidenceDetailGetReq;
import com.ssafy.api.request.ResidenceEstateIdsPostReq;
import com.ssafy.api.request.ResidenceGetReq;
import com.ssafy.db.entity.QResidenceInfo;
import com.ssafy.db.entity.QUserFavorite;
import com.ssafy.db.entity.ResidenceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * 방 검색 관련 디비 쿼리 생성을 위한 구현 정의.
 */
@Repository
public class ResidenceInfoRepositorySupport {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QResidenceInfo qresidenceInfo = QResidenceInfo.residenceInfo;
    QUserFavorite quserFavorite = QUserFavorite.userFavorite;
    int pageSize = 10;

    public ResidencePaging findRooms(ResidenceDetailGetReq residenceDetailGetReq) {
        JPAQuery<ResidenceInfo> residences = jpaQueryFactory.select(qresidenceInfo).from(qresidenceInfo);

        BooleanBuilder builder = new BooleanBuilder();
        for (int i = 0; i< residenceDetailGetReq.getResidenceType().size(); i++)
            builder.or(qresidenceInfo.residenceType.type.eq(residenceDetailGetReq.getResidenceType().get(i)));
        for (int i = 0; i< residenceDetailGetReq.getResidenceCategory().size(); i++)
            builder.or(qresidenceInfo.residenceCategory.categoryName.eq(residenceDetailGetReq.getResidenceCategory().get(i)));
        if (residenceDetailGetReq.getStartPrice() > 0) builder.and(qresidenceInfo.cost.goe(residenceDetailGetReq.getStartPrice()));
        if (residenceDetailGetReq.getEndPrice() > 0) builder.and(qresidenceInfo.cost.loe(residenceDetailGetReq.getEndPrice()));
        if (residenceDetailGetReq.getStartJPrice() > 0) builder.and(qresidenceInfo.jeonseCost.goe(residenceDetailGetReq.getStartJPrice()));
        if (residenceDetailGetReq.getEndJPrice() > 0) builder.and(qresidenceInfo.jeonseCost.loe(residenceDetailGetReq.getEndJPrice()));
        if (residenceDetailGetReq.getStartWPrice() > 0) builder.and(qresidenceInfo.wolseCost.goe(residenceDetailGetReq.getStartWPrice()));
        if (residenceDetailGetReq.getEndWPrice() > 0) builder.and(qresidenceInfo.wolseCost.loe(residenceDetailGetReq.getEndWPrice()));
        if (residenceDetailGetReq.getStartManagePrice() > 0) builder.and(qresidenceInfo.manageCost.goe(residenceDetailGetReq.getStartManagePrice()));
        if (residenceDetailGetReq.getEndManagePrice() > 0) builder.and(qresidenceInfo.manageCost.loe(residenceDetailGetReq.getEndManagePrice()));
        if (residenceDetailGetReq.getStartArea() > 0) builder.and(qresidenceInfo.area.goe(residenceDetailGetReq.getStartArea()));
        if (residenceDetailGetReq.getEndArea() > 0) builder.and(qresidenceInfo.area.loe(residenceDetailGetReq.getEndArea()));
        if (residenceDetailGetReq.getStartDeposit() > 0) builder.and(qresidenceInfo.deposit.goe(residenceDetailGetReq.getStartDeposit()));
        if (residenceDetailGetReq.getEndDeposit() > 0) builder.and(qresidenceInfo.deposit.loe(residenceDetailGetReq.getEndDeposit()));
        if (residenceDetailGetReq.getGugun() != null) builder.and(qresidenceInfo.dong.Gugun.gugunName.eq(residenceDetailGetReq.getGugun()));
        if (residenceDetailGetReq.getDong() != null) builder.and(qresidenceInfo.dong.dongName.eq(residenceDetailGetReq.getDong()));
        residences.where(builder);

        // 정렬
        if(residenceDetailGetReq.getSortType()!=null){
            if(residenceDetailGetReq.getSortType().equals("cost")){
                if(residenceDetailGetReq.getSortOrder().equals("asc")) residences.orderBy(qresidenceInfo.cost.asc());
                if(residenceDetailGetReq.getSortOrder().equals("desc")) residences.orderBy(qresidenceInfo.cost.desc());
            }
            if(residenceDetailGetReq.getSortType().equals("area")){
                if(residenceDetailGetReq.getSortOrder().equals("asc")) residences.orderBy(qresidenceInfo.area.asc());
                if(residenceDetailGetReq.getSortOrder().equals("desc")) residences.orderBy(qresidenceInfo.area.desc());
            }
            if(residenceDetailGetReq.getSortType().equals("favorite")){
                if(residenceDetailGetReq.getSortOrder().equals("asc")) residences.orderBy(quserFavorite.residenceInfo.id.count().asc());
                if(residenceDetailGetReq.getSortOrder().equals("desc")) residences.orderBy(quserFavorite.residenceInfo.id.count().desc());
            }
        }
        ResidencePaging residencePaging = new ResidencePaging();
        residencePaging.setResidenceInfos(residences.offset((residenceDetailGetReq.getPageNum()-1)*pageSize).limit(pageSize).fetch());
        residencePaging.setPageSize((residences.fetchCount()-1)/10+1);
        return residencePaging;
    }

    public ResidencePaging findRoomsBySiGuDong(ResidenceGetReq residenceDetailGetReq) {
        JPAQuery<ResidenceInfo> residences = jpaQueryFactory.select(qresidenceInfo).from(qresidenceInfo);
        BooleanBuilder builder = new BooleanBuilder();
        if (residenceDetailGetReq.getGugun() != null) builder.and(qresidenceInfo.dong.Gugun.gugunName.eq(residenceDetailGetReq.getGugun()));
        if (residenceDetailGetReq.getDong() != null) builder.and(qresidenceInfo.dong.dongName.eq(residenceDetailGetReq.getDong()));
        residences.where(builder);

        ResidencePaging residencePaging = new ResidencePaging();
        residencePaging.setResidenceInfos(residences.fetch());
        return residencePaging;
    }

    public long findGuGunCount(Long id) {
        long count = jpaQueryFactory.select(qresidenceInfo).from(qresidenceInfo)
                .where(qresidenceInfo.dong.Gugun.id.eq(id)).fetchCount();
        return count;
    }

    public long findDongCount(Long id) {
        long count = jpaQueryFactory.select(qresidenceInfo).from(qresidenceInfo)
                .where(qresidenceInfo.dong.id.eq(id)).fetchCount();
        return count;
    }

    public List<PositionModel> findPositionsByDongName(String dongName) {
        List<PositionModel> positionModels = new ArrayList<>();
        List<Tuple> list= jpaQueryFactory.select(qresidenceInfo.id, qresidenceInfo.lon, qresidenceInfo.lat).from(qresidenceInfo)
                .where(qresidenceInfo.dong.dongName.eq(dongName)).fetch();

        for(Tuple i: list){
            PositionModel positionModel = new PositionModel();
            positionModel.setId(i.get(qresidenceInfo.id));
            positionModel.setLat(i.get(qresidenceInfo.lat));
            positionModel.setLon(i.get(qresidenceInfo.lon));
            positionModels.add(positionModel);
        }

        return positionModels;
    }

    public ResidencePaging findRoomsByEstateId(ResidenceEstateIdsPostReq residenceEstateIdsPostReq) {
        JPAQuery<ResidenceInfo> residenceInfos = jpaQueryFactory.select(qresidenceInfo).from(qresidenceInfo)
                .where(qresidenceInfo.estateInfo.id.eq(residenceEstateIdsPostReq.getEstateId()));

        ResidencePaging residencePaging = new ResidencePaging();
        residencePaging.setResidenceInfos(residenceInfos.offset((residenceEstateIdsPostReq.getPageNum()-1)*pageSize).limit(pageSize).fetch());
        residencePaging.setPageSize((residenceInfos.fetchCount()-1)/10+1);
        return residencePaging;
    }
}
