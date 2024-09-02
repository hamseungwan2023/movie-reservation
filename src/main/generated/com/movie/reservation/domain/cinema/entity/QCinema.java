package com.movie.reservation.domain.cinema.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCinema is a Querydsl query type for Cinema
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCinema extends EntityPathBase<Cinema> {

    private static final long serialVersionUID = -173954916L;

    public static final QCinema cinema = new QCinema("cinema");

    public final com.movie.reservation.global.entity.QTimestamped _super = new com.movie.reservation.global.entity.QTimestamped(this);

    public final StringPath address = createString("address");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath gungu = createString("gungu");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath name = createString("name");

    public final StringPath sido = createString("sido");

    public QCinema(String variable) {
        super(Cinema.class, forVariable(variable));
    }

    public QCinema(Path<? extends Cinema> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCinema(PathMetadata metadata) {
        super(Cinema.class, metadata);
    }

}

