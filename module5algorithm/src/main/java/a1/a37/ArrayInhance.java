package a1.a37;

import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;

/**
 * @author Eirk
 * @Description
 * @Date 2024/3/18 13:44
 */
public class ArrayInhance {
    public static class SBTNode<T> {
        T value;
        SBTNode<T> l;
        SBTNode<T> r;
        int size;

        public SBTNode(T t) {
            this.value = t;
            size = 1;

        }
    }

    public static class TreeMap<T> {
        SBTNode<T> root;

        // 左旋，右旋，维持
        public SBTNode<T> rightRotate(SBTNode<T> cur) {
            SBTNode<T> l = cur.l;
            cur.l = l.r;
            l.r = cur;
            l.size = cur.size;
            cur.size = (cur.r == null ? 0 : cur.r.size) + (cur.l == null ? 0 : cur.l.size) + 1;
            return l;
        }

        public SBTNode<T> leftRotate(SBTNode<T> cur) {
            SBTNode<T> r = cur.r;
            cur.r = r.l;
            r.l = cur;
            r.size = cur.size;
            cur.size = (cur.r == null ? 0 : cur.r.size) + (cur.l == null ? 0 : cur.l.size) + 1;
            return r;
        }

        public SBTNode<T> maintain(SBTNode<T> cur) {
            int l = cur.l == null ? 0 : cur.l.size;
            int r = cur.r == null ? 0 : cur.r.size;
            int ll = cur.l == null ? 0 : (cur.l.l == null ? 0 : cur.l.l.size);
            int rr = cur.r == null ? 0 : (cur.r.r == null ? 0 : cur.r.r.size);
            int lr = cur.l == null ? 0 : (cur.l.r == null ? 0 : cur.l.r.size);
            int rl = cur.r == null ? 0 : (cur.r.l == null ? 0 : cur.r.l.size);
            if (ll > r) {
                // 右旋
                cur = rightRotate(cur);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            } else if (lr > r) {

                cur.l = leftRotate(cur.l);
                cur = rightRotate(cur);
                cur.l = maintain(cur.l);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            } else if (rr > l) {
                // 右旋
                cur = leftRotate(cur);
                cur.l = maintain(cur.l);
                cur = maintain(cur);
            } else if (rl > l) {
                //左旋  右旋
                cur.r = rightRotate(cur.r);
                cur = leftRotate(cur);
                cur.l = maintain(cur.l);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            }
            return cur;
        }

        // 增
        public void add(int index, T num) {
            SBTNode<T> cur = new SBTNode<>(num);
            if (root == null) {
                root = cur;
            } else {
                if (index <= root.size) {
                    root = add(root, index, cur);
                }
            }

        }

        // index为9 那么就要找到的数是第十大的数字。 对于前面9的树不理
        public SBTNode<T> add(SBTNode<T> root, int index, SBTNode<T> cur) {
            if (root == null) {
                return cur;
            }
            root.size++;
            // 左边的大小
            int leftheadsize = (root.l == null ? 0 : root.l.size) + 1; //
            if (index < leftheadsize) { // 左移啥都考虑
                root.l = add(root.l, index, cur);
            } else {
                root.r = add(root.r, index - leftheadsize, cur);
            }
            root = maintain(root);
            return root;
        }

        public T get(int index) {
            SBTNode<T> res = get(root, index);
            return res.value;
        }

        public SBTNode<T> get(SBTNode<T> root, int index) {
            int leftsize = (root.l == null ? 0 : root.l.size);
            if (index < leftsize) {
                return get(root.l, index);
            } else if (index == leftsize) {
                return root;
            } else {
                return get(root.r, index - leftsize - 1);
            }
        }

        // 删
        public void remove(int index) {
//            if (index >= 0 && index < root.size) {
            if (index >= 0 && index <  size()) {
                root = remove(root, index);
            }
        }

        private SBTNode<T> remove(SBTNode<T> root, int index) {
            root.size--;
            // 左边的大小
            int leftSize = (root.l == null ? 0 : root.l.size); //
            if (index < leftSize) { // 左移
                root.l = remove(root.l, index);
            } else if (index > leftSize) {
                root.r = remove(root.r, index - leftSize - 1);
            } else {
                if (root.l == null && root.r == null) {
                    return null;
                } else if (root.l == null) {
                    return root.r;
                } else if (root.r == null) {
                    return root.l;
                } else {
                    // 找到左边的最右边
                    SBTNode<T> pre = null;
                    SBTNode<T> res = root.r;
                    res.size--;
                    while (res.l != null) {
                        pre = res;
                        res = res.l;
                        res.size--;
                    }
                    if (pre != null) {
                        pre.l = res.r;
                        res.r =  root.r;
                    }
                    res.l =  root.l;
                    res.size = res.l.size + (res.r == null ? 0 : res.r.size) + 1;
                    return res;
                }
            }
            return root;
        }

