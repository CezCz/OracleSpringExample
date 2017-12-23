CREATE TABLE nip_state (
  id        NUMBER(10),
  nip_state VARCHAR(255),
  CONSTRAINT nip_state_pk PRIMARY KEY (id)
);

CREATE SEQUENCE nip_state_seq START WITH 100 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER nip_state_tr
BEFORE INSERT
  ON nip_state
FOR EACH ROW
WHEN (NEW.id IS NULL OR NEW.id = 0)
  BEGIN
    SELECT nip_state_seq.NEXTVAL
    INTO :NEW.id
    FROM dual;
  END;
/

CREATE TABLE nip_user (
  id       NUMBER(10)   NOT NULL,
  name     VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  role     VARCHAR(255) NOT NULL,
  CONSTRAINT nip_user_pk PRIMARY KEY (id),
  CONSTRAINT nip_name_uniq UNIQUE (name)
);

CREATE SEQUENCE nip_user_seq START WITH 100 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER nip_seq_tr
BEFORE INSERT
  ON nip_user
FOR EACH ROW
WHEN (NEW.id IS NULL OR NEW.id = 0)
  BEGIN
    SELECT nip_user_seq.NEXTVAL
    INTO :NEW.id
    FROM dual;
  END;
/

CREATE TABLE nip_role (
  id   NUMBER(10),
  role VARCHAR(255) NOT NULL,
  CONSTRAINT role_px PRIMARY KEY (id),
  CONSTRAINT role_role_uniq UNIQUE (role)
);

CREATE SEQUENCE nip_role_seq START WITH 100 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER role_seq_tr
BEFORE INSERT
  ON nip_role
FOR EACH ROW
WHEN (NEW.id IS NULL OR NEW.id = 0)
  BEGIN
    SELECT nip_role_seq.NEXTVAL
    INTO :NEW.id
    FROM dual;
  END;
/

CREATE TABLE nip (
  id             NUMBER(10),
  nip_name       VARCHAR(255)           NOT NULL,
  start_date     DATE                   NOT NULL,
  end_date       DATE                   NOT NULL,
  sdqc_frequency INTERVAL DAY TO SECOND NOT NULL,
  state_id       NUMBER(10)             NOT NULL,
  CONSTRAINT nip_pk PRIMARY KEY (ID),
  CONSTRAINT state_fk FOREIGN KEY (state_id) REFERENCES nip_state (id),
  CONSTRAINT compliant_nip_date_check CHECK ((start_date + sdqc_frequency) <= end_date )
);

CREATE SEQUENCE nip_seq START WITH 100 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER nip_tr
BEFORE INSERT
  ON nip
FOR EACH ROW
WHEN (NEW.id IS NULL OR NEW.id = 0)
  BEGIN
    SELECT nip_seq.NEXTVAL
    INTO :NEW.id
    FROM dual;
  END;
/

CREATE TABLE sdqc (
  id             NUMBER(10),
  nip_id         NUMBER(10) NOT NULL,
  performed_by   NUMBER(10),
  due_date       DATE,
  date_performed DATE,
  document_path  VARCHAR(255),
  CONSTRAINT sdqc_pk PRIMARY KEY (ID),
  CONSTRAINT nip_id_fk FOREIGN KEY (nip_id) REFERENCES nip (id),
  CONSTRAINT performed_by_fk FOREIGN KEY (performed_by) REFERENCES nip_user (id)
);

CREATE SEQUENCE sdqc_seq START WITH 100 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER sdqc_tr
BEFORE INSERT
  ON sdqc
FOR EACH ROW
WHEN (NEW.id IS NULL OR NEW.id = 0)
  BEGIN
    SELECT sdqc_seq.nextval
    INTO :NEW.id
    FROM dual;
  END;
/

CREATE OR REPLACE TRIGGER nip_sqdc_gen_tr
AFTER INSERT
  ON nip
FOR EACH ROW
WHEN (NEW.state_id = 1)
  BEGIN
    DELETE FROM sdqc
    WHERE sdqc.nip_id = NEW.id;
  END;
/

CREATE OR REPLACE TRIGGER nip_sqdc_gen_tr
AFTER INSERT
  ON nip
FOR EACH ROW
WHEN (NEW.state_id = 4)
  DECLARE
    sdqc_date DATE := NEW.start_date;
  BEGIN
    WHILE sdqc_date <= NEW.end_date
    LOOP
      sdqc_date = sdqc_date + :NEW.sdqc_frequency;
      INSERT INTO sdqc (nip_id, due_date) VALUES (NEW.id, sdqc_date);
    END LOOP;

    IF sdqc_date != NEW.end_date
    THEN
      INSERT INTO sdqc (nip_id, due_date) VALUES (new.id, NEW.end_date);
    END IF;
  END;
