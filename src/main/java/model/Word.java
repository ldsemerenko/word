package model;

import java.util.Date;

public class Word {
    Long id;
    String word;
    String translation;
    String alternativeTranslation;
    String example;
    String exampleTranslation;
    Date creature;
    Date lastRead;
    int callCount;
    int correctTranslations;
}
