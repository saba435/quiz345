import java.util.Scanner;

public class ChatbotConsoleApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Chatbot chatbot = new Chatbot();

        System.out.println("Chatbot application started. Type your message and press Enter to send. Type 'exit' to quit.");

        while (true) {
            System.out.print("User: ");
            String userInput = scanner.nextLine();
            
            if (userInput.equalsIgnoreCase("exit")) {
                break;
            }

            try {
                String response = chatbot.sendMessage(userInput);
                System.out.println("Chatbot: " + response);
            } catch (Exception e) {
                System.err.println("Error communicating with chatbot service: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
