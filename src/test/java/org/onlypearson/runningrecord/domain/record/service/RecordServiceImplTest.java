package org.onlypearson.runningrecord.domain.record.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.onlypearson.runningrecord.domain.record.Record;
import org.onlypearson.runningrecord.domain.record.repository.MemoryRecordRepository;
import org.onlypearson.runningrecord.domain.record.service.RecordService;
import org.onlypearson.runningrecord.domain.record.service.RecordServiceImpl;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RecordServiceImplTest {

    private final RecordService recordService = new RecordServiceImpl(new MemoryRecordRepository());

    @BeforeEach
    void init(){
        recordService.removeAllRecords();
    }


    @Test
    void submit() {
        //given
        Record record = new Record(LocalDateTime.now(),0.0,10.0,1,0,0,180,"testComment");

        //when
        recordService.submit(record);
        Record findRecord = recordService.findRecord(record.getId());

        //then
        assertThat(findRecord).isEqualTo(record);

    }

    @Test
    void findRecord() {
        //given
        Record record = new Record(LocalDateTime.now(),0.0,10.0,1,0,0,180,"testComment");
        recordService.submit(record);

        //when
        Record findRecord = recordService.findRecord(record.getId());

        //then
        assertThat(findRecord).isEqualTo(record);
    }

    @Test
    void findAllRecords() {
        //given
        Record record1 = new Record(LocalDateTime.now(),0.0,10.0,1,0,0,180,"testComment");
        Record record2 = new Record(LocalDateTime.now(),1.0,13.0,2,19,0,180,"testComment");
        recordService.submit(record1);
        recordService.submit(record2);

        //when
        List<Record> list = recordService.findAllRecords();

        //then
        assertThat(list.size()).isEqualTo(2);
        assertThat(list).contains(record1, record2);
    }

    @Test
    void edit() {
        //given
        Record record = new Record(LocalDateTime.now(),0.0,10.0,1,0,0,180,"testComment");
        recordService.submit(record);
        Long id = record.getId();

        //when
        Record editRecord = new Record(LocalDateTime.now(), -2.0, 15.8,1, 28, 0, 0, "testComment");
        recordService.edit(id, editRecord);
        Record findRecord = recordService.findRecord(id);

        //then
        assertThat(findRecord).isEqualTo(editRecord);
    }

    @Test
    void remove() {
        //given
        Record record = new Record(LocalDateTime.now(),0.0,10.0,1,0,0,180,"testComment");

        //when
        recordService.submit(record);
        Long id = record.getId();

        Record deletedRecord = recordService.remove(id);

        //then
        assertThat(recordService.findRecord(id)).isNull();
        assertThat(deletedRecord).isEqualTo(record);
    }
}