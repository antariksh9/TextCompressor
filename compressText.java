package Compressor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import BSTAssignment.BinaryTreeNode;
public class compressText {

	public static HashMap<Character, Integer> giveFreq(String text){
		HashMap<Character, Integer> freqMap= new HashMap<Character, Integer>();
		for(int i=0;i<text.length();i++){
			char a=text.charAt(i);
			if(freqMap.containsKey(a)){
				freqMap.put(a, freqMap.get(a)+1);
			}
			else{
				freqMap.put(a,1);
			}
		}
		return freqMap;
	}
	public static BinaryTreeNode<Character> makeTree(Heap<BinaryTreeNode<Character>> freqHeap){
		BinaryTreeNode<Character> newNode=new BinaryTreeNode<Character>();
		while(freqHeap.size()!=0){
			PQNode<Integer, BinaryTreeNode<Character>> node1 , node2;
			if(freqHeap.size()>0)
				node1=freqHeap.removeMin();
			else
				break;
			if(freqHeap.size()>0)
				node2=freqHeap.removeMin();
			else
				break;
			newNode= new BinaryTreeNode<Character>();
			newNode.left=node1.value;
			newNode.right=node2.value;
			int newValue=node1.priority+node2.priority;
			freqHeap.insert(newValue, newNode);
		}
		return newNode;
	}
	public static HashMap<Character, String> printFreqTree(BinaryTreeNode<Character> root,HashMap<BinaryTreeNode<Character>,String> map,HashMap<Character, String> finalMap){
		if(root.left==null && root.right==null){
			//System.out.println(root.data+"  "+map.get(root));
			finalMap.put(root.data, map.get(root));
			return finalMap;
		}
		String value1=map.get(root)+"0";
		String value2=map.get(root)+"1";
		map.put(root.left, value1);
		map.put(root.right, value2);
		finalMap=printFreqTree(root.left,map,finalMap);
		finalMap=printFreqTree(root.right,map,finalMap);
		return finalMap;

	}
	public static HashMap<String, Character> reverseMap = new HashMap<String, Character>();
	public static String codedString(String text){
		HashMap<Character, Integer> map=giveFreq(text);

		Heap<BinaryTreeNode<Character>> priority= new Heap<>();
		for(Character c:map.keySet()){
			int value=map.get(c);
			BinaryTreeNode<Character> node= new BinaryTreeNode<Character>();
			node.data=c;
			priority.insert(value,node);
		}
		BinaryTreeNode<Character> tree=makeTree(priority);
		HashMap<BinaryTreeNode<Character>, String> codeMap= new HashMap<BinaryTreeNode<Character>, String>();
		codeMap.put(tree, "");
		HashMap<Character,String> finalMap= new HashMap<Character, String>(); 
		finalMap=printFreqTree(tree,codeMap,finalMap);
		for(Character a :finalMap.keySet()){
			reverseMap.put(finalMap.get(a), a);
		}
		int length=text.length();
		String newFile="";
		for(int i=0;i<length;i++){
			char a=text.charAt(i);
			newFile+=finalMap.get(a);
		}
		return newFile;

	}
	public static int decToBinary(int num){
		int ten=0;
		int ans=0;
		while(ten<=7){
			int temp=num%10;
			ans=ans+(int)(temp*Math.pow(2, ten));
			num=num/10;
			ten++;
		}
		return ans;
	}
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
		byte[] bs = new byte[66];
		i=fis.read(bs);
		String str="";
		String ans="";
		for(byte b:bs)
		{
			int x=(b & 0xff);
			String a=giveBinary(x);
			ans+=a;
			//System.out.print(b+ " "+x+"  "+a);
			//System.out.println();
			fis.close();
		}
		return ans;
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//		String text="aaaaaaaaaaaaaaaaaaaaaaaabbbbbbbbbbbbccccccccccddddddddeeeeeeee";
		String file_name="C:/Users/Antariksh Goyal/Desktop/antariksh.txt";
		FileContentReader file= new FileContentReader(file_name);
		String[] content=file.openFile();
		//System.out.println(priority.giveMin().priority + " " + priority.giveMin().value.data);
		String combined="";
		for(int i=0;i<content.length;i++){
			combined+=content[i];
		}
		//System.out.println(combined);
		String answer=codedString(combined);
		String temp=answer;
		String finalStringAns="";
		FileOutputStream fos = new FileOutputStream("C:\\Users/Antariksh Goyal/Desktop/check");
		int x=0;
		while(temp.length()>0){
			int count=1;
			String decNo="";
			while(count<=8){
				if(temp.length()>0)
					decNo+=temp.charAt(0);
				else
					break;
				temp=temp.substring(1);
				count++;
			}
			while(decNo.length()<8){
				decNo="0"+decNo;
			}
			//System.out.println(decNo);
			int dec= Integer.parseInt(decNo);
			//System.out.println(dec);
			int bin=decToBinary(dec);
			//System.out.println(bin);
			byte a=(byte)bin;
			System.out.println(a);
			fos.write(a);
			finalStringAns+=a;
		}
//		for(String s : reverseMap.keySet()){
//			System.out.println(s+ " ..... "+ reverseMap.get(s));
//		}
		//System.out.println(finalStringAns);
		String decomp=deComprString();	
		String printDecom="";
		String decomTemp="";
		for(int i=0,j=1;i<decomp.length() && i+j<=decomp.length();){
				if(reverseMap.containsKey(decomp.substring(i, j+i))){
					decomTemp=decomTemp+reverseMap.get(decomp.substring(i, j+i));
					i=j+i;
					j=1;
				}
				else{
					j++;
				}
			
			//printDecom=printDecom+decomTemp;
		}
		printDecom=decomTemp;
		System.out.println(printDecom);
	}

}