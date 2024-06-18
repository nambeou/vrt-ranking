CREATE TABLE IF NOT EXISTS category
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(255),
    description TEXT,
    gender      VARCHAR(10),
    start_age   INTEGER,
    end_age     INTEGER
);

CREATE TABLE IF NOT EXISTS team
(
    id              SERIAL PRIMARY KEY,
    name            VARCHAR(255),
    date_of_creation DATE,
    location        VARCHAR(255),
    description     TEXT,
    logo_url        VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS tournament
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(255),
    description TEXT,
    start_date  DATE,
    end_date    DATE,
    location    VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS tournament_categories
(
    tournament_id INTEGER REFERENCES tournament(id),
    category_id   INTEGER REFERENCES category(id),
    PRIMARY KEY (tournament_id, category_id)
);

CREATE TABLE IF NOT EXISTS achievement
(
    id            SERIAL PRIMARY KEY,
    tournament_id INTEGER REFERENCES tournament (id),
    category_id   INTEGER REFERENCES category (id),
    position      INTEGER CHECK (position BETWEEN 1 AND 3)
);

CREATE TABLE IF NOT EXISTS athlete
(
    id              SERIAL PRIMARY KEY,
    name            VARCHAR(255),
    date_of_birth   DATE,
    gender          VARCHAR(10),
    join_date       DATE,
    profile_photo_url   VARCHAR(255),
    team_id         INTEGER REFERENCES team(id)
);


CREATE TABLE IF NOT EXISTS result
(
    id            SERIAL PRIMARY KEY,
    category_id   INTEGER REFERENCES category (id),
    tournament_id INTEGER REFERENCES tournament (id),
    athlete_id    INTEGER REFERENCES athlete (id),
    result        TEXT,
    point         INTEGER
);

CREATE TABLE IF NOT EXISTS ranking
(
    id          SERIAL PRIMARY KEY,
    category_id INTEGER REFERENCES category (id),
    description TEXT,
    point       INTEGER,
    best_result INTEGER REFERENCES result (id)
);



CREATE TABLE IF NOT EXISTS athlete_rankings
(
    athlete_id INTEGER REFERENCES athlete(id),
    ranking_id INTEGER REFERENCES ranking(id),
    PRIMARY KEY (athlete_id, ranking_id)
);

CREATE TABLE IF NOT EXISTS athlete_achievements
(
    athlete_id    INTEGER REFERENCES athlete(id),
    achievement_id INTEGER REFERENCES achievement(id),
    PRIMARY KEY (athlete_id, achievement_id)
);

CREATE TABLE IF NOT EXISTS overall
(
    id          SERIAL PRIMARY KEY,
    category_id INTEGER REFERENCES category (id),
    point       INTEGER
);

CREATE TABLE IF NOT EXISTS overall_athletes
(
    overall_id INTEGER REFERENCES overall(id),
    athlete_id INTEGER REFERENCES athlete(id),
    PRIMARY KEY (overall_id, athlete_id)
);
