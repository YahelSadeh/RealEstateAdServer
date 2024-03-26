package server;

import controller.AdController;
import dao.AdDaoImpl;
import dao.UserDaoImpl;
import service.AdService;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public void start() {
        try {
            ServerSocket server = new ServerSocket(12346);
            AdController adController = new AdController(new AdService(new AdDaoImpl(), new UserDaoImpl()));
            while (true) {
                Socket someClient = server.accept();
                HandleRequest request = new HandleRequest(someClient, adController);
                request.process();
            }

        } catch (Exception e) {
            System.out.println("tiered of waiting for connection");
        }
    }
}
