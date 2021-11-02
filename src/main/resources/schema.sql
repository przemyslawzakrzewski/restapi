create table POST(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(400) NOT NULL,
    content VARCHAR(2000) NULL,
    created timestamp
);

create table COMMENT(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    post_id BIGINT NOT NULL,
    content VARCHAR(2000) NULL,
    created timestamp
);

ALTER TABLE COMMENT
ADD CONSTRAINT comment_post_id
FOREIGN KEY (post_id) references post(id);

create table owner(
    owner_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(400),
    last_name VARCHAR(400),
    mobile VARCHAR(400),
    email VARCHAR(400),
    notes VARCHAR(400)
);

create table animal(
    animal_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    owner_id BIGINT,
    animal_type_id BIGINT NOT NULL,
    name VARCHAR(400),
    age BIGINT,
    weight BIGINT,
    food BIGINT,
    entertainment BIGINT,
    health BIGINT
);

create table animal_type(
    animal_type_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(400),
    thumbnail VARCHAR(400)
);

create table food(
    food_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(400),
    value BIGINT
);

create table game(
    game_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(400),
    value BIGINT
);

create table intervention(
    intervention_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(400),
    value BIGINT
);

create table animal_type_food(
    animal_type_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    food_id BIGINT NOT NULL
);

create table animal_type_game(
    animal_type_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    game_id BIGINT NOT NULL
);

create table animal_type_intervention(
    animal_type_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    intervention_id BIGINT NOT NULL
);