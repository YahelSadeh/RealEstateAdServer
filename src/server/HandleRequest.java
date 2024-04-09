package server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controller.AdController;
import dm.Ad;
import dm.User;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HandleRequest {

    private AdController adController;
    private Socket someClient;
    Gson g = new GsonBuilder().create();

    public HandleRequest(Socket someClient, AdController adController){
        this.someClient = someClient;
        this.adController = adController;

    }

    public void process() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(this.someClient.getInputStream()));
        String line = reader.readLine();
        Request request = g.fromJson(line, Request.class);
        String action = request.getAction();


        Gson gson = new Gson();
        PrintWriter writer = null;
        Map<String, Object> body = request.getBody();
        //Map<String, Object> sendBody = new HashMap<>();
        Response response;
        switch (action){
            case "Register":
                User user = (User) body.get("Register");
                boolean bool3 = adController.saveNewUser(user);
                Map<String, Object> registerUser = new HashMap<>();
                if (bool3) {
                    Response response1 = new Response("true", registerUser);
                    System.out.println("server.response."+user.getUserName());
                }else{
                    Response response0 = new Response("false", registerUser);
                    System.out.println("server.notResponse."+user.getUserName());
                }
            case "addAd":
                String bodyStr = g.toJson(body);
                Ad ad = g.fromJson(bodyStr, Ad.class);
                boolean bool = adController.saveAd(ad);
                HashMap<String, Object> map = new HashMap<>();
                if (bool) {
                    Response response1 = new Response("true", map);
                    System.out.println(response1);
                }else{
                    Response response1 = new Response("false", map);
                    System.out.println(response1);
                }
            case "deleteAd":
                String bodystr2 = null;
                Ad ad2 = g.fromJson(bodystr2, Ad.class);
                adController.deleteAd(ad2);
                //response = new Response("ad deleted", sendBody);
                //System.out.println(response);
            case "searchAd":
                String bodystr3 = null;
                Ad ad3 = g.fromJson(bodystr3, Ad.class);
                List<Ad> ad31 = adController.findAd(ad3.getCity(), ad3.getDescription());
                Map<String, Object> map2 = new HashMap<>();
                map2.put("searchAd", ad31);
                response = new Response("searchAd", map2);
                System.out.println(response);
            case "getAllAds":
                // Get all ads from the adController
                List<Ad> allAds = adController.getAllAds();
                Map<String, Object> map3 = new HashMap<>();
                map3.put("searchAd", allAds);
                String adsJson = gson.toJson(allAds);
                response = new Response("getAllAds", map3);
                System.out.println(response);
            case "getAllAdsByID":
                String bodystr4 = null;
                Ad ad4 = g.fromJson(bodystr4, Ad.class);
                List<Ad> adsById = adController.findAdsById(ad4.getId());
                Map<String, Object> mapId = new HashMap<>();
                mapId.put("searchAd", adsById);
                response = new Response("getAllAdsByID", mapId);
                System.out.println(response);
            case "verifyUser":
                String bodystr5 = null;
                User ad5 = g.fromJson(bodystr5, User.class);
                boolean bool2 = adController.verifyUser(ad5);
                Map<String, Object> mapVerifyUser = new HashMap<>();
                if (bool2) {
                    Response response1 = new Response("true", mapVerifyUser);
                    System.out.println(response1);
                }else{
                    Response response0 = new Response("false", mapVerifyUser);
                    System.out.println(response0);
                }

        }
        reader.close();
        this.someClient.close();
//        PrintWriter writer = new PrintWriter(this.someClient.getOutputStream(), true);
    }
}
