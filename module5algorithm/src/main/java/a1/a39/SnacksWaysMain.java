package a1.a39;

import jdk.internal.org.objectweb.asm.tree.analysis.Value;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Eirk
 * @Description
 * @Date 2024/3/19 23:07
 */
public class SnacksWaysMain {
    public static void main(String[] args) throws IOException {
        InputStream in = System.in;
        InputStreamReader inputStreamReader = new InputStreamReader(in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StreamTokenizer tokenizer = new StreamTokenizer(bufferedReader);
        PrintStream out = System.out;

        while (tokenizer.nextToken() != StreamTokenizer.TT_EOF) {
            int n = (int) tokenizer.nval;
            tokenizer.nextToken();
            int bagsize = (int) tokenizer.nval;
            tokenizer.nextToken();
            int[] arr = new int[n];
            for (int i = 0; i < arr.length; i++) {
                int tmp = (int) tokenizer.nval;
                tokenizer.nextToken();
                arr[i] = tmp;
            }
            long sum = f(n, arr, bagsize);
            out.println(sum);
            out.flush();
        }

    }

    private static long f(int n, int[] arr, int bagsize) {
        TreeMap<Long, Long> treeMap1 = new TreeMap<>();
        TreeMap<Long, Long> treeMap2 = new TreeMap<>();
        // 这里不仅要收集左右两边的ways，还要收集累加和的map
        long ans = 0;
        long ans1 = p(0, (n - 1) / 2, arr, treeMap1, 0, bagsize);
        long ans2 = p((n - 1) / 2 + 1, n - 1, arr, treeMap2, 0, bagsize);
        ans += ans1;
        ans += ans2;
        // 可以有很关键的一步。只可意会，很难言传
        long tmp=0;
        for (Map.Entry<Long, Long> entry : treeMap2.entrySet()) {
            Long key = entry.getKey();
            Long value = entry.getValue();
            tmp+= value;
            entry.setValue(tmp);
        }
        for (Map.Entry<Long, Long> entry : treeMap1.entrySet()) {
            Long key = entry.getKey();
            Long value = entry.getValue();
            Map.Entry<Long, Long> entry1 = treeMap2.floorEntry(bagsize - key);
            if (entry1 != null) {
                long value1  = entry1.getValue();
                ans += value * value1;
            }
        }
        return ans + 1;
    }

    private static long p(int start, int end, int[] arr,
                          TreeMap<Long, Long> treeMap, long total, int bagsize) {
        if (total > bagsize) {
            return 0;
        }
        if (start == end + 1) {
            if (total == 0) {
                return 0;
            }

            Long integer = treeMap.get(total);
            integer = integer == null ? 0 : integer;
            treeMap.put(total, integer + 1);
            return 1;
        }
        long ans = 0;
        ans += p(start + 1, end, arr, treeMap, total, bagsize);
        ans += p(start + 1, end, arr, treeMap, total + arr[start], bagsize);
        return ans;
    }
}
















