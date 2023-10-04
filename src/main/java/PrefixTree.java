import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PrefixTree {

    private final Node root;

    public PrefixTree() {
        this.root = new Node();
    }


    //добавление слова
    public void insert(String word) {
        Node node = this.root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!node.hasChild(ch)) {
                node.addChild(ch);
            }
            node = node.getChild(ch);
        }
        node.setEndOfWord(true);
    }


    //содержит ли слово
    public boolean hasWord(String word){
        Node node = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (node.hasChild(ch)) {
                node = node.getChild(ch);
            } else {
                return false;
            }
        }
        return node.isEndOfWord();
    }

    public void deleteWord(String word) {
        delete(root, word, 0);
    }

    private boolean delete(Node currentNode, String word, int index) {
        //если дошли до длины, по нужным буквам, но слова такого всё ещё нет, то удалять ничего не нужно, тк слово уже удалено.
        if (index == word.length()) {
            if (!currentNode.isEndOfWord()) {
                return false;
            }
            currentNode.setEndOfWord(false);
            return currentNode.isEmpty();
        }

        //если проходя по ветвям упираемся в тупик (значит такого слова по прежнему не существует)
        char ch = word.charAt(index);
        Node nextNode = currentNode.getChild(ch);
        if (nextNode == null) {
            return false;
        }

        //рекурсивно проходимся по ветвям до тех пор, пока не дойдем до нашего слова
        //затем удаляем эту ветвь и все предыдущие, которые не имеют потомков, ведущим к другим словам.
        boolean shouldDelete = delete(nextNode, word, ++index);
        if (shouldDelete) {
            currentNode.deleteChild(ch);
            return currentNode.isEmpty();
        }
        return false;
    }

    public boolean hasWordStartsWith(String prefix) {
        Node node = root;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            if (node.hasChild(ch)) {
                node = node.getChild(ch);
            } else {
                return false;
            }
        }
        return true;
    }

    /*
     Написать 2 функции для вывода всех слов из дерева:
      1-ая функция просто вызывается, а внутри себя вызывает вторую функцию с параметрами, которая будет вызываться рекурсивно
      2-ая:
            на псевдокоде:
                    private List getAllWords(Node currentNode, String currentStr, ) {
                        foreach (child : children) {

                        }
                    }



     */
    public List<String> getAllWords() {
        return getAllWords(root, "");
    }

    private List<String> getAllWords(Node currentNode, String curString) {
        List<String> list = new ArrayList<>();
        for (Map.Entry<Character, Node> child : currentNode.children.entrySet()) {
            if (child.getValue().isEndOfWord()) {
                list.add(curString + child.getKey());
            }
            if (child.getValue().isEmpty()) {
                return list;
            } else {
                list.addAll(getAllWords(child.getValue(), curString + child.getKey()));
            }
        }
        return list;
    }
}
