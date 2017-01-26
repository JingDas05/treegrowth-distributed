package com.treegrowth.service.provider.web;

import com.treegrowth.dao.mybatis.NoteMapper;
import com.treegrowth.model.mybatis.entity.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/service/note")
public class NoteService{

    @Autowired
    private NoteMapper noteMapper;

    @RequestMapping(method = POST)
    public void createNote(@RequestBody Note note) {
        noteMapper.insertNote(note);
    }

    @RequestMapping(value = "/{noteId}", method = GET)
    public Note getNote(@PathVariable("noteId") String noteId) {
        return noteMapper.selectNote(Long.parseLong(noteId));
    }
}
