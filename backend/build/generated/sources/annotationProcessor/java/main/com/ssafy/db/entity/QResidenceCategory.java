package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QResidenceCategory is a Querydsl query type for ResidenceCategory
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QResidenceCategory extends EntityPathBase<ResidenceCategory> {

    private static final long serialVersionUID = 1097062050L;

    public static final QResidenceCategory residenceCategory = new QResidenceCategory("residenceCategory");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath categoryName = createString("categoryName");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public QResidenceCategory(String variable) {
        super(ResidenceCategory.class, forVariable(variable));
    }

    public QResidenceCategory(Path<? extends ResidenceCategory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QResidenceCategory(PathMetadata metadata) {
        super(ResidenceCategory.class, metadata);
    }

}

