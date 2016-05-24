package pl.edu.amu.wmi.projekt_szi.decision;

import pl.edu.amu.wmi.projekt_szi.priority.Table;
import weka.classifiers.Classifier;
import weka.classifiers.trees.LMT;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

class DecisionEvaluator {

    private static final String FILE_DECISION_MAKER_PATH = "res/decisionMaker.arff";

    private final Classifier decisionTree;

    private Instances decisionTrainSet;

    public DecisionEvaluator() {

        DataSource decisionMakerDataSource = null;
        try {
            decisionMakerDataSource = new DataSource(FILE_DECISION_MAKER_PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            decisionTrainSet = decisionMakerDataSource.getDataSet();
        } catch (Exception e) {
            e.printStackTrace();
        }
        decisionTrainSet.setClassIndex(2);
        decisionTree = new LMT();
        // decisionTree = new J48();
        try {
            decisionTree.buildClassifier(decisionTrainSet);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public LevelledDecision classifyTableDecision(Table.Richness richness,
            double time) {
        Instance currentInstance = new DenseInstance(4);
        currentInstance.setValue(0, time);
        currentInstance.setValue(1, richness.getValue());
        currentInstance.setMissing(2);
        currentInstance.setDataset(decisionTrainSet);
        double toReturn = 0;
        try {
            toReturn = decisionTree.classifyInstance(currentInstance);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return LevelledDecision.getEnumFromInt((int) toReturn);
    }


}