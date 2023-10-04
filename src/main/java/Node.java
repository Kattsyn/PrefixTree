import java.util.HashMap;

public class Node {

    private boolean isEndOfWord;
    public HashMap<Character, Node> children;

    public Node() {
        this.isEndOfWord = false;
        this.children = new HashMap<Character, Node>();
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
        return children.size() == 0;
    }

    //является ли текущая ветвь - листочком (последним узлом слова)
    public boolean isLeaf(){
        return children.size() == 0;
    }

    public boolean isEndOfWord() {
        return isEndOfWord;
    }

    public void setEndOfWord(boolean endOfWord) {
        isEndOfWord = endOfWord;
    }
}
