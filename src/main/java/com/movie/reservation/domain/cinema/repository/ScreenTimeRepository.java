package com.movie.reservation.domain.cinema.repository;

import com.movie.reservation.domain.cinema.dto.response.ScreenTimeResponseDto;
import com.movie.reservation.domain.cinema.entity.ScreenTime;
import com.movie.reservation.domain.cinema.repository.mapper.ScreenTimeMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScreenTimeRepository extends JpaRepository<ScreenTime, Long>, ScreenTimeCustomRepository {

//    @Query("SELECT new com.movie.reservation.domain.cinema.dto.response.ScreenTimeResponseDto(" +
//            "st.id, m.title, m.duration, st.startTime, st.endTime, s.name, c.name, c.address) " +
//            "FROM ScreenTime st " +
//            "JOIN st.movie m " +
//            "JOIN st.screen s " +
//            "JOIN s.cinema c " +
//            "WHERE FUNCTION('DATE_FORMAT', st.startTime, '%Y-%m-%d') = :date " +
//            "AND m.id = :movieId " +
//            "ORDER BY st.createdAt DESC")
//    Page<ScreenTimeResponseDto> findScreenTimesByDateAndMovieId(@Param("movieId") Long movieId,@Param("date") String date,Pageable pageable);
}
