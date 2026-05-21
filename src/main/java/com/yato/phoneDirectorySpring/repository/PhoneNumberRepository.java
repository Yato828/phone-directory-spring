package com.yato.phoneDirectorySpring.repository;

import com.yato.phoneDirectorySpring.entity.PhoneNumber;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class PhoneNumberRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<PhoneNumber> findByContactId(Integer contactId) {
        return entityManager
                .createQuery("SELECT p FROM PhoneNumber p WHERE p.contact.id = :contactId", PhoneNumber.class)
                .setParameter("contactId", contactId)
                .getResultList();
    }
    @Transactional
    public void deleteByContactId(Integer contactId) {
        entityManager.createQuery("DELETE FROM PhoneNumber p WHERE p.contact.id = :contactId")
                .setParameter("contactId", contactId)
                .executeUpdate();
    }
    @Transactional
    public void save(PhoneNumber phoneNumber) {
        entityManager.persist(phoneNumber);
    }
}