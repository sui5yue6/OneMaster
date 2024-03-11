package a1.a30;

import javax.swing.tree.TreeNode;

/**
 * @author Eirk
 * @Description
 * @Date 2024/3/11 0:46
 */
public class Code01_MorrisTraversal {

    public static class Node {
        public int value;
        Node left;
        Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static void process(Node root) {
        if (root == null) {
            return;
        }
        // 1
        process(root.left);
        // 2
        process(root.right);
        // 3
    }

    public static void morris(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            cur = cur.right;
        }
    }

    public static void morrisPre(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    System.out.print(cur.value + " ");
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            } else {
                System.out.print(cur.value + " ");
            }
            cur = cur.right;
        }
        System.out.println();
    }

    public static void morrisIn(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            System.out.print(cur.value + " ");
            cur = cur.right;
        }
        System.out.println();
    }

    public static void morrisPos(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                    printEdge(cur.left);
                }
            }
            cur = cur.right;
        }
        printEdge(head);
        System.out.println();
    }

    public static void printEdge(Node head) {
        Node tail = reverseEdge(head);
        Node cur = tail;
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.right;
        }
        reverseEdge(tail);
    }

    public static Node reverseEdge(Node from) {
        Node pre = null;
        Node next = null;
        while (from != null) {
            next = from.right;
            from.right = pre;
            pre = from;
            from = next;
        }
        return pre;
    }

    // for test -- print tree
    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    public static boolean isBST(Node head) {
        if (head == null) {
            return true;
        }
        Node cur = head;
        Node mostRight = null;
        Integer pre = null;
        boolean ans = true;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            if (pre != null && pre >= cur.value) {
                ans = false; // 一定要跑完再返回，因为要恢复现场
            }
            pre = cur.value;
            cur = cur.right;
        }
        return ans;
    }

    public static void main(String[] args) {
        Node head = new Node(4);
        head.left = new Node(2);
        head.right = new Node(6);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.right.left = new Node(5);
        head.right.right = new Node(7);
//        printTree(head);
        morrisIn(head);
        f3(head); // 左 中 右
        f1(head); // 中 左 右
        morrisPre(head);
        morrisPos(head);
//        printTree(head);

    }

    public static void f(Node head) {
        Node cur = head;
        Node mostright = null;
        while (cur != null) {
            mostright = cur.left;
            if (mostright != null) {
                while (mostright.right != null && mostright.right != cur) {
                    mostright = mostright.right; // 到达左边的最右边
                }
                if (mostright.right == null) { // 说明第一次到，指向cur
                    mostright.right = cur;
                    cur = cur.left;// cur往左边走
                    continue;
                } else {  // 说明第二次到
                    mostright.right = null;
                }
            }

            // 如果左边没有，也直接走向右边
            cur = cur.right;

        }
    }

    public static void f1(Node head) {
        Node cur = head;
        Node mostright = null;
        while (cur != null) {
            mostright = cur.left;
            if (mostright != null) {
                while (mostright.right != null && mostright.right != cur) {
                    mostright = mostright.right; // 到达左边的最右边
                }
                if (mostright.right == null) { // 说明第一次到，指向cur
                    System.out.print(cur.value + " ");

                    mostright.right = cur;
                    cur = cur.left;// cur往左边走
                    continue;
                } else {  // 说明第二次到
                    mostright.right = null;
                }
            } else { // 如果没有左树，直接打印
                System.out.print(cur.value + " ");
            }
            // 如果左边没有，也直接走向右边
            cur = cur.right;
        }
        System.out.println();
    }

    public static void f3(Node head) {
        Node cur = head;
        Node mostright = null;
        while (cur != null) {
            mostright = cur.left;
            if (mostright != null) {
                while (mostright.right != null && mostright.right != cur) {
                    mostright = mostright.right; // 到达左边的最右边
                }
                if (mostright.right == null) { // 说明第一次到，指向cur
                    mostright.right = cur;
                    cur = cur.left;// cur往左边走
                    continue;
                } else {  // 说明第二次到
                    mostright.right = null;
                    System.out.print(cur.value + " ");
                }
            } else {
                System.out.print(cur.value + " ");
            }

            // 如果左边没有，也直接走向右边
            cur = cur.right;

        }
        System.out.println();
    }

    // 在递归中，第三次回到是后续遍历。这个太难了，直接放弃。


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return f(root);
    }

    public int f(TreeNode root) {
        if (root.left == null && root.right == null) {
            return 1;
        }
        int f1 = Integer.MAX_VALUE;
        int f2 = Integer.MAX_VALUE;
        if (root.left != null) {
            f1 = f(root.left) + 1;
        }
        if (root.right != null) {
            f2 = f(root.right) + 1;
        }
        return Math.min(f1, f2);
    }

    public int minDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        TreeNode cur = root;
        TreeNode mostRight = null;
        int curLevel = 0;
        int minHeight = Integer.MAX_VALUE;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                int rightBoardSize = 1;
                while (mostRight.right != null && mostRight.right != cur) {
                    rightBoardSize++;
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    curLevel++;
                    // 第一次过来
                    mostRight.right = cur;
                    cur = cur.left; // 因为左边是有值的，因此，需要走到左边
                    continue;
                } else { // 恢复现场
                    if (mostRight.left == null) {
                        minHeight = Math.min(minHeight, curLevel); // 结算
                    }
                    curLevel -= rightBoardSize;
                    mostRight.right = null;
                }
            } else {
                curLevel++;
            }
            cur = cur.right;
        }
        int finalRight = 1;
        cur = root;
        while (cur.right != null) {
            finalRight++;
            cur = cur.right;
        }
        if (cur.left == null) {
            minHeight = Math.min(minHeight, finalRight);
        }
        return minHeight;
    }

}
