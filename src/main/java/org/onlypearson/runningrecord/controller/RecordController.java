package org.onlypearson.runningrecord.controller;

import lombok.extern.slf4j.Slf4j;
import org.onlypearson.runningrecord.domain.Record;
import org.onlypearson.runningrecord.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

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
    public String findRecords(Model model, HttpServletRequest request){
        log.trace("REQUEST : {} from {}",request.getRequestURL(), request.getRemoteAddr());
        model.addAttribute("records", recordService.findAllRecords());
        return "records";
    }

    @PostMapping
    public String addRecord(@ModelAttribute Record record, HttpServletRequest request){
        log.trace("REQUEST : {} from {}",request.getRequestURL(), request.getRemoteAddr());
        log.info("Submit new record : {}",record);
        recordService.submit(record);
        return "redirect:/records";
    }

    @GetMapping("/add")
    public String addRecordForm(){
        return "addRecordForm";
    }

    @GetMapping("/{recordId}/edit")
    public String editRecordForm(@PathVariable Long recordId, Model model, HttpServletRequest request){
        log.trace("REQUEST : {} from {}",request.getRequestURL(), request.getRemoteAddr());

        Record findRecord = recordService.findRecord(recordId);
        model.addAttribute("record", findRecord);

        return "editRecordForm";

    }

    @PostMapping("/{recordId}")
    public String editRecord(@PathVariable Long recordId, @ModelAttribute Record record, HttpServletRequest request){
        log.trace("REQUEST : {} from {}",request.getRequestURL(), request.getRemoteAddr());
        log.info("Edit Record : ID={}, editRecord={}", recordId, record);
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
