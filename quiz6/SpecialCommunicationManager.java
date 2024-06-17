import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class SpecialCommunicationManager {
    private String commonServiceUrl;
    private String specialServiceUrl;
    private HttpClient client;
    private List<String> chatHistory;

    public SpecialCommunicationManager(String commonServiceUrl, String specialServiceUrl) {
        this.commonServiceUrl = commonServiceUrl;
        this.specialServiceUrl = specialServiceUrl;
        this.client = HttpClient.newHttpClient();
        this.chatHistory = new ArrayList<>();
    }

    public String sendMessage(String message) throws Exception {
        chatHistory.add("User: " + message);

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode requestJson = objectMapper.createObjectNode();
        requestJson.put("message", message);
        requestJson.put("chatHistory", String.join("\n", chatHistory));

        String serviceUrl = commonServiceUrl;
        if (message.toLowerCase().contains("help") || chatHistory.stream().anyMatch(chat -> chat.toLowerCase().contains("help"))) {
            serviceUrl = specialServiceUrl;
        }

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(serviceUrl))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(requestJson.toString()))
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String chatbotResponse = response.body();
        chatHistory.add("Chatbot: " + chatbotResponse);

        return chatbotResponse;
    }
}
