import java.util.HashMap;
import java.util.Map;

public class Node {

    private boolean isEndOfWord;
    public Map<Character, Node> children;



    private String word;

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

    public void setWord(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
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
