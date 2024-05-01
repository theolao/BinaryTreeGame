public class Node {
    private String question;
    private Node yesNode;
    private Node noNode;

    public Node(String question) {
        this.question = question;
        yesNode = null;
        noNode = null;
    }

    public String getQuestion() {
        return question;
    }

    public Node getYesNode() {
        return yesNode;
    }

    public void setYesNode(Node yesNode) {
        this.yesNode = yesNode;
    }

    public Node getNoNode() {
        return noNode;
    }

    public void setNoNode(Node noNode) {
        this.noNode = noNode;
    }
}