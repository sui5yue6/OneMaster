package a1.a32;

/**
 * @author Eirk
 * @Description
 * @Date 2024/3/12 0:57
 */
public class ACDemo {
    public static class AC {
        private Node root;

        public AC() {
            root = new Node();
        }

        public void insert(String s) {
            // 构建前缀数
            char[] chars = s.toCharArray();
            Node cur = root;
            for (int i = 0; i < chars.length; i++) {
                char aChar = chars[i];
                if (cur.nexts[aChar] == null) {
                    cur.nexts[aChar] = new Node();
                }
                cur = cur.nexts[aChar];
            }
            cur.end++;
        }
        public void build(){ // 宽度优先遍历用queue

        }
    }

    public static class Node {
        public int end; // 有多少个字符串以该节点结尾
        public Node fail;
        public Node[] nexts;

        public Node() {
            end = 0;
            fail = null;
            nexts = new Node[26];
        }
    }

}
