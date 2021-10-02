DROP TABLE IF EXISTS login_event;

CREATE TABLE login_event (
    login_event_id RAW(16) NOT NULL,
    login VARCHAR(250) NOT NULL,
    request_count NUMBER NOT NULL
);
COMMENT ON TABLE login_event IS 'Stores number of access users data with specific login';
COMMENT ON COLUMN login_event.login_event_id IS 'Unique event id';
COMMENT ON COLUMN login_event.login IS 'Unique user login';
COMMENT ON COLUMN login_event.request_count IS 'Counter of accessing user data';

CREATE UNIQUE INDEX pk_login_event
   ON login_event (login_event_id);

CREATE UNIQUE INDEX uq_login
 ON login_event ( login );
