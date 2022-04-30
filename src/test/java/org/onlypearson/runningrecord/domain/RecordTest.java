package org.onlypearson.runningrecord.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class RecordTest {

    @Test
    void setDistance() {

        //given
        Record record = new Record(LocalDateTime.now(), 0.0,10.0, 1,0,0,100,"테스트코멘트");

        //when
        record.setDistance(5.0);

        //then
        assertThat(record.getDistance()).isEqualTo(5);
    }

    @Test
    void setDurationHour() {
        //given
        Record record = new Record(LocalDateTime.now(), 0.0,10.0, 1,0,0,100,"테스트코멘트");

        //when
        record.setDurationHour(2);

        //then
        assertThat(record.getDurationHour()).isEqualTo(2);
    }

    @Test
    void setDurationMin() {
        //given
        Record record = new Record(LocalDateTime.now(), 0.0,10.0, 1,0,0,100,"테스트코멘트");

        //when
        record.setDurationHour(0);
        record.setDurationMin(30);

        //then
        assertThat(record.getDurationMin()).isEqualTo(30);
    }

    @Test
    void setDurationSec() {
        //given
        Record record = new Record(LocalDateTime.now(), 0.0,1.0, 1,0,0,100,"테스트코멘트");

        //when
        record.setDurationHour(0);
        record.setDurationSec(30);

        //then
        assertThat(record.getDurationSec()).isEqualTo(30);
    }

    @Test
    void getPace(){
        //given
        Record record = new Record(LocalDateTime.now(), 0.0, 10.0, 1,0,0,100, "");

        //when
        String pace = record.getPace();

        //then
        assertThat(pace).isEqualTo("6'00\"/km");
    }
}