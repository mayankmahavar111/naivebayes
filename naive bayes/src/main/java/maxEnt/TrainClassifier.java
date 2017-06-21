package maxEnt;

import java.io.IOException;

import edu.stanford.nlp.classify.Classifier;
import edu.stanford.nlp.classify.ColumnDataClassifier;
import edu.stanford.nlp.io.IOUtils;

public class TrainClassifier {

	public static void main(String[] args) throws IOException
	{
		ColumnDataClassifier cdc = new ColumnDataClassifier(
				"src/main/resources/properties.prop");
		//System.out.println("Reading training file");
		Classifier<String, String> cl = cdc.makeClassifier(cdc.readTrainingExamples(
				"src/main/resources/labelled_data.txt"));
		IOUtils.writeObjectToFile(cl, "src/main/resources/TrainedClassifier");
	}
}
