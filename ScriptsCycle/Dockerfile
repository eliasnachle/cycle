#Image maven
FROM mysql:5.7

# Define and create direcotry
ARG ROOT_PASSWORD=Bandtec@123
ENV MYSQL_ROOT_PASSWORD=${ROOT_PASSWORD}

ARG SETUP_DATABASE=cycle
ENV MYSQL_DATABASE=${SETUP_DATABASE}

ARG SETUP_REMOTE_USERNAME=remote
ARG SETUP_REMOVE_PASSWORD=Bandtec@123

# Add port
EXPOSE 3306

# Add path app and define entrypoint
COPY ./ScriptCriaçãoBanco-CYCLE.sql /docker-entrypoint-initdb.d/ScriptCriaçãoBanco-CYCLE.sql

RUN echo "CREATE USER '${SETUP_REMOTE_USERNAME}'@'%' IDENTIFIED BY '${SETUP_REMOVE_PASSWORD}';GRANT ALL PRIVILEGES ON *.* TO '${SETUP_REMOTE_USERNAME}'@'%' WITH GRANT OPTION;" > /docker-entrypoint-initdb.d/_grant_remote.sql