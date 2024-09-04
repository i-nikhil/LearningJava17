package networking;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

public class Urls {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {

        // relative uris, can have partial value like paths
        URI website = URI.create("https://github.com/i-nikhil");
        URI website1 = new URI("https://github.com/i-nikhil");

        System.out.println(website1);

        URL url = website1.toURL(); //url should be absolute, else will throw error
        System.out.println(url);

        URL jsonUrl = new URL("https://jsonplaceholder.typicode.com/todos?id=5");
        URLConnection connection = jsonUrl.openConnection(); //configure the connection

        System.out.println(connection.getContentType());
        System.out.println(connection.getHeaderFields());

        connection.connect(); //connected to connection
        printContents(connection.getInputStream());

        ///////////////////////////////////////////////////////////////

        // Create an HttpClient
        HttpClient client = HttpClient.newHttpClient();

        // Create a GET request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/posts/1"))
                .GET()
                .build();

        // Send the request and get the response
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Print the response
        System.out.println("Response status code: " + response.statusCode());
        System.out.println("Response body: " + response.body());

        // Create a JSON string as the request body
        String json = """
                {
                    "title": "foo",
                    "body": "bar",
                    "userId": 1
                }
                """;

        // Create a POST request
        HttpRequest request2 = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/posts"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        // Send the request and get the response
        HttpResponse<String> response2 = client.send(request2, HttpResponse.BodyHandlers.ofString());

        // Print the response
        System.out.println("Response status code: " + response2.statusCode());
        System.out.println("Response body: " + response.body());

        /////////////////////////////////////////////////
        // ASYNCHRONOUS HttpClient
        /////////////////////////////////////////////////

        // Send the GET request asynchronously
        CompletableFuture<HttpResponse<String>> responseFuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        // Handle the response asynchronously
        responseFuture.thenApply(HttpResponse::body)
                .thenAccept(body -> System.out.println("Async Response body: " + body))
                .exceptionally(e -> {
                    System.err.println("Error occurred: " + e.getMessage());
                    return null;
                })
                .join(); // Wait for the completion and get the result

        // Send the POST request asynchronously
        CompletableFuture<HttpResponse<String>> responseFuture2 = client.sendAsync(request2, HttpResponse.BodyHandlers.ofString());

        // Handle the response asynchronously
        responseFuture2.thenApply(HttpResponse::body)
                .thenAccept(body -> System.out.println("Async Response body: " + body))
                .exceptionally(e -> {
                    System.err.println("Error occurred: " + e.getMessage());
                    return null;
                })
                .join(); // Wait for the completion and get the result

        /*
        Future - interface supporting asynchronous programming.

        CompletableFuture - implements Future. Same as Task in C#, holds the result of an async call.
        It's a promise of a future result.

        thenApply(<callback_function>) - to process or modify the result and pass the transformed value further
        along in the computation chain. Eg: future.thenApply(result -> result * 2);

        thenAccept(<callback_function>) - to perform an action with the result (e.g., logging, printing)
        without modifying it or returning a value. Eg: future.thenAccept(result -> sout("Result: " + result));
         */
    }

    private static void printContents(InputStream s) {
        try (Scanner input = new Scanner(s)) {
            String line;
            while ((line = input.nextLine()) != null) {
                System.out.println(line);
            }
        } catch (NoSuchElementException ignored) {

        }
    }
}
