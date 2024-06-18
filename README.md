# vrt-ranking
Vietnam Roller Team's Ranking system for internal athletes


# Database Tables Structure

## category
| **Column**   | **Type**       | **Constraints** |
|--------------|----------------|------------------|
| id           | SERIAL         | PRIMARY KEY      |
| name         | VARCHAR(255)   |                  |
| description  | TEXT           |                  |
| gender       | VARCHAR(10)    |                  |
| start_age    | INTEGER        |                  |
| end_age      | INTEGER        |                  |


## team
| **Column**         | **Type**       | **Constraints** |
|--------------------|----------------|------------------|
| id                 | SERIAL         | PRIMARY KEY      |
| name               | VARCHAR(255)   |                  |
| date_of_creation   | DATE           |                  |
| location           | VARCHAR(255)   |                  |
| description        | TEXT           |                  |
| logo_url           | VARCHAR(255)   |                  |


## tournament
| **Column**   | **Type**       | **Constraints** |
|--------------|----------------|------------------|
| id           | SERIAL         | PRIMARY KEY      |
| name         | VARCHAR(255)   |                  |
| description  | TEXT           |                  |
| start_date   | DATE           |                  |
| end_date     | DATE           |                  |
| location     | VARCHAR(255)   |                  |


## achievement
| **Column**      | **Type**       | **Constraints**                                |
|-----------------|----------------|------------------------------------------------|
| id              | SERIAL         | PRIMARY KEY                                    |
| tournament_id   | INTEGER        | REFERENCES tournament(id)                      |
| category_id     | INTEGER        | REFERENCES category(id)                        |
| position        | INTEGER        | CHECK (position BETWEEN 1 AND 3)               |


## athlete
| **Column**           | **Type**       | **Constraints** |
|----------------------|----------------|------------------|
| id                   | SERIAL         | PRIMARY KEY      |
| name                 | VARCHAR(255)   |                  |
| date_of_birth        | DATE           |                  |
| gender               | VARCHAR(10)    |                  |
| join_date            | DATE           |                  |
| profile_photo_url    | VARCHAR(255)   |                  |
| team_id              | INTEGER        | REFERENCES team(id)                      |


## result
| **Column**      | **Type**       | **Constraints** |
|-----------------|----------------|------------------|
| id              | SERIAL         | PRIMARY KEY      |
| cat_id          | INTEGER        | REFERENCES category(id)                  |
| tournament_id   | INTEGER        | REFERENCES tournament(id)                |
| athlete_id      | INTEGER        | REFERENCES athlete(id)                   |
| result          | TEXT           |                  |
| point           | INTEGER        |                  |


## ranking
| **Column**      | **Type**       | **Constraints** |
|-----------------|----------------|------------------|
| id              | SERIAL         | PRIMARY KEY      |
| cat_id          | INTEGER        | REFERENCES category(id)                  |
| description     | TEXT           |                  |
| point           | INTEGER        |                  |
| best_result     | INTEGER        | REFERENCES result(id)                    |


## overall
| **Column**      | **Type**       | **Constraints** |
|-----------------|----------------|------------------|
| id              | SERIAL         | PRIMARY KEY      |
| cat_id          | INTEGER        | REFERENCES category(id)                  |
| point           | INTEGER        |                  |



