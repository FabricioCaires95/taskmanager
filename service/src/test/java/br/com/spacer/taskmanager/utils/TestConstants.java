package br.com.spacer.taskmanager.utils;

import static br.com.spacer.taskmanager.utils.DateUtils.now;

import java.time.OffsetDateTime;
import java.util.UUID;

public final class TestConstants {
    public static final UUID DEFAULT_ID = UUID.fromString("3d90f8af-0ef7-4d5a-85a6-07349a8b4458");
    public static final String DEFAULT_TITLE = "Learn to code";
    public static final String DEFAULT_DESCRIPTION = "learn java programming";
    public static final OffsetDateTime DEFAULT_FINISH = now().plusDays(2);
    public static final Boolean IS_FINISHED = Boolean.FALSE;
    public static final OffsetDateTime CREATED_AT = now().plusDays(1);
}
