import java.util.Scanner;

public class Game {
    private BinaryTree tree;
    private Scanner scanner;

    public Game() {
        tree = new BinaryTree();
        scanner = new Scanner(System.in);
        // Initializes game tree with a default question if it's empty
        if (tree.getRoot() == null) {
            Defaultquestions();
        }
    }

    private void Defaultquestions() {
        // root node with a generic question
        Node rootNode = new Node("Is it a mammal?");
        tree.setRoot(rootNode);

        //branches for yes and no answers
        Node yesNode = new Node("Does it live on land?");
        Node noNode = new Node("Is it a fish?");

        rootNode.setYesNode(yesNode);
        rootNode.setNoNode(noNode);

        // More detailed when yes
        Node yesYesNode = new Node("Is it bigger than a cat?");
        Node yesNoNode = new Node("Is it a mouse?");
        yesNode.setYesNode(yesYesNode);
        yesNode.setNoNode(yesNoNode);

        // Example leaves when yes
        Node leafNode1 = new Node("Is it a dog?");
        Node leafNode2 = new Node("Is it a horse?");
        yesYesNode.setYesNode(leafNode1);
        yesYesNode.setNoNode(leafNode2);

        // Simple leaf when no
        noNode.setNoNode(new Node("Is it a shark?"));
        noNode.setYesNode(new Node("Is it a salmon?"));
    }

    public void play() {
        System.out.println("Think of an animal, and I'll try to guess it.");
        Node currentNode = tree.getRoot();

        while (currentNode != null) {
            System.out.println(currentNode.getQuestion());
            String answer = scanner.nextLine().trim().toLowerCase();

            if (answer.equals("yes") || answer.equals("no")) {
                if (answer.equals("yes")) {
                    if (currentNode.getYesNode() == null) {
                        handleGuess(currentNode, true);
                        break;
                    } else {
                        currentNode = currentNode.getYesNode();
                    }
                } else {
                    if (currentNode.getNoNode() == null) {
                        handleGuess(currentNode, false);
                        break;
                    } else {
                        currentNode = currentNode.getNoNode();
                    }
                }
            } else {
                System.out.println("Please answer 'yes' or 'no'.");
            }
        }
    }

    private void handleGuess(Node currentNode, boolean wasYes) {
        System.out.println("Is it " + currentNode.getQuestion() + "? (yes/no)");
        String isCorrect = scanner.nextLine().trim().toLowerCase();
        if (isCorrect.equals("yes")) {
            System.out.println("I guessed it! Yay!");
        } else {
            System.out.println("I was wrong. Help me learn.");
            learnNewAnimal(currentNode, wasYes);
        }
    }

    private void learnNewAnimal(Node currentNode, boolean wasYes) {
        System.out.println("What animal were you thinking of?");
        String newAnimal = scanner.nextLine().trim();
        System.out.println("Please give me a yes/no question that would distinguish " + newAnimal + " from " + currentNode.getQuestion() + ".");
        String newQuestion = scanner.nextLine().trim();
        System.out.println("What is the correct answer for this question for " + newAnimal + "? (yes/no)");
        String correctAnswer = scanner.nextLine().trim().toLowerCase();

        Node newQuestionNode = new Node(newQuestion);
        Node newAnimalNode = new Node(newAnimal);

        newQuestionNode.setYesNode(correctAnswer.equals("yes") ? newAnimalNode : currentNode);
        newQuestionNode.setNoNode(correctAnswer.equals("yes") ? currentNode : newAnimalNode);

        if (correctAnswer.equals("yes")) {
            newQuestionNode.setYesNode(newAnimalNode);
            newQuestionNode.setNoNode(currentNode);
        } else {
            newQuestionNode.setYesNode(currentNode);
            newQuestionNode.setNoNode(newAnimalNode);
        }

        if (wasYes) {
            currentNode.setYesNode(newQuestionNode);
        } else {
            currentNode.setNoNode(newQuestionNode);
        }

        System.out.println("Thank you! I've learned something new.");
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }
}