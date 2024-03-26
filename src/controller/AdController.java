package controller;

import dm.Ad;
import service.AdService;

public class AdController {
    private AdService adService;
    public AdController(AdService adService){
        this.adService = adService;
    }

    public void saveAd(Ad ad){
        adService.addNewAd(ad);
    }
}
