package com.ssafy.db.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QQuestionOption is a Querydsl query type for QuestionOption
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QQuestionOption extends EntityPathBase<QuestionOption> {

    private static final long serialVersionUID = 1651239997L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQuestionOption questionOption = new QQuestionOption("questionOption");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath optionContent = createString("optionContent");

    public final NumberPath<Integer> optionContentIndex = createNumber("optionContentIndex", Integer.class);

    public final QQuestion question;

    public QQuestionOption(String variable) {
        this(QuestionOption.class, forVariable(variable), INITS);
    }

    public QQuestionOption(Path<? extends QuestionOption> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QQuestionOption(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QQuestionOption(PathMetadata metadata, PathInits inits) {
        this(QuestionOption.class, metadata, inits);
    }

    public QQuestionOption(Class<? extends QuestionOption> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.question = inits.isInitialized("question") ? new QQuestion(forProperty("question")) : null;
    }

}

