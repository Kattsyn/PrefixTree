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
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            stringBuilder.append(word.charAt(i));
            char ch = word.charAt(i);
            if (!node.hasChild(ch)) {
                node.addChild(ch);
            }
            node = node.getChild(ch);
        }
        node.setWord(stringBuilder.toString());
        node.setEndOfWord(true);
    }


    public boolean hasWord(String word) {
        return hasWordOrStartsWith(word, true);
    }

    public boolean hasWordStartsWith(String prefix) {
        return hasWordOrStartsWith(prefix, false);
    }

    private boolean hasWordOrStartsWith(String str, boolean isEndOfWord) {
        Node node = root;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (node.hasChild(ch)) {
                node = node.getChild(ch);
            } else {
                return false;
            }
        }
        return !isEndOfWord || node.isEndOfWord();
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

    public List<String> getAllWords() {
        return getAllWords(root);
    }

    private List<String> getAllWords(Node currentNode) {
        List<String> list = new ArrayList<>();
        for (Map.Entry<Character, Node> child : currentNode.children.entrySet()) {
            if (child.getValue().isEndOfWord()) {
                list.add(child.getValue().getWord());
            }
            if (child.getValue().isEmpty()) {
                return list;
            } else {
                list.addAll(getAllWords(child.getValue()));
            }
        }
        return list;
    }

    /*Прошлая версия функции со StringBuilder
    private List<String> getAllWords(Node currentNode, StringBuilder curBuilder) {
        List<String> list = new ArrayList<>();
        for (Map.Entry<Character, Node> child : currentNode.children.entrySet()) {
            if (child.getValue().isEndOfWord()) {
                list.add(String.valueOf(curBuilder.append(child.getKey())));
            }
            if (child.getValue().isEmpty()) {
                curBuilder.setLength(curBuilder.length() - 1);
                return list;
            } else {
                curBuilder.append(child.getKey());
                list.addAll(getAllWords(child.getValue(), curBuilder));
                curBuilder.setLength(curBuilder.length() - 1);
            }
        }
        return list;
    }
   */

}
