package controller;

import dm.Ad;
import dm.User;
import service.AdService;

import java.util.ArrayList;
import java.util.List;

public class AdController {
    private AdService adService;
    public AdController(AdService adService){
        this.adService = adService;
    }

    public boolean saveAd(Ad ad){
        return adService.addNewAd(ad);
    }
    public void deleteAd(Ad ad){
        adService.deleteAd(ad);

    }
    public List<Ad> getAllAds(){
        return adService.getAllAds();
    }
    public List<Ad> findAdsById(Long id){
        return adService.findById(id);
    }
    public List<Ad> findAd(String city, String description){
        return adService.findAdByDescription(city, description);
    }
    public boolean saveNewUser(User newUser) {
        return adService.addNewUser(newUser);
    }
    public boolean verifyUser(User user) {
        return adService.verifyUser(user);
    }
}
