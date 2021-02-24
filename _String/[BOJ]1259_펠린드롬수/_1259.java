import java.io.*;

public class _1259 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuffer sb = new StringBuffer();
		
		String[] s = br.readLine().split("");
		if(s[0].equals("0"))	System.exit(0);
		sb.append(ChkYesorNo(s));
		
		while(true) {
			s = br.readLine().split("");
			if(s[0].equals("0"))	break;
			sb.append("\n");
			sb.append(ChkYesorNo(s));
		}
		
		System.out.print(sb.toString());
	}

	private static String ChkYesorNo(String[] s) {
		int len = s.length;
		
		for(int i=0; i<len/2; i++) {
			if(!s[i].equals(s[len-1-i])) {
				return "no";
			}
		}
		
		return "yes";
	}

}
