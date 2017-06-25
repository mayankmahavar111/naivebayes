package test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import txt.*;

public class trainNew {
	
	private static ArrayList<String> read = new ArrayList<String>();
	private static ArrayList<String> Text = new ArrayList<String>();
	private static ArrayList<String> write = new ArrayList<String>();
	public static FileReader fr;
	private static BufferedReader br;
	
	
	
	
	public static void trainModel() throws IOException{
		String path="data/trial_1.txt";
		read=readFile(path);
		Text=readFile("data/test/distinctWords.txt");
		//System.out.println(read);
		String [] x = null;
		String s=null,temp=null,temp2;
		int count;
		for(int i=0;i<read.size();i++){
			x=(read.get(i).toString()).split(" ");
			s="0 ";
			temp2="";
			count=1;
			for(int j=0;j<x.length;j++){
				System.out.println("Counting : " + k++) ;
				temp=find(x[j],Text).toString();
				if(!temp.equals("0")){
					count=feature(x,j);	
					//System.out.println(" "+ count);
					temp2+=temp+":"+count+" ";
					if(count>1){
						x=removeIndex(x,j).split(" ");
					}
				}
			}
			if(temp2!="")
			write.add(s+ascending(temp2));			
		}
		System.out.println(write);
		txt.write.buffer(write, "test/testTrain.txt");
		System.out.print("\nSuccess\n");
	}
	static int k=1;
	//private static String [] tests;
	public static String find(String Key, ArrayList<String> test) throws IOException{
		//FileReader fr = new FileReader(path);
		//BufferedReader br= new BufferedReader(fr);
		String [] x =null;
		
		int i=0;
		String s=test.get(0);
		try{
			while(i<test.size()-1){
				x=s.split(" ");
				if(Key.equalsIgnoreCase(x[2].trim())) 
					return x[0];
				i++;
				s=test.get(i);
			}
		}

		finally{
			try{
				if(br!=null)
					br.close();
				if(fr!=null)
					fr.close();
			}
			catch(IOException e ){
				e.printStackTrace();
			}
		}
		return "0";
	}
	public static ArrayList<String> readFile(String path){
		ArrayList<String> test= new ArrayList<String>();
		try{
			fr = new FileReader(path);
			br = new BufferedReader(fr);
			String[] x = null;
			int i=0;
			String s= "";
			while((s=br.readLine()) != null){
				 test.add(s);
				 //System.out.print("\nCounting :"+i++);
			}
			
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
		return test;
	}
	public static int feature(String [] array , int index){
		int count=1;
		for(int i=index+1;i<array.length;i++){
			if(array[i].equalsIgnoreCase(array[index])){
				count++;
				}
		}
		return count;
	}
	public static String removeIndex(String [] array ,  int index){
		
		String tests = "";
		for(int i=0;i<index;i++){
			tests+=array[i]+" ";
		}
		//int j=index;
		for(int i=index+1;i<array.length-1;i++){
			if(!array[i].equalsIgnoreCase(array[index])){
				tests+=array[i];
			//	j++;
			}
		}
		
		return tests;
	}
	public static void main(String args[]) throws IOException{
		trainModel();
		//ascending("56:1 16:2 4706:1 646:1 121:2 436:1 1066:1 119:3 2240:1" );
		System.out.println("Hello World");
	}
	public static String ascending(String test){
		test=test+" ";
		HashMap<Integer, Integer> m = new HashMap<Integer,Integer>();
		String [] x = test.split(" ");
		String [] temp;
		for(int i=0;i<x.length;i++){
			temp=x[i].split(":");
			m.put(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
		}
		//System.out.println(m + " " + m.size());
		x=test.split("[: ]");
		//ArrayList<Integer> number = new ArrayList<Integer>();
		int [] number=new int[x.length/2];
		int j=0;
		for(int i=0;i<x.length;i+=2){
			number[j]=Integer.parseInt(x[i]);
			j++;
		}
		Arrays.sort(number);
		//System.out.println(" " + Arrays.toString(number) + " " + number.length);
		//test=String.join(":1 " , Arrays.toString(number));
		test="";
		for(int i=0;i<number.length;i++){
			test+=number[i]+":"+getFeature(number[i],m)+" ";
		}
		//System.out.println(test);
		return test;
	}
	public static int getFeature(int key,HashMap <Integer, Integer> m){
		return m.get(key);
	}
	
}
