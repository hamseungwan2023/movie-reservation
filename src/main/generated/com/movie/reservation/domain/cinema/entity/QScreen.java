package com.movie.reservation.domain.cinema.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QScreen is a Querydsl query type for Screen
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QScreen extends EntityPathBase<Screen> {

    private static final long serialVersionUID = 278689303L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QScreen screen = new QScreen("screen");

    public final com.movie.reservation.global.entity.QTimestamped _super = new com.movie.reservation.global.entity.QTimestamped(this);

    public final QCinema cinema;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath name = createString("name");

    public final NumberPath<Integer> totalSeat = createNumber("totalSeat", Integer.class);

    public QScreen(String variable) {
        this(Screen.class, forVariable(variable), INITS);
    }

    public QScreen(Path<? extends Screen> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QScreen(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QScreen(PathMetadata metadata, PathInits inits) {
        this(Screen.class, metadata, inits);
    }

    public QScreen(Class<? extends Screen> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.cinema = inits.isInitialized("cinema") ? new QCinema(forProperty("cinema")) : null;
    }

}

