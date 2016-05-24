package pl.edu.amu.wmi.projekt_szi.decision;

import pl.edu.amu.wmi.projekt_szi.movement.Waiter;
import pl.edu.amu.wmi.projekt_szi.priority.Table;

import java.util.TreeMap;

public class WaiterDecisionController {


    private final TreeMap<String, LevelledDecision> decisions;

    private final DecisionEvaluator decisionEvaluator;

    private Table servedTable;

    public WaiterDecisionController(Waiter waiter) {
        decisions = new TreeMap<>();
        decisionEvaluator = new DecisionEvaluator();
        decisions.put("TIME", LevelledDecision.UNKNOWN);
    }

    public void setServedTable(Table servedTable) {
        this.servedTable = servedTable;
    }

    public void makeServeDecision() {
        LevelledDecision levelledDecision = decisionEvaluator.classifyTableDecision(servedTable.getRichness(), servedTable.getTime());
        switch (levelledDecision) {
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
        decisions.put("TIME", levelledDecision);
    }

    private void serve() {
        servedTable.setWaiting(false);
    }

    public TreeMap<String, LevelledDecision> getDecisions() {
        return this.decisions;
    }


}
