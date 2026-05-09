import lru.Cache;

class member {
    Integer val;
    int p;
    Boolean q;
    String str;
}
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        member mem = new member();
        mem.val = 2;
        mem.str = "amogh";
        changeVal(mem.val);
        System.out.println(mem.val);
        System.out.println(mem);
        changeMem(mem);
        System.out.println(mem.val);
        changeVal(mem.str);
        System.out.println(mem.str);
        testLruCache();
    }

    public static void changeVal(int val) {
        val = 5;
    }

    public static void changeVal(String val) {
        val = "sunidhi";
    }

    public static void changeMem(member m) {
        System.out.println(m);
        m.val = 5;
        m.str = "AAmogh";
    }

    public static void testLruCache() {
        Cache lruCache = new Cache(5);
        String[] arr = new String[]{"A", "B", "C", "B", "D", "E", "F", "A", "E", "E", "F" };
        for (String s : arr) {
            lruCache.processStream(s);
        }
    }
}