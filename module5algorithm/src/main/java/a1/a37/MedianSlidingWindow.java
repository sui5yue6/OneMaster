package a1.a37;

/**
 * @author Eirk
 * @Description
 * @Date 2024/3/15 1:33
 */
public class MedianSlidingWindow {
    // https://leetcode.cn/problems/sliding-window-median/
    public static void main(String[] args) {
        MedianSlidingWindow medianSlidingWindow = new MedianSlidingWindow();
//        double[] doubles = medianSlidingWindow.medianSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);
//        double[] doubles = medianSlidingWindow.medianSlidingWindow(new int[]{1,4,2,3}, 4);
//        double[] doubles = medianSlidingWindow.medianSlidingWindow(new int[]{1}, 1);
        double[] doubles = medianSlidingWindow.medianSlidingWindow(new int[]{5, 2, 2, 7, 3, 7, 9, 0, 2, 3}, 9);
        for (int i = 0; i < doubles.length; i++) {
            System.out.println(doubles[i]);
        }
    }

    // k是位置，v是value。排序根据value排序
    public double[] medianSlidingWindow(int[] nums, int k) {  // ——这里的逻辑可以写的和老师的几乎一样，大概是最大的喜悦吧！
        double[] doubles = new double[nums.length - k + 1];
        int index = 0;
        TreeMap treeMap = new TreeMap();
        // 如果k是偶数，需要找两个。4   k/2=2  k/2+1=3   找第2大和第3大的数字  3   2
        // 奇数   3 3/2=1    3/2+1  找第二个数
        for (int i = 0; i < nums.length; i++) {  /// 3   0 1 2 3 4 5 6 7       0,1,2   -  5,6,7
            // 放入，计算，移除第一个
            // 放入
            treeMap.add(new Node(i, nums[i]));
            if (i >= k - 1) {
                double ans = 0;
                if (k % 2 == 0) {
                    ans += treeMap.getIndex(k / 2);
                    ans += treeMap.getIndex(k / 2 + 1);
                    ans /= 2;
                } else {
                    ans += treeMap.getIndex(k / 2 + 1);
                }
                doubles[index] = ans;
                index++;
                treeMap.remove(new Node(i - (k - 1), nums[i - (k - 1)]));
            }
            // i 从2开始
        }
        return doubles;
    }

    public static class Node implements Comparable<Node> {
        public int index;
        public int value;

        public Node(int index, int value) {
            this.index = index;
            this.value = value;

        }

//        @Override
//        public int compareTo(Node o) {
//            // 如果大小不同，返回大小。 如果大小相同返回下标
//            if (this.value != o.value) {
//                return this.value - o.value;
//            } else {
//                return this.index - o.index;
//            }
//        }
        @Override
        public int compareTo(Node o) {
            return value != o.value ? Integer.valueOf(value).compareTo(o.value)
                    : Integer.valueOf(index).compareTo(o.index);
        }
    }

    public static class SBTNode {
        Node key;
        SBTNode l;
        SBTNode r;
        int size;

        public SBTNode(Node key) {
            this.key = key;
            size = 1;
        }
    }

    public static class TreeMap {
        SBTNode root;

        //        private SBTNode rightRotate(SBTNode cur) {
//            // 右旋
//            SBTNode l = cur.l;
//            cur.l = l.r;
//            l.r = cur;
//            l.size = cur.size;
//            cur.size = (cur.l == null ? 0 : cur.l.size) + (cur.r == null ? 0 : cur.r.size) + 1;
//            return l;
//        }
//
//        private SBTNode leftRotate(SBTNode cur) {
//            SBTNode r = cur.r;
//            cur.r = r.l;
//            r.l = cur;
//            r.size = cur.size;
//            cur.size = (cur.r == null ? 0 : cur.r.size) + (cur.l == null ? 0 : cur.l.size) + 1;
//            return r;
//        }
        private SBTNode rightRotate(SBTNode cur) {
            SBTNode leftNode = cur.l;
            cur.l = leftNode.r;
            leftNode.r = cur;
            leftNode.size = cur.size;
            cur.size = (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0) + 1;
            return leftNode;
        }

        private SBTNode leftRotate(SBTNode cur) {
            SBTNode rightNode = cur.r;
            cur.r = rightNode.l;
            rightNode.l = cur;
            rightNode.size = cur.size;
            cur.size = (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0) + 1;
            return rightNode;
        }

