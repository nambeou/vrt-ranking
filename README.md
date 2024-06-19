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
| **Column**   | **Type**       | **Constraints**                      |
|--------------|----------------|--------------------------------------|
| id           | SERIAL         | PRIMARY KEY                          |
| name         | VARCHAR(255)   |                                      |
| description  | TEXT           |                                      |
| start_date   | DATE           |                                      |
| end_date     | DATE           |                                      |
| location     | VARCHAR(255)   |                                      |
| categories   | INTEGER[]      | REFERENCES category(id)              |



## achievement
| **Column**      | **Type**       | **Constraints**                                |
|-----------------|----------------|------------------------------------------------|
| id              | SERIAL         | PRIMARY KEY                                    |
| tournament_id   | INTEGER        | REFERENCES tournament(id)                      |
| category_id     | INTEGER        | REFERENCES category(id)                        |
| position        | INTEGER        | CHECK (position BETWEEN 1 AND 3)               |


## athlete
| **Column**          | **Type**       | **Constraints**                      |
|---------------------|----------------|--------------------------------------|
| id                  | SERIAL         | PRIMARY KEY                          |
| name                | VARCHAR(255)   |                                      |
| date_of_birth       | DATE           |                                      |
| gender              | VARCHAR(10)    |                                      |
| join_date           | DATE           |                                      |
| profile_photo_url   | VARCHAR(255)   |                                      |
| team_id             | INTEGER        | REFERENCES team(id)                  |
| rankings            | INTEGER[]      | REFERENCES ranking(id)               |
| overalls            | INTEGER[]      | REFERENCES overall(id)               |



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


# API 

The application can be deployed locally
## Base URL

```
http://localhost:8080/api
```

## Endpoints

### Categories

#### Get All Categories

```
GET /categories
```

**Response:**
Array of categories
```
    [{
        "name": "Speed Slalom Female Junior",
        "description": "Some random description.",
        "start_age": 8,
        "end_age": 12,
        "gender": "Female"
    }]
```

#### Get Category by ID

```
GET /categories/{id}
```

**Path Parameters:**
- `id` (required): ID of the category

**Response:**
```
{
    "name": "Speed Slalom Female Junior",
    "description": "Some random description.",
    "start_age": 8,
    "end_age": 12,
    "gender": "Female"
}
```

#### Get all tournament having a category

```
GET /categories/{id}/tournaments
```

**Path Parameters:**
- `id` (required): ID of the category

**Response:**
Array of tournaments
```
[
  {
        "name": "Giải các đội mạnh quốc gia Hải Phòng",
        "description": "Some random description.",
        "start_date": "09-06-2024",
        "end_date": "11-06-2024",
        "location": "Hải Phòng",
        "categories": [
            {
                "name": "Speed Slalom Female Junior",
                "description": "Some random description.",
                "start_age": 8,
                "end_age": 12,
                "gender": "Female"
            },
            {
                "name": "Speed Slalom Male Junior",
                "description": "Some random description.",
                "start_age": 8,
                "end_age": 12,
                "gender": "Male"
            },
        ]
    }
]
```

### Tournaments

#### Get All Tournaments

```
GET /tournaments
```

**Response:**
Array of tournaments
```
[
  {
        "name": "Giải các đội mạnh quốc gia Hải Phòng",
        "description": "Some random description.",
        "start_date": "09-06-2024",
        "end_date": "11-06-2024",
        "location": "Hải Phòng",
        "categories": [
            {
                "name": "Speed Slalom Female Junior",
                "description": "Some random description.",
                "start_age": 8,
                "end_age": 12,
                "gender": "Female"
            },
            {
                "name": "Speed Slalom Male Junior",
                "description": "Some random description.",
                "start_age": 8,
                "end_age": 12,
                "gender": "Male"
            },
        ]
    }
]
```

#### Get Tournament by ID

```
GET /tournaments/{id}
```

**Path Parameters:**
- `id` (required): ID of the tournament

**Response:**
Tournament object
```
  {
    "name": "Giải các đội mạnh quốc gia Hải Phòng",
    "description": "Some random description.",
    "start_date": "09-06-2024",
    "end_date": "11-06-2024",
    "location": "Hải Phòng",
    "categories": [
        {
            "name": "Speed Slalom Female Junior",
            "description": "Some random description.",
            "start_age": 8,
            "end_age": 12,
            "gender": "Female"
        },
        {
            "name": "Speed Slalom Male Junior",
            "description": "Some random description.",
            "start_age": 8,
            "end_age": 12,
            "gender": "Male"
        },
    ]
}
```

