package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QResidenceDetail is a Querydsl query type for ResidenceDetail
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QResidenceDetail extends EntityPathBase<ResidenceDetail> {

    private static final long serialVersionUID = -87209227L;

    public static final QResidenceDetail residenceDetail = new QResidenceDetail("residenceDetail");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath floor = createString("floor");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath structure = createString("structure");

    public QResidenceDetail(String variable) {
        super(ResidenceDetail.class, forVariable(variable));
    }

    public QResidenceDetail(Path<? extends ResidenceDetail> path) {
        super(path.getType(), path.getMetadata());
    }

    public QResidenceDetail(PathMetadata metadata) {
        super(ResidenceDetail.class, metadata);
    }

}

