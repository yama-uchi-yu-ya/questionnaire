CREATE TABLE IF NOT EXISTS vegetables (
    vegetable_id int not null PRIMARY KEY ,
    name VARCHAR(255) not null ,
    is_deleted tinyint ,
    created_date datetime not null ,
    update_date datetime
);

CREATE TABLE IF NOT EXISTS meats (
    meat_id int not null PRIMARY KEY ,
    name VARCHAR(255) not null ,
    is_deleted tinyint ,
    created_date datetime not null ,
    update_date datetime
);

CREATE TABLE IF NOT EXISTS answers (
    answer_id int not null PRIMARY KEY ,
    meat_id int not null ,
    idol_name VARCHAR(64) not null ,
    is_deleted tinyint ,
    create_date datetime not null ,
    update_date datetime ,
    FOREIGN KEY(meat_id) REFERENCES meats(meat_id)
);

CREATE TABLE IF NOT EXISTS answervegetables (
    answervegetable_id int not null PRIMARY KEY ,
    vegetable_id int not null ,
    answer_id int not null ,
    is_deleted tinyint ,
    create_date datetime not null ,
    update_date datetime ,
    FOREIGN KEY (vegetable_id) REFERENCES vegetables(vegetable_id) ,
    FOREIGN KEY (answer_id) REFERENCES answers(answer_id)
);

CREATE TABLE IF NOT EXISTS admins (
    admin_id int not null ,
    name VARCHAR(255) not null ,
    password VARCHAR(255) not null ,
    is_deleted tinyint ,
    created_date datetime not null ,
    update_date datetime
);