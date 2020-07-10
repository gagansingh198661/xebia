DROP TABLE IF EXISTS Article;

CREATE TABLE Article (
  id int  PRIMARY KEY,
  uuid varchar(100) not null unique,
  slug VARCHAR(250) ,
  title VARCHAR(250) ,
  description VARCHAR(250) ,
  body VARCHAR(250),
  tags VARCHAR(250),
  created_At DATE,
  updated_At date,
  favorited bit,
  favorites_Count int
);
DROP TABLE IF EXISTS human_word_speed;
CREATE TABLE human_word_speed (
  id int  PRIMARY KEY,
  word_number_minute int not null
);


