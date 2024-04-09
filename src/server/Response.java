package server;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Response {
    private Request request;
    public Response(String action, Map<String, Object> body) {
        request = new Request(action, body);
        sendToClient();
    }
    public Request getRequest() {
        return request;
    }
    public void setRequest(Request request) {
        this.request = request;
    }
    public void sendToClient() {
        PrintWriter writer = null;
        try {
            Socket myServer = new Socket("localhost", 12346);
            writer = new PrintWriter(myServer.getOutputStream(), true);
            // Convert the body to JSON
            Gson gson = new Gson();
            String jsonBody = gson.toJson(this.request);
            writer.println(jsonBody);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
