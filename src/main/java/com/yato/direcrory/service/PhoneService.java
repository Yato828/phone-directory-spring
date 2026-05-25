package com.yato.direcrory.service;

import com.yato.direcrory.entity.Person;
import com.yato.direcrory.entity.PhoneNumber;
import com.yato.direcrory.repository.PhoneNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class PhoneService {

    @Autowired
    private PhoneNumberRepository phoneNumberRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public List<PhoneNumber> findByPersonId(Integer personId) {
        return phoneNumberRepository.findByPersonId(personId);
    }

    public PhoneNumber findById(Integer id) {
        return phoneNumberRepository.findById(id);
    }

    @Transactional
    public void saveOrUpdate(PhoneNumber phone) {
        phoneNumberRepository.saveOrUpdate(phone);
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
            phoneNumberRepository.deleteById(id);
        }
    }
}