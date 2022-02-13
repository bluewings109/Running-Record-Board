package org.onlypearson.runningrecord.repository;

import org.onlypearson.runningrecord.domain.Record;

import java.util.List;

public interface RecordRepository {

    //create
    Record save(Record record);

    //read
    Record findById(Long id);
    List<Record> findAll();

    //update
    void update(Long id, Record record);

    //delete
    Record delete(Long id);
    void clearStore();


}
