import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Player {
    private static final int PORT = 8989;
    private static final String HOST = "localhost";
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try (Socket clientSocket = new Socket(HOST, PORT);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            String serverResponse = in.readLine();
            if ("???".equals(serverResponse)) {
                System.out.println("Hi! Starting the game Cities");
                System.out.println("Enter the City name: ");
                String firstCity = scanner.nextLine();
                out.println(firstCity);

            } else {
                System.out.println("Last entered City is " + serverResponse);
                System.out.println("Enter your City: ");
                String cityNext = scanner.nextLine();
                out.println(cityNext);
            }
            serverResponse = in.readLine();
            System.out.println(serverResponse);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}