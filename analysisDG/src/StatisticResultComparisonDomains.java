
public class StatisticResultComparisonDomains
{
    String name;
    double averageLength = 0;
    int minLength = Integer.MAX_VALUE;
    int maxLength = 0;
    int intervalsAmount = 0;

    ComparisonResult comparisonResult;

    String ToString()
    {
        String retVal = name + ": \n";
        retVal += "\t " + maxLength + " the maximum length of a domain\n";
        retVal += "\t " + minLength + " the minimum length of a domain\n";
        retVal += "\t " + averageLength + " The average length of the domain\n";
        retVal += "\t " + comparisonResult.insideIntervalsCounterDomainsInGenes + " domains within genes " + "\n";
        retVal += "\t " + comparisonResult.sameIntervals + " similar domain's number" + "\n";
        retVal += "\t " + comparisonResult.similarIntervals + " similar domain's number with adjusted limit of epsilon " +
                comparisonResult.epsilon + "\n";
        retVal += "\t " + intervalsAmount + " domain's number" + "\n";
        return retVal;
    }

    void setComparisonResult(ComparisonResult result)
    {
        comparisonResult = result;
    }
}
