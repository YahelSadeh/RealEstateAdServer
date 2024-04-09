package service;

import dao.AdDaoImpl;
import dao.UserDaoImpl;
import dm.Ad;
import dm.User;

import java.util.List;

public class AdService {
    AdDaoImpl adDao;
    UserDaoImpl userDao;
    public AdService(AdDaoImpl bookDao, UserDaoImpl userDao) {
        this.adDao = bookDao;
        this.userDao = userDao;
    }

    public Ad findAds(Long id){
        return adDao.find(id);
    }
    public List<Ad> getAllAds(){
        return adDao.findAll();
    }
    public boolean addNewAd(Ad ad) {
        return adDao.save(ad);
    }
    public void deleteAd(Ad ad) {
        adDao.delete(ad);
    }
    public List<Ad> findById(Long id) {
        return adDao.findById(id);
    }
    //**********************************************************//
    public boolean addNewUser(User user) {
        return userDao.save(user);
    }
    public boolean verifyUser(User user) {

        return userDao.verifyUser(user);
    }
    public List<Ad> findAdByDescription(String city, String description) {
        return adDao.findByDescription(city,description);
    }
}
