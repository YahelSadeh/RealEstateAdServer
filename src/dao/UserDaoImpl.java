package dao;

import dm.Ad;
import dm.User;

import java.io.*;
import java.util.HashMap;

public class UserDaoImpl implements IDao<Long, User>{
    public static final String FILE_PATH = "C:\\Users\\Shanny Affias\\IdeaProjects\\RealEstateAdsProject\\RealEstateAdServer\\src\\repository\\users.txt";

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
        HashMap<Long, User> users = null;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            users = (HashMap<Long, User>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (users != null) {
            // Remove the Ad object associated with the given id
            users.remove(entity);

            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
                // Write the updated HashMap back to the file
                objectOutputStream.writeObject(users);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    @Override
    public User find(Long id) throws IllegalArgumentException {
        HashMap<Long, User> users = null;
        try {
            users = (HashMap<Long, User>) objectInputStream.readObject();
        } catch (ClassNotFoundException | IOException e) {
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
    public boolean verifyUser(User user) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("user.txt"))) {
                // Read objects from the file until the end
                while (true) {
                    try {
                        // Read the next object from the file
                        User existingUser = (User) ois.readObject();
                        // Check if the existingUser matches the given user
                        if (existingUser.getUserName().equals(user.getUserName()) && existingUser.getPassword().equals(user.getPassword())) {
                            return true; // User exists
                        }
                    } catch (EOFException e) {
                        break; // End of file reached
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            return false; // User does not exist
    }
}
