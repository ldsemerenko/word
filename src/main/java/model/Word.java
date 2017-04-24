package model;

import java.util.Date;

public class Word {
    private Long id;
    private String word;
    private String transcription;
    private String translation;
    private String[] alternativeTranslation;
    private String example;
    private String exampleTranslation;
    private Date creature;
    private Date lastRead;
    private int callCount;
    private int correctTranslations;
}
