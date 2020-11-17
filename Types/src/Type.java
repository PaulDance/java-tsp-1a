public class Type {
    public static void main(final String[] args) {
        final int x = 5;
        final int y = x / 3;
        final double d = 5;
        final double e = d / 3;
        final double f = x / 3;
        final int g = (int) e; // type moins fort : conversion explicite
                               // nécessaire
        final char r = 'a';
        final int s = r;
        final int t = 98;
        final char u = (char) t; // type moins fort : conversion explicite
                                 // nécessaire

        final String s1 = "y vaut " + y;
        final String s2 = "e vaut " + e;
        final String s3 = "f vaut " + f;
        final String s4 = "g vaut " + g;
        final String s5 = "r vaut " + r;
        final String s6 = "s vaut " + s;
        final String s7 = "t vaut " + t;
        final String s8 = "u vaut " + u;

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
        System.out.println(s4);
        System.out.println(s5);
        System.out.println(s6);
        System.out.println(s7);
        System.out.println(s8);
    }
}
