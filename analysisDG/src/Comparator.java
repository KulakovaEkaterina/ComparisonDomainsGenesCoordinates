import java.util.*;

public class Comparator {
    private Map<String, Chromosome> _a1;
    private Map<String, Chromosome> _a2;

    private LinkedList<StatisticResultComparisonDomains> results1 = new LinkedList<StatisticResultComparisonDomains>();
    private LinkedList<StatisticResultComparisonDomains> results2 = new LinkedList<StatisticResultComparisonDomains>();
    private LinkedList<StatisticResultComparisonDomainsWithGenes> results3 = new LinkedList<StatisticResultComparisonDomainsWithGenes>();
    private int epsilon;

    public Comparator (Map<String, Chromosome> a1, Map<String, Chromosome> a2, int error)
    {
        _a1 = a1;
        _a2 = a2;
        epsilon = error;
    }
    void doItCompareDomainsWithGenes()
    {
        for (String name : _a1.keySet()) {
            Chromosome c1 = _a1.get(name);
            Chromosome c2 = _a2.get(name);

            if (c2 != null) {
                StatisticResultComparisonDomainsWithGenes r1 = c1.analyzeG();
                r1.setComparisonResult(c1.comparisonDomainsWithGenes(c2, epsilon));
                results3.add(r1);
            }
        }
    }
    String printInfListGenes()
    {
        String retVal = "";
        PrintResult printResult = new PrintResult(epsilon);
        retVal += printResult.printInfListGenes(results3);
        return retVal;
    }
    void doItCompareDomainsWithGenesOnlyBorder()
    {
        for (String name : _a1.keySet()) {
            Chromosome c1 = _a1.get(name);
            Chromosome c2 = _a2.get(name);

            if (c2 != null) {
                StatisticResultComparisonDomainsWithGenes r1 = c1.analyzeG();
                r1.setComparisonResult(c1.comparisonDomainsWithGenesOnlyBorder(c2, epsilon));
                results3.add(r1);
            }
        }
    }
    void doItFindInformationAboutGenes()
    {
        for (String name : _a2.keySet()) {
            Chromosome c1 = _a2.get(name);
            Chromosome c2 = _a1.get(name);

            if (c2 != null) {
                StatisticResultComparisonDomainsWithGenes r1 = c1.analyzeG();
                r1.setComparisonResult(c1.findGenes(c2));
                results3.add(r1);
            }
        }
    }
    void doItCompareDomains()
    {
        for (String name : _a1.keySet()) {
            Chromosome c1 = _a1.get(name);
            Chromosome c2 = _a2.get(name);

            if (c2 != null) {
                StatisticResultComparisonDomains r1 = c1.analyze();
                r1.setComparisonResult(c1.comparisonOfDomains(c2, epsilon));
                results1.add(r1);
            }
        }

        for (String name : _a2.keySet()) {
            Chromosome c1 = _a2.get(name);
            Chromosome c2 = _a1.get(name);

            if (c2 != null) {
                StatisticResultComparisonDomains r1 = c1.analyze();
                r1.setComparisonResult(c1.comparisonOfDomains(c2, epsilon));
                results2.add(r1);
            }
        }
    }
    String printResultInformationAboutGenes()
    {
        String retVal = "inDom - inside domain; RBord - crosses the right boundary; LBord - similarly \n\n";
        retVal += "name\t[start-end]\tchromosome\tinDom\tRBord\tLBord\t[dStart, dEnd] \n";
        PrintResult printResult = new PrintResult(epsilon);
        retVal += printResult.printInformationAboutGenes(results3);
        return retVal;
    }
    String printResultOnlyBorderInformation(int mark)
    {
        String retVal = "";
        PrintResult printResult = new PrintResult(epsilon);
        retVal += printResult.printResultOnlyBorderInformation(results3, mark);
        return retVal;
    }
    String printResultTableGenes()
    {
        String retVal = "";
        PrintResult printResult = new PrintResult(epsilon);
        retVal += printResult.printResultTableGenesReturn(results3);
        return retVal;
    }
    String printResultsComparisonDomainsWithGenes()
    {
        String retVal = "";
        PrintResult printResult = new PrintResult(epsilon);
        retVal += printResult.printResultG(results3);
        return retVal;
    }
    String printResultsComparisonDomains()
    {
        String retVal = "";
        PrintResult printResult = new PrintResult(epsilon);
        retVal += printResult.printResult(results1);
        retVal += "\n\n----\n\n";
        retVal += printResult.printResult(results2);

        return retVal;
    }
}
