package dao;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import model.Word;
import play.db.jpa.JPAApi;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import java.util.Collections;
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
        TypedQuery<Word> query = em.createQuery("SELECT w from Word w", Word.class);
        try {
            return query.getResultList();
        } catch (NoResultException e){
            return Collections.emptyList();
        }
    }

    @Override
    public Word findWord(String findWord) {
        EntityManager em = jpaApi.em();
        TypedQuery<Word> query = em.createQuery("SELECT w from Word w where w.word = :findW", Word.class)
                .setParameter("findW", findWord);
        try {
            return query.getSingleResult();
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    public Word findTranslation(String findWord) {
        EntityManager em = jpaApi.em();
        TypedQuery<Word> query = em.createQuery("SELECT w from Word w where w.word = :findT", Word.class)
                .setParameter("findT", findWord);
        try {
            return query.getSingleResult();
        } catch (NoResultException e){
            return null;
        }
    }
}