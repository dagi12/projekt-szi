package pl.edu.amu.wmi.projekt_szi.decision;

import pl.edu.amu.wmi.projekt_szi.movement.Waiter;
import pl.edu.amu.wmi.projekt_szi.priority.Table;

import java.util.TreeMap;

public class WaiterDecisionController {


    private TreeMap<String, LevelledDecision> decisions;

    private DecisionEvaluator decisionEvaluator;

    private Table servedTable;

    private Waiter waiter;

    public WaiterDecisionController(Waiter waiter) {
        this.waiter = waiter;
        decisions = new TreeMap<>();
        decisionEvaluator = new DecisionEvaluator();
        decisions.put("TIME", LevelledDecision.UNKNOWN);
    }

    public void setServedTable(Table servedTable) {
        this.servedTable = servedTable;
    }

    public void makeServeDecision() {
        LevelledDecision l = decisionEvaluator.classifyTableDecision(servedTable.getRichness(), servedTable.getTime());
        System.out.println("irrDec: " + l);
        switch (l) {
            case HEAVY:
                serve();
                break;
            case MEDIUM:
                serve();
                break;
            case LIGHT:
                serve();
                break;
            case NO:
                break;
            default:
                break;
        }
        decisions.put("TIME", l);
    }

    private void serve() {

    }

    public TreeMap<String, LevelledDecision> getDecisions() {
        return this.decisions;
    }


}
