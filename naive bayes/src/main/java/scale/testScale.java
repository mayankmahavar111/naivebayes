package scale;

import java.io.IOException;
import java.util.ArrayList;

import wordUtils.*;
import svm.*;
import txt.*;

public class testScale {
	static ArrayList<String> words= new ArrayList<String>();
	static ArrayList<String> read =new ArrayList<String>();
	public static void main(String args[]) throws IOException{
		String path = "data/test/TotalWords.txt";
		read=swapSpace(svm.trainStackOverflow.readFile(path));
		words=wordUtils.WordCount.DistinctWords(read);
		//System.out.println(read.get(0).toString().equals("work")+ " " + read.get(0));
		txt.write.buffer(words, "test/distinctWords.txt");
		System.out.println("\nSuccess");
	}
	public static ArrayList<String> swapSpace(ArrayList<String> test){
		ArrayList<String> temp = new ArrayList<String>();
		String [] x = null;
		for(int i=0; i<test.size();i++)
		{
			x=test.get(i).split(" ");
			for(int j=0;j<x.length;j++)
			{
				temp.add(x[j].toString());
			}
		}
		return temp;
	}
}
