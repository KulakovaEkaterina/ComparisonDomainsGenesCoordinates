
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Chromosome {
    ArrayList<Interval> intervals = new ArrayList<Interval>();
    String nameChrom;

    public Chromosome(String _nameChrom) {
        nameChrom = _nameChrom;
    }

    public void addInterval(Interval interval) {
        intervals.add(interval);
    }

    public StatisticResultComparisonDomains analyze() {
        StatisticResultComparisonDomains result = new StatisticResultComparisonDomains();
        result.name = nameChrom;
        result.intervalsAmount = intervals.size();
        double lengthSum = 0;

        for (Interval i1 : intervals) {
            int l = i1.getLengts();
            lengthSum += l;
            if (l < result.minLength) result.minLength = l;
            if (l > result.maxLength) result.maxLength = l;
        }

        double newAverageLength = new BigDecimal(lengthSum / intervals.size()).setScale(3, RoundingMode.HALF_UP).doubleValue();
        result.averageLength = newAverageLength;
        return result;
    }

    public StatisticResultComparisonDomainsWithGenes analyzeG() {
        StatisticResultComparisonDomainsWithGenes result = new StatisticResultComparisonDomainsWithGenes();
        result.name = nameChrom;
        result.intervalsAmount = intervals.size();
        double lengthSum = 0;

        for (Interval i1 : intervals) {
            int l = i1.getLengts();
            lengthSum += l;
            if (l < result.minLength) result.minLength = l;
            if (l > result.maxLength) result.maxLength = l;
        }

        double newAverageLength = new BigDecimal(lengthSum / intervals.size()).setScale(3, RoundingMode.HALF_UP).doubleValue();
        result.averageLength = newAverageLength;
        return result;
    }

    public ComparisonResult comparisonDomainsWithGenes(Chromosome other, int epsilon) {
        ComparisonResult comparisonResult = new ComparisonResult();
        for (Interval interval : intervals) {
            ArrayList<Interval> intersectedIntervals = new ArrayList<Interval>();
            ArrayList<String> geneInsideDomain = new ArrayList<String>();
            ArrayList<String> genesOnBorderDomain = new ArrayList<String>();
            boolean isInsideGenes = false;

            for (Interval i2 : other.intervals) {
                if (!isInsideGenes && interval.isInsideGene(i2)) isInsideGenes = true;
                if (interval.isInsideDomain(i2)) {
                    if (!geneInsideDomain.contains(i2.name))
                        geneInsideDomain.add(i2.name);
                    ++comparisonResult.insideIntervalsCounterGenesInDomains;
                }
                if (interval.isIntersected(i2)) intersectedIntervals.add(i2);
                if (interval.isRightBorder(i2)) {
                    if (!genesOnBorderDomain.contains(i2.name) && !comparisonResult.checkRepeatGene.contains(i2.name)) {
                        genesOnBorderDomain.add(i2.name);
                        comparisonResult.checkRepeatGene.add(i2.name);
                    }
                }
                if (interval.isLeftBorder(i2))// isLeftBorder = true;
                {
                    if (!genesOnBorderDomain.contains(i2.name) && !comparisonResult.checkRepeatGene.contains(i2.name)) {
                        genesOnBorderDomain.add(i2.name);
                        comparisonResult.checkRepeatGene.add(i2.name);
                    }
                }
            }

            comparisonResult.epsilon = epsilon;
            if (isInsideGenes) ++comparisonResult.insideIntervalsCounterDomainsInGenes;

            if (intersectedIntervals.size() > 1) {
                comparisonResult.intersectedIntervalsAmount.put(interval, intersectedIntervals.size());
                for (Interval intersectedInterval : intersectedIntervals) {
                    for (Interval intersectedInterval1 : intersectedIntervals) {
                        if (!comparisonResult.intersectedNeighbourDomainsGenes.contains(interval)) {
                            if (intersectedInterval.isNeighbour(intersectedInterval1))
                                comparisonResult.intersectedNeighbourDomainsGenes.add(interval);
                            if (!intersectedInterval.isIntersected(intersectedInterval1))
                                comparisonResult.intersectedNeighbourDomainsGenes.add(interval);
                        }
                    }
                }
            }
            comparisonResult.numberGenesOnBorderInterval.put(interval, genesOnBorderDomain);  ////////////// <- amount on border
            if (geneInsideDomain.size() > 0)
                comparisonResult.numberOfGenesIncludedInInterval.put(interval, geneInsideDomain);
        }
        comparisonResult.genRichInterval = genRichDomain(comparisonResult.numberOfGenesIncludedInInterval);
        comparisonResult.countMax = comparisonResult.numberOfGenesIncludedInInterval.get(comparisonResult.genRichInterval).size();
        return comparisonResult;
    }

    private void intersectBorder(ComparisonResult comparisonResult, Interval genes) {
        int IntersectBorder = 5;
        int LongGenes = 10;

        if (!comparisonResult.numberGenesOnBorderIntervalAllSide.containsKey(genes.name))
            comparisonResult.numberGenesOnBorderIntervalAllSide.put(genes.name, IntersectBorder);
        else {
            comparisonResult.numberGenesOnBorderIntervalAllSide.put(genes.name, LongGenes); //<--- long
        }
    }

    public ComparisonResult comparisonDomainsWithGenesOnlyBorder(Chromosome other, int epsilon) // <---- other this is genes
    {
        int Closer = 4;
        int InsideNotCloser = 6;
        int CloserAndInside = 7;
        int Other = 1;

        ComparisonResult comparisonResult = new ComparisonResult();
//        for (Interval genes : other.intervals) {
//            for (Interval domain : intervals) {
//                if (genes.isRightBorder2(domain) || genes.isLeftBorder2(domain)) {
//                    intersectBorder(comparisonResult, genes);
//                } else if (genes.closerToTheRight(domain, epsilon) || genes.closerToTheLeft(domain, epsilon)) {
//                    comparisonResult.numberGenesOnBorderIntervalAllSide.put(genes.name, CloserAndInside); // <--- closer
//                } else if (genes.isInsideGene(domain)) {
//                    if (!comparisonResult.numberGenesOnBorderIntervalAllSide.containsKey(genes.name)) {
//                        comparisonResult.numberGenesOnBorderIntervalAllSide.put(genes.name, InsideNotCloser); //<--inside исключая крайние
//                    } else
//                        comparisonResult.numberGenesOnBorderIntervalAllSide.put(genes.name, CloserAndInside); // и у края и внутри
//                }
//            }
//            if (!comparisonResult.numberGenesOnBorderIntervalAllSide.containsKey(genes.name)) {
//                comparisonResult.numberGenesOnBorderIntervalAllSide.put(genes.name, Other);
//            }
//        }
        System.out.println("Size " + other.intervals.size());
        for (Interval genes : other.intervals)
        {
            for(Interval domain : intervals)
            {
                if (genes.isRightBorder2(domain) || genes.isLeftBorder2(domain))
                    intersectBorder(comparisonResult, genes);                       // <--- intersected or longer
                if (genes.closerToTheRight(domain, epsilon) || genes.closerToTheLeft(domain, epsilon))
                    comparisonResult.numberGenesOnBorderIntervalAllSide.put(genes.name, CloserAndInside);
                if (genes.isInsideGene(domain) && !comparisonResult.numberGenesOnBorderIntervalAllSide.containsKey(genes.name))
                        comparisonResult.numberGenesOnBorderIntervalAllSide.put(genes.name, InsideNotCloser);
            }
            if (!comparisonResult.numberGenesOnBorderIntervalAllSide.containsKey(genes.name))
                comparisonResult.numberGenesOnBorderIntervalAllSide.put(genes.name, Other);

        }
        return comparisonResult;
    }

    public ComparisonResult findGenes(Chromosome other) {
        ComparisonResult comparisonResult = new ComparisonResult(); // знаю имя координаты нужного гена, найти нужный домен
        comparisonResult.sizeGenes = other.intervals.size();
        System.out.println(other.intervals.size());
        for (Interval domain: intervals)
        {
            for (Interval currentGene: other.intervals)
            {
                for (Interval otherGene: other.intervals)
                {
                    if (domain.isInsideDomain(otherGene) && domain.isInsideDomain(currentGene) && !otherGene.name.equals(currentGene.name))
                    {
                        comparisonResult.pairs ++;
                        if (!comparisonResult.coupleGenes.containsKey(domain))
                        {
                            comparisonResult.coupleGenes.put(domain, new ArrayList<String>());
                            comparisonResult.coupleGenes.get(domain).add(currentGene.name);
                        }
                        else if (!comparisonResult.coupleGenes.get(domain).contains(currentGene.name))
                                    comparisonResult.coupleGenes.get(domain).add(currentGene.name);
//                        else if (!comparisonResult.coupleGenes.get(currentGene.name).contains(otherGene.name) && !currentGene.name.equals(otherGene.name))
//                                            comparisonResult.coupleGenes.get(currentGene.name).add(otherGene.name);
                    }
                }
            }
        }
//        for (Interval interval : intervals) {
//            ArrayList<String> coordinatesDomainInside = new ArrayList<String>();  //contains two field start end
//            ArrayList<String> coordinatesDomainOnRight = new ArrayList<String>();
//            ArrayList<String> coordinatesDomainOnLeft = new ArrayList<String>();
//            for (Interval intervalDomain : other.intervals) {
//                if (interval.isInsideGene(intervalDomain)) {
//                    coordinatesDomainInside.add(Integer.toString(intervalDomain.a));
//                    coordinatesDomainInside.add(Integer.toString(intervalDomain.b));
//                    comparisonResult.informationAboutGenesInside.put(interval, coordinatesDomainInside);
//                } else if (intervalDomain.isRightBorder(interval)) {
//                    coordinatesDomainOnRight.add(Integer.toString(intervalDomain.a));
//                    coordinatesDomainOnRight.add(Integer.toString(intervalDomain.b));
//                    comparisonResult.informationAboutGenesRight.put(interval, coordinatesDomainOnRight);
//                } else if (intervalDomain.isLeftBorder(interval)) {
//                    coordinatesDomainOnLeft.add(Integer.toString(intervalDomain.a));
//                    coordinatesDomainOnLeft.add(Integer.toString(intervalDomain.b));
//                    comparisonResult.informationAboutGenesLeft.put(interval, coordinatesDomainOnLeft);
//                }
//            }
//        }
        return comparisonResult;
    }

    public ComparisonResult comparisonOfDomains(Chromosome other, int epsilon) {
        ComparisonResult comparisonResult = new ComparisonResult();

        for (Interval i1 : intervals) {
            boolean isInside = false;
            boolean isSame = false;
            boolean isSimilar = false;

            for (Interval i2 : other.intervals) {
                if (!isInside && i1.isInsideGene(i2)) isInside = true;
                if (!isSame && i1.Compare(i2)) isSame = true;
                if (!isSimilar && i1.Compare(i2, epsilon)) isSimilar = true;
            }

            comparisonResult.epsilon = epsilon;
            if (isInside) ++comparisonResult.insideIntervalsCounterDomainsInGenes;
            if (isSame) ++comparisonResult.sameIntervals;
            if (isSimilar) ++comparisonResult.similarIntervals;
        }
        return comparisonResult;
    }

    Interval genRichDomain(Map<Interval, ArrayList<String>> map) {
        int count_Max = 0;
        Interval interval = new Interval(0, 0, "");
        for (Interval i : map.keySet()) {
            if (map.get(i).size() > count_Max) {
                count_Max = map.get(i).size();
                interval = i;
            }
        }
        return interval;
    }
}
