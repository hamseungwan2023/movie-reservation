package com.movie.reservation.domain.cinema.repository;

import com.movie.reservation.domain.cinema.dto.response.ScreenTimeResponseDto;
import com.movie.reservation.domain.cinema.entity.QCinema;
import com.movie.reservation.domain.cinema.entity.QScreen;
import com.movie.reservation.domain.cinema.entity.QScreenTime;
import com.movie.reservation.domain.cinema.entity.ScreenTime;
import com.movie.reservation.domain.movie.entity.QMovie;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.hateoas.PagedModel;

import java.util.List;
import java.util.Optional;

public class ScreenTimeCustomRepositoryImpl implements ScreenTimeCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private PageableExecutionUtils pageableExecutionUtils;
    public ScreenTimeCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public Page<ScreenTimeResponseDto> searchScreenTImeByMovie(Long movieId, Pageable pageable) {

        QMovie movie = QMovie.movie;
        QScreenTime screenTime = QScreenTime.screenTime;
        QScreen screen = QScreen.screen;
        QCinema cinema = QCinema.cinema;

        JPQLQuery<ScreenTimeResponseDto> query = jpaQueryFactory
                .select(Projections.constructor(ScreenTimeResponseDto.class,
                        screenTime.id.as("id"),
                        movie.title.as("title"),
                        movie.duration.as("duration"),
                        screenTime.startTime.as("startTime"),
                        screenTime.endTime.as("endTime"),
                        screen.name.as("screenName"),
                        cinema.name.as("cinemaName"),
                        cinema.address.as("address")
                ))
                .from(screenTime)
                .join(screenTime.movie, movie)
                .join(screenTime.screen, screen)
                .join(screen.cinema, cinema)
                .where(movie.id.eq(movieId))
                .orderBy(screenTime.createdAt.desc());

        List<ScreenTimeResponseDto> content = query
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return PageableExecutionUtils.getPage(content, pageable, query::fetchCount);
    }

    @Override
    @Cacheable
    public Page<ScreenTimeResponseDto> searchScreenTImeByStartTime(Long movieId, Long screenId, String day, Pageable pageable) {

        QMovie movie = QMovie.movie;
        QScreenTime screenTime = QScreenTime.screenTime;
        QScreen screen = QScreen.screen;
        QCinema cinema = QCinema.cinema;

        JPQLQuery<ScreenTimeResponseDto> fetchQuery = jpaQueryFactory
                .select(Projections.constructor(ScreenTimeResponseDto.class,
                        screenTime.id.as("id"),
                        movie.title.as("title"),
                        movie.duration.as("duration"),
                        screenTime.startTime.as("startTime"),
                        screenTime.endTime.as("endTime"),
                        screen.name.as("screenName"),
                        cinema.name.as("cinemaName"),
                        cinema.address.as("address")
                ))
                .from(screenTime)
                .join(screenTime.movie, movie)
                .join(screenTime.screen, screen)
                .join(screen.cinema, cinema)
                .where(screenTime.startTime.like(day + "%")
                        .and(movie.id.eq(movieId)
                                .and(screen.id.eq(screenId))))
                .orderBy(screenTime.createdAt.desc())
                .limit(20);

        List<ScreenTimeResponseDto> content = fetchQuery
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return PageableExecutionUtils.getPage(content, pageable, fetchQuery::fetchCount);
    }

    public Optional<Long> findScreenTime(Long screenTimeId) {
        QScreenTime screenTime = QScreenTime.screenTime;

        return Optional.ofNullable(jpaQueryFactory.select(screenTime.id)
                .from(screenTime)
                .where(screenTime.id.eq(screenTimeId)).fetchOne());
    }
}