package a1.a37;

import java.util.ArrayList;

/**
 * @author Eirk
 * @Description
 * @Date 2024/3/18 16:57
 */
public class test {
    public static void main(String[] args) {
//        TreeMap<Integer> map = new TreeMap<>();
//        map.add(0, 100);
//        Integer integer1 = map.get(0);

        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(1, 100);
        arr.add(0, 100);
        Integer integer = arr.get(0);
        arr.remove(1);
    }
}
