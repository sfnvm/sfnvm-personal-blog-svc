DROP TABLE IF EXISTS article;
CREATE TABLE article
(
    id                 BIGSERIAL PRIMARY KEY NOT NULL,
    content            TEXT                  NOT NULL,
    title              VARCHAR               NOT NULL,
    published_date     TIMESTAMP             NOT NULL,
    -- AUDIT
    created_by         VARCHAR(255)          NOT NULL,
    created_date       TIMESTAMP             NOT NULL,
    last_modified_By   VARCHAR(255)          NOT NULL,
    last_modified_date TIMESTAMP             NOT NULL
)