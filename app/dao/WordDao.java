package dao;

import model.Word;

import java.util.List;


public interface WordDao {
    void create (Word users);
    void update (Word users);
    List<Word> findAll();

    }
