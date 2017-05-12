package dao;

import model.Word;

import java.util.List;


public interface WordDao {
    void create (Word users);
    void update (Word users);
    List<Word> findAll();
    List<Word> findNewWords();
    List<Word> findBadWords();
    Word findWord (String findWord);
    Word findTranslation (String findWord);
    }
