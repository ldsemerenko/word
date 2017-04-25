package dao;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import model.Word;
import play.db.jpa.JPAApi;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class WordDaoImpl implements WordDao {
    private final JPAApi jpaApi;

    @Inject
    public WordDaoImpl(JPAApi jpaApi) {
        this.jpaApi = jpaApi;
    }

    @Override
    public void create(Word users) {
        EntityManager em = jpaApi.em();
        em.merge(users);
    }

    @Override
    public void update(Word users) {
        EntityManager em = jpaApi.em();
        em.merge(users);
    }

    @Override
    public List<Word> findAll() {
        EntityManager em = jpaApi.em();
        List<Word> wordList = new ArrayList<>();
//        em.find(Word.class, );
        return wordList.isEmpty()?new ArrayList<Word>():wordList;
    }
}