package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.inject.Inject;
import dao.WordDao;
import dao.WordDaoImpl;
import model.Word;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Application extends Controller{
    private final WordDao wordDao;
    private final String FILE_NAME = "words.json";

    @Inject
    public Application(WordDaoImpl wordDao) {
        this.wordDao = wordDao;
    }

    @Transactional(readOnly = true)
    public Result uploadWords(){
        FileOutputStream fileOutputStream;
        File file = new File(FILE_NAME);
        List<Word> wordList = wordDao.findAll();
        Gson gson = new Gson();
        JsonElement jsonElement = new JsonObject();
        jsonElement.getAsJsonObject().add("words", gson.toJsonTree(wordList).getAsJsonArray());
        try {
            fileOutputStream = new FileOutputStream(file);
            try {
                fileOutputStream.write(jsonElement.toString().getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return ok("Uploaded " + wordList.size() + " words");
    }

    @Transactional
    public Result downloadWords(){
        Gson gson = new Gson();
        Word[] words;
        int counter = 0;
        String wordsAsStr = "";

        try {
            wordsAsStr = Files.readAllLines(Paths.get(FILE_NAME)).get(0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JsonElement jsonElement = gson.fromJson(wordsAsStr, JsonElement.class);
        words = gson.fromJson(jsonElement.getAsJsonObject().getAsJsonArray("words"), Word[].class);

        for (Word w : words){
            if(wordDao.findWord(w.getWord()) == null){
                counter++;
                wordDao.create(w);
            }
        }

        return ok("Downloaded " + counter + " words");
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
            Collections.shuffle(tempList);
            JsonArray randWordArray = new JsonArray();
            randWordArray.add(gson.toJsonTree(word));
            tempList.remove(word);
            tempList = tempList.subList(0,4);

            for (Word w : tempList){
                randWordArray.add(gson.toJsonTree(w));
            }
            wordArray.add(randWordArray);
        }

        return ok(wordArray.toString());
    }

    @Transactional
    public Result updateWord(){
        Word word;
        Gson gson = new Gson();
        JsonNode body = request().body().asJson();
        word = gson.fromJson(body.toString(), Word.class);
        word.setLastRead(new Date(System.currentTimeMillis()));
        wordDao.update(word);
        return ok("update ok");
    }

    @Transactional
    public Result addWord() {
        Word word;
        Gson gson = new Gson();
        JsonNode body = request().body().asJson();
        word = gson.fromJson(body.toString(), Word.class);
        if(word.getWord() == null || word.getTranslation() == null){return ok("Input error");}
        if (wordDao.findWord(word.getWord()) == null) {
            word.setCreature(new Date(System.currentTimeMillis()));
            wordDao.create(word);
            return ok("Word added");
        } else {
            return ok("The word already exists");
        }
    }
}
