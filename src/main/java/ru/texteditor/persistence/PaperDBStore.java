package ru.texteditor.persistence;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.texteditor.model.Paper;

import javax.persistence.Query;
import java.util.Date;
import java.util.List;

@Repository
public class PaperDBStore implements DBStore {

    private final SessionFactory sf;

    public PaperDBStore(SessionFactory sf) {
        this.sf = sf;
    }

    public Paper add(Paper paper) {
        return tx(
                session -> {
                    session.save(paper);
                    return paper;
                }, sf
        );
    }

    public void update(Paper paper) {
        tx(
                session -> {
                    session.merge(paper);
                    return new Object();
                }, sf

        );
    }

    public boolean delete(int id) {
        return tx(
                session -> {
                    String hql = "delete Paper p "
                            + " where p.id = :id";
                    final Query query = session.createQuery(hql);
                    query.setParameter("id", id);
                    int rsl = query.executeUpdate();
                    return rsl != 0;
                }, sf
        );
    }

    public List<Paper> findAll() {
        return tx(
                session -> session.createQuery("select distinct p from Paper p").list(), sf
        );
    }

    public List<Paper> findByDone(boolean isDone) {
        return tx(
                session -> session.createQuery("from Paper where done = :isDone")
                        .setParameter("isDone", isDone).list(), sf
        );
    }

    public Paper findById(int id) {
        return tx(
                session -> session.get(Paper.class, id), sf
        );
    }

    public boolean setDone(int id) {
        return tx(
                session -> {
                    String hql = "update Paper p "
                            + " SET p.done = :done "
                            + " where p.id = :id";
                    final Query query = session.createQuery(hql);
                    query.setParameter("id", id);
                    query.setParameter("done", true);
                    int rsl = query.executeUpdate();
                    return rsl != 0;
                }, sf
        );
    }
}
