import java.util.Scanner;

public class AIChatbot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--- Welcome to AI Chatbot ---");
        System.out.println("Ask me basic questions or say 'hi'. Type 'exit' to quit.");

        while (true) {
            System.out.print("\nYou: ");
            String input = scanner.nextLine().toLowerCase();

            if (input.contains("exit") || input.contains("bye") || input.contains("quit")) {
                System.out.println("Chatbot: Goodbye! Have a great day.");
                break;
            } else if (input.contains("hi") || input.contains("hello")) {
                System.out.println("Chatbot: Hello there! How can I assist you today?");
            } else if (input.contains("how are you")) {
                System.out.println("Chatbot: I'm just a Java program, but I'm functioning perfectly!");
            } else if (input.contains("your name")) {
                System.out.println("Chatbot: I am the CodeAlpha AI Chatbot.");
            } else if (input.contains("java")) {
                System.out.println("Chatbot: Java is an awesome object-oriented programming language.");
            } else if (input.contains("codealpha")) {
                System.out.println("Chatbot: CodeAlpha is an awesome software development company offering internships!");
            } else {
                System.out.println("Chatbot: I'm not trained to answer that yet. Try asking something else.");
            }
        }
        scanner.close();
    }
}