package org.onlypearson.runningrecord.web.TestDataInit;

import lombok.extern.slf4j.Slf4j;
import org.onlypearson.runningrecord.domain.record.Record;
import org.onlypearson.runningrecord.domain.record.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Slf4j
public class TestRecordDataInit {
    private final RecordRepository recordRepository;

    @Autowired
    public TestRecordDataInit(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initTestRecord(){
        log.info("initTestRecord Start");
        List<Record> records = recordRepository.findAll();
        if(records.isEmpty()){
            LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
            Record record = new Record(now,9.0,10.0,1,3,0,170,"testComment");
            recordRepository.save(record);
        }
    }
}
