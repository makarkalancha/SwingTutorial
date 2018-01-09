package p001_100;

public class test {
	int i=0;
	public test(){ i=8;}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		int i = 0;
//		test h = new test();
//		while(h.i <=10){
//			h.doit();
//		}
		
//		int i1 =2;
//		int i2 = 5;
//		double d;
//		d = 3+i1 / i2 + 2;
//		System.out.println("d="+d);
		
//		StringBuffer[] mess = new StringBuffer[5];
////		mess[0] = new StringBuffer();
//		mess[0].append("hello");
//		System.out.println("fisr "+mess[0]);
//		
//		long m = 3L;
//		float f = 2.1f;
//		int i = 5;
//		System.out.println("result: "+m+f+i);
		
		String x = "> This is a test <";
		fix(x);
		System.out.println(x);
	}

//	public static void doit(){
//		i++;
//		System.out.println("heloo");
//	}
	
	static void fix(String s){
		String t = s;
		t=t.trim();
		t = t.replace(' ', '_');
		s=t;
				
	}
}
