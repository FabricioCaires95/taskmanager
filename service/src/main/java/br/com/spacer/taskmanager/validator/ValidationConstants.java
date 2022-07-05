package br.com.spacer.taskmanager.validator;

public final class ValidationConstants {

    public static final String MISSING_FIELD = ".missing";
    public static final String DESCRIPTION = "description";
    public static final String TITLE = "title";
    public static final String FINISH_AT = "finishAt";
    public static final String EXCEEDS_MAX_LENGTH = ".exceedsMaxLength";
    public static final String EXCEEDS_MAX_VALUE = ".exceedsMaxValue";
    public static final String BELLOW_MIN_VALUE = ".bellowMinValue";
    public static final String IN_THE_PAST = ".inThePast";
    public static final int MAX_LENGTH_DESCRIPTION = 250;
    public static final int MAX_LENGTH_TITLE = 100;

    private ValidationConstants() {}
}
