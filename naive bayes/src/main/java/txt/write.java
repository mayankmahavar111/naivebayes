package txt;

import java.util.ArrayList;
import java.io.*;

public class write {

	public static void buffer(ArrayList<String> word, String path) throws IOException{
		String filename= "data/"+path;
		BufferedWriter bw = null;
		FileWriter fw =null;
		try{
			fw=new FileWriter(filename);
			bw=new BufferedWriter(fw);
			String s="";
			for(int i=0;i<word.size();i++)
			{
				bw.write(word.get(i)+"\n");
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
