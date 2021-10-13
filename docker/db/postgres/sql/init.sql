CREATE USER mannal WITH encrypted password '1234';
CREATE DATABASE mannal;
GRANT ALL privileges on database "mannal" to mannal;
alter role mannal superuser