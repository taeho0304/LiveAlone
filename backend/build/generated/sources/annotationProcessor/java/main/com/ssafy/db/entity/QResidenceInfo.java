package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QResidenceInfo is a Querydsl query type for ResidenceInfo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QResidenceInfo extends EntityPathBase<ResidenceInfo> {

    private static final long serialVersionUID = 1917382738L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QResidenceInfo residenceInfo = new QResidenceInfo("residenceInfo");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final NumberPath<Integer> area = createNumber("area", Integer.class);

    public final StringPath content = createString("content");

    public final NumberPath<Integer> cost = createNumber("cost", Integer.class);

    public final StringPath dong = createString("dong");

    public final StringPath gu = createString("gu");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath imgurl = createString("imgurl");

    public final StringPath lat = createString("lat");

    public final StringPath lon = createString("lon");

    public final NumberPath<Integer> manageCost = createNumber("manageCost", Integer.class);

    public final StringPath name = createString("name");

    public final QResidenceCategory residenceCategory;

    public final QResidenceDetail residenceDetail;

    public final QResidenceEstate residenceEstate;

    public final QResidenceType residenceType;

    public final QResidenceWeight residenceWeight;

    public QResidenceInfo(String variable) {
        this(ResidenceInfo.class, forVariable(variable), INITS);
    }

    public QResidenceInfo(Path<? extends ResidenceInfo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QResidenceInfo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QResidenceInfo(PathMetadata metadata, PathInits inits) {
        this(ResidenceInfo.class, metadata, inits);
    }

    public QResidenceInfo(Class<? extends ResidenceInfo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.residenceCategory = inits.isInitialized("residenceCategory") ? new QResidenceCategory(forProperty("residenceCategory")) : null;
        this.residenceDetail = inits.isInitialized("residenceDetail") ? new QResidenceDetail(forProperty("residenceDetail")) : null;
        this.residenceEstate = inits.isInitialized("residenceEstate") ? new QResidenceEstate(forProperty("residenceEstate")) : null;
        this.residenceType = inits.isInitialized("residenceType") ? new QResidenceType(forProperty("residenceType")) : null;
        this.residenceWeight = inits.isInitialized("residenceWeight") ? new QResidenceWeight(forProperty("residenceWeight"), inits.get("residenceWeight")) : null;
    }

}

