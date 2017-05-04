CREATE role wordUser;
ALTER role wordUser PASSWORD 'word';
CREATE DATABASE worddb WITH OWNER wordUser;
-------------------------------------------
CREATE SCHEMA word;
ALTER SCHEMA word OWNER TO wordUser;
Alter user worduser with superuser login;
-----
CREATE SEQUENCE word.word_id_seq;
-----
CREATE TABLE word.word(
  id BIGINT PRIMARY KEY DEFAULT nextval('word.word_id_seq'),
  word TEXT,
  transcription TEXT,
  translation TEXT,
  alternativeTranslation TEXT[],
  example TEXT,
  exampleTranslation TEXT,
  creature TIMESTAMP WITH TIME ZONE DEFAULT now(),
  lastRead TIMESTAMP WITH TIME ZONE,
  callCount INT,
  correctTranslations INT
);