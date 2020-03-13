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
    ILivreDao mLivreDao;

    @Override
    public List<Livre> findAll(){return  mLivreDao.findAll();
    }

    @Override
    public Optional<Livre> findById(Long id){
        return mLivreDao.findById(id);
    }

    @Override
    public Livre save(Livre livre){
        return mLivreDao.save(livre);
    }


}
