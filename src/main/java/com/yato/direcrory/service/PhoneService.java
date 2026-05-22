package com.yato.direcrory.service;

import com.yato.direcrory.entity.Person;
import com.yato.direcrory.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PhoneService {

    @Autowired
    private PersonRepository repository;





}