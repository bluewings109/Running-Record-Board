package org.onlypearson.runningrecord.controller;

import lombok.extern.slf4j.Slf4j;
import org.onlypearson.runningrecord.domain.Record;
import org.onlypearson.runningrecord.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

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
    public String records(Model model){
        model.addAttribute("records", recordService.findAllRecords());
        return "records";
    }

    @PostMapping
    public String addRecord(@ModelAttribute Record record){
        log.info("record={}",record);

        recordService.submit(record);
        return "redirect:/records";
    }

    @GetMapping("/add")
    public String addRecordForm(){
        return "addRecordForm";
    }

    @GetMapping("/{recordId}/edit")
    public String editRecordForm(@PathVariable Long recordId, Model model){
        Record findRecord = recordService.findRecord(recordId);
        model.addAttribute("record", findRecord);

        return "editRecordForm";

    }

    @PostMapping("/{recordId}")
    public String editRecord(@PathVariable Long recordId, @ModelAttribute Record record){
        recordService.edit(recordId, record);

        return "redirect:/records";
    }

    @DeleteMapping
    public String deleteRecord(@RequestParam Long recordId){
        recordService.remove(recordId);
        return "redirect:/records";
    }

    @PostConstruct
    void init(){
        Record record1 = new Record(LocalDateTime.now(),9.0,10.0,1,3,0,170,"testComment");
        Record record2 = new Record(LocalDateTime.now(), 0.0, 10.0, 1, 0, 0, 180, "testComment");

        recordService.submit(record1);
        recordService.submit(record2);
    }
}
