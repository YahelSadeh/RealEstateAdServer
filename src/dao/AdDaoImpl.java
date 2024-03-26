package dao;

import dm.Ad;

import java.io.*;
import java.util.HashMap;
import java.util.List;

public class AdDaoImpl implements IDao<Long, Ad>{
    public static final String FILE_PATH = "ads.txt";

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
        // TODO Auto-generated method stub

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
        return ads.get(id);
    }

    public List<Ad> findAll() {
        HashMap<Long, Ad> books = null;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILE_PATH));
            books = (HashMap<Long, Ad>) objectInputStream.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return (List<Ad>) books.values();
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
}
