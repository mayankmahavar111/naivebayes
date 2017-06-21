import java.util.*;
import java.io.*;
import txt.write;
import ner.*;
import wordUtils.*;

public class NaiveBayes2 {
	private static BufferedReader br ;
	private static FileReader fr ;
	static String neg ="data/NewNegativeModified.txt";
	static String pos = "data/NewPositiveModified.txt";
	static ArrayList<String> Words = new ArrayList<String>();
	static ArrayList<String> count = new ArrayList<String>();
	static ArrayList<Double> pos_wordcount = new ArrayList<Double>();
	static ArrayList<Double> count_positive= new ArrayList<Double>();
	static ArrayList<Double> neg_wordcount = new ArrayList<Double>();
	static ArrayList<Double> count_negative= new ArrayList<Double>();
	static int pos_word_count, neg_word_count,diff_pos,diff_neg;
	static double Total_Pos_Words,Total_Pos,range;
	static double Total_Neg_Words,Total_Neg;
	
	public static void main (String args[])throws FileNotFoundException, IOException{
		try{
			fr = new FileReader("data/trial_1.txt");
			br = new BufferedReader(fr);
			 Total_Pos_Words=WordCount.totalWord(pos);
			 Total_Neg_Words=WordCount.totalWord(neg);
			String x = "";
			while((x=br.readLine()) != null){
				//System.out.println("Lines: "+x);
				 Words=ner.Ner2.Input(x);
				 //System.out.println(Words);
				 count.add(x);
				 diff_neg=5080;
				 diff_pos=3236;
				 for(int i=0;i<Words.size();i++)
				 {
					 if(Negation(i))
					 {
						 pos_wordcount.add(WordCount.wordCount((Words.get(i).toString()),"NewPositiveModified.txt"));
						 neg_wordcount.add(WordCount.wordCount((Words.get(i).toString()),"NewNegativeModified.txt"));
					 }
					 else
					 {
						 neg_wordcount.add(WordCount.wordCount((Words.get(i).toString()),"NewPositiveModified.txt"));
						 pos_wordcount.add(WordCount.wordCount((Words.get(i).toString()),"NewNegativeModified.txt"));
					 }
				
				 }
				 Total_Pos=totalPositiveProbality();
				 Total_Neg=totalNegativeProbality();
				 count_positive.add(Total_Pos);
				 count_negative.add(Total_Neg);
				 Words.clear();
				 pos_wordcount.clear();
				 neg_wordcount.clear();
				// System.out.println(""+count_positive.get(j)+" "+count_positive.get(j)+"\n");
			
	
			}
				for(int i=0;i<count.size();i++)
			{
				//System.out.print("\n"+count.get(i).toString());
				//System.out.print("\nDifference :" +Math.abs(count_positive.get(i)-count_negative.get(i)));
				//if((count_positive.get(i)-count_negative.get(i))<=1)
					//System.out.print("\n Neutral \n");
				//else{
					if((count_positive.get(i)>count_negative.get(i))){
						//System.out.print("\n Positive\n");
						test.efficiencey("positive",count.get(i));
					}
					else{
						test.efficiencey("negative",count.get(i));
						//System.out.print("\n Negative\n");
					}
				}
				test.CalculateEfficiency();
				
			//}
		}
		finally{
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
	static double PositiveWordProbability(double x, double y){
		return Math.log((x)/(x+y));
	}
	static double NegativeWordProbability(double x, double y){
		return Math.log((y)/(x+y));
	}
	public static double totalPositiveProbality() throws FileNotFoundException, IOException{
		double res=0;
		for(int i=0;i<Words.size();i++){
			//System.out.println("probword"+Words.get(i)+"word count"+pos_wordcount.get(i));
			if (WordCount.Search(Words.get(i).toString(),"negationWords.txt"))
				continue;
			if(i!=0&&WordCount.Search(Words.get(i-1).toString(),"adject.txt"))
				res=res + 2*Math.log((pos_wordcount.get(i)+1)/(Total_Pos_Words));
			else
			res=res + Math.log((pos_wordcount.get(i)+1)/(Total_Pos_Words));
		}
		//System.out.println(PositiveWordProbability(Total_Pos_Words,Total_Neg_Words)+res);
		return (PositiveWordProbability(Total_Pos_Words,Total_Neg_Words)+res);
	}
	public static double totalNegativeProbality() throws FileNotFoundException, IOException{
		double res=0;
		for(int i=0;i<Words.size();i++){
			//System.out.println("prob word"+Words.get(i)+"word count"+pos_wordcount.get(i));
			if (WordCount.Search(Words.get(i).toString(),"negationWords.txt"))
				continue;
			if(i!=0&&WordCount.Search(Words.get(i-1).toString(),"adject.txt"))
				res=res + 2*Math.log((neg_wordcount.get(i)+1)/(Total_Neg_Words));
			else
			res=res + Math.log((neg_wordcount.get(i)+1)/(Total_Neg_Words));
		}
		//System.out.println(NegativeWordProbability(Total_Pos_Words,Total_Neg_Words)+res);
		return (NegativeWordProbability(Total_Pos_Words,Total_Neg_Words)+res);
	}
	public static boolean Negation(int index) throws FileNotFoundException, IOException {
		
		if (index ==0)
			return true;
		if(WordCount.Search(Words.get(index-1).toString(),"negationWords.txt"))
			return false;
		return true;
	}
	
}