#### Get categories of a Tournament

```
GET /tournaments/{id}/categories
```

**Path Parameters:**
- `id` (required): ID of the tournament

**Response:**
Array of Category object
```
 [
    {
        "name": "Speed Slalom Female Junior",
        "description": "Some random description.",
        "start_age": 8,
        "end_age": 12,
        "gender": "Female"
    },
    {
        "name": "Speed Slalom Male Junior",
        "description": "Some random description.",
        "start_age": 8,
        "end_age": 12,
        "gender": "Male"
    },
]

```


### Achievements

#### Get All Achievements of an athlete

```
GET /achievements/athlete/{athleteId}
```

**Response:**
Array of Achievement of the athlete
```
[
    {
        "position": 2,
        "category":{
              "name": "Speed Slalom Female Junior",
              "description": "Some random description.",
              "start_age": 8,
              "end_age": 12,
              "gender": "Female"
        },
        "tournament":   {
            "name": "Giải các đội mạnh quốc gia Hải Phòng",
            "description": "Some random description.",
            "start_date": "09-06-2024",
            "end_date": "11-06-2024",
            "location": "Hải Phòng",
            "categories": [
                {
                    "name": "Speed Slalom Female Junior",
                    "description": "Some random description.",
                    "start_age": 8,
                    "end_age": 12,
                    "gender": "Female"
                },
                {
                    "name": "Speed Slalom Male Junior",
                    "description": "Some random description.",
                    "start_age": 8,
                    "end_age": 12,
                    "gender": "Male"
                },
            ]
        }
    }
...
]
```

#### Get Achievement by ID

```
GET /achievements/{id}
```

**Path Parameters:**
- `id` (required): ID of the achievement

**Response:**
```
    {
        "position": 2,
        "category":{
              "name": "Speed Slalom Female Junior",
              "description": "Some random description.",
              "start_age": 8,
              "end_age": 12,
              "gender": "Female"
        },
        "tournament":   {
            "name": "Giải các đội mạnh quốc gia Hải Phòng",
            "description": "Some random description.",
            "start_date": "09-06-2024",
            "end_date": "11-06-2024",
            "location": "Hải Phòng",
            "categories": [
                {
                    "name": "Speed Slalom Female Junior",
                    "description": "Some random description.",
                    "start_age": 8,
                    "end_age": 12,
                    "gender": "Female"
                },
                {
                    "name": "Speed Slalom Male Junior",
                    "description": "Some random description.",
                    "start_age": 8,
                    "end_age": 12,
                    "gender": "Male"
                },
            ]
        }
    }
```

### Rankings

#### Get All Rankings of an athlete

```
GET /rankings/athlete/{athleteId}
```

**Response:**
Array of Ranking
```
[
    {
        "id": 1,
        "description": "Some random description.",
        "rank": 159,
        "best_result_id": 38,
        "category_id": 2,
        "best_result": {
            "result": "Some random description.",
            "point": 73,
            "category": {
                "name": "none",
                "description": "Some random description.",
                "start_age": 20,
                "end_age": 34,
                "gender": "Female"
            }
        },
        "category": {
            "name": "none",
            "description": "Some random description.",
            "start_age": 20,
            "end_age": 34,
            "gender": "Female"
        }
    }
]
```

#### Get Ranking by category

```
GET /rankings/category/{categoryId}
```

**Path Parameters:**
- `id` (required): ID of the ranking

**Response:**
- Array of (ranking, athelete)
```
[
    {
        "ranking": {
            "id": 1,
            "description": "Some random description.",
            "rank": 159,
            "best_result_id": 38,
            "category_id": 2,
            "best_result": {
                "result": "Some random description.",
                "point": 73,
                "category": {
                    "name": "none",
                    "description": "Some random description.",
                    "start_age": 20,
                    "end_age": 34,
                    "gender": "Female"
                }
            },
            "category": {
                "name": "none",
                "description": "Some random description.",
                "start_age": 20,
                "end_age": 34,
                "gender": "Female"
            }
        },
        "athlete": {
            "id": 2,
            "name": "Vu Hoang Nam",
            "date_of_birth": "28-07-1993",
            "gender": "Male",
            "join_date": "16-06-2024",
            "profile_photo_url_url": "https://dummyimage.com/762x950",
            "team": {
                "id": 8,
                "name": "Câu lạc bộ patin Bình Dương",
                "date_of_creation": "02-06-2022",
                "location": "Bình Dương",
                "description": "Some random description.",
                "logo_url": "https://placeimg.com/950/452/any"
            }
        }
    },
...
]
```

