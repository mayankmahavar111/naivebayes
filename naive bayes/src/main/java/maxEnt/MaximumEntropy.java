package maxEnt;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import edu.stanford.nlp.classify.Classifier;
import edu.stanford.nlp.classify.ColumnDataClassifier;
import edu.stanford.nlp.io.IOUtils;
import edu.stanford.nlp.ling.Datum;
import junit.framework.Test;

public class MaximumEntropy {

	public static ColumnDataClassifier cdc = new ColumnDataClassifier(
			"src/main/resources/properties.prop");
	
	public static float calMaxEntropySentiment(String tweet) {
		
		System.out.println("Calling maximum entropy");
		return getMaxEntropySentiment(tweet);
	}

	public static int getMaxEntropySentiment(String tweet) {

		String filteredTweet = tweet;
		filteredTweet = filteredTweet.trim();
		Classifier<String, String> cl = null;
		try {
			cl = IOUtils
					.readObjectFromFile("src/main/resources/TrainedClassifier");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Datum<String, String> d = cdc.makeDatumFromLine(filteredTweet);
		System.out.println(filteredTweet + "  ==>  " + cl.classOf(d) + " " + cl.scoresOf(d)+""+d);
		if (cl.classOf(d).equals("0")) {
			System.out.println("Sentiment of max entropy is 0");
			return 0;
		} else {
			System.out.println("Sentiment of max entropy is 4");
			return 4;
		}
	}
	public static void main (String args[]) throws FileNotFoundException{
		int correct=0,incorrect=0;
		FileReader fr = new FileReader("data/test.txt");
		BufferedReader br = new BufferedReader(fr);
		try{
			String s= "";
			int j=0;
			while((s=br.readLine()) != null&&j<=10000){
				 if (calMaxEntropySentiment(s)==4){
					 System.out.println("Positive");
					 if(s.charAt(s.length()-1)=='1')
					 correct++;
					 else
						 incorrect++;
				 }
				 else{
					 System.out.println("Negative");
					 if(s.charAt(s.length()-1)=='0')
						 correct++;
					 else
							 incorrect++;
				 }
				 j++;
			}		
			System.out.print((correct*100/(correct+incorrect))+"\nSuccess");

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
}

}
