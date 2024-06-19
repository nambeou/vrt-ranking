from faker import Faker
import random
import datetime

fake = Faker()

def generate_category(num=10):
    categories = []
    for _ in range(num):
        categories.append({
            'id': _ + 1,
            'name': '\'' + fake.word().replace("'", "''") + '\'',
            'description': 'Some random description.',
            'gender': random.choice(['Male', 'Female']),
            'start_age': random.randint(10, 20),
            'end_age': random.randint(21, 35)
        })
    return categories

def generate_team(num=10):
    teams = []
    for _ in range(num):
        teams.append({
            'id': _ + 1,
            'name': '\'' +fake.company().replace("'", "''") + '\'',
            'date_of_creation': fake.date_this_century().strftime('%Y-%m-%d'),
            'location':'\'' + fake.city().replace("'", "''")+ '\'',
            'description': 'Some random description.',
            'logo_url': '\'' + fake.image_url().replace("'", "''") + '\''
        })
    return teams

def generate_tournament(num=10):
    tournaments = []
    for _ in range(num):
        start_date = fake.date_this_century()
        end_date = start_date + datetime.timedelta(days=random.randint(1, 10))
        tournaments.append({
            'id': _ + 1,
            'name': '\'' + fake.word().replace("'", "''") + '\'',
            'description': 'Some random description.',
            'start_date': start_date.strftime('%Y-%m-%d'),
            'end_date': end_date.strftime('%Y-%m-%d'),
            'location': '\'' + fake.city().replace("'", "''")+ '\''
        })
    return tournaments

def generate_tournament_categories(tournaments, categories):
    tournament_categories = []
    for i, tournament in enumerate(tournaments):
        for category in random.sample(categories, random.randint(1, len(categories))):
            tournament_categories.append({
                'tournament_id': tournament['id'],
                'category_id': category['id']
            })
    return tournament_categories

def generate_achievement(tournaments, categories):
    achievements = []
    for i in range(30):
        tournament = random.choice(tournaments)
        category = random.choice(categories)
        achievements.append({
            'id': i + 1,
            'tournament_id': tournament['id'],
            'category_id': category['id'],
            'position': random.randint(1, 3)
        })
    return achievements

def generate_athlete(num=50, teams=[]):
    athletes = []
    for i in range(num):
        athletes.append({
            'id': i + 1,
            'name': '\'' + fake.name().replace("'", "''") + '\'',
            'date_of_birth': fake.date_of_birth().strftime('%Y-%m-%d'),
            'gender': random.choice(['Male', 'Female']),
            'join_date': fake.date_this_century().strftime('%Y-%m-%d'),
            'profile_photo_url': '\'' + fake.image_url().replace("'", "''") + '\'',
            'team_id': random.choice(teams)['id'] if teams else None
        })
    return athletes

def generate_result(categories, tournaments, athletes):
    results = []
    for i in range(50):
        results.append({
            'id': i + 1,
            'category_id': random.choice(categories)['id'],
            'result': 'Some random description.',
            'point': random.randint(1, 100)
        })
    return results

def generate_ranking(categories, results):
    rankings = []
    for i in range(20):
        rankings.append({
            'id': i + 1,
            'category_id': random.choice(categories)['id'],
            'description': 'Some random description.',
            'rank': random.randint(1, 1000),
            'best_result_id': random.choice(results)['id']
        })
    return rankings

def generate_athlete_rankings(athletes, rankings):
    athlete_rankings = []
    for athlete in athletes:
        for ranking in random.sample(rankings, random.randint(1, len(rankings))):
            athlete_rankings.append({
                'athlete_id': athlete['id'],
                'ranking_id': ranking['id']
            })
    return athlete_rankings

def generate_athlete_achievements(athletes, achievements):
    athlete_achievements = []
    for athlete in athletes:
        for achievement in random.sample(achievements, random.randint(1, len(achievements))):
            athlete_achievements.append({
                'athlete_id': athlete['id'],
                'achievement_id': achievement['id']
            })
    return athlete_achievements

def generate_overall(categories):
    overall = []
    for i, category in enumerate(categories):
        overall.append({
            'id': i + 1,
            'category_id': category['id'],
            'point': random.randint(1, 1000)
        })
    return overall

def generate_overall_athletes(overall, athletes):
    overall_athletes = []
    for o in overall:
        for athlete in random.sample(athletes, random.randint(1, len(athletes))):
            overall_athletes.append({
                'overall_id': o['id'],
                'athlete_id': athlete['id']
            })
    return overall_athletes

def generate_tournament_results(tournaments, results):
    tournament_results = []
    for tournament in tournaments:
        for result in random.sample(results, random.randint(1, len(results))):
            tournament_results.append({
                'tournament_id': tournament['id'],
                'result_id': result['id']
            })
    return tournament_results

def generate_athlete_results(athletes, results):
    athlete_results = []
    for athlete in athletes:
        for result in random.sample(results, random.randint(1, len(results))):
            athlete_results.append({
                'athlete_id': athlete['id'],
                'result_id': result['id']
            })
    return athlete_results

# Generate data
categories = generate_category()
teams = generate_team()
tournaments = generate_tournament()
athletes = generate_athlete(teams=teams)
results = generate_result(categories, tournaments, athletes)
tournament_categories = generate_tournament_categories(tournaments, categories)
achievements = generate_achievement(tournaments, categories)
rankings = generate_ranking(categories, results)
athlete_rankings = generate_athlete_rankings(athletes, rankings)
athlete_achievements = generate_athlete_achievements(athletes, achievements)
overall = generate_overall(categories)
overall_athletes = generate_overall_athletes(overall, athletes)
tournament_results = generate_tournament_results(tournaments, results)
athlete_results = generate_athlete_results(athletes, results)

def print_insert_statements(table, data):
    for row in data:
        columns = ', '.join(row.keys())
        values = ', '.join("'{}'".format(v) if isinstance(v, str) else str(v) for v in row.values())
        print("INSERT INTO {} ({}) VALUES ({});".format(table, columns, values))

print_insert_statements('category', categories)
print_insert_statements('team', teams)
print_insert_statements('tournament', tournaments)
print_insert_statements('athlete', athletes)
print_insert_statements('result', results)
print_insert_statements('tournament_categories', tournament_categories)
print_insert_statements('achievement', achievements)
print_insert_statements('ranking', rankings)
print_insert_statements('athlete_rankings', athlete_rankings)
print_insert_statements('athlete_achievements', athlete_achievements)
print_insert_statements('overall', overall)
print_insert_statements('overall_athletes', overall_athletes)
print_insert_statements('tournament_results', tournament_results)
print_insert_statements('athlete_results', athlete_results)
