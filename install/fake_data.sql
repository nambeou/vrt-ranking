-- Insert sample data for categories
INSERT INTO category (name, description, gender, start_age, end_age)
VALUES ('Category A', 'Description for Category A', 'Male', 18, 30),
       ('Category B', 'Description for Category B', 'Female', 20, 35),
       ('Category C', 'Description for Category C', 'Male', 25, 40);

-- Insert sample data for tournaments
INSERT INTO tournament (name, description, categories, start_date, end_date, location)
VALUES ('Tournament X', 'Description for Tournament X', '{1, 2}', '2024-04-01', '2024-04-05', 'Location X'),
       ('Tournament Y', 'Description for Tournament Y', '{2, 3}', '2024-04-10', '2024-04-15', 'Location Y'),
       ('Tournament Z', 'Description for Tournament Z', '{1, 2, 3}', '2024-04-20', '2024-04-25', 'Location Z');

-- Insert sample data for athletes
INSERT INTO athlete (name, date_of_birth, gender, join_date, rankings, achievements)
VALUES ('Athlete 1', '1990-01-01', 'Male', '2020-01-01', '{1, 2, 3}', '{}'),
       ('Athlete 2', '1995-02-15', 'Female', '2021-03-15', '{1, 2}', '{}'),
       ('Athlete 3', '1988-05-20', 'Male', '2019-07-10', '{1, 3}', '{}'),
       ('Athlete 4', '1992-08-30', 'Female', '2020-05-05', '{2, 3}', '{}'),
       ('Athlete 5', '1993-11-10', 'Male', '2018-09-20', '{2, 3}', '{}'),
       ('Athlete 6', '1997-04-25', 'Female', '2022-02-28', '{1, 2, 3}', '{}');

-- Insert sample data for results
INSERT INTO result (cat_id, tournament_id, athlete_id, result, point)
VALUES (1, 1, 1, 'First Place', 100),
       (2, 1, 1, 'Second Place', 90),
       (1, 1, 2, 'Third Place', 80),
       (2, 1, 2, 'First Place', 100),
       (1, 1, 3, 'Second Place', 90),
       (2, 1, 3, 'Third Place', 80),
       (2, 2, 2, 'First Place', 100),
       (3, 2, 2, 'Second Place', 90),
       (2, 2, 4, 'Third Place', 80),
       (3, 2, 4, 'First Place', 100),
       (1, 3, 1, 'Second Place', 90),
       (2, 3, 1, 'Third Place', 80),
       (3, 3, 1, 'First Place', 100),
       (1, 3, 2, 'Third Place', 80),
       (2, 3, 2, 'First Place', 100),
       (3, 3, 2, 'Second Place', 90);

-- Insert sample data for achievements
INSERT INTO achievement (tournament_id, category_id, position)
VALUES (1, 1, 1),
       (1, 1, 2),
       (1, 1, 3),
       (1, 2, 1),
       (1, 2, 2),
       (1, 2, 3),
       (2, 2, 1),
       (2, 2, 2),
       (2, 2, 3),
       (2, 3, 1),
       (2, 3, 2),
       (2, 3, 3),
       (3, 1, 1),
       (3, 1, 2),
       (3, 1, 3),
       (3, 2, 1),
       (3, 2, 2),
       (3, 2, 3),
       (3, 3, 1),
       (3, 3, 2),
       (3, 3, 3);


INSERT INTO overall (cat_id, athletes)
VALUES (1, '{1, 2, 3}'),
       (2, '{1, 2, 4}'),
       (3, '{1, 2}');

-- Add achievements to athletes based on the sample data
UPDATE athlete
SET achievements = array_append(achievements, 1)
WHERE id = 1; -- Athlete 1 achieved 1st position
UPDATE athlete
SET achievements = array_append(achievements, 2)
WHERE id = 2; -- Athlete 2 achieved 2nd position
UPDATE athlete
SET achievements = array_append(achievements, 3)
WHERE id = 3; -- Athlete 3 achieved 3rd position

UPDATE athlete
SET achievements = array_append(achievements, 1)
WHERE id = 2; -- Athlete 2 achieved 1st position
UPDATE athlete
SET achievements = array_append(achievements, 2)
WHERE id = 4; -- Athlete 4 achieved 2nd position
UPDATE athlete
SET achievements = array_append(achievements, 3)
WHERE id = 1; -- Athlete 1 achieved 3rd position

UPDATE athlete
SET achievements = array_append(achievements, 2)
WHERE id = 1; -- Athlete 1 achieved 2nd position
UPDATE athlete
SET achievements = array_append(achievements, 3)
WHERE id = 2; -- Athlete 2 achieved 3rd position
UPDATE athlete
SET achievements = array_append(achievements, 1)
WHERE id = 1; -- Athlete 1 achieved 1st position

UPDATE athlete
SET achievements = array_append(achievements, 3)
WHERE id = 2; -- Athlete 2 achieved 3rd position
UPDATE athlete
SET achievements = array_append(achievements, 1)
WHERE id = 2; -- Athlete 2 achieved 1st position
UPDATE athlete
SET achievements = array_append(achievements, 2)
WHERE id = 2; -- Athlete 2 achieved 2nd position
