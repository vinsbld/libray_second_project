package com.bibliotheque.microservicemylibrary.service.copie;

import com.bibliotheque.microservicemylibrary.dao.ICopieDao;
import com.bibliotheque.microservicemylibrary.model.Copie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ICopieServiceImpl implements ICopieService {

    @Autowired
    ICopieDao ICopieDao;

    @Override
    public List<Copie> findAllByLivreId(Long id){
        return ICopieDao.findAllByLivreId(id);
    }

    @Override
    public Optional<Copie> findById(Long id){
        return ICopieDao.findById(id);
    }
}
