package org.onlypearson.runningrecord.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class Record {

    private Long id;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dateTime; // 날짜. 회차의 경우 날짜로 정렬시켜서 list 리턴
    private Double temperature; // 기온
    private int paceMin; // 페이스 분
    private int paceSec; // 페이스 초
    private double distance; // 거리
    private int durationHour; // 달리기 시간
    private int durationMin; // 달리기 분
    private int durationSec; // 달리기 초
    private Integer heartRate; // 심박수
    private String comment; // 비고


    public Record(LocalDateTime dateTime, Double temperature, double distance, int durationHour, int durationMin, int durationSec, Integer heartRate, String comment) {
        this.dateTime = dateTime;
        this.temperature = temperature;
        this.distance = distance;
        this.durationHour = durationHour;
        this.durationMin = durationMin;
        this.durationSec = durationSec;
        this.heartRate = heartRate;
        this.comment = comment;
        calcPace();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public void setDistance(double distance) {
        this.distance = distance;
        calcPace();
    }

    public void setDurationHour(int durationHour) {
        this.durationHour = durationHour;
        calcPace();
    }

    public void setDurationMin(int durationMin) {
        this.durationMin = durationMin;
        calcPace();
    }

    public void setDurationSec(int durationSec) {
        this.durationSec = durationSec;
        calcPace();
    }

    public void setHeartRate(Integer heartRate) {
        this.heartRate = heartRate;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    private void calcPace(){
        int duration = 3600*durationHour + 60*durationMin + durationSec;

        int pace = (int)(duration / distance);

        paceMin = pace/60;
        paceSec = pace - 60*paceMin;
    }


    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", temperature=" + temperature +
                ", paceMin=" + paceMin +
                ", paceSec=" + paceSec +
                ", distance=" + distance +
                ", durationHour=" + durationHour +
                ", durationMin=" + durationMin +
                ", durationSec=" + durationSec +
                ", heartRate=" + heartRate +
                ", comment='" + comment + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return paceMin == record.paceMin && paceSec == record.paceSec && Double.compare(record.distance, distance) == 0 && durationHour == record.durationHour && durationMin == record.durationMin && durationSec == record.durationSec && dateTime.equals(record.dateTime) && Objects.equals(temperature, record.temperature) && Objects.equals(heartRate, record.heartRate) && Objects.equals(comment, record.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateTime, temperature, paceMin, paceSec, distance, durationHour, durationMin, durationSec, heartRate, comment);
    }


}