/

CREATE TABLE nip_role_user (
  ID      NUMBER(10),
  nip_id  NUMBER(10) NOT NULL,
  role_id NUMBER(10) NOT NULL,
  user_id NUMBER(10) NOT NULL,
  CONSTRAINT nip_role_user_px PRIMARY KEY (ID),
  CONSTRAINT nip_role_user_nip_id_fk FOREIGN KEY (nip_id) REFERENCES nip (ID),
  CONSTRAINT nip_role_user_role_id_fk FOREIGN KEY (role_id) REFERENCES nip_role (ID),
  CONSTRAINT nip_role_user_user_id_fk FOREIGN KEY (user_id) REFERENCES nip_user (ID)
);

CREATE SEQUENCE nip_role_user_seq START WITH 100 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER nip_role_user_tr
BEFORE INSERT
  ON nip_role_user
FOR EACH ROW
WHEN (NEW.id IS NULL OR NEW.id = 0)
  BEGIN
    SELECT nip_role_user_seq.NEXTVAL
    INTO :NEW.id
    FROM dual;
  END;
/

CREATE TABLE nip_audit_trial (
  id             NUMBER(10),
  nip_id         NUMBER(10)             NOT NULL,
  nip_name       VARCHAR(255)           NOT NULL,
  start_date     DATE                   NOT NULL,
  end_date       DATE                   NOT NULL,
  sdqc_frequency INTERVAL DAY TO SECOND NOT NULL,
  state_id       NUMBER(10)             NOT NULL,
  action_date    DATE                   NOT NULL,
  nip_user       NUMBER(10)             NOT NULL,
  nip_role       NUMBER(10)             NOT NULL,
  CONSTRAINT nip_audit_trial_px PRIMARY KEY (ID)
);

CREATE SEQUENCE nip_audit_trial_seq START WITH 100 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER nip_audit_trial_tr
BEFORE INSERT
  ON nip_audit_trial
FOR EACH ROW
WHEN (NEW.id IS NULL OR NEW.id = 0)
  BEGIN
    SELECT nip_audit_trial_seq.NEXTVAL
    INTO :NEW.id
    FROM dual;
  END;
/

CREATE OR REPLACE PROCEDURE INSERT_INTO_NIP(nip_name_p       IN VARCHAR, --- Create NIP
                                            start_date_p     IN DATE,
                                            end_date_p       IN DATE,
                                            sdqc_frequency_p IN INTERVAL DAY TO SECOND,
                                            nip_user_p       IN NUMBER)
IS
  current_date DATE;
  draft_state  NUMBER := 1;
  admin        NUMBER := 1;
  nip_id_p     NUMBER;
  BEGIN
    SELECT sysdate
    INTO current_date
    FROM dual;

    SELECT nip_seq.NEXTVAL
    INTO nip_id_p
    FROM dual;

    INSERT INTO nip_audit_trial (nip_id, nip_name, start_date, end_date, sdqc_frequency, state_id, action_date, nip_user, nip_role)
    VALUES (nip_id_p, nip_name_p, start_date_p, end_date_p, sdqc_frequency_p, draft_state, current_date, nip_user_p,
            admin);

    INSERT INTO nip (id, nip_name, start_date, end_date, sdqc_frequency, state_id)
    VALUES (nip_id_p, nip_name_p, start_date_p, end_date_p, sdqc_frequency_p, draft_state);
  END;
/

CREATE OR REPLACE PROCEDURE UPDATE_NIP(nip_id_p         IN NUMBER,
                                       nip_name_p       IN VARCHAR,
                                       start_date_p     IN DATE,
                                       end_date_p       IN DATE,
                                       sdqc_frequency_p IN INTERVAL DAY TO SECOND,
                                       nip_user_p       IN NUMBER,
                                       nip_role_p       IN NUMBER)
