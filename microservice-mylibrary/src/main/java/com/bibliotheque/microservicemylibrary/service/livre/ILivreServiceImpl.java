package com.bibliotheque.microservicemylibrary.service.livre;

import com.bibliotheque.microservicemylibrary.dao.ILivreDao;
import com.bibliotheque.microservicemylibrary.model.Livre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ILivreServiceImpl implements ILivreService {

    @Autowired
    ILivreDao ILivreDao;

    @Override
    public List<Livre> findAll(){
        return ILivreDao.findAll();
    }

    @Override
    public Optional<Livre> findById(Long id){
        return ILivreDao.findById(id);
    }
}
