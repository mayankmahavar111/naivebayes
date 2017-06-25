package svm;

import java.awt.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import txt.*;

public class trainStackOverflow {

	public static ArrayList<String> bagOfWords =new ArrayList<String>(); 
	public static ArrayList<String> dataSet =new ArrayList<String>();
	public static ArrayList<String> negData =new ArrayList<String>();
	public static ArrayList<String> posData =new ArrayList<String>();
	private static ArrayList<String> write =new ArrayList<String>();
	public static FileReader fr;
	private static BufferedReader br; 
	public static void main(String...  arg) throws IOException{ 

		/*dataSet=readFile("data/TotalFile.txt");
		 bagOfWords = prepareBOW(dataSet);*/ // Provide dataset 
		 negData=readFile("data/NewNegativeModified.txt");
		  write=prepareSentimentalSentencesList(negData, "0 ");
		   //txt.write.buffer(write, "negativeTrain.txt");
		//posData=readFile("data/NewPositiveModified.txt");
			 
		  // write=prepareSentimentalSentencesList(posData, "1 ");
		  // txt.write.buffer(write, "positiveTrain.txt");
		   //System.out.println("Successs");
	

		}


		/*public static ArrayList<String> prepareBOW(ArrayList<String> dataSet) {

		    bagOfWords = new ArrayList<String>();

		    // iterating each and every set of data/sentence.
		    for (String s : dataSet) {

		        String[] words = s.split(" ");
		        bagOfWords.add("*&^(0");


		        // adding each word of sentence/data in list.
		        for (int i = 0; i < words.length; i++) {
		            words[i] = words[i].replaceAll(",", "");
		            words[i] = words[i].replaceAll(" ", "");
		            words[i] = words[i].replaceAll("\\.", "");
		            words[i] = words[i].toLowerCase();
		            bagOfWords.add(words[i]);

		        }

		    }
		    bagOfWords.remove("");
		    bagOfWords = new ArrayList<String>(new LinkedHashSet<String>(bagOfWords));// Removing duplicates.

		    return bagOfWords;

		}*/

		public static ArrayList<String> prepareSentimentalSentencesList(ArrayList<String> dataSet, String label) {
		        ArrayList<String> list = new ArrayList<String>();
		        int i=1;
		        for (String data : dataSet) {

		        String wordsIndex = label;
		        for (String word : data.split(" ")) {
		            word = word.replaceAll(",", "");
		            word = word.replaceAll(" ", "");
		            word = word.replaceAll("\\.", "");
		            word = word.toLowerCase();
		            int index = getIndex(word);
		            if (index != -1) {
		                wordsIndex += (index+1) + ":1 ";
		            }

					 System.out.print("\nCounting :"+i++);

		        }
		        list.add(wordsIndex);
		    }

		    for (String s : list) {
		          System.out.println(s);
		    }
		    return list;
		}
		public static ArrayList readFile(String path){
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
		public static int getIndex(String key){
			
			try{
				fr = new FileReader("data/TotalFile.txt");
				br = new BufferedReader(fr);
				String[] x = null;
				int i,j=0;
				String s= "";
				while((s=br.readLine()) != null){
					x=s.split(" ");
					 for(i=0;i<x.length;i++)
					 {
						 if(key.equalsIgnoreCase(x[i]))
							 return j;
					 }
					 j++;
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
			return 1;
		}
}
