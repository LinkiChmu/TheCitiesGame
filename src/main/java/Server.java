import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PORT = 8989;
    public static String city = "";

    public static void main(String[] args) throws InterruptedException {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started");
            while (true) {
                try (Socket socket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                    if ("".equals(city)) {
                        out.println("???");
                        city = in.readLine();
                        System.out.println("The first city is " + city);
                        out.println("OK");
                        //                       socket.wait(3000);
                    } else {
                        System.out.println("New player started");
                        out.println(city);
                        String nextCity = in.readLine();
                        char first = nextCity.charAt(0);
                        char last = city.charAt((city.length()) - 1);
                        if (first == last) {
                            city = nextCity;
                            System.out.println("Current city is " + city);
                            out.println("OK");
                        } else {
                            System.out.println("Unsuccessful try");
                            out.println("NOT OK");
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Can't start server");
            e.printStackTrace();
        }
    }
}
