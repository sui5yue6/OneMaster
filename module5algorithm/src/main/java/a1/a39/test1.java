package a1.a39;

/**
 * @author Eirk
 * @Description
 * @Date 2024/3/20 22:20
 */
public class test1 {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            int i1 = 95 - 9 * i;
            if (i1 % 16 == 0) {
                System.out.println(i + " " +( i1 /16));
            }
        }
        //
        // 7*9=54   16x2=32         95
        // 7*5+15*2 = 35+30 =65
    }
}
