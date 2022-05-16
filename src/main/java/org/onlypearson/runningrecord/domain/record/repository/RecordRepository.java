package org.onlypearson.runningrecord.domain.record.repository;

import org.onlypearson.runningrecord.domain.record.Record;

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
