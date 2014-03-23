import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;

public class PrintResult {
    int _epsilon;

    PrintResult(int epsilon) {
        _epsilon = epsilon;
    }

    String printResultTableGenesReturn(LinkedList<StatisticResultComparisonDomainsWithGenes> results) {
        String retVal = "name\tonBorder\tInside\tOther\n";
        for (StatisticResultComparisonDomainsWithGenes st : results) {
            retVal += st.printResultTable();
        }
        return retVal;
    }

    String printInformationAboutGenes(LinkedList<StatisticResultComparisonDomainsWithGenes> results) {
        String retVal = "";
        for (StatisticResultComparisonDomainsWithGenes st : results) {
            retVal += st.toStringInformationAboutGenes();
        }
        return retVal;
    }

    String printResultOnlyBorderInformation(LinkedList<StatisticResultComparisonDomainsWithGenes> results, int mark) {
        String retVal = "";
        for (StatisticResultComparisonDomainsWithGenes st : results) {
            retVal += st.ToStringOnlyBorderInformation(mark);
        }
        return retVal;
    }

    String printResultG(LinkedList<StatisticResultComparisonDomainsWithGenes> results) {
        String retVal = "";

        int commonIntervalsAmount = 0;
        for (StatisticResultComparisonDomainsWithGenes st : results) {
            retVal += st.ToString();
            commonIntervalsAmount += st.intervalsAmount;
        }

        //retVal += "Всего блоков " + commonIntervalsAmount + "\n";

        return retVal;
    }

    String printResult(LinkedList<StatisticResultComparisonDomains> results) {
        String retVal = "";

        int commonIntervalsAmount = 0;
        int commonMatch = 0;
        int commonSimilarIntervals = 0;
        for (StatisticResultComparisonDomains st : results) {
            retVal += st.ToString();
            commonIntervalsAmount += st.intervalsAmount;
            commonMatch += st.comparisonResult.sameIntervals;
            commonSimilarIntervals += st.comparisonResult.similarIntervals;
        }

        retVal += "All domain's number " + commonIntervalsAmount + "\n";
        retVal += "Coincedens on " + pp(commonMatch / (double) commonIntervalsAmount, 3) + "%\n";
        retVal += "Coincedens with epsilon " + _epsilon + " на " + pp(commonSimilarIntervals / (double) commonIntervalsAmount, 3);

        return retVal;
    }

    static String pp(double d, int accuracy) {
        return new BigDecimal(100.0 * d).setScale(accuracy, RoundingMode.HALF_UP).doubleValue() + "%";
    }
}
