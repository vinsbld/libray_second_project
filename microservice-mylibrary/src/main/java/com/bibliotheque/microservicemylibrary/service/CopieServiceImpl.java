package com.bibliotheque.microservicemylibrary.service;

import com.bibliotheque.microservicemylibrary.dao.CopieDao;
import com.bibliotheque.microservicemylibrary.model.Copie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CopieServiceImpl implements CopieService {

    @Autowired
    CopieDao copieDao;

    @Override
    public List<Copie> findAllByLivreId(Long id){
        return copieDao.findAllByLivreId(id);
    }

    @Override
    public Optional<Copie> findById(Long id){
        return copieDao.findById(id);
    }
}