        //        private SBTNode maintain(SBTNode cur) {
//            int rr = cur.r == null ? 0 : (cur.r.r == null ? 0 : cur.r.r.size);
//            int ll = cur.l == null ? 0 : (cur.l.l == null ? 0 : cur.l.l.size);
//            int lr = cur.l == null ? 0 : (cur.l.r == null ? 0 : cur.l.r.size);
//            int rl = cur.r == null ? 0 : (cur.r.l == null ? 0 : cur.r.l.size);
//            int r = cur.r == null ? 0 : cur.r.size;
//            int l = cur.l == null ? 0 : cur.l.size;
//            // 需要右旋。把左边提上来
//            if (ll > r) {
//                cur = rightRotate(cur);
//                // cur和cur.r都要maintain
//                cur.r = maintain(cur.r);
//                cur = maintain(cur);
//            } else if (lr > r) {
//                cur.l = leftRotate(cur.l);
//                cur = rightRotate(cur);
//                cur.r = maintain(cur.r);
//                cur.l = maintain(cur.l);
//                cur = maintain(cur);
//            } else if (rr > l) {
//                cur = leftRotate(cur);
//                // cur和cur.r都要maintain
//                cur.l = maintain(cur.l);
//                cur = maintain(cur);
//            } else if (rl > l) {
//                cur.r = rightRotate(cur.r);
//                cur = leftRotate(cur);
//                cur.r = maintain(cur.r);
//                cur.l = maintain(cur.l);
//                cur = maintain(cur);
//            }
//            return cur;
//        }
        private SBTNode maintain(SBTNode cur) {
            if (cur == null) {
                return null;
            }
            int leftSize = cur.l != null ? cur.l.size : 0;
            int leftLeftSize = cur.l != null && cur.l.l != null ? cur.l.l.size : 0;
            int leftRightSize = cur.l != null && cur.l.r != null ? cur.l.r.size : 0;
            int rightSize = cur.r != null ? cur.r.size : 0;
            int rightLeftSize = cur.r != null && cur.r.l != null ? cur.r.l.size : 0;
            int rightRightSize = cur.r != null && cur.r.r != null ? cur.r.r.size : 0;
            if (leftLeftSize > rightSize) {
                cur = rightRotate(cur);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            } else if (leftRightSize > rightSize) {
                cur.l = leftRotate(cur.l);
                cur = rightRotate(cur);
                cur.l = maintain(cur.l);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            } else if (rightRightSize > leftSize) {
                cur = leftRotate(cur);
                cur.l = maintain(cur.l);
                cur = maintain(cur);
            } else if (rightLeftSize > leftSize) {
                cur.r = rightRotate(cur.r);
                cur = leftRotate(cur);
                cur.l = maintain(cur.l);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            }
            return cur;
        }

        private void add(Node key) {
            //  这里的代码加了一堆判断不是很懂意思
            System.out.println("add key" + key.index);
            root = add(root, key);
         }


        private SBTNode add(SBTNode cur, Node key) {
            if (cur == null) {
                return new SBTNode(key);
            } else {
                cur.size++;
                // 看是往左边加还是往右边加
                if (key.compareTo(cur.key) < 0) {
                    cur.l = add(cur.l, key);
                } else {
                    cur.r = add(cur.r, key);
                }
                return maintain(cur);
            }
        }

        private void remove(Node key) {
            root = delete(root, key);
        }

        private SBTNode delete(SBTNode cur, Node key) {
            // 这里的删除非常的有学问
            // 路上的每一个cur都需要-1
            cur.size--;
            if (key.compareTo(cur.key) > 0) {
                // 右移动
                cur.r = delete(cur.r, key);
            } else if (key.compareTo(cur.key) < 0) {
                cur.l = delete(cur.l, key);
            } else {
                // 就是要删除当前节点。需要讨论，左边，右边和两边
                if (cur.l == null && cur.r == null) {
                    cur = null;
                } else if (cur.l == null) {
                    cur = cur.r;
                } else if (cur.r == null) {
                    cur = cur.l;
                } else {
                    // 找到右边的最左
                    SBTNode pre = null;
                    SBTNode des = cur.r;
                    des.size--;
                    while (des.l != null) { // 如果没有可以往左边走的，pre就会为空
                        pre = des;
                        des = des.l;
                        des.size--;
                    }
                    if (pre != null) {
                        pre.r = des.r;
                        des.r = cur.r;
                    }
                    des.l = cur.l;
                    des.size = des.l.size + (des.r == null ? 0 : des.r.size);
                    cur = des;
                }
            }
            return cur;
        }

        private Integer getIndex(int kth) {  // 这里只能找+1个。  比如我要找第十个。那么就用找前面比他小的9个数
            return getIndex(root, kth).key.value;
        }

        private SBTNode getIndex(SBTNode cur, int kth) {
            if (kth == (cur.l == null ? 0 : cur.l.size) + 1) {
                return cur;
            } else if (kth <= (cur.l == null ? 0 : cur.l.size)) {
                return getIndex(cur.l, kth);
            } else {
                return getIndex(cur.r, kth - (cur.l == null ? 0 : cur.l.size) - 1);
            }
        }
    }
}
