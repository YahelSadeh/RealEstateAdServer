package service;

import dao.AdDaoImpl;
import dao.UserDaoImpl;
import dm.Ad;

import java.util.List;

public class AdService {
    AdDaoImpl adDao;
    UserDaoImpl userDao;
    public AdService(AdDaoImpl bookDao, UserDaoImpl userDao) {
        this.adDao = bookDao;
        this.userDao = userDao;
    }
    public List<Ad> getAllAds(){
        return adDao.findAll();
    }
    public void addNewAd(Ad book) {
        adDao.save(book);
    }
    public Ad findById(Long id) {
        return adDao.find(id);
    }
}
