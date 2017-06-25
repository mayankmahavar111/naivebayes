package wordUtils;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class WordCount {
	private static BufferedReader br ;
	private static FileReader fr ;
	private static Scanner sc;
	private static Scanner sc2;
	public static double totalWord(String path) throws FileNotFoundException, IOException
	{
		int count=0;
		try{
			fr = new FileReader(path);
			br = new BufferedReader(fr);
			String[] x = null;
			int i;
			String s= "";
			while((s=br.readLine()) != null){
				 x= s.split(" ");
				for( i=0;i<x.length;i++)
					{
					//System.out.print(""+x[i] );
					
					}
				count+=i;
				//System.out.println("\n");
			}
			//System.out.println(" "+count);
			
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
		return count;
	}
	public static double wordCount(String Key,String path) throws FileNotFoundException, IOException{
		int count=0;
		String x= "data/"+path,y;
		//System.out.println("Counting : "+i++);
		sc = new Scanner(new File(x));
		try{
			while(sc.hasNext()){
				y=sc.next();
				if (Key.equalsIgnoreCase(y.toString()))
					count++;
			}
		}
		finally {

			if (sc != null)
				sc.close();

		}
		System.out.print("Wordcount: "+count+"\t");
		return count;
	}
	public static boolean Search(String Key , String path )throws FileNotFoundException, IOException{
		
		String x= "data/"+path,y;
		sc2 = new Scanner(new File(x));
		try{
			while(sc2.hasNext()){
				y=sc2.next();
				if (Key.equalsIgnoreCase(y.toString()))
					return true;
			}
		}
		finally {

			if (sc2 != null)
				sc2.close();

		}
		return false;
		
	}
	public static ArrayList DistinctWords(ArrayList<String> word){
		String Key=word.get(0).toString();
		for(int i=0;i<word.size();i++)
		{
			Key=word.get(i).toString().trim();
			for(int j=0;j<word.size();j++)
			{
				if(Key.equalsIgnoreCase(word.get(j).toString())&&i!=j)
					word.remove(j);
			}
		}
		return word;
	}
	public static int findMax(ArrayList<Integer> count ){
		int max=count.get(0);
		for (int i=1;i<count.size();i++)
		{
			if(max<count.get(i))
				max=count.get(i);
		}
		return max;
	}
	public static int findMin(ArrayList<Integer> count ){
		int min=count.get(0);
		for (int i=1;i<count.size();i++)
		{
			if(min>count.get(i))
				min=count.get(i);
		}
		return min;
	}
	/*public static void main(String args[]) throws FileNotFoundException, IOException{
		String neg ="C:\\Users\\Hp\\Desktop\\newnegative.txt";
		double x = totalWord(neg);
	}*/
}
