package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Word {
    @Id
    @Column
    private Long id;
    @Column
    private String word;
    @Column
    private String transcription;
    @Column
    private String translation;
    @Column
    private String[] alternativeTranslation;
    @Column
    private String example;
    @Column
    private String exampleTranslation;
    @Column
    private Date creature;
    @Column
    private Date lastRead;
    @Column
    private int callCount;
    @Column
    private int correctTranslations;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTranscription() {
        return transcription;
    }

    public void setTranscription(String transcription) {
        this.transcription = transcription;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String[] getAlternativeTranslation() {
        return alternativeTranslation;
    }

    public void setAlternativeTranslation(String[] alternativeTranslation) {
        this.alternativeTranslation = alternativeTranslation;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getExampleTranslation() {
        return exampleTranslation;
    }

    public void setExampleTranslation(String exampleTranslation) {
        this.exampleTranslation = exampleTranslation;
    }

    public Date getCreature() {
        return creature;
    }

    public void setCreature(Date creature) {
        this.creature = creature;
    }

    public Date getLastRead() {
        return lastRead;
    }

    public void setLastRead(Date lastRead) {
        this.lastRead = lastRead;
    }

    public int getCallCount() {
        return callCount;
    }

    public void setCallCount(int callCount) {
        this.callCount = callCount;
    }

    public int getCorrectTranslations() {
        return correctTranslations;
    }

    public void setCorrectTranslations(int correctTranslations) {
        this.correctTranslations = correctTranslations;
    }
}
