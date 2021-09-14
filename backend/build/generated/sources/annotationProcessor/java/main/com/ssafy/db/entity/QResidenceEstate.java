package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QResidenceEstate is a Querydsl query type for ResidenceEstate
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QResidenceEstate extends EntityPathBase<ResidenceEstate> {

    private static final long serialVersionUID = -45650448L;

    public static final QResidenceEstate residenceEstate = new QResidenceEstate("residenceEstate");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath address = createString("address");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath name = createString("name");

    public final StringPath phone = createString("phone");

    public final StringPath tel = createString("tel");

    public QResidenceEstate(String variable) {
        super(ResidenceEstate.class, forVariable(variable));
    }

    public QResidenceEstate(Path<? extends ResidenceEstate> path) {
        super(path.getType(), path.getMetadata());
    }

    public QResidenceEstate(PathMetadata metadata) {
        super(ResidenceEstate.class, metadata);
    }

}

