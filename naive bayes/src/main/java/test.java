import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class test {
	private static BufferedReader br ;
	private static FileReader fr ;
	static double correct=0;
	static double incorrect=0;

	public static void efficiencey(String key,String sentence) throws IOException {
		//String filename= "data/test.txt";
		
		try{
			//fr = new FileReader(filename);
			//br = new BufferedReader(fr);
			//
			//while((s=br.readLine())!=null)
			//{
				if (sentence.charAt(sentence.length()-1)=='0'&& key.equals("negative")||sentence.charAt(sentence.length()-1)=='1'&& key.equals("positive"))
					correct+=1;
				else
					incorrect+=1;
			//}
			//System.out.print("\n"+negative+"\n"+positive);
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
	}
	public static void CalculateEfficiency(){
		System.out.println(((correct)*100)/(correct+incorrect)+"%");
	}
}


