import java.io.*;

enum Command {
    domain, domaingenes, listonborder, maketable, listlonggenes, listnamegenes, help
}

public class Main {
    static int ValueError = 1000;

    public static void main(String args[]) throws FileNotFoundException, ArrayIndexOutOfBoundsException {
        if (args.length > 4) {
            ValueError = Integer.parseInt(args[4]);
        }
        Comparator c;
        CharSequence cq = "";
        String str = args[0].toLowerCase();
        try {
            Command command = Command.valueOf(str);

            System.out.println("For Help enter \"help\"\n");
            switch (command) {
                case domain:
                    c = new Comparator(DataLoader.loadDomainFile(args[1]),
                            DataLoader.loadDomainFile(args[2]), ValueError);
                    c.doItCompareDomains();
                    cq = c.printResultsComparisonDomains();
                    break;
                case domaingenes:
                    c = new Comparator(DataLoader.loadDomainFile(args[1]),
                            DataLoader.loadGenesFile(args[2]), ValueError);
                    c.doItCompareDomainsWithGenes();
                    cq = c.printResultsComparisonDomainsWithGenes();
                    break;
                case listonborder:
                    c = new Comparator(DataLoader.loadDomainFile(args[1]),  //////listborder total.HindIII.combined.domain genomMouseSmall.txt onlyBorder.txt
                            DataLoader.loadGenesFile(args[2]), ValueError);
                    c.doItCompareDomainsWithGenesOnlyBorder();
                    cq = c.printResultOnlyBorderInformation(7);
                    break;
                case maketable:
                    c = new Comparator(DataLoader.loadDomainFile(args[1]), // makeTable Sp_domain.txt genomMouseSmall.txt result.txt
                            DataLoader.loadGenesFile(args[2]), ValueError);
                    c.doItCompareDomainsWithGenesOnlyBorder();
                    cq = c.printResultTableGenes();
                    break;
                case listlonggenes:
                    c = new Comparator(DataLoader.loadDomainFile(args[1]),
                            DataLoader.loadGenesFile(args[2]), ValueError);
                    c.doItCompareDomainsWithGenesOnlyBorder();
                    cq = c.printResultOnlyBorderInformation(10);
                    break;
                case listnamegenes:
                    c = new Comparator(DataLoader.loadDomainFile(args[1]),
                            DataLoader.loadFromListNameFile(args[2]), ValueError);  /// listNameGenes total.HindIII.combined.domain listGeneName.txt
                    c.doItFindInformationAboutGenes();
                    cq = c.printResultInformationAboutGenes();
                    break;
                case help:
                    Help help = new Help();
                    help.writeHelp();
            }

            if (!cq.equals(null)) {
                try {
                    File flt = new File(args[3]);
                    FileWriter wrt = new FileWriter(flt);
                    wrt.append(cq);
                    wrt.flush();
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        } catch (IllegalArgumentException e) {
            Help help = new Help();
            help.writeHelp();
        }
    }
}
