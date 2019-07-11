# --- !Ups
CREATE TABLE people
(
    id int auto_increment PRIMARY KEY,
    name VARCHAR(255) not null,
    mail VARCHAR(255) not NULL,
    tel VARCHAR(255)
);

INSERT into people
VALUES
    (default, 'taro', 'taro@yamada', '999-999');
INSERT into people
VALUES
    (default, 'hanako', 'hanako@flower', '888-888');
INSERT into people
VALUES
    (default, 'sachiko', 'sachiko@happy', '777-777');

#--- !Downs
drop TABLE people
