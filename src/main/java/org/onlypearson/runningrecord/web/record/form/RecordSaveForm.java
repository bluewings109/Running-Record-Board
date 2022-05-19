package org.onlypearson.runningrecord.web.record.form;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;

@Getter
@Setter
public class RecordSaveForm {

    private Long id;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dateTime; // 날짜. 회차의 경우 날짜로 정렬시켜서 list 리턴


    private Double temperature; // 기온

    @NotNull
    @Positive
    private Double distance; // 거리

    @NotNull
    @PositiveOrZero
    private Integer durationHour; // 달리기 시간

    @NotNull
    @PositiveOrZero
    private Integer durationMin; // 달리기 분

    @NotNull
    @PositiveOrZero
    private Integer durationSec; // 달리기 초

    @Positive
    private Integer heartRate; // 심박수

    private String comment; // 비고


    public RecordSaveForm() {
    }




}
