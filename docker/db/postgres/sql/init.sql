CREATE USER mennal WITH encrypted password '1234';
CREATE DATABASE mennal;
GRANT ALL privileges on database "mennal" to mennal;
alter role mennal superuser