IS
  current_date       DATE;
  draft_state        NUMBER := 1;
  approved           NUMBER := 4;
  rejected           NUMBER := 5;
  nip_state_id       NUMBER;
  is_user_nip_member NUMBER;
  BEGIN
    SELECT state_id
    INTO nip_state_id
    FROM nip
    WHERE nip.id = nip_id_p;

    SELECT count(1)
    INTO is_user_nip_member
    FROM nip_role_user n
    WHERE n.user_id = nip_user_p AND n.nip_id = nip_id_p;

    IF nip_state_id = rejected
    THEN
      raise_application_error(-20001, 'Cannot update rejected NIPS');
    ELSIF nip_state_id = approved
      THEN
        -- this can later be improved to not accept approved nips that have any sdqc filled and allow approved with no sdqc filled yet.
        raise_application_error(-20001, 'Cannot update approved NIPS');
    ELSIF is_user_nip_member = 0
      THEN
        raise_application_error(-20001, 'User is not involved in this NIP');
    ELSE
      SELECT sysdate
      INTO current_date
      FROM dual;

      INSERT INTO nip_audit_trial (nip_id, nip_name, start_date, end_date, sdqc_frequency, state_id, action_date, nip_user, nip_role)
      VALUES (nip_id_p, nip_name_p, start_date_p, end_date_p, sdqc_frequency_p, draft_state, current_date, nip_user_p,
              nip_role_p);

      UPDATE nip
      SET nip_name = nip_name_p, start_date = start_date_p, end_date = end_date_p, sdqc_frequency = sdqc_frequency_p,
        state_id   = draft_state
      WHERE nip.id = nip_id_p;
    END IF;
  END;
/


CREATE OR REPLACE PROCEDURE UPDATE_NIP_STATE(nip_id_p      IN NUMBER,
                                             nip_user_id   IN NUMBER,
                                             new_nip_state IN NUMBER,
                                             nip_role_p    IN NUMBER)
IS
  current_date         DATE;
  current_nip_state    NUMBER;

  pending_approval     NUMBER := 2;
  amendment_required   NUMBER := 3;
  approved             NUMBER := 4;
  rejected             NUMBER := 5;

  admin                NUMBER := 1;
  nip_user_role        NUMBER := 2;
  approver             NUMBER := 3;

  nip_name_p           VARCHAR(255);
  start_date_p         DATE;
  end_date_p           DATE;
  sdqc_frequency_p     INTERVAL DAY TO SECOND;
  is_user_nip_member   NUMBER;
  is_user_nip_user     NUMBER;
  is_user_nip_approver NUMBER;
  BEGIN
    SELECT state_id
    INTO current_nip_state
    FROM nip
    WHERE nip.id = nip_id_p;

    SELECT count(1)
    INTO is_user_nip_member
    FROM nip_role_user n
    WHERE n.user_id = nip_user_id AND n.nip_id = nip_id_p;

    SELECT count(1)
    INTO is_user_nip_user
    FROM nip_role_user n
    WHERE
      n.user_id = nip_user_id AND
      n.nip_id = nip_id_p AND
      n.role_id = nip_user_role;

    SELECT count(1)
    INTO is_user_nip_approver
    FROM nip_role_user n
    WHERE n.user_id = nip_user_id AND n.nip_id = nip_id_p AND n.role_id = approver;

    IF current_nip_state = rejected
    THEN
      raise_application_error(-20001, 'Cannot update rejected NIPS');
    ELSIF is_user_nip_member = 0
      THEN
        raise_application_error(-20001, 'User is not involved in this NIP');
    ELSIF new_nip_state = pending_approval AND nip_role_p != nip_user_role AND is_user_nip_user = 0
      THEN
        raise_application_error(-20001, 'Only role user can send NIP to approval');
    ELSIF (new_nip_state = amendment_required OR new_nip_state = approved OR new_nip_state = rejected) AND
          nip_role_p != approver AND is_user_nip_approver = 0
      THEN
        raise_application_error(-20001, 'Only role approver approve, reject or send nip to amendment.');
    ELSE
      SELECT sysdate
      INTO current_date
      FROM dual;

      SELECT
        nip_name,
        start_date,
        end_date,
        sdqc_frequency
      INTO nip_name_p, start_date_p, end_date_p, sdqc_frequency_p
      FROM nip
      WHERE nip.id = nip_id_p;

      INSERT INTO nip_audit_trial (nip_id, nip_name, start_date, end_date, sdqc_frequency, state_id, action_date, nip_user, nip_role)
      VALUES
        (nip_id_p, nip_name_p, start_date_p, end_date_p, sdqc_frequency_p, new_nip_state, current_date, nip_user_id,
         nip_role_p);

      UPDATE nip
      SET state_id = new_nip_state
      WHERE nip.id = nip_id_p;
    END IF;
  END;
/

