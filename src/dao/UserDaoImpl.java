package dao;

import dm.User;

import java.io.*;
import java.util.HashMap;

public class UserDaoImpl implements IDao<Long, User>{
    public static final String FILE_PATH = "user.txt";

    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;

    public UserDaoImpl() {
        try {
            boolean emptyData = emptyDB();
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILE_PATH));
            objectInputStream = new ObjectInputStream(new FileInputStream(FILE_PATH));
            if(emptyData) {
                objectOutputStream.writeObject(new HashMap<Long, User>());
                objectOutputStream.flush();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(User entity) {
        // TODO Auto-generated method stub

    }

    @Override
    public User find(Long id) throws IllegalArgumentException {
        HashMap<Long, User> users = null;
        try {
            users = (HashMap<Long, User>) objectInputStream.readObject();
        } catch (ClassNotFoundException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return users.get(id);
    }

    @Override
    public boolean save(User user) throws IllegalArgumentException {
        try {
            HashMap<Long, User> users = (HashMap<Long, User>) objectInputStream.readObject();
            users.put(user.getId(), user);
            objectOutputStream.writeObject(users);
            objectOutputStream.flush();
        } catch (ClassNotFoundException | IOException e) {
            return false;
        }
        return true;
    }

    private boolean emptyDB() throws IOException, ClassNotFoundException {
        File file = new File(FILE_PATH);
        return !file.exists() || file.length() == 0;
    }

}
