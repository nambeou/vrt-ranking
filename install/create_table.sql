CREATE TABLE IF NOT EXISTS category
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(255),
    description TEXT,
    gender      VARCHAR(10),
    start_age   INTEGER,
    end_age     INTEGER
);

CREATE TABLE IF NOT EXISTS tournament
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(255),
    description TEXT,
    categories  INTEGER[],
    start_date  DATE,
    end_date    DATE,
    location    VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS achievement
(
    id            SERIAL PRIMARY KEY,
    tournament_id INTEGER REFERENCES tournament (id),
    category_id   INTEGER REFERENCES category (id),
    position      INTEGER CHECK (position BETWEEN 1 AND 3)
);


CREATE TABLE IF NOT EXISTS ranking
(
    id          SERIAL PRIMARY KEY,
    cat_id      INTEGER REFERENCES category (id),
    description TEXT,
    point       INTEGER
);

CREATE TABLE IF NOT EXISTS athlete
(
    id            SERIAL PRIMARY KEY,
    name          VARCHAR(255),
    date_of_birth DATE,
    gender        VARCHAR(10),
    join_date     DATE,
    rankings      INTEGER[],
    achievements  INTEGER[]
);

CREATE TABLE IF NOT EXISTS result
(
    id            SERIAL PRIMARY KEY,
    cat_id        INTEGER REFERENCES category (id),
    tournament_id INTEGER REFERENCES tournament (id),
    athlete_id    INTEGER REFERENCES athlete (id),
    result        TEXT,
    point         INTEGER
);

CREATE TABLE IF NOT EXISTS overall
(
    id       SERIAL PRIMARY KEY,
    cat_id   INTEGER REFERENCES category (id),
    athletes INTEGER[]
);

ALTER TABLE ranking
    ADD COLUMN IF NOT EXISTS best_result INTEGER REFERENCES result (id);