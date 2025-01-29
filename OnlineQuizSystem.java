// Online Quiz System Application

import java.util.*;

// Question class
class Question {
    private String questionText;
    private List<String> options;
    private int correctOption;

    public Question(String questionText, List<String> options, int correctOption) {
        this.questionText = questionText;
        this.options = options;
        this.correctOption = correctOption;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getOptions() {
        return options;
    }

    public boolean isCorrect(int chosenOption) {
        return chosenOption == correctOption;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(questionText + "\n");
        for (int i = 0; i < options.size(); i++) {
            builder.append((i + 1) + ". " + options.get(i) + "\n");
        }
        return builder.toString();
    }
}

// Quiz class
class Quiz {
    private List<Question> questions = new ArrayList<>();

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void conductQuiz() {
        Scanner scanner = new Scanner(System.in);
        int score = 0;

        for (Question question : questions) {
            System.out.println(question);
            System.out.print("Enter your answer (1-" + question.getOptions().size() + "): ");
            int answer = scanner.nextInt();

            if (question.isCorrect(answer)) {
                System.out.println("Correct!\n");
                score++;
            } else {
                System.out.println("Wrong!\n");
            }
        }

        System.out.println("Quiz Over! Your score: " + score + "/" + questions.size());
    }
}

// User class
class User {
    private String username;

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}

// Online Quiz System
public class OnlineQuizSystem {
    public static void main(String[] args) {
        Quiz quiz = new Quiz();

        // Sample questions
        quiz.addQuestion(new Question("What is the capital of France?",
                Arrays.asList("Berlin", "Paris", "Madrid", "Rome"), 2));
        quiz.addQuestion(new Question("Which programming language is used for Android development?",
                Arrays.asList("Python", "Java", "Swift", "C++"), 2));
        quiz.addQuestion(new Question("What is 2 + 2?",
                Arrays.asList("3", "4", "5", "6"), 2));

        // Start the quiz
        System.out.println("Welcome to the Online Quiz System!");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your username: ");
        User user = new User(scanner.nextLine());

        System.out.println("Hello, " + user.getUsername() + "! Let's start the quiz.\n");
        quiz.conductQuiz();
    }
}