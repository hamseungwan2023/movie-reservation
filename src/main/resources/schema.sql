CREATE TABLE cinema (
  id bigint NOT NULL AUTO_INCREMENT,
  name varchar(50) COLLATE utf8mb3_unicode_ci NOT NULL,
  address varchar(255) COLLATE utf8mb3_unicode_ci NOT NULL,
  sido varchar(20) COLLATE utf8mb3_unicode_ci NOT NULL,
  gungu varchar(50) COLLATE utf8mb3_unicode_ci NOT NULL,
  created_at timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  modified_at timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;

CREATE TABLE movie (
  id bigint NOT NULL AUTO_INCREMENT,
  title varchar(255) COLLATE utf8mb3_unicode_ci NOT NULL,
  description varchar(255) COLLATE utf8mb3_unicode_ci NOT NULL,
  genre enum('ACTION','ADVENTURE','COMEDY','CRIME','DRAMA','FANTASY','HISTORICAL','HORROR','MYSTERY','ROMANCE','SCIENCE_FICTION','THRILLER','WESTERN','ANIMATION','DOCUMENTARY','MUSICAL','WAR','BIOGRAPHY','SPORTS','FAMILY','NOIR','SUPERHERO') COLLATE utf8mb3_unicode_ci NOT NULL,
  poster varchar(255) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  duration int DEFAULT NULL,
  created_at timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  modified_at timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;

CREATE TABLE screen (
  id bigint NOT NULL AUTO_INCREMENT,
  name varchar(50) COLLATE utf8mb3_unicode_ci NOT NULL,
  total_seat int NOT NULL,
  cinema_id bigint NOT NULL,
  created_at timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  modified_at timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  KEY cinema_id (cinema_id),
  CONSTRAINT screen_ibfk_1 FOREIGN KEY (cinema_id) REFERENCES cinema (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;

CREATE TABLE screen_time (
  id bigint NOT NULL AUTO_INCREMENT,
  start_time varchar(255) COLLATE utf8mb3_unicode_ci NOT NULL,
  end_time varchar(255) COLLATE utf8mb3_unicode_ci NOT NULL,
  screen_id bigint NOT NULL,
  movie_id bigint NOT NULL,
  created_at timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  modified_at timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  KEY screen_id (screen_id),
  KEY movie_id (movie_id),
  KEY idx_created_at_start_time (created_at),
  CONSTRAINT screen_time_ibfk_1 FOREIGN KEY (screen_id) REFERENCES screen (id),
  CONSTRAINT screen_time_ibfk_2 FOREIGN KEY (movie_id) REFERENCES movie (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;

CREATE TABLE seat (
  id BIGINT NOT NULL AUTO_INCREMENT,
  screen_id BIGINT NOT NULL,
  row_name VARCHAR(5) NOT NULL,
  seat_number INT NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  CONSTRAINT uk_screen_seat UNIQUE (screen_id, row_name, seat_number),
  CONSTRAINT fk_seat_theater FOREIGN KEY (screen_id) REFERENCES screen(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE reservation (
  id bigint NOT NULL AUTO_INCREMENT,
  reservation_status enum('CONFIRMED','CANCELLED') COLLATE utf8mb3_unicode_ci NOT NULL,
  screen_time_id bigint NOT NULL,
  seat_number int NOT NULL,
  username varchar(20) NOT NULL,
  created_at timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  modified_at timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
