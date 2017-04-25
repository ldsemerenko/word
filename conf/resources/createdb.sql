CREATE role wordUser;
ALTER role wordUser PASSWORD 'word';
CREATE DATABASE worddb WITH OWNER wordUser;
-------------------------------------------
CREATE SCHEMA word;
ALTER SCHEMA word owner to wordUser;
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
  lastRead DATE,
  callCount INT,
  correctTranslations INT
)