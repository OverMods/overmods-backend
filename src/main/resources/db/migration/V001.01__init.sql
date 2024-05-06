CREATE SCHEMA "overmods";

CREATE TYPE "overmods"."role" AS ENUM(
    'ADMIN',
    'MODDER',
    'USER'
);

CREATE TABLE "overmods"."user" (
    "id"                SERIAL          PRIMARY KEY,
    "username"          VARCHAR         NOT NULL UNIQUE,
    "email"             VARCHAR         NOT NULL UNIQUE,
    "password"          VARCHAR         NOT NULL,
    "color"             CHAR(6)         DEFAULT NULL,
    "role"              "overmods"."role" NOT NULL,
    "site_rating"       SMALLINT        DEFAULT NULL,
    "registered_at"     TIMESTAMP       NOT NULL,
    "updated_at"        TIMESTAMP       NOT NULL,
    "password_changed"  TIMESTAMP       NOT NULL,
    "banned"            BOOLEAN         NOT NULL DEFAULT FALSE,
    "deleted"           BOOLEAN         NOT NULL DEFAULT FALSE
);

CREATE TABLE "overmods"."game" (
    "id"                SERIAL          PRIMARY KEY,
    "title"             VARCHAR         NOT NULL,
    "short_title"       VARCHAR         NOT NULL,
    "logo"              VARCHAR         NOT NULL
);

CREATE TABLE "overmods"."mod" (
    "id"                SERIAL          PRIMARY KEY,
    "game"              INTEGER         NOT NULL,
    "title"             VARCHAR         NOT NULL,
    "logo"              VARCHAR         NOT NULL,
    "author"            INTEGER         NOT NULL,
    "author_title"      VARCHAR         DEFAULT NULL,
    "rating"            FLOAT           NOT NULL DEFAULT '0',
    "uploaded_at"       TIMESTAMP       NOT NULL,
    "description"       TEXT            NOT NULL,
    "game_version"      VARCHAR         DEFAULT NULL,
    "mod_version"       VARCHAR         DEFAULT NULL,
    "instruction"       TEXT,
    "downloaded"        INTEGER         NOT NULL DEFAULT '0',
    "file"              VARCHAR         NOT NULL,
    "file_size"         INTEGER         NOT NULL,
    "deleted"           BOOLEAN         NOT NULL DEFAULT FALSE,

    CONSTRAINT "fk_mod_game"
        FOREIGN KEY("game")     REFERENCES "overmods"."game"("id"),
    CONSTRAINT "fk_mod_author"
        FOREIGN KEY("author")   REFERENCES "overmods"."user"("id")
);

CREATE TABLE "overmods"."mod_comment" (
    "id"                SERIAL          PRIMARY KEY,
    "mod"               INTEGER         NOT NULL,
    "user"              INTEGER         NOT NULL,
    "commented_at"      TIMESTAMP       NOT NULL,
    "comment"           TEXT            NOT NULL,
    "deleted"           BOOLEAN         NOT NULL DEFAULT FALSE,

    CONSTRAINT "fk_mod_comment_mod"
        FOREIGN KEY("mod")      REFERENCES "overmods"."mod"("id"),
    CONSTRAINT "fk_mod_comment_user"
        FOREIGN KEY("user")     REFERENCES "overmods"."user"("id")
);

-- https://stackoverflow.com/questions/41143913/sql-jpa-multiple-columns-as-primary-key

CREATE TABLE "overmods"."mod_rating" (
    "mod"               INTEGER         NOT NULL,
    "user"              INTEGER         NOT NULL,
    "rating"            SMALLINT        NOT NULL,

    PRIMARY KEY("mod", "user"),
    CONSTRAINT "fk_mod_rating_mod"
        FOREIGN KEY("mod")      REFERENCES "overmods"."mod"("id"),
    CONSTRAINT "fk_mod_rating_user"
        FOREIGN KEY("user")     REFERENCES "overmods"."user"("id")
);

CREATE TABLE "overmods"."mod_screenshot" (
    "id"                SERIAL          PRIMARY KEY,
    "mod"               INTEGER         NOT NULL,
    "screenshot"        VARCHAR         NOT NULL,
    "title"             VARCHAR,
    "description"       TEXT,

    CONSTRAINT "fk_mod_screenshot_mod"
        FOREIGN KEY("mod")      REFERENCES "overmods"."mod"("id")
);

CREATE TYPE "overmods"."status" AS ENUM(
    'PENDING',
    'APPROVED',
    'DECLINED'
);

CREATE TABLE "overmods"."request_role" (
    "id"                SERIAL              PRIMARY KEY,
    "requested_by"      INTEGER             NOT NULL,
    "requested_at"      TIMESTAMP           NOT NULL,
    "request_text"      TEXT,
    "new_role"          "overmods"."role",
    "considered_by"     INTEGER,
    "considered_at"     TIMESTAMP,
    "status"            "overmods"."status"   NOT NULL,

    CONSTRAINT "fk_request_role_requested_by"
        FOREIGN KEY("requested_by")     REFERENCES "overmods"."user"("id"),
    CONSTRAINT "fk_request_role_considered_by"
        FOREIGN KEY("considered_by")    REFERENCES "overmods"."user"("id")
);