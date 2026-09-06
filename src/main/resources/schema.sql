CREATE SEQUENCE IF NOT EXISTS echoes_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS echoes (
    id INTEGER PRIMARY KEY DEFAULT nextval('echoes_id_seq'),
    message VARCHAR(500) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

ALTER TABLE echoes
    ALTER COLUMN id SET DEFAULT nextval('echoes_id_seq');

SELECT setval(
    'echoes_id_seq',
    COALESCE((SELECT MAX(id) FROM echoes), 0) + 1,
    false
);
