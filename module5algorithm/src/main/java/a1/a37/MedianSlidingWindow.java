package a1.a37;

/**
 * @author Eirk
 * @Description
 * @Date 2024/3/15 1:33
 */
public class MedianSlidingWindow {
    // https://leetcode.cn/problems/sliding-window-median/

    // k是位置，v是value。排序根据value排序
    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] doubles = new double[nums.length - k + 1];
        int index = 0;
        TreeMap treeMap = new TreeMap();
        // 如果k是偶数，需要找两个。4   k/2=2  k/2+1=3   找第2大和第3大的数字
        for (int i = 0; i < nums.length - k + 1; i++) {  /// 3   0 1 2 3 4 5 6 7       0,1,2   -  5,6,7
            //  放入，计算，移除第一个
            // 放入
            treeMap.put(nums[i]);

            if (i >= 2) {
                double ans = 0;
                if (k % 2 == 0) {
                    ans += treeMap.getIndex(k / 2);
                    ans += treeMap.getIndex(k / 2 + 1);
                    ans /= 2;
                } else {
                    ans += treeMap.getIndex(k / 2);
                }
                doubles[index] = ans;
                index++;
                treeMap.remove(nums[i - 2]);
            }
            // i 从2开始
        }
    }

    public static class Node {
        int key;
        Node l;
        Node r;
        int size;

        public Node(int key) {
            this.key = key;
            size = 1;
        }
    }

    public static class TreeMap {
        Node root;

        private Node rightRotate(Node cur) {
            // 右旋
            Node l = cur.l;
            cur.l = l.r;
            l.r = cur;
            l.size = cur.size;
            cur.size = (cur.l == null ? 0 : cur.l.size) + (cur.r == null ? 0 : cur.r.size) + 1;
            return l;
        }

        private Node leftRotate(Node cur) {
            Node r = cur.r;
            cur.r = r.l;
            r.l = cur;
            r.size = cur.size;
            cur.size = (cur.r == null ? 0 : cur.r.size) + (cur.l == null ? 0 : cur.l.size) + 1;
            return r;
        }

        private Node maintain(Node cur) {
            int rr = cur.r == null ? 0 : (cur.r.r == null ? 0 : cur.r.r.size);
            int ll = cur.l == null ? 0 : (cur.l.l == null ? 0 : cur.l.l.size);
            int lr = cur.l == null ? 0 : (cur.l.r == null ? 0 : cur.l.r.size);
            int rl = cur.r == null ? 0 : (cur.r.l == null ? 0 : cur.r.l.size);
            int r = cur.r == null ? 0 : cur.r.size;
            int l = cur.l == null ? 0 : cur.l.size;
            // 需要右旋。把左边提上来
            if (ll > r) {
                cur = rightRotate(cur);
                // cur和cur.r都要maintain
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            } else if (lr > r) {
                cur.l = leftRotate(cur.l);
                cur = rightRotate(cur);
                cur.r = maintain(cur.r);
                cur.l = maintain(cur.l);
                cur = maintain(cur);
            } else if (rr > l) {
                cur = leftRotate(cur);
                // cur和cur.r都要maintain
                cur.l = maintain(cur.l);
                cur = maintain(cur);
            } else if (rl > l) {
                cur.r = rightRotate(cur.r);
                cur = leftRotate(cur);
                cur.r = maintain(cur.r);
                cur.l = maintain(cur.l);
                cur = maintain(cur);
            }
            return cur;
        }

        private Node put(int key) {

        }

        private Node remove(int key) {

        }

        private Node add(Node cur, int key) {

        }

        private Node delete(Node cur, int key) {

        }

        private Double getIndex(int kth) {

        }

        private Node getIndex(Node cur, int kth) {

        }
    }
}
