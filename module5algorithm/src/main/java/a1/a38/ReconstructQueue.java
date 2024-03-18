package a1.a38;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Eirk
 * @Description
 * @Date 2024/3/18 18:07
 */
public class ReconstructQueue {
    public static void main(String[] args) {
        ReconstructQueue reconstructQueue = new ReconstructQueue();
        int[][] ints = reconstructQueue.reconstructQueue(new int[][]{{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}});
        for (int[] anInt : ints) {
            System.out.println(anInt[0] + " " + anInt[1]);
        }
    }

    public int[][] reconstructQueue(int[][] people) {
        // 第一遍的排序是无法优化的，但是第二遍的时候可以进行加速
        // 为空的时候就可以进行占位。
        // 如果有值，但是我是作为后来的，我不能进行数字上的减小。因为之前来的值一定比我小
        int[][] p2 = new int[people.length][2];
        for (int i = 0; i < people.length; i++) {
            p2[i] = people[i];
        }
        Arrays.sort(p2, (a, b) -> {
            if (a[0] != b[0]) {
                return -(a[0] - b[0]);
            } else {
                return a[1] - b[1];
            }
        });
        TreeMap<int[]> treeMap = new TreeMap<>();
        for (int i = 0; i < p2.length; i++) {
            treeMap.add(p2[i][1], p2[i]);
        }
        ArrayList<SBNode<int[]>> getallindex = treeMap.getallindex();
        int[][] res = new int[p2.length][2];

        for (int i = 0; i < getallindex.size(); i++) {
            SBNode<int[]> sbNode = getallindex.get(i);
            res[i] = sbNode.value;
        }
        return res;
    }

    public static class SBNode<T> {
        T value;
        int size;
        SBNode<T> l;
        SBNode<T> r;

        public SBNode(T value) {
            this.value = value;
            this.size = 1;
        }

    }

    public static class TreeMap<T> {
        SBNode<T> root;

        public SBNode<T> rightRotate(SBNode<T> cur) {
            SBNode<T> l = cur.l;
            cur.l = l.r;
            l.r = cur;
            l.size = cur.size;
            cur.size = (cur.l == null ? 0 : cur.l.size) + (cur.r == null ? 0 : cur.r.size) + 1;
            return l;
        }

        public SBNode<T> leftRotate(SBNode<T> cur) {
            SBNode<T> r = cur.r;
            cur.r = r.l;
            r.l = cur;
            r.size = cur.size;
            cur.size = (cur.l == null ? 0 : cur.l.size) + (cur.r == null ? 0 : cur.r.size) + 1;
            return r;
        }

        public SBNode<T> maintain(SBNode<T> cur) {
            int l = cur.l == null ? 0 : cur.l.size;
            int r = cur.r == null ? 0 : cur.r.size;
            int ll = cur.l == null ? 0 : (cur.l.l == null ? 0 : cur.l.l.size);
            int rr = cur.r == null ? 0 : (cur.r.r == null ? 0 : cur.r.r.size);
            int lr = cur.l == null ? 0 : (cur.l.r == null ? 0 : cur.l.r.size);
            int rl = cur.r == null ? 0 : (cur.r.l == null ? 0 : cur.r.l.size);
            if (ll > r) {
                // 右旋
                cur = rightRotate(cur);
                cur = maintain(cur);
            } else if (lr > r) {
                cur.l = leftRotate(cur.l);
                cur = rightRotate(cur);
                cur.l = maintain(cur.l);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            } else if (rr > l) {
                cur = leftRotate(cur);
                cur = maintain(cur);
            } else if (rl > l) {
                cur.r = rightRotate(cur.r);
                cur = leftRotate(cur);
                cur.l = maintain(cur.l);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            }
            return cur;
        }

        public void add(int index, T value) {
            SBNode<T> tsbNode = new SBNode<>(value);

            if (root == null) {
                root = tsbNode;
            } else {
                root = add(root, index, tsbNode);
            }
        }

        // 0，1，2，3，4   index插入4
        public SBNode<T> add(SBNode<T> root, int index, SBNode<T> cur) {
            if (root == null) {
                return cur;
            }
            root.size++;
            int leftheadsize = (root.l == null ? 0 : root.l.size) + 1;
            if (index < leftheadsize) {

                root.l = add(root.l, index, cur);
            } else {
                root.r = add(root.r, index - leftheadsize, cur);
            }
            root = maintain(root);
            return root;
        }

        public ArrayList<SBNode<T>> getallindex() {
            ArrayList<SBNode<T>> arr = new ArrayList<>();
            f(root, arr);
            return arr;
        }

        public void f(SBNode<T> node, List<SBNode<T>> arr) {
            if (node == null) {
                return;
            }
            f(node.l, arr);
            arr.add(node);
            f(node.r, arr);
        }
    }
}
