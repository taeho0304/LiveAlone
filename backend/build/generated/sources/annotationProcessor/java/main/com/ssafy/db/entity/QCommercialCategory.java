package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QCommercialCategory is a Querydsl query type for CommercialCategory
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCommercialCategory extends EntityPathBase<CommercialCategory> {

    private static final long serialVersionUID = -1069661782L;

    public static final QCommercialCategory commercialCategory = new QCommercialCategory("commercialCategory");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath categoryName = createString("categoryName");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public QCommercialCategory(String variable) {
        super(CommercialCategory.class, forVariable(variable));
    }

    public QCommercialCategory(Path<? extends CommercialCategory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCommercialCategory(PathMetadata metadata) {
        super(CommercialCategory.class, metadata);
    }

}

