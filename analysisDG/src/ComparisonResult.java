
import java.util.*;

public class ComparisonResult
{
    int insideIntervalsCounterDomainsInGenes = 0;
    int insideIntervalsCounterGenesInDomains = 0;
    int sameIntervals = 0;
    int similarIntervals = 0;
    int epsilon;
    Interval genRichInterval;
    int countMax = 0;
    int pairs = 0;
    int sizeGenes = 0;

    Map <Interval, ArrayList<String>> numberGenesOnBorderInterval = new HashMap<Interval, ArrayList<String>>();
    Map <String, Integer> numberGenesOnBorderIntervalAllSide = new HashMap<String, Integer>();
    ArrayList<String> checkRepeatGene = new ArrayList<String>();
    ArrayList<Interval> intersectedNeighbourDomainsGenes  = new ArrayList<Interval>();
    Map<Interval, ArrayList<String>> numberOfGenesIncludedInInterval = new HashMap<Interval, ArrayList<String>>();
    Map<Interval, Integer> intersectedIntervalsAmount = new HashMap<Interval, Integer>();

    Map<Interval, ArrayList<String>> coupleGenes = new HashMap<Interval, ArrayList<String>>();

    Map<Interval, ArrayList<String>> informationAboutGenesInside = new HashMap<Interval, ArrayList<String>>();
    Map<Interval, ArrayList<String>> informationAboutGenesRight = new HashMap<Interval, ArrayList<String>>();
    Map<Interval, ArrayList<String>> informationAboutGenesLeft = new HashMap<Interval, ArrayList<String>>();
    String ToString()
    {
        String retVal = "";
        return retVal;
    }
}