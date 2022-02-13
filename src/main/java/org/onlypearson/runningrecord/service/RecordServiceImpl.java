package org.onlypearson.runningrecord.service;

import org.onlypearson.runningrecord.domain.Record;
import org.onlypearson.runningrecord.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordServiceImpl implements RecordService{

    private final RecordRepository recordRepository;

    @Autowired
    public RecordServiceImpl(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @Override
    public Record submit(Record record) {
        return recordRepository.save(record);
    }


    @Override
    public Record findRecord(Long recordId) {
        return recordRepository.findById(recordId);
    }

    @Override
    public List<Record> findAllRecords() {
        return recordRepository.findAll();
    }

    @Override
    public void edit(Long recordId, Record editRecord) {
        recordRepository.update(recordId, editRecord);
    }

    @Override
    public Record remove(Long recordId) {
        Record deletedRecord = recordRepository.findById(recordId);
        recordRepository.delete(recordId);
        return deletedRecord;
    }

    @Override
    public void removeAllRecords() {
        recordRepository.clearStore();
    }
}
