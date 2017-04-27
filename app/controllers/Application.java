package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;
import com.google.inject.Inject;
import dao.WordDao;
import dao.WordDaoImpl;
import model.Word;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.Collections;
import java.util.List;

import static play.libs.Json.toJson;

public class Application extends Controller{
    private final WordDao wordDao;

    @Inject
    public Application(WordDaoImpl wordDao) {
        this.wordDao = wordDao;
    }

    @Transactional(readOnly = true)
    public Result getRandomWords() {
        int count = Integer.parseInt(request().getQueryString("count"));

        List<Word> wordList = wordDao.findAll();
        Collections.shuffle(wordList);
        count = Math.min(count, wordList.size());
        wordList = wordList.subList(0, count);

        return ok(toJson(wordList));
    }

    @Transactional
    public Result addWord() {
        Word word;
        Gson gson = new Gson();
        JsonNode body = request().body().asJson();
        word = gson.fromJson(body.toString(), Word.class);
        if (wordDao.findWord(word.getWord()) == null) {
            wordDao.create(word);
            return ok("Word added");
        } else {
            return ok("The word already exists");
        }
    }

}
