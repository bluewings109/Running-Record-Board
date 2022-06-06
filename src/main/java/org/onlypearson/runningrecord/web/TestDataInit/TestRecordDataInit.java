package org.onlypearson.runningrecord.web.TestDataInit;

import org.onlypearson.runningrecord.domain.record.Record;
import org.onlypearson.runningrecord.domain.record.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
public class TestRecordDataInit {
    private final RecordService recordService;

    @Autowired
    public TestRecordDataInit(RecordService recordService) {
        this.recordService = recordService;
    }

    @PostConstruct
    void init(){
        LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        Record record1 = new Record(now,9.0,10.0,1,3,0,170,"testComment");
        Record record2 = new Record(now, 0.0, 10.0, 1, 0, 0, 180, "testComment");
        recordService.submit(record1);
        recordService.submit(record2);
    }
}
