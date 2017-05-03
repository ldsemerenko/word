package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.inject.Inject;
import dao.WordDao;
import dao.WordDaoImpl;
import model.Word;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.ArrayList;
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
        Gson gson = new Gson();
        int count = Integer.parseInt(request().getQueryString("count"));

        List<Word> wordList = wordDao.findAll();
        Collections.shuffle(wordList);
        count = Math.min(count, wordList.size());
        List<Word> allWodrsList = new ArrayList<>(wordList);
        wordList = wordList.subList(0, count);

        JsonArray wordArray = new JsonArray();
        for (Word word : wordList){
            List<Word> tempList = new ArrayList<>(allWodrsList);
            JsonElement wordElement = gson.toJsonTree(word);
            tempList.remove(word);

            tempList = tempList.subList(0,4);
            tempList.add(word);
            Collections.shuffle(tempList);
            JsonArray randWordArray = new JsonArray();
            randWordArray.add(tempList.get(0).getTranslation());
            randWordArray.add(tempList.get(1).getTranslation());
            randWordArray.add(tempList.get(2).getTranslation());
            randWordArray.add(tempList.get(3).getTranslation());
            randWordArray.add(tempList.get(4).getTranslation());
            wordElement.getAsJsonObject().add("translations", randWordArray);

            wordArray.add(wordElement);
        }

        return ok(wordArray.toString());
    }

    @Transactional
    public Result addWord() {
        Word word;
        Gson gson = new Gson();
        JsonNode body = request().body().asJson();
        word = gson.fromJson(body.toString(), Word.class);
        if(word.getWord() == null || word.getTranslation() == null){return ok("Input error");}
        if (wordDao.findWord(word.getWord()) == null) {
            wordDao.create(word);
            return ok("Word added");
        } else {
            return ok("The word already exists");
        }
    }

}