        public int size() {
            return root == null ? 0 : root.size;
        }
    }
//
//    public static void main(String[] args) {
//        TreeMap<Integer> map = new TreeMap<>();
//        map.add(0, 100);
//        Integer integer1 = map.get(0);
//
//        ArrayList<Integer> arr = new ArrayList<>();
//        arr.add(0, 100);
//        Integer integer = arr.get(0);
//        arr.remove(1);
//    }

    // 通过以下这个测试，
    // 可以很明显的看到LinkedList的插入、删除、get效率不如SbtList
    // LinkedList需要找到index所在的位置之后才能插入或者读取，时间复杂度O(N)
    // SbtList是平衡搜索二叉树，所以插入或者读取时间复杂度都是O(logN)
    public static void main(String[] args) {
        Code03_AddRemoveGetIndexGreat.SbtList<Integer> map2 = new Code03_AddRemoveGetIndexGreat.SbtList<>();
        for (int i = 0; i < 10; i++) {
            map2.add(1,i);
        }
        map2.remove(5);
        map2.get(5);


        TreeMap<Integer> map = new TreeMap<>();
        for (int i = 0; i < 10; i++) {
//            map.add(i,i);
            map.add(1,i);
        }
        map.remove(5);
        map.get(5);



        // 功能测试
        int test = 50000;
        int max = 1000000;
        boolean pass = true;
        ArrayList<Integer> list = new ArrayList<>();
        TreeMap<Integer> treeMap = new TreeMap<>();
        for (int i = 0; i < test; i++) {
            if (list.size() != treeMap.size()) {
                System.out.println("size");
                pass = false;
                break;
            }
            if (list.size() > 1 && Math.random() < 0.5) {
                int removeIndex = (int) (Math.random() * list.size());
                list.remove(removeIndex);
                treeMap.remove(removeIndex);
            } else {
                int randomIndex = (int) (Math.random() * (list.size() + 1));
                int randomValue = (int) (Math.random() * (max + 1));
                list.add(randomIndex, randomValue);
                treeMap.add(randomIndex, randomValue);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).equals(treeMap.get(i))) {
                System.out.println("get");
                pass = false;
                break;
            }
        }
        System.out.println("功能测试是否通过 : " + pass);

        // 性能测试
        test = 500000;
        list = new ArrayList<>();
        treeMap = new TreeMap<>();
        long start = 0;
        long end = 0;

        start = System.currentTimeMillis();
        for (int i = 0; i < test; i++) {
            int randomIndex = (int) (Math.random() * (list.size() + 1));
            int randomValue = (int) (Math.random() * (max + 1));
            list.add(randomIndex, randomValue);
        }
        end = System.currentTimeMillis();
        System.out.println("ArrayList插入总时长(毫秒) ： " + (end - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < test; i++) {
            int randomIndex = (int) (Math.random() * (i + 1));
            list.get(randomIndex);
        }
        end = System.currentTimeMillis();
        System.out.println("ArrayList读取总时长(毫秒) : " + (end - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < test; i++) {
            int randomIndex = (int) (Math.random() * list.size());
            list.remove(randomIndex);
        }
        end = System.currentTimeMillis();
        System.out.println("ArrayList删除总时长(毫秒) : " + (end - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < test; i++) {
            int randomIndex = (int) (Math.random() * (treeMap.size() + 1));
            int randomValue = (int) (Math.random() * (max + 1));
            treeMap.add(randomIndex, randomValue);
        }
        end = System.currentTimeMillis();
        System.out.println("SbtList插入总时长(毫秒) : " + (end - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < test; i++) {
            int randomIndex = (int) (Math.random() * (i + 1));
            treeMap.get(randomIndex);
        }
        end = System.currentTimeMillis();
        System.out.println("SbtList读取总时长(毫秒) :  " + (end - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < test; i++) {
            int randomIndex = (int) (Math.random() * treeMap.size());
            treeMap.remove(randomIndex);
        }
        end = System.currentTimeMillis();
        System.out.println("SbtList删除总时长(毫秒) :  " + (end - start));

    }
}
