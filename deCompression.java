package Compressor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class deCompression {

	public static String giveBinary(int dec){
		int temp=dec;
		int count=0;
		String ans="";
		while(temp>0){
			ans=temp%2 + ans;
			temp=temp/2;
			count++;
		}
		while(count<8){
			ans="0"+ans;
			count++;
		}
		return ans;
	}
	public static String deComprString() throws IOException{
		FileInputStream fis = new FileInputStream("C:\\Users/Antariksh Goyal/Desktop/check");
		int i = 0;
		char c;
		byte[] bs = new byte[30];
		i=fis.read(bs);
		String str="";
		String ans="";
		for(byte b:bs)
		{
			int x=(b & 0xff);
			String a=giveBinary(x);
			ans+=a;
			System.out.print(b+ " "+x+"  "+a);
			System.out.println();
			fis.close();
		}
		return ans;
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		deComprString();
	}

}
