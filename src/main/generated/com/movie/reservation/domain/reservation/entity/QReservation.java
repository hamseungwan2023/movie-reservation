package com.movie.reservation.domain.reservation.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReservation is a Querydsl query type for Reservation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReservation extends EntityPathBase<Reservation> {

    private static final long serialVersionUID = -2102287200L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReservation reservation = new QReservation("reservation");

    public final com.movie.reservation.global.entity.QTimestamped _super = new com.movie.reservation.global.entity.QTimestamped(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final EnumPath<ReservationStatus> reservationStatus = createEnum("reservationStatus", ReservationStatus.class);

    public final com.movie.reservation.domain.cinema.entity.QScreenTime screenTime;

    public final com.movie.reservation.domain.cinema.entity.QSeat seat;

    public final com.movie.reservation.domain.user.entity.QUser user;

    public QReservation(String variable) {
        this(Reservation.class, forVariable(variable), INITS);
    }

    public QReservation(Path<? extends Reservation> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReservation(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReservation(PathMetadata metadata, PathInits inits) {
        this(Reservation.class, metadata, inits);
    }

    public QReservation(Class<? extends Reservation> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.screenTime = inits.isInitialized("screenTime") ? new com.movie.reservation.domain.cinema.entity.QScreenTime(forProperty("screenTime"), inits.get("screenTime")) : null;
        this.seat = inits.isInitialized("seat") ? new com.movie.reservation.domain.cinema.entity.QSeat(forProperty("seat"), inits.get("seat")) : null;
        this.user = inits.isInitialized("user") ? new com.movie.reservation.domain.user.entity.QUser(forProperty("user")) : null;
    }

}

