package com.yato.direcrory.repository;

import com.yato.direcrory.entity.PhoneNumber;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class PhoneNumberRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<PhoneNumber> findByPersonId(Integer personId) {
        return entityManager
                .createQuery("SELECT p FROM PhoneNumber p WHERE p.person.id = :personId ORDER BY p.id", PhoneNumber.class)
                .setParameter("personId", personId)
                .getResultList();
    }

    public PhoneNumber findById(Integer id) {
        return entityManager.find(PhoneNumber.class, id);
    }

    @Transactional
    public void saveOrUpdate(PhoneNumber phoneNumber) {
        if (phoneNumber.getId() == null) {
            entityManager.persist(phoneNumber);
        } else {
            entityManager.merge(phoneNumber);
        }
    }

    @Transactional
    public void deleteByPersonId(Integer personId) {
        entityManager.createQuery("DELETE FROM PhoneNumber p WHERE p.person.id = :personId")
                .setParameter("personId", personId)
                .executeUpdate();
    }

    @Transactional
    public void deleteById(Integer id) {
        PhoneNumber phone = findById(id);
        if (phone != null) {
            entityManager.remove(phone);
        }
    }
}