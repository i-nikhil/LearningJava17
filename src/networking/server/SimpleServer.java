package networking.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SimpleServer {
    public static void main(String[] args) throws IOException {

        /*
        Types of connection:
        TCP/IP - two-way, response confirmation, slow as keeps listening, continuous stream of data.
        Eg: Client-server using sockets, web browsing, message apps
        UDP - uses datagrams, small packets of data, fast, connection-less, no tcp handshake.
        Eg: Online gaming, video calls and online media streaming
         */
        ServerSocket serverSocket = new ServerSocket(5000);// port no. = 0 to 65535
        System.out.println("Server running on localhost:5000...");

        Socket socket = serverSocket.accept(); //application will be blocked here waiting for a client
        System.out.println("Server accepted a client connection.");

        // socket.setSoTimeout(5000); // after connection the socket will timeout if no request

        // Handling input and output for the socket using IO Methods
        Scanner input = new Scanner(socket.getInputStream());
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

        while(true) {
            String echoString = input.nextLine();
            System.out.println("Server got request: "+echoString);
            if(echoString.equalsIgnoreCase("exit"))
                break;
            output.println("Echo from server: "+echoString);
        }
    }
}
