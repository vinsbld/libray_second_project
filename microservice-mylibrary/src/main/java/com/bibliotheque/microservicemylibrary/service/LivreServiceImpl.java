package com.bibliotheque.microservicemylibrary.service;

import com.bibliotheque.microservicemylibrary.dao.LivreDao;
import com.bibliotheque.microservicemylibrary.model.Livre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivreServiceImpl implements LivreService {

    @Autowired
    LivreDao livreDao;

    @Override
    public List<Livre> findAll(){
        return livreDao.findAll();
    }

    @Override
    public Optional<Livre> findById(Long id){
        return livreDao.findById(id);
    }
}
