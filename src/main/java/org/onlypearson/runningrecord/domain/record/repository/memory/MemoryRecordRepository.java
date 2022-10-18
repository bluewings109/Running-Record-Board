package org.onlypearson.runningrecord.domain.record.repository.memory;

import org.onlypearson.runningrecord.domain.record.Record;
import org.onlypearson.runningrecord.domain.record.repository.RecordRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MemoryRecordRepository implements RecordRepository {

    private static long sequence;
    private static final Map<Long, Record> store = new ConcurrentHashMap<>();

    @Override
    public Record save(Record record) {
        record.setId(++sequence);
        store.put(sequence, record);
        return record;
    }

    @Override
    public Record findById(Long id) {
        return store.get(id);
    }

    @Override
    public List<Record> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void update(Long id, Record record) {
        Record savedRecord = store.get(id);
        savedRecord.setDateTime(record.getDateTime());
        savedRecord.setTemperature(record.getTemperature());
        savedRecord.setDistance(record.getDistance());
        savedRecord.setDurationHour(record.getDurationHour());
        savedRecord.setDurationMin(record.getDurationMin());
        savedRecord.setDurationSec(record.getDurationSec());
        savedRecord.setHeartRate(record.getHeartRate());
        savedRecord.setComment(record.getComment());
    }

    @Override
    public Record delete(Long id) {
        Record record = store.get(id);
        store.remove(id);
        return record;
    }

    @Override
    public void clearStore() {
        store.clear();
    }
}
