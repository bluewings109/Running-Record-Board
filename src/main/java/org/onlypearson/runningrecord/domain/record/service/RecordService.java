package org.onlypearson.runningrecord.domain.record.service;

import org.onlypearson.runningrecord.domain.record.Record;

import java.util.List;

public interface RecordService {

    //create
    Record submit(Record record);

    //read
    Record findRecord(Long recordId);
    List<Record> findAllRecords();

    //update
    void edit(Long recordId, Record editRecord);

    //delete
    Record remove(Long recordId);
    void removeAllRecords();
}