### Athletes

#### Get All Athletes

```
GET /athletes
```

**Response:**
- Body: Array of Athlete
```
[
  {
      "id": 2,
      "name": "Vu Hoang Nam",
      "date_of_birth": "28-07-1993",
      "gender": "Male",
      "join_date": "16-06-2024",
      "profile_photo_url_url": "https://dummyimage.com/762x950",
      "team": {
          "id": 8,
          "name": "Câu lạc bộ patin Bình Dương",
          "date_of_creation": "02-06-2022",
          "location": "Bình Dương",
          "description": "Some random description.",
          "logo_url": "https://placeimg.com/950/452/any"
      }
  },
...
]
```

#### Get Athlete by ID

```
GET /athletes/{id}
```

**Path Parameters:**
- `id` (required): ID of the athlete

**Response:**
```
{
    "id": 2,
    "name": "Vu Hoang Nam",
    "date_of_birth": "28-07-1993",
    "gender": "Male",
    "join_date": "16-06-2024",
    "profile_photo_url_url": "https://dummyimage.com/762x950",
    "team": {
        "id": 8,
        "name": "Câu lạc bộ patin Bình Dương",
        "date_of_creation": "02-06-2022",
        "location": "Bình Dương",
        "description": "Some random description.",
        "logo_url": "https://placeimg.com/950/452/any"
    }
}
```

### Results

#### Get All Results of a Tournament

```
GET /results/tournament/{tournamentId}
```

**Response:**
```
[
    {
        "id": 2,
        "category_id": 3,
        "result": "Some random description.",
        "point": 62,
        "category": {
            "id": 3,
            "name": "Speed Slalom Female Junior",
            "description": "Some random description.",
            "start_age": 8,
            "end_age": 12,
            "gender": "Female"
        }
    },
    {
        "id": 1,
        "category_id": 1,
        "result": "Some random description.",
        "point": 77,
        "category": {
            "id": 1,
            "name": "Speed Slalom Male Junior",
            "description": "Some random description.",
            "start_age": 8,
            "end_age": 12,
            "gender": "Male"
        }
    }
...
]
```
#### Get All Results of a Tournament

```
GET /results/tournament/{tournamentId}/category/{categoryId}
```

**Response:**
```
[
    {
        "id": 2,
        "category_id": 2,
        "result": "Some random description.",
        "point": 62,
        "category": {
            "id": 2,
            "name": "Speed Slalom Female Junior",
            "description": "Some random description.",
            "start_age": 8,
            "end_age": 12,
            "gender": "Female"
        }
    },
    {
        "id": 1,
        "category_id": 2,
        "result": "Some random description.",
        "point": 77,
        "category": {
            "id": 2,
            "name": "Speed Slalom Female Junior",
            "description": "Some random description.",
            "start_age": 8,
            "end_age": 12,
            "gender": "Female"
        }
    }
...
]
```


#### Get Result by ID

```
GET /results/{id}
```

**Path Parameters:**
- `id` (required): ID of the result

**Response:**
```
    {
        "id": 1,
        "category_id": 2,
        "result": "Some random description.",
        "point": 77,
        "category": {
            "id": 2,
            "name": "Speed Slalom Female Junior",
            "description": "Some random description.",
            "start_age": 8,
            "end_age": 12,
            "gender": "Female"
        }
    }
```

### Teams

#### Get All Teams

```
GET /teams
```

**Response:**
Array of Team
```
[
  {
      "id": 8,
      "name": "Câu lạc bộ patin Bình Dương",
      "date_of_creation": "02-06-2022",
      "location": "Bình Dương",
      "description": "Some random description.",
      "logo_url": "https://placeimg.com/950/452/any"
  },
...
]
```

#### Get Team by ID

```
GET /teams/{id}
```

**Path Parameters:**
- `id` (required): ID of the team

**Response:**
```
  {
      "id": 8,
      "name": "Câu lạc bộ patin Bình Dương",
      "date_of_creation": "02-06-2022",
      "location": "Bình Dương",
      "description": "Some random description.",
      "logo_url": "https://placeimg.com/950/452/any"
  }
```













