public class StatisticResultComparisonDomainsWithGenes {
    String name;
    double averageLength = 0;
    int minLength = Integer.MAX_VALUE;
    int maxLength = 0;
    int intervalsAmount = 0;

    int closerToTheBorder = 0;
    int crossBorder = 0;
    int totalBorder = 0;
    int inside = 0;
    int other = 0;
    int size = 0;
    int pairs = 0;

    ComparisonResult comparisonResult;

    String ToString() {
        String retVal = "**************\n" + name + ": \n";
        retVal += "************** \n";
        retVal += "\t " + maxLength + " the maximum length of a domain\n";
        retVal += "\t " + minLength + " the minimum length of a domain\n";
        retVal += "\t " + averageLength + " The average length of the domain\n";
        retVal += "\t " + comparisonResult.insideIntervalsCounterDomainsInGenes + " domains within genes" + "\n";
        if (!comparisonResult.intersectedNeighbourDomainsGenes.isEmpty()) {
            retVal += "\t domains on the border: ";
            for (Interval i : comparisonResult.intersectedNeighbourDomainsGenes) {
                retVal += " " + i + ",";
            }
            retVal = retVal.substring(0, retVal.length() - 1) + "\n";
        }
        retVal += "\t " + comparisonResult.insideIntervalsCounterGenesInDomains + " genes within the domains" + "\n";
        retVal += "\t " + intervalsAmount + " number of domains in the current chromosome" + "\n";
        if (!comparisonResult.numberGenesOnBorderInterval.isEmpty()) {
            for (Interval i : comparisonResult.numberGenesOnBorderInterval.keySet()) {
                retVal += "in domain " + i + " " + comparisonResult.numberGenesOnBorderInterval.get(i).size() +
                        " : " + comparisonResult.numberGenesOnBorderInterval.get(i) + " genes cross the border" + "\n";
            }
            retVal = retVal.substring(0, retVal.length() - 1) + "\n";
        }
        if (!comparisonResult.intersectedNeighbourDomainsGenes.isEmpty()) {
            retVal += "\t Domain on the border: ";
            for (Interval i : comparisonResult.intersectedNeighbourDomainsGenes) {
                retVal += " " + i + ",";
            }
            retVal = retVal.substring(0, retVal.length() - 1) + "\n";
        }
        retVal += "\t domain " + comparisonResult.genRichInterval + " contains the highest number of genes " + comparisonResult.countMax + "\n";
        if (!comparisonResult.numberOfGenesIncludedInInterval.isEmpty()) {
            for (Interval i : comparisonResult.numberOfGenesIncludedInInterval.keySet()) {
                retVal += "domain " + i + "\t contains\t" + comparisonResult.numberOfGenesIncludedInInterval.get(i).size() + "\tgenes\t" + comparisonResult.numberOfGenesIncludedInInterval.get(i) + " \n";
            }
            retVal = retVal.substring(0, retVal.length() - 1) + "\n";
        }
        return retVal;
    }

    String ToStringOnlyBorderInformation(int mark) {
        String retVal = "";
        if (!comparisonResult.numberGenesOnBorderIntervalAllSide.isEmpty()) {
            for (String gene : comparisonResult.numberGenesOnBorderIntervalAllSide.keySet()) {
                if (comparisonResult.numberGenesOnBorderIntervalAllSide.get(gene) == mark)
                    retVal += gene + "\n";
            }
        }
        return retVal + "";
    }

    String printResultTable() {
        String retVal = "";
        size = comparisonResult.numberGenesOnBorderIntervalAllSide.size();
        if (!comparisonResult.numberGenesOnBorderIntervalAllSide.isEmpty())
        {
            for (String gene : comparisonResult.numberGenesOnBorderIntervalAllSide.keySet())
            {
                switch (comparisonResult.numberGenesOnBorderIntervalAllSide.get(gene))
                {
                    case 1: retVal += gene + "\t" + "0\t0\t1\n";
                        other++;
                        break;
//                    case 4: retVal += gene + "\t" + "1\t0\t0\n";
//                        crossBorder++;
//                        totalBorder++;
//                        break;
                    case 5: retVal += gene + "\t" + "1\t0\t0\n";
                        crossBorder++;
                        totalBorder++;
                        break;// border
                    case 6: retVal += gene + "\t" + "0\t1\t0\n"; //inside
                        inside++;
                        break;
                    case 7: retVal += gene + "\t" + "1\t1\t0\n"; // inside and closer to the border
                        closerToTheBorder++;
                        totalBorder++;
                        break;
                    case 10: retVal += gene + "\t" + "0\t0\t1\n";    // long as rest
                        other++;
                        break;
                }
            }
        }
        return retVal + "";
    }

//    String toStringInformationAboutGenes() {
//        String retVal = "";
//        if (!comparisonResult.informationAboutGenesInside.isEmpty()) {
//            for (Interval interval : comparisonResult.informationAboutGenesInside.keySet()) {
//                retVal += interval.name + "\t" + interval + "\t" + name + "\t+\t-\t-\t" +
//                        comparisonResult.informationAboutGenesInside.get(interval) + "\n";
//            }
//        }
//        if (!comparisonResult.informationAboutGenesRight.isEmpty()) {
//            for (Interval interval : comparisonResult.informationAboutGenesRight.keySet()) {
//                retVal += interval.name + "\t" + interval + "\t" + name + "\t-\t+\t-\t" +
//                        comparisonResult.informationAboutGenesRight.get(interval) + "\n";
//            }
//        }
//        if (!comparisonResult.informationAboutGenesLeft.isEmpty()) {
//            for (Interval interval : comparisonResult.informationAboutGenesLeft.keySet()) {
//                retVal += interval.name + "\t" + interval + "\t" + name + "\t-\t-\t+\t" +
//                        comparisonResult.informationAboutGenesLeft.get(interval) + "\n";
//            }
//        }
//        return retVal;
//    }
    String toStringInformationAboutGenes()
    {
        String retVal = "";
        size = comparisonResult.sizeGenes;
        pairs = comparisonResult.pairs;
        if (!comparisonResult.coupleGenes.isEmpty())
        {
            for (Interval gene: comparisonResult.coupleGenes.keySet())
            {
                retVal +=name + " " + gene + " " + comparisonResult.coupleGenes.get(gene) + "\n"; // + domain + pairs
            }
        }

        return retVal;
    }
    void setComparisonResult(ComparisonResult result) {
        comparisonResult = result;
    }
}
