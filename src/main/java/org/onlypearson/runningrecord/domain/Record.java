package org.onlypearson.runningrecord.domain;

import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class Record implements Comparable<Record>{

    private Long id;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dateTime; // 날짜. 회차의 경우 날짜로 정렬시켜서 list 리턴
    private Double temperature; // 기온
    private Double distance; // 거리
    private Integer durationHour; // 달리기 시간
    private Integer durationMin; // 달리기 분
    private Integer durationSec; // 달리기 초
    private Integer heartRate; // 심박수
    private String comment; // 비고

    public Record() {
    }

    public Record(LocalDateTime dateTime, Double temperature, Double distance, Integer durationHour, Integer durationMin, Integer durationSec, Integer heartRate, String comment) {
        this.dateTime = dateTime;
        this.temperature = temperature;
        this.distance = distance;
        this.durationHour = durationHour;
        this.durationMin = durationMin;
        this.durationSec = durationSec;
        this.heartRate = heartRate;
        this.comment = comment;
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

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public void setDurationHour(Integer durationHour) {
        this.durationHour = durationHour;
    }

    public void setDurationMin(Integer durationMin) {
        this.durationMin = durationMin;
    }

    public void setDurationSec(Integer durationSec) {
        this.durationSec = durationSec;
    }

    public String getPace(){
        if(durationHour == null || durationMin == null || durationSec == null){
            return "";
        }

        int duration = 3600*durationHour + 60*durationMin + durationSec;

        int pace = (int)(duration / distance);

        int paceMin = pace/60;
        int paceSec = pace - 60*paceMin;

        StringBuilder sb = new StringBuilder();
        sb.append(paceMin);
        sb.append("'");
        if(paceSec < 10){
            sb.append(0);
        }
        sb.append(paceSec);
        sb.append("\"/km");

        return sb.toString();

    }

    public void setHeartRate(Integer heartRate) {
        this.heartRate = heartRate;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", temperature=" + temperature +
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
        return dateTime.equals(record.dateTime) && Objects.equals(temperature, record.temperature) && distance.equals(record.distance) && durationHour.equals(record.durationHour) && durationMin.equals(record.durationMin) && durationSec.equals(record.durationSec) && Objects.equals(heartRate, record.heartRate) && Objects.equals(comment, record.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateTime, temperature, distance, durationHour, durationMin, durationSec, heartRate, comment);
    }

    @Override
    public int compareTo(Record o) {
        return this.dateTime.compareTo(o.dateTime);
    }
}
