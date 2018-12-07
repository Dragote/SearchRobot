# docker run -p 4306:3306 -p 5567:5567 -p 5444:5444 -p 5568:5568 -d db_as_mariadb
FROM mariadb:latest

ENV MYSQL_ROOT_PASSWORD mypass

COPY /SQLS/* /docker-entrypoint-initdb.d/
EXPOSE 3306
CMD ["mysqld"]