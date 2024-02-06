CREATE TABLE IF NOT EXISTS member (
    member_id BIGINT NOT NULL AUTO_INCREMENT,
    email VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(30) NOT NULL,
    nickname VARCHAR(20) NOT NULL UNIQUE,
    status ENUM ('DELETED','ACTIVE') NOT NULL DEFAULT 'ACTIVE',
    created_at DATETIME(6) NOT NULL,
    modified_at DATETIME(6) NOT NULL,
    PRIMARY KEY (member_id)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS forms (
    forms_id BIGINT NOT NULL AUTO_INCREMENT,
    member_id BIGINT NOT NULL,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    access ENUM ('PUBLIC', 'PRIVATE') NOT NULL DEFAULT 'PUBLIC',
    status ENUM ('READY', 'OPENED', 'CLOSED', 'DELETED') NOT NULL DEFAULT 'READY',
    started_at DATETIME(6) NOT NULL,
    ended_at DATETIME(6) NOT NULL,
    created_at DATETIME(6) NOT NULL,
    modified_at DATETIME(6) NOT NULL,
    PRIMARY KEY (forms_id),
    FOREIGN KEY (member_id) REFERENCES member (member_id)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS forms_question (
    forms_question_id BIGINT NOT NULL AUTO_INCREMENT,
    forms_id BIGINT NOT NULL,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    question_type ENUM ('RADIO', 'CHECK', 'TEXT') NOT NULL,
    required BOOL DEFAULT FALSE,
    ordinal INTEGER NOT NULL,
    quesiton_options JSON,
    created_at DATETIME(6) NOT NULL,
    modified_at DATETIME(6) NOT NULL,
    PRIMARY KEY (forms_question_id),
    FOREIGN KEY (forms_id) REFERENCES forms (forms_id)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS forms_respondent (
    forms_respondent_id BIGINT NOT NULL AUTO_INCREMENT,
    forms_id BIGINT NOT NULL,
    answers JSON,
    created_at DATETIME(6) NOT NULL,
    modified_at DATETIME(6) NOT NULL,
    PRIMARY KEY (forms_respondent_id),
    FOREIGN KEY (forms_id) REFERENCES forms (forms_id)
) engine=InnoDB;

insert into member (email, password, nickname, status, created_at, modified_at)
values ('test@test.com', 'test', 'Test', 'ACTIVE', CURRENT_DATE(), CURRENT_DATE());