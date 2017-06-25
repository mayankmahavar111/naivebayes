package scale;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import wordUtils.*;

public class dataScaling {
	public static ArrayList<String> sentence=new ArrayList<String>();
	public static void scale() throws FileNotFoundException, IOException{
		ArrayList<Integer> pos = new ArrayList<Integer>();
		ArrayList<Integer> neg = new ArrayList<Integer>();
		FileReader fr= null;
		BufferedReader br=null;
		FileWriter fw =null;
		BufferedWriter bw=null;
		int max,min;
		//posFile=wordUtils.WordCount.totalWord("data/LEMMANewPositiveModified.txt");
		//negFile=wordUtils.WordCount.totalWord("data/LEMMANewNegativeModified.txt");
		try{
			String path="data/LEMMANewPositiveModified.txt";
			fr = new FileReader(path);
			br = new BufferedReader(fr);
			String[] x = null;
			int i;
			String s= "";
			while((s=br.readLine()) != null){
				sentence.add(s);
				 x= s.split(" ");
				for( i=0;i<x.length;i++)
					{
					neg.add((int) wordUtils.WordCount.wordCount(x[i], "TotalFile.txt"));
					};
				
			}
			/*path="data/LEMMANewNegativeModified.txt";
			fr = new FileReader(path);
			br=	new BufferedReader(fr);
			while((s=br.readLine())!=null)
			{
				x=s.split(" ");
				for(i=0;i<x.length;i++)
				{
					neg.add((int) wordUtils.WordCount.wordCount(x[i], "TotalFile.txt"));
				}
			}*/
			System.out.println("Counting done\n");
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
		max=wordUtils.WordCount.findMax(neg);
		min=wordUtils.WordCount.findMin(neg);
		System.out.println(""+max + " "+min);
		try{
			String path="data/positiveScale.txt";
			fw = new FileWriter(path);
			bw = new BufferedWriter(fw);
			String[] x = null;
			int i,j=0,k=0;
			String s= "";
			for(j=0;j<sentence.size();j++){
				s=sentence.get(j).toString();
				 x= s.split(" ");
				 bw.write("1 ");
				for( i=0;i<x.length;i++)
					{
					bw.write(i+":"+getScale(max,min,0,1,neg.get(k))+" ");
					k++;
					};
					bw.write("\n");
			
			}
			System.out.println("Success\n");
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
	}
	public static float getScale(float max ,float min,float a, float b,float x)
	{
		float res;
		res=(b-a)*(x-min)/(max-min);
		return res;
	}
	public static void main(String args[]) throws FileNotFoundException, IOException{
		scale();
	}
}
