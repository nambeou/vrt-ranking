# Use an official OpenJDK runtime as a parent image
FROM eclipse-temurin:21-jre

# Install dependencies
RUN apt-get update && \
    apt-get install -y wget gnupg2 lsb-release software-properties-common

# Add PostgreSQL repository and install PostgreSQL 16
RUN wget --no-check-certificate -O- https://www.postgresql.org/media/keys/ACCC4CF8.asc | gpg --dearmor -o /usr/share/keyrings/pgdg-archive-keyring.gpg && \
    echo "deb [signed-by=/usr/share/keyrings/pgdg-archive-keyring.gpg] http://apt.postgresql.org/pub/repos/apt $(lsb_release -cs)-pgdg main" | tee /etc/apt/sources.list.d/pgdg.list && \
    apt-get update && \
    apt-get install -y postgresql-16 postgresql-client-16 && \
    rm -rf /var/lib/apt/lists/*

# Clean up APT when done.
RUN apt-get clean && rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/*
# Add the Spring Boot app jar file
COPY target/ranking-0.0.1-SNAPSHOT.jar app.jar

# Add the PostgreSQL initialization scripts
COPY install/init_db.sh /docker-entrypoint-initdb.d/
COPY install/create_user.sql /docker-entrypoint-initdb.d/
COPY install/create_table.sql /docker-entrypoint-initdb.d/
COPY install/populate_data.sql /docker-entrypoint-initdb.d/
COPY install/pg_hba.conf /etc/postgresql/16/main/pg_hba.conf

# Make the init-db.sh script executable
RUN chmod +x /docker-entrypoint-initdb.d/init_db.sh

# Expose the port for the Spring Boot app
EXPOSE 8080

# Expose the port for PostgreSQL
EXPOSE 5432

# Health check for PostgreSQL
HEALTHCHECK --interval=30s --timeout=10s --start-period=10s --retries=3 \
  CMD pg_isready -U postgres || exit 1

# Start PostgreSQL and run the init script, then start the Spring Boot app
CMD service postgresql start && \
    /docker-entrypoint-initdb.d/init_db.sh && \
    java -jar /app.jar
