package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QResidenceType is a Querydsl query type for ResidenceType
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QResidenceType extends EntityPathBase<ResidenceType> {

    private static final long serialVersionUID = 1917721310L;

    public static final QResidenceType residenceType = new QResidenceType("residenceType");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath type = createString("type");

    public QResidenceType(String variable) {
        super(ResidenceType.class, forVariable(variable));
    }

    public QResidenceType(Path<? extends ResidenceType> path) {
        super(path.getType(), path.getMetadata());
    }

    public QResidenceType(PathMetadata metadata) {
        super(ResidenceType.class, metadata);
    }

}

