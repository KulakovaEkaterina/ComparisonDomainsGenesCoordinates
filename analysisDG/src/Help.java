
public class Help {
    void writeHelp ()
    {
        System.out.println("Example: command filename1.bed filename2.bed 5000\n\n");

        System.out.println( "To compare 2 bed files - domain file1.domain file2.domain result.txt 1000\n" +
                            "To analysis a domain with genes - domainGene file.domain genes.txt result.txt 1000\n" +
                            "To get an information how genes settle on the domain's border - listonborder file.domain genes.txt result.txt\n" +
                            "To get an information using the list of genes name - listNameGene file.domain nameGenes.txt result.txt\n" +
                            "To get a table genes with categories: on border, inside, other - makeTable file.domain genes.txt result.txt\n" +
                            "To get the list of long genes - listLongGenes file.domain genes.txt result.txt\n");
    }
}