CREATE OR REPLACE VIEW overdue_sdqc AS
  SELECT
    nip.id,
    nip.nip_name,
    sdqc.due_date,
    ((SELECT sysdate
      FROM dual) - sdqc.due_date) AS Overdue,
    nip_user.name
  FROM sdqc
    INNER JOIN nip ON sdqc.nip_id = nip.id
    LEFT OUTER JOIN nip_role_user ON nip.id = nip_role_user.nip_id
    INNER JOIN nip_user ON nip_role_user.user_id = nip_user.id
    INNER JOIN nip_role ON nip_role_user.role_id = nip_role.id
  WHERE
    sdqc.due_date < (SELECT sysdate
                     FROM dual) AND nip_role.id = 3;

-- view nip role user with insert of user/role into nip

CREATE OR REPLACE VIEW user_role_nips (nip_id, nip_name, username, user_role) AS
  SELECT
    nip.id,
    nip.nip_name,
    nip_user.name,
    nip_role.role
  FROM nip
    INNER JOIN nip_role_user ON nip.id = nip_role_user.nip_id
    INNER JOIN nip_role ON nip_role_user.role_id = nip_role.id
    INNER JOIN nip_user ON nip_role_user.user_id = nip_user.id;

CREATE TRIGGER add_user_to_nip_with_role
INSTEAD OF INSERT
  ON user_role_nips
  DECLARE
    user_id NUMBER;
    role_id NUMBER;
  BEGIN
    SELECT id
    INTO role_id
    FROM NIP_ROLE
    WHERE NIP_ROLE.role = :NEW.user_role;

    SELECT id
    INTO user_id
    FROM NIP_USER
    WHERE NIP_USER.name = :NEW.username;

    INSERT INTO NIP_ROLE_USER (nip_id, role_id, user_id) VALUES (:NEW.nip_id, role_id, user_id);
  END;
/

CREATE OR REPLACE VIEW nip_sdqc (id, nip_name, sdqc_id, due_date, date_performed, performed_by, document_path) AS
  SELECT
    nip.id,
    nip.nip_name,
    sdqc.id,
    sdqc.due_date,
    sdqc.date_performed,
    nip_user.name,
    sdqc.document_path
  FROM nip
    INNER JOIN sdqc ON nip.id = sdqc.nip_id
    INNER JOIN nip_user ON sdqc.performed_by = nip_user.id;

CREATE TRIGGER perform_sdqc
INSTEAD OF INSERT
  ON nip_sdqc
  DECLARE
    is_already_performed NUMBER;
    can_user_update      NUMBER;
    does_nip_exist       NUMBER;
    does_sdqc_exist      NUMBER;
    current_date         DATE;
  BEGIN
    SELECT sysdate
    INTO current_date
    FROM dual;

    IF NEW.sdqc_id IS NULL
    THEN
      raise_application_error(-20001, 'No sdqc_id provided on sdqc document submission.');
    END IF;

    IF NEW.document_path IS NULL
    THEN
      raise_application_error(-20001, 'No document provided on sdqc document submission.');
    END IF;

    IF NEW.performed_by IS NULL
    THEN
      raise_application_error(-20001, 'No user performing this sdqc was provided.');
    END IF;

    SELECT count(1)
    INTO does_nip_exist
    FROM nip
    WHERE nip.id = new.id;

    IF does_nip_exist = 0
    THEN
      raise_application_error(-20001, 'Nip with such ID does not exist.');
    END IF;

    SELECT count(1)
    INTO does_sdqc_exist
    FROM sdqc
    WHERE sdqc.id = new.sdqc_id;

    IF does_sdqc_exist = 0
    THEN
      raise_application_error(-20001, 'Sdqc due date with such ID does not exist.');
    END IF;

    SELECT count(1)
    INTO is_already_performed
    FROM sdqc
    WHERE sdqc.id = NEW.sdqc_id AND sdqc.date_performed IS NOT NULL;

    IF is_already_performed = 1
    THEN
      raise_application_error(-20001, 'Sdqc for this date was already performed.');
    END IF;

    SELECT count(1)
    INTO can_user_update
    FROM nip_role_user
    WHERE nip_role_user.id = NEW.id AND nip_role_user.role_id IN (1, 2);

    IF can_user_update = 0
    THEN
      raise_application_error(-20001, 'This user cannot post sdqc documents.');
    END IF;

    UPDATE SDQC
    SET
      performed_by = NEW.performed_by, date_performed = current_date, document_path = NEW.document_path
    WHERE sdqc.id = NEW.sdqc_id;
  END;
/
