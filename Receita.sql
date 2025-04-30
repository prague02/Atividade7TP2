DROP DATABASE IF EXISTS "Receita";

CREATE DATABASE "Receita"(
Id Integer,
name String,
ingredients Sring,
prepTimeMinutes Integer,
cookTimeMinutes Integer,
servings Integer,
difficulty String
)
    WITH
    OWNER = fatec
    ENCODING = 'UTF8'
    LC_COLLATE = 'pt_BR.UTF-8'
    LC_CTYPE = 'pt_BR.UTF-8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;
