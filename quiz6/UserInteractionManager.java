import java.util.Scanner;

public class UserInteractionManager {
    private SpecialCommunicationManager communicationManager;
    private Scanner scanner;

    public UserInteractionManager(String commonServiceUrl, String specialServiceUrl) {
        this.communicationManager = new SpecialCommunicationManager(commonServiceUrl, specialServiceUrl);
        this.scanner = new Scanner(System.in);
    }

    public void startChat() {
        System.out.println("Chatbot application started. Type your message and press Enter to send. Type 'exit' to quit.");

        while (true) {
            System.out.print("User: ");
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("exit")) {
                break;
            }

            try {
                String response = communicationManager.sendMessage(userInput);
                System.out.println("Chatbot: " + response);
            } catch (Exception e) {
                System.err.println("Error communicating with chatbot service: " + e.getMessage());
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        // Replace these URLs with your actual service URLs
        String commonServiceUrl = "https://example.com/chatbot";
        String specialServiceUrl = "https://example.com/special-chatbot";

        UserInteractionManager manager = new UserInteractionManager(commonServiceUrl, specialServiceUrl);
        manager.startChat();
    }
}
