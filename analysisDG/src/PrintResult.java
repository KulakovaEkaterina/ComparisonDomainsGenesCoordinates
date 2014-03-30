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
    String printInfListGenes(LinkedList<StatisticResultComparisonDomainsWithGenes> results)
    {
        String retVal = "name\tonBorder\tInside\tOther\n";
        int closerToTheBorder = 0;
        int crossBorder = 0;
        int totalBorder = 0;
        int inside = 0;
        int other = 0;
        double size = 0;
        for (StatisticResultComparisonDomainsWithGenes st : results) {
            retVal += st.printResultTable();
            closerToTheBorder += st.closerToTheBorder;
            inside += st.inside;
            other += st.other;
            totalBorder += st.totalBorder;
            crossBorder += st.crossBorder;
            size += st.size;
        }
        double pTotalBorder = (totalBorder / size) * 100.0;
        double pInside = (inside / size) * 100.0;
        double pCrossBorder = (crossBorder/size) * 100.0;
        double pOther = (other/size) * 100.0;
        double pcloserToTheBorder = (closerToTheBorder/size) * 100.0;
        //retVal += "\n\n" + pBorder + "%.2f % hit on the domain boundaries " + pInside + "%.2f % inside \n";
        retVal += "\n\n" + String.format("%.2f", pInside) + "% inside \n"
                + String.format("%.2f", pTotalBorder) + "% close to the border or cross\n"
                + "\t" + String.format("%.2f", pCrossBorder) + "% crossing the border \n"
                + "\t" + String.format("%.2f", pcloserToTheBorder) + "% close to the border \n"
                + String.format("%.2f", pOther) + "% unknown \n"
                + size + " total size";

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
