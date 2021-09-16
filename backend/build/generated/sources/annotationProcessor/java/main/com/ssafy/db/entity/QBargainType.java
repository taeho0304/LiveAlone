package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBargainType is a Querydsl query type for BargainType
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBargainType extends EntityPathBase<BargainType> {

    private static final long serialVersionUID = 1638967146L;

    public static final QBargainType bargainType = new QBargainType("bargainType");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath type = createString("type");

    public QBargainType(String variable) {
        super(BargainType.class, forVariable(variable));
    }

    public QBargainType(Path<? extends BargainType> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBargainType(PathMetadata metadata) {
        super(BargainType.class, metadata);
    }

}

