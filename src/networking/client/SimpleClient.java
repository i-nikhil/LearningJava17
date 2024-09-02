package networking.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SimpleClient {
    public static void main(String[] args) throws IOException {
        // client listening to server's port number
        Socket socket = new Socket("localhost", 5000);

        // Handling input and output for the socket using IO Methods
        Scanner input = new Scanner(socket.getInputStream());
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

        Scanner sc = new Scanner(System.in);
        String request, response;

        do {
            System.out.print("Enter a string to be echoed (sent to server): ");
            request = sc.nextLine();

            output.println(request);
            if(!request.equalsIgnoreCase("exit")) {
                response = input.nextLine();
                System.out.println(response);
            }
        }
        while (!request.equalsIgnoreCase("exit"));
    }
}
