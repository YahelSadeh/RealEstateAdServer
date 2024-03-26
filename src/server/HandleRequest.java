package server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controller.AdController;
import dm.Ad;

import java.io.*;
import java.net.Socket;
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
        switch (action){
            case "addAd":
                Map<String, Object> body = request.getBody();
                String bodyStr = g.toJson(body);
                Ad ad = g.fromJson(bodyStr, Ad.class);
                adController.saveAd(ad);
            case "getAllAds":



        }
        reader.close();
//        PrintWriter writer = new PrintWriter(this.someClient.getOutputStream(), true);
        System.out.println(request);

    }

    private void close(ObjectOutputStream output, ObjectInputStream input) throws IOException {
        output.close();
        input.close();
        someClient.close();
    }
}
