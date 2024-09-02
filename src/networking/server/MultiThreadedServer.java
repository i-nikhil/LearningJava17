package networking.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadedServer {
    public static void main(String[] args) throws IOException {

        // Thread Executor service - new pool of reusable threads
        ExecutorService executorService = Executors.newCachedThreadPool();

        ServerSocket serverSocket = new ServerSocket(5000);// port no. = 0 to 65535
        System.out.println("Server running on localhost:5000...");

        while(true) {
            Socket socket = serverSocket.accept(); //application will be blocked here waiting for a client
            System.out.println("Server accepted a client connection.");

            socket.setSoTimeout(9_00_000); // after connection each sockets will timeout (in 15 minutes), if no request

            // ServerSocket is a blocking IO, hence we are using timeout here
            // ServerSocketChannel can be used instead, as its non-blocking (N IO)

            executorService.submit(() -> {
                try {
                    handleClientRequest(socket);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    private static void handleClientRequest(Socket socket) throws IOException {
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
