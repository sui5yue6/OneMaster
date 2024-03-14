package a1.a37;

import java.util.HashSet;

/**
 * @author Eirk
 * @Description
 * @Date 2024/3/14 23:07
 */
public class Code01_CountofRangeSum {
    // 因为有序表不能接受重复数字，因此需要将有序表压缩到一起

    public static void main(String[] args) {
        int i = new Code01_CountofRangeSum().countRangeSum(new int[]{-2, 5, -1}, -2, 2);
        System.out.println(i);
    }

    public int countRangeSum(int[] nums, int lower, int upper) {
        // 定义有序表
        long sum = 0;
        int ans = 0;
        TreeSet treeSet = new TreeSet();
        // 一个数没有，需要加上0
        treeSet.put(0);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            long a1 = sum - upper;  //  80-60=20
            long a2 = sum - lower + 1; //80-30=50+1    如果范围是30到60   而当前值是80      那么a1-a2 分别为  20-50  那么找到小于19的数  找到小于50的数 相减
            // 找到20小的数，就是x-19  找到51小的数，就是 x-50 两个范围差就是20到50
            long n1 = treeSet.lessThanKey(a1);
            long n2 = treeSet.lessThanKey(a2);
            ans += n2 - n1;
            treeSet.put(sum);
        }
        return ans;
    }

    public static class Node {
        long key;
        Node l;
        Node r;
        long size; // 不同key的size   这里都没有必要使用long类型把
        long all; // 总的size

        public Node(long k) {
            key = k;
            size = 1;
            all = 1;
        }
    }

    public static class TreeSet {
        Node root;

        HashSet<Long> set = new HashSet<>();

        public void put(long value) {
            // 这里非常的关键，需要把root放进去，然后拿root来接住
            root = add(root, value, set.contains(value));
            set.add(value);
        }

        public long lessThanKey(long key) {
            Node cur = root;
            long ans = 0;
            while (cur != null) {
                if (key == cur.key) {
                    // 自己不加，但是会把自己左边的都加上
                    ans += cur.l == null ? 0 : cur.l.all;
                    return ans;
                } else if (key > cur.key) { // 往右边移动
                    ans += cur.all - (cur.r == null ? 0 : cur.r.all); // 加上左边的和自身的 就是自身减去右边。沃日
                    cur = cur.r;
                } else if (key < cur.key) {
                    cur = cur.l;
                }
            }
            return ans;
        }

        public Node add(Node cur, long key, boolean contains) {
            if (cur == null) { // 说明没法加入进去
                return new Node(key);
            } else {
                cur.all++;
                if (key == cur.key) {
                    return cur;
                } else {
                    // 为什么是不含有这个key，size++
                    if (!contains) {
                        cur.size++;  // 因为size是用来保持平衡的
                    }
                    if (key < cur.key) {
                        // cur左移
                        cur.l = add(cur.l, key, contains);
                    } else {
                        //cur右移
                        cur.r = add(cur.r, key, contains);
                    }
                    return maintain(cur);
                }

            }
        }

        private Node maintain(Node cur) {
            // r如果为空，就不需要调节平衡了
            if (cur == null) {
                return null;
            }
            long l = cur.l == null ? 0 : cur.l.size;
            long r = cur.r == null ? 0 : cur.r.size;
            long ll = cur.l == null ? 0 : cur.l.l == null ? 0 : cur.l.l.size;
            long lr = cur.l == null ? 0 : cur.l.r == null ? 0 : cur.l.r.size;
            long rl = cur.r == null ? 0 : cur.r.l == null ? 0 : cur.r.l.size;
            long rr = cur.r == null ? 0 : cur.r.r == null ? 0 : cur.r.r.size;
            // 右旋的情况
            if (ll > r) {
                cur = rightRotate(cur);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            } else if (lr > r) {
                // 先右旋，再左旋
                cur.l = leftRotate(cur.l);
                cur = rightRotate(cur);
                // 无脑调用左右和自己

                cur.l = maintain(cur.l);
                cur.r = maintain(cur.r);
                cur = maintain(cur);

            } else if (rr > l) {
                // 左旋把右边的提上来
                cur = leftRotate(cur); // 这里的cur已经不是原来的cur，而是转上来的cur，因为左边的节点和自身发生了变化
                cur.l = maintain(cur.l);
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


        // 左旋，右旋
        public Node rightRotate(Node cur) {
            // 这里提取出来 数量cur的个数
            long same = (cur.l == null ? 0 : cur.l.all) + (cur.r == null ? 0 : cur.r.all) - cur.all;
            // 右旋就是左边的提上来
            Node left = cur.l;
            cur.l = left.r;
            left.r = cur;
            left.size = cur.size;
            left.all = cur.all;
            // cur的size。要变。
            cur.size = (cur.l == null ? 0 : cur.l.size) + (cur.r == null ? 0 : cur.r.size) + 1;
            cur.all = (cur.l == null ? 0 : cur.l.all) + (cur.r == null ? 0 : cur.r.all) - same;
            return left;
        }

        public Node leftRotate(Node cur) {
            long same = cur.all - (cur.r == null ? 0 : cur.r.all) - (cur.l == null ? 0 : cur.l.all);
            Node r = cur.r;
            cur.r = r.l;
            r.l = cur;
            r.size = cur.size;
            r.all = cur.all;
            cur.size = (cur.l == null ? 0 : cur.l.size) + (cur.r == null ? 0 : cur.r.size) + 1;
            cur.all = (cur.l == null ? 0 : cur.l.all) + (cur.r == null ? 0 : cur.r.all) + same;
            return r;
        }
    }


}
