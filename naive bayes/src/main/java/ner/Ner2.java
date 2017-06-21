package ner;


import java.util.*;
import java.io.*;
import edu.stanford.nlp.*;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

public class Ner2 {
	public static ArrayList<String> Words = new ArrayList<String>();
	public static ArrayList<String> Count = new ArrayList<String>();
	public static ArrayList<String> Input(String text) throws FileNotFoundException, IOException{
		String x= "data/"+text;
		Scanner sc = new Scanner(new File(x));
		ArrayList<String> sentence = new ArrayList<String>();
		while(sc.hasNextLine())
		sentence.add(sc.nextLine());
		//System.out.println("LINE PASSED"+text);
		for(int i=0;i<sentence.size();i++){
			
			String s = sentence.get(i).toString();
			//System.out.println(s);
			ArrayList<String> obj = new ArrayList<String>();
			obj= processLine(s);
		}
		sc.close();
		
		return Words;
	}
	
	
	public static ArrayList<String> identifier(Annotation annotation) throws FileNotFoundException, IOException{
		ArrayList<String> words = new ArrayList<String>();
		for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)){
			for(CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)){
				
				String word = token.get(CoreAnnotations.TextAnnotation.class);
				String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
				String ner = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);
				String lemma = token.get(CoreAnnotations.LemmaAnnotation.class);
				if (ner.equals("O") && StopWord(word.toLowerCase())){
					Words.add(lemma);
					System.out.println("\nPos: "+pos+"\nner:"+ner+"\nlemma:"+lemma+"\nword:"+word);
				}
			}
		}
		return words;
		
	}
	
	
	public static ArrayList<String> processLine(String line) throws FileNotFoundException, IOException {

		StanfordCoreNLP pipeline = createPipeline();
		Annotation annotation = pipeline.process(line);
		return identifier(annotation);
		
	}

	private static StanfordCoreNLP createPipeline() {

		Properties props = createPipelineProperties();

		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

		return pipeline;

	}

	private static Properties createPipelineProperties() {

		Properties props = new Properties();
		props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner");

		return props;

	}
	public static boolean StopWord(String key) throws IOException,FileNotFoundException{
		String x= "data/stopWords.txt";
		Scanner sc = new Scanner(new File(x));
		while(sc.hasNext()){
			if (key.equals(sc.next()))
				return false;
		}
		return true;
	}
	


	public static void main(String args[]) throws FileNotFoundException, IOException{
		Count=Input("test.txt");
		//txt.write.buffer(Count,"adject.txt");
		System.out.print("Success");
	}
}
