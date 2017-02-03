package com.treegrowth.consumer.service;

import com.treegrowth.model.mybatis.entity.Note;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@FeignClient("service-provider")
@RequestMapping("service/note")
public interface NoteService {

    @RequestMapping(method = POST)
    void createNote(@RequestBody Note note);

    @RequestMapping(value = "/{noteId}", method = GET)
    Note getNote(@PathVariable("noteId") String noteId);
}
