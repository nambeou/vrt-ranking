#!/bin/bash
set -e

# Wait for PostgreSQL to start
until pg_isready -h localhost; do
  echo "Waiting for PostgreSQL to start..."
  sleep 2
done

# Create avnadmin user and database, and set avnadmin as the default user
psql postgresql://postgres@localhost:5432/postgres -c "ALTER USER postgres WITH PASSWORD '123456789';"

# Create avnadmin user and database, and set avnadmin as the default user
psql postgresql://postgres:123456789@localhost:5432/postgres -c "CREATE USER avnadmin WITH PASSWORD '123456789';"
psql postgresql://postgres:123456789@localhost:5432/postgres -c "CREATE DATABASE vrt-ranking;"
psql postgresql://postgres:123456789@localhost:5432/postgres -c "GRANT ALL PRIVILEGES ON DATABASE vrt-ranking TO avnadmin;"
psql postgresql://postgres:123456789@localhost:5432/postgres -c "ALTER USER avnadmin WITH SUPERUSER;"

# Run the SQL scripts as avnadmin user
psql postgresql://avnadmin:123456789@localhost:5432/vrt-ranking -f /docker-entrypoint-initdb.d/create_table.sql
psql postgresql://avnadmin:123456789@localhost:5432/vrt-ranking -f /docker-entrypoint-initdb.d/populate_data.sql