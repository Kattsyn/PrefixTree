import java.util.HashMap;

public class Node {

    private boolean isEndOfWord;
    public HashMap<Character, Node> children;

    public Node() {
        this.isEndOfWord = false;
        this.children = new HashMap<>();
    }


    //добавление потомка с ключом символа и значением новой ветви
    public void addChild(char ch) {
        if (!children.containsKey(ch)) {
            children.put(ch, new Node());
        }
    }

    //Если есть потомок, то вернуть, иначе вернуть null
    public Node getChild(char ch) {
        return children.getOrDefault(ch, null);
    }

    public void deleteChild(char ch) {
        children.remove(ch);
    }

    public boolean hasChild(char ch) {
        return children.containsKey(ch);
    }

    public boolean isEmpty() {
        return children.isEmpty();
    }

    public boolean isEndOfWord() {
        return isEndOfWord;
    }

    public void setEndOfWord(boolean endOfWord) {
        isEndOfWord = endOfWord;
    }
}
