FROM mysql:8

MAINTAINER henrique.zucareli

EXPOSE 3306

ENV MYSQL_DATABASE=avaliacao
ENV MYSQL_ROOT_PASSWORD=admin

ADD schema.sql /docker-entrypoint-initdb.d

EXPOSE 3306