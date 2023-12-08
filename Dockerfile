FROM postgres:14

COPY sql.sql /docker-entrypoint-initdb.d/init.sql

EXPOSE 5432