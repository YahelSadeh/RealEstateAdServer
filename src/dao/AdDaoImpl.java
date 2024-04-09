package dao;

import dm.Ad;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class AdDaoImpl implements IDao<Long, Ad>{
    public static final String FILE_PATH = "C:\\Users\\Shanny Affias\\IdeaProjects\\RealEstateAdsProject\\RealEstateAdServer\\src\\repository\\ads.txt";

    public AdDaoImpl() {
        try {
            ObjectOutputStream objectOutputStream;
            boolean emptyData = emptyData();
            if(emptyData) {
                objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILE_PATH));
                objectOutputStream.writeObject(new HashMap<Long, Ad>());
                objectOutputStream.flush();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Ad entity) {
        HashMap<Long, Ad> ads = null;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            ads = (HashMap<Long, Ad>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (ads != null) {
            // Remove the Ad object associated with the given id
            ads.remove(entity);

            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
                // Write the updated HashMap back to the file
                objectOutputStream.writeObject(ads);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public Ad find(Long id) throws IllegalArgumentException {
        HashMap<Long, Ad> ads = null;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILE_PATH));
            ads = (HashMap<Long, Ad>) objectInputStream.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        assert ads != null;
        return ads.get(id);
    }
    public List<Ad> findAll() {
        HashMap<Long, Ad> ads = null;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILE_PATH));
            ads = (HashMap<Long, Ad>) objectInputStream.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return (List<Ad>) ads.values();
    }
    @Override
    public boolean save(Ad ad) throws IllegalArgumentException {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILE_PATH));
            HashMap<Long, Ad> ads = (HashMap<Long, Ad>) objectInputStream.readObject();
            ads.put(ad.getId(), ad);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILE_PATH));
            objectOutputStream.writeObject(ads);
            objectOutputStream.flush();
        } catch (ClassNotFoundException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
        return true;
    }
    private boolean emptyData() throws IOException, ClassNotFoundException {
        File file = new File(FILE_PATH);
        return !file.exists() || file.length() == 0;
    }
    public List<Ad> findById(Long id) {
        HashMap<Long, Ad> ads = null;
        List<Ad> adById = new ArrayList<>();
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILE_PATH));
            ads = (HashMap<Long, Ad>) objectInputStream.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        assert ads != null;
        for (Ad ad:ads.values()){
            if (Objects.equals(ad.getId(), id)){
                adById.add(ad);
            }
        }
        return adById;
    }
    public List<Ad> findByDescription(String city, String description) {
        HashMap<Long, Ad> ads = null;
        List<Ad> newAds = null;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILE_PATH));
            ads = (HashMap<Long, Ad>) objectInputStream.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        assert ads != null;

        for(Ad ad:ads.values()){
            if (ad.getCity().contains(city) || ad.getDescription().contains(description)){
                newAds.add(ad);
            }
        }
        return newAds;

        //kmpStringMatch Use the string match ALGO;Fix the ALGO;
    }
}
