CREATE TABLE IF NOT EXISTS query_answer (
    id VARCHAR(8) PRIMARY KEY ,
    like_meat VARCHAR(1) not null ,
    like_veg VARCHAR(4)not null ,
    like_idol VARCHAR(64)not null
);

CREATE TABLE IF NOT EXISTS admin_info (
    admin_id VARCHAR(16) PRIMARY KEY not null ,
    admin_pw VARCHAR(16) not null
);
