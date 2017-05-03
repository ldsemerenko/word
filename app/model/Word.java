package model;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Date;

@Entity
@TypeDefs(value={
        @TypeDef(name = "varcharArr", typeClass = db_types.VarcharArrayType.class)
})
@Table(name = "word", schema = "word")
public class Word {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "word")
    private String word;

    @Column(name = "transcription")
    private String transcription;

    @Column(name = "translation")
    private String translation;

    @Column(name = "alternativeTranslation")
    @Type(type = "varcharArr")
    private String[] alternativeTranslation;

    @Column(name = "example")
    private String example;

    @Column(name = "exampleTranslation")
    private String exampleTranslation;

    @Column(name = "creature")
    private Date creature;

    @Column(name = "lastRead")
    private Date lastRead;

    @Column(name = "callCount")
    private int callCount;

    @Column(name = "correctTranslations")
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

    @Override
    public String toString() {
        return "Word{" +
                "id=" + id +
                ", word='" + word + '\'' +
                ", transcription='" + transcription + '\'' +
                ", translation='" + translation + '\'' +
                ", alternativeTranslation=" + Arrays.toString(alternativeTranslation) +
                ", example='" + example + '\'' +
                ", exampleTranslation='" + exampleTranslation + '\'' +
                ", creature=" + creature +
                ", lastRead=" + lastRead +
                ", callCount=" + callCount +
                ", correctTranslations=" + correctTranslations +
                '}';
    }
}
