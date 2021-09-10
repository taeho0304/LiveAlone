package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QQuestionResult is a Querydsl query type for QuestionResult
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QQuestionResult extends EntityPathBase<QuestionResult> {

    private static final long serialVersionUID = 1726950373L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQuestionResult questionResult = new QQuestionResult("questionResult");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Integer> result = createNumber("result", Integer.class);

    public final NumberPath<Integer> resultIndex = createNumber("resultIndex", Integer.class);

    public final QUser user;

    public QQuestionResult(String variable) {
        this(QuestionResult.class, forVariable(variable), INITS);
    }

    public QQuestionResult(Path<? extends QuestionResult> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QQuestionResult(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QQuestionResult(PathMetadata metadata, PathInits inits) {
        this(QuestionResult.class, metadata, inits);
    }

    public QQuestionResult(Class<? extends QuestionResult> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

