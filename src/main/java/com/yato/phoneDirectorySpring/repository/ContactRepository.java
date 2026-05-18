package com.yato.phoneDirectorySpring.repository;

import com.yato.phoneDirectorySpring.entity.Contact;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;


@Repository
public class ContactRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping
    public List<Contact> findAll(){
        return entityManager
                .createQuery("FROM Contact", Contact.class)
                .getResultList();

    }

    public void edit(Contact contact){
        entityManager.merge(contact);
    }

    public Contact findById(int id) {
        return entityManager.find(Contact.class, id);
    }

    public void update(Contact contact) {
        entityManager.merge(contact);
    }

    public void save(Contact contact) {
        entityManager.persist(contact);
    }

    public void delete(int id) {
        Contact contact = entityManager.find(Contact.class, id);
        if (contact != null)
        entityManager.remove(contact);
    }
}
