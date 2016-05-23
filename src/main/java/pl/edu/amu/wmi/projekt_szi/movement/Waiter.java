package pl.edu.amu.wmi.projekt_szi.movement;

import pl.edu.amu.wmi.projekt_szi.model.AbstractField;
import pl.edu.amu.wmi.projekt_szi.model.Location;
import pl.edu.amu.wmi.projekt_szi.priority.DecisionEvaluator;
import pl.edu.amu.wmi.projekt_szi.model.weather.Weather;
import pl.edu.amu.wmi.projekt_szi.model.weather.WeatherChanger;
import pl.edu.amu.wmi.projekt_szi.Application;
import pl.edu.amu.wmi.projekt_szi.priority.LevelledDecision;

import java.util.Observable;
import java.util.TreeMap;

public class Waiter extends AbstractField {

    public static final String IRRIGATION = "IRRIGATION";
    public static final String FERTILIZATION = "FERTILIZATION";
    public static final String NEW_TARGET = "NEW_TARGET";
    private volatile Location location;
    private TreeMap<String, LevelledDecision> decisions;
    private DecisionEvaluator decisionEvaluator;
    private volatile Location targetLocation;

    Waiter() {
        super(1, 1, FieldType.WAITER);
        decisions = new TreeMap<>();
        decisionEvaluator = new DecisionEvaluator();
        decisions.put(FERTILIZATION, LevelledDecision.UNKNOWN);
        decisions.put(IRRIGATION, LevelledDecision.UNKNOWN);
        targetLocation = new Location(0, 0);

    }

    public void makeIrrigationDecision(Field f) throws Exception {

        LevelledDecision l = decisionEvaluator.classifyIrrigation(f.getIrrigation(),
                weatherObserver.getWeather().getSunType(), weatherObserver.getWeather().getRain());
        if (Application.DEBUG) {
            System.out.println("irrDec: " + l);
        }
        switch (l) {
            case HEAVY:
                f.setIrrigation(f.getIrrigation() + 40);
                Thread.sleep(4000);
                break;
            case MEDIUM:
                f.setIrrigation(f.getIrrigation() + 25);
                Thread.sleep(2000);
                break;
            case LIGHT:
                f.setIrrigation(f.getIrrigation() + 10);
                Thread.sleep(1000);
                break;
            case NO:
                break;
            default:
                break;
        }
        decisions.put(IRRIGATION, l);
        setChanged();
        notifyObservers(IRRIGATION);
    }

    public void makeFertilizationDecision(Field f) throws Exception {

        LevelledDecision l = decisionEvaluator.calassifyFertilization(f.getPriority(),
                f.getSoilRichness(), f.getIrrigation());
        if (Application.DEBUG) {
            System.out.println("fert dec:" + l);
        }
        switch (l) {
            case HEAVY:
                f.setSoilRichness(f.getSoilRichness() + 40);
                Thread.sleep(4000);
                break;
            case MEDIUM:
                f.setSoilRichness(f.getSoilRichness() + 25);
                Thread.sleep(2000);
                break;
            case LIGHT:
                f.setSoilRichness(f.getSoilRichness() + 10);
                Thread.sleep(1000);
                break;
            case NO:
                break;
            default:
                break;
        }
        decisions.put(FERTILIZATION, l);
        setChanged();
        notifyObservers(FERTILIZATION);
    }

    public void setTargetLocation(Location target) {
        this.targetLocation = target;
        setChanged();
        notifyObservers(NEW_TARGET);
    }

    public Location getTargetLocation() {
        return this.targetLocation;
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof WeatherChanger) {
            if(arg instanceof Weather) {
                setChanged();
                notifyObservers(arg);
            }
        }
    }
    public TreeMap<String, LevelledDecision> getDecisions() {
        return this.decisions;
    }

    enum Direction {
        LEFT, RIGHT, UP, DOWN
    }
}
