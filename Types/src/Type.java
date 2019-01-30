
public class Type {

	public static void main(String[] args) {
		
		int x = 5;
		int y = x / 3;
		double d = 5;
		double e = d / 3;
		double f = x / 3;
		int g = (int)e;			// type moins fort : conversion explicite nécessaire
		char r = 'a';
		int s = r;
		int t = 98;
		char u = (char)t;		// type moins fort : conversion explicite nécessaire
		
		String s1 = "y vaut " + y;
		String s2 = "e vaut " + e;
		String s3 = "f vaut " + f;
		String s4 = "g vaut " + g;
		String s5 = "r vaut " + r;
		String s6 = "s vaut " + s;
		String s7 = "t vaut " + t;
		String s8 = "u vaut " + u;

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
