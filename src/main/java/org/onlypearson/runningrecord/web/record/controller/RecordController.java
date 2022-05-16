package org.onlypearson.runningrecord.web.record.controller;

import lombok.extern.slf4j.Slf4j;
import org.onlypearson.runningrecord.domain.record.Record;
import org.onlypearson.runningrecord.domain.record.service.RecordService;
import org.onlypearson.runningrecord.web.validation.form.RecordEditForm;
import org.onlypearson.runningrecord.web.validation.form.RecordSaveForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Slf4j
@Controller
@RequestMapping("/records")
public class RecordController {

    private final RecordService recordService;

    @Autowired
    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }


    @GetMapping
    public String findRecords(Model model){
        model.addAttribute("records", recordService.findAllRecords());
        return "records";
    }

    @PostMapping("/add")
    public String addRecord(@Validated @ModelAttribute RecordSaveForm form, BindingResult bindingResult){

        // 특정 필드 validation 이 아닌 전체 로직 validation
        if(form.getDurationHour() != null && form.getDurationMin() != null && form.getDurationSec() != null){
            int totalDuration = form.getDurationHour() + form.getDurationMin() + form.getDurationSec();
            if(totalDuration <= 0){
                bindingResult.reject("zeroDuration");
            }
        }


        // validation
        if(bindingResult.hasErrors()){
            log.info("bindingResult={}",bindingResult);
            return "addRecordForm";
        }

        Record record = new Record();
        record.setId(form.getId());
        record.setDateTime(form.getDateTime());
        record.setDistance(form.getDistance());
        record.setDurationHour(form.getDurationHour());
        record.setDurationMin(form.getDurationMin());
        record.setDurationSec(form.getDurationSec());
        record.setTemperature(form.getTemperature());
        record.setComment(form.getComment());

        // 성공로직
        recordService.submit(record);
        return "redirect:/records";
    }

    @GetMapping("/add")
    public String addRecordForm(Model model){
        model.addAttribute(new RecordSaveForm());
        return "addRecordForm";
    }

    @GetMapping("/{recordId}/edit")
    public String editRecordForm(@PathVariable Long recordId, Model model, HttpServletRequest request){
        log.trace("REQUEST : {} from {}",request.getRequestURL(), request.getRemoteAddr());

        Record findRecord = recordService.findRecord(recordId);

        RecordEditForm form = new RecordEditForm();
        form.setId(findRecord.getId());
        form.setDateTime(findRecord.getDateTime());
        form.setDurationHour(findRecord.getDurationHour());
        form.setDurationMin(findRecord.getDurationMin());
        form.setDurationSec(findRecord.getDurationSec());
        form.setTemperature(findRecord.getTemperature());
        form.setComment(findRecord.getComment());
        form.setDistance(findRecord.getDistance());
        form.setHeartRate(findRecord.getHeartRate());

        model.addAttribute(form);

        return "editRecordForm";

    }

    @PostMapping("/{recordId}")
    public String editRecord(@PathVariable Long recordId, @Validated @ModelAttribute RecordEditForm form, BindingResult bindingResult){
        // 특정 필드 validation 이 아닌 전체 로직 validation
        if(form.getDurationHour() != null && form.getDurationMin() != null && form.getDurationSec() != null){
            int totalDuration = form.getDurationHour() + form.getDurationMin() + form.getDurationSec();
            if(totalDuration <= 0){
                bindingResult.reject("zeroDuration");
            }
        }

        // validation
        if(bindingResult.hasErrors()){
            log.info("bindingResult={}",bindingResult);
            return "editRecordForm";
        }

        Record record = new Record();
        record.setId(form.getId());
        record.setDateTime(form.getDateTime());
        record.setDistance(form.getDistance());
        record.setDurationHour(form.getDurationHour());
        record.setDurationMin(form.getDurationMin());
        record.setDurationSec(form.getDurationSec());
        record.setTemperature(form.getTemperature());
        record.setComment(form.getComment());

        // 성공로직
        recordService.edit(recordId, record);
        return "redirect:/records";
    }

    @DeleteMapping
    @ResponseBody
    public String deleteRecord(@RequestParam Long recordId, HttpServletRequest request){
        log.trace("REQUEST : {} from {}",request.getRequestURL(), request.getRemoteAddr());
        log.info("Delete Record. ID : {}", recordId);

        recordService.remove(recordId);
        return "삭제 성공";
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
