package txt;

import java.util.ArrayList;
import java.io.*;

public class write {

	public static void buffer(ArrayList<String> word, String path) throws IOException{
		String filename= "data/"+path;
		BufferedWriter bw = null;
		int j=0;
		FileWriter fw =null;
		try{
			fw=new FileWriter(filename);
			bw=new BufferedWriter(fw);
			String s=null;
			for(int i=0;i<word.size();i++)
			{
				//s=word.get(i).toString().split(" ");
				s=word.get(i).toString();
				//System.out.print(s[1]+"\n");
				//for (int k=0;k<s.length;k++){
					//bw.write(j+" = "+s[k]+"\n");
				bw.write(s+"\n");
				j++;
					System.out.println("\nCounting : "+j);
				//}
			//System.out.println(word);
			}
		}
		finally{
			try{
				if(bw!=null)
					bw.close();
				if(fw!=null)
					fw.close();
			}
			catch(IOException e ){
				e.printStackTrace();
			}
		}
	}
}
