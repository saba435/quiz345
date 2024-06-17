import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Chatbot {
    private static final String CHATBOT_URL = "https://example.com/chatbot"; // Replace with your chatbot service URL
    private HttpClient client;
    private List<String> chatHistory;

    public Chatbot() {
        client = HttpClient.newHttpClient();
        chatHistory = new ArrayList<>();
    }

    public String sendMessage(String message) throws Exception {
        chatHistory.add("User: " + message);
        
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode requestJson = objectMapper.createObjectNode();
        requestJson.put("message", message);
        requestJson.put("chatHistory", String.join("\n", chatHistory));

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(CHATBOT_URL))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(requestJson.toString()))
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String chatbotResponse = response.body();
        chatHistory.add("Chatbot: " + chatbotResponse);

        return chatbotResponse;
    }
}
