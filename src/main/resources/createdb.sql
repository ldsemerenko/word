CREATE SEQUENCE word.word_id_seq;

CREATE TABLE word.word(
  id BIGINT PRIMARY KEY DEFAULT nextval('word.word_id_seq'),
  word TEXT,
  translation TEXT,
  alternativeTranslation TEXT,
  example TEXT,
  exampleTranslation TEXT,
  creature TIMESTAMP WITH TIME ZONE DEFAULT now(),
  lastRead DATE,
  callCount INT,
  correctTranslations INT

)