package server;

import controller.AdController;
import dao.AdDaoImpl;
import dao.UserDaoImpl;
import service.AdService;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    int port;
    public Server(int port){
        this.port = port;
    }
    public void start() {
        System.out.println("Server working!");
        try {
            ServerSocket server = new ServerSocket(this.port);
            AdController adController = new AdController(new AdService(new AdDaoImpl(), new UserDaoImpl()));
            while (true) {
                System.out.println("Server working!");
                Socket someClient = server.accept();
                HandleRequest request = new HandleRequest(someClient, adController);
                request.process();
                System.out.println("Request handled/n");
            }

        } catch (Exception e) {
            System.out.println("tiered of waiting for connection");
        }
    }
}
