package org.onlypearson.runningrecord.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.onlypearson.runningrecord.domain.Record;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemoryRecordRepositoryTest {

    private MemoryRecordRepository memoryRecordRepository = new MemoryRecordRepository();

    @BeforeEach
    void init(){
        memoryRecordRepository.clearStore();
    }

    @Test
    @DisplayName("저장 테스트")
    void save(){

        //given
        Record record = new Record(LocalDateTime.now(),0.0,10.0,1,0,0,180,"testComment");

        //when
        memoryRecordRepository.save(record);
        Record findRecord = memoryRecordRepository.findById(record.getId());

        //then
        assertThat(findRecord).isEqualTo(record);
    }

    @Test
    @DisplayName("ID로 검색")
    void findById(){
        //given
        Record record = new Record(LocalDateTime.now(),0.0,10.0,1,0,0,180,"testComment");
        memoryRecordRepository.save(record);

        //when
        Record findRecord = memoryRecordRepository.findById(record.getId());

        //then
        assertThat(findRecord).isEqualTo(record);


    }

    @Test
    @DisplayName("List로 가져오기 테스트")
    void findAll(){
        //given
        Record record1 = new Record(LocalDateTime.now(),0.0,10.0,1,0,0,180,"testComment");
        Record record2 = new Record(LocalDateTime.now(),0.0,10.3,2,0,0,180,"testComment2");
        memoryRecordRepository.save(record1);
        memoryRecordRepository.save(record2);

        //when
        List<Record> list = memoryRecordRepository.findAll();

        //then
        assertThat(list.size()).isEqualTo(2);
        assertThat(list).contains(record1, record2);
    }

    @Test
    @DisplayName("업데이트")
    void update(){
        //given
        Record record = new Record(LocalDateTime.now(),0.0,10.0,1,0,0,180,"testComment");
        memoryRecordRepository.save(record);
        Long id = record.getId();

        //when
        Record editRecord = new Record(LocalDateTime.now(),0.0,15.0,1,20,29,180,"testComment");
        memoryRecordRepository.update(id, editRecord);
        Record findRecord = memoryRecordRepository.findById(id);

        //then
        assertThat(findRecord).isEqualTo(editRecord);
    }

    @Test
    @DisplayName("id로 삭제")
    void delete(){
        //given
        Record record = new Record(LocalDateTime.now(),0.0,10.0,1,0,0,180,"testComment");

        //when
        memoryRecordRepository.save(record);
        Long id = record.getId();

        Record deletedRecord = memoryRecordRepository.delete(id);

        //then
        assertThat(memoryRecordRepository.findById(id)).isNull();
        assertThat(deletedRecord).isEqualTo(record);
    }

}