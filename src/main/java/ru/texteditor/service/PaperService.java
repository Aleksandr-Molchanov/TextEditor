package ru.texteditor.service;

import org.springframework.stereotype.Service;
import ru.texteditor.model.Paper;
import ru.texteditor.persistence.PaperDBStore;

import java.util.Collection;

@Service
public class PaperService {

    private final PaperDBStore store;

    public PaperService(PaperDBStore store) {
        this.store = store;
    }

    public void add(Paper paper) {
        store.add(paper);
    }

    public void update(Paper paper) {
        store.update(paper);
    }

    public void delete(int id) {
        store.delete(id);
    }

    public Collection<Paper> findAll() {
        return store.findAll();
    }

    public Paper findById(int id) {
        return store.findById(id);
    }

    public Collection<Paper> findByDone(boolean isDone) {
        return store.findByDone(isDone);
    }

    public void setDone(int id) {
        store.setDone(id);
    }

}