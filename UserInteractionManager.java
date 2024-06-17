import java.util.Scanner;

public class UserInteractionManager {
    private CommunicationManager communicationManager;
    private Scanner scanner;

    public UserInteractionManager() {
        this.communicationManager = new CommunicationManager();
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
        UserInteractionManager manager = new UserInteractionManager();
        manager.startChat();
    }
}
