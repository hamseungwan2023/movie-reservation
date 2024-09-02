package com.movie.reservation.domain.cinema.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QScreenTime is a Querydsl query type for ScreenTime
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QScreenTime extends EntityPathBase<ScreenTime> {

    private static final long serialVersionUID = -488810108L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QScreenTime screenTime = new QScreenTime("screenTime");

    public final com.movie.reservation.global.entity.QTimestamped _super = new com.movie.reservation.global.entity.QTimestamped(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final DateTimePath<java.util.Date> endTime = createDateTime("endTime", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final com.movie.reservation.domain.movie.entity.QMovie movie;

    public final QScreen screen;

    public final DateTimePath<java.util.Date> startTime = createDateTime("startTime", java.util.Date.class);

    public QScreenTime(String variable) {
        this(ScreenTime.class, forVariable(variable), INITS);
    }

    public QScreenTime(Path<? extends ScreenTime> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QScreenTime(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QScreenTime(PathMetadata metadata, PathInits inits) {
        this(ScreenTime.class, metadata, inits);
    }

    public QScreenTime(Class<? extends ScreenTime> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.movie = inits.isInitialized("movie") ? new com.movie.reservation.domain.movie.entity.QMovie(forProperty("movie")) : null;
        this.screen = inits.isInitialized("screen") ? new QScreen(forProperty("screen"), inits.get("screen")) : null;
    }

}

