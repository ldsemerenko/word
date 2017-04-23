package dao;

import model.Word;

import java.util.List;

public interface WordDAO {
    void create (Word users);
    void update (Word users);
    List<Word> findAll();

    }
