package a1.a39;

/**
 * @author Eirk
 * @Description
 * @Date 2024/3/18 22:45
 */
public class Code02_EatGrass {
    public static void main(String[] args) {
        Code02_EatGrass code02_eatGrass = new Code02_EatGrass();

        for (int i = 0; i <= 50; i++) {
            System.out.println(i + " : " + whoWin(i));
            String s = whoWin(i);
                boolean whowin = code02_eatGrass.whowin(i);
            System.out.println(whowin);
            if(s.equals("后手")&& !whowin){
                System.out.println("ok");
            }else if(s.equals("先手")&&  whowin){
                System.out.println("ok");
            }else {
                System.out.println("wrong");
            }

        }
        System.out.println("===");
        for (int i = 0; i < 50; i++) {

        }
    }

    public boolean whowin(int N) {
        boolean f = f(N);
        return f;
    }

    public boolean f(int N) {
        if(N==0){
            return false;
        }
        // 这里进行 1,4,16的递归
        int tmp = 1;
        while (tmp <= N) {
            if (N - tmp == 0) {
                return true;
            }
            if (!p(N - tmp)) {
                return true;
            }
            tmp *= 4;
        }
        return false;
    }

    public boolean p(int N) {
        if(N==0){
            return false;
        }
        // 这里进行 1,4,16的递归
        int tmp = 1;
        while (tmp <= N) {
            if (N - tmp == 0) {
                return true;
            }
            if (!f(N - tmp)) {
                return true;
            }
            tmp *= 4;
        }
        return false;
    }


    // 如果n份草，最终先手赢，返回"先手"
    // 如果n份草，最终后手赢，返回"后手"
    public static String whoWin(int n) {
        if (n < 5) {
            return n == 0 || n == 2 ? "后手" : "先手";
        }
        // 进到这个过程里来，当前的先手，先选
        int want = 1;
        while (want <= n) {
            if (whoWin(n - want).equals("后手")) {
                return "先手";
            }
            if (want <= (n / 4)) {
                want *= 4;
            } else {
                break;
            }
        }
        return "后手";
    }

    public static String winner1(int n) {
        if (n < 5) {
            return (n == 0 || n == 2) ? "后手" : "先手";
        }
        int base = 1;
        while (base <= n) {
            if (winner1(n - base).equals("后手")) {
                return "先手";
            }
            if (base > n / 4) { // 防止base*4之后溢出
                break;
            }
            base *= 4;
        }
        return "后手";
    }

    public static String winner2(int n) {
        if (n % 5 == 0 || n % 5 == 2) {
            return "后手";
        } else {
            return "先手";
        }
    }

}
