package ner;


import java.util.*;
import java.io.*;
import edu.stanford.nlp.*;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

public class Ner {
	public static ArrayList<String> Words = new ArrayList<String>();
	private static Map<String, String> abbreviation = null;
	private static int count=0;
	public static ArrayList<String> Input(String text) throws FileNotFoundException, IOException{
		//String x= "data/"+path;
		//Scanner sc = new Scanner(new File(x));
		ArrayList<String> sentence = new ArrayList<String>();
		//while(sc.hasNextLine())
		sentence.add(text);
		//System.out.println("LINE PASSED"+text);
		//for(int i=0;i<sentence.size();i++){
			
			String s = sentence.get(0).toString();
			//System.out.println(s);
			ArrayList<String> obj = new ArrayList<String>();
			obj= processLine(s);
		//}
		
		
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
					Words.add(initAbbreviation(lemma));
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
		System.out.println("\n"+(++count));

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
	public static void main(String args[]){
		System.out.println();
	}
	private static String initAbbreviation(String key) {
		abbreviation = new HashMap<String, String>();
		abbreviation.put("lol", " laugh out loud");
		abbreviation.put("r", " are");
		abbreviation.put("re", " are");
		abbreviation.put("m", " am");
		abbreviation.put("d", " would");
		abbreviation.put("ve", " have");
		abbreviation.put("n"," and");
		abbreviation.put("u", " you");
		abbreviation.put("s", " is");
		abbreviation.put("t", " not");
		abbreviation.put("k", " okay");
		abbreviation.put("ll", " will");
		abbreviation.put("nd", "and");
		abbreviation.put("dm", " direct message");
		abbreviation.put("mt", " modified tweet");
		abbreviation.put("rt", " retweet");
		abbreviation.put("cc", " carbon copy");
		abbreviation.put("tc", " take care");
		abbreviation.put("ur", "your");
		abbreviation.put("hv", " have");
		abbreviation.put("sh", " shit happens");
		abbreviation.put("gm", " good morning");
		abbreviation.put("gn", " good night");
		abbreviation.put("fb", " Facebook");
		abbreviation.put("b4", " before");
		abbreviation.put("2b", " to be");
		abbreviation.put("l8", " late");
		abbreviation.put("ty", " thank You");
		abbreviation.put("abt", " about");
		abbreviation.put("exp", " experience");
		abbreviation.put("mrt", " modified retweet");
		abbreviation.put("idk", " I don't know");
		abbreviation.put("ikr", " I know right");
		abbreviation.put("tbh", " to be honest");
		abbreviation.put("brb", " be right back");
		abbreviation.put("bbs", " be back soon");
		abbreviation.put("omg", " Oh my God");
		abbreviation.put("amp", " and");
		abbreviation.put("diy", " do it yourself");
		abbreviation.put("sup", " what is up");
		abbreviation.put("btw", " by the way");
		abbreviation.put("fyi", " for your information");
		abbreviation.put("wth", " what the hell");
		abbreviation.put("plz", " please");
		abbreviation.put("pls", " please");
		abbreviation.put("gr8", " great");
		abbreviation.put("sry", " sorry");
		abbreviation.put("bff", " best friends forever");
		abbreviation.put("fam", " family");
		abbreviation.put("fav", "favourite");
		abbreviation.put("ama", " ask me anything");
		abbreviation.put("bae", " before anyone else");
		abbreviation.put("imo", " in my opinion");
		abbreviation.put("irl", " in real life");
		abbreviation.put("smh", " shaking my head");
		abbreviation.put("tbt", " throwback thursday");
		abbreviation.put("fbf", " flashback friday");
		abbreviation.put("cust", " customer");
		abbreviation.put("govt", " government");
		abbreviation.put("rofl", " rolling on the floor laughing");
		abbreviation.put("ttyl", " talk to you later");
		abbreviation.put("asap", " as soon as possible");
		abbreviation.put("nsfw", " Not Safe For Work");
		abbreviation.put("nsfl", " not safe for life");
		abbreviation.put("ftfy", " fixed that for you");
		abbreviation.put("lmao", " laugh my ass off");
		abbreviation.put("jsyk", " just so you know");
		abbreviation.put("yolo", " you only live once");
		abbreviation.put("icymi", " in case tou missed it");
		abbreviation.put("ilysm", " i love you so much");
		for (int i=0;i<abbreviation.size();i++)
		{
			if(abbreviation.containsKey(key))
				return abbreviation.get(key);
		}
		return key;
	}
	
}
