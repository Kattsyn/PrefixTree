public class Main {
    public static void main(String[] args) {
        PrefixTree trie = new PrefixTree();

        trie.insert("chicken");
        trie.insert("chocolate");
        trie.insert("milkyway");
        trie.insert("millitary");

        System.out.println(trie.hasWord("milkyway")); //true

        trie.deleteWord("milkyway");
        System.out.println(trie.hasWord("milkyway")); //true
        System.out.println(trie.hasWordStartsWith("mil")); //true
        System.out.println(trie.getAllWords());
    }
}
