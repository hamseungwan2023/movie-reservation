# movie-reservation (MyBatis)

영화관/상영관/좌석/영화/상영시간/예매 도메인을 MyBatis 기반으로 재구성한 백엔드입니다.

## 변경 사항
- JPA/Spring Security/JWT/AWS S3 제거
- MyBatis Mapper + XML 기반 CRUD로 전환
- 유저 테이블 제거, 예매는 `username` 문자열 기준으로 처리
- DDL은 `src/main/resources/schema.sql` 기준

## 도메인
- cinema
- movie
- screen
- screen_time
- seat
- reservation

## 실행
```bash
./gradlew bootRun
```

## DDL 적용
```bash
mysql -u root -p movie < src/main/resources/schema.sql
```


## 스키마 파일 다운로드 API
```bash
curl -L -o movie-reservation-schema.sql http://localhost:8080/api/download/schema
```

