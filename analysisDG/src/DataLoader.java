import com.sun.deploy.util.ArrayUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DataLoader {
    static String nameHardcoreFile = "mm10.txt";

    public static Map<String, Chromosome> loadDomainFile(String fileName) throws FileNotFoundException {
        ArrayList<ArrayList<String>> arrrrr = loadArrayList(fileName);
        Map<String, Chromosome> chrs = getSorteredMap();
        int rowIndex = 0;
        while (arrrrr.size() != rowIndex) {
            Chromosome chr = new Chromosome(arrrrr.get(rowIndex).get(0));
            while (arrrrr.size() > rowIndex && chr.nameChrom.equals(arrrrr.get(rowIndex).get(0))) {
                chr.addInterval(new Interval(Integer.parseInt(arrrrr.get(rowIndex).get(1)),
                        Integer.parseInt(arrrrr.get(rowIndex).get(2)), ""));
                rowIndex++;
            }
            chrs.put(chr.nameChrom, chr);
        }

        return chrs;
    }

    public static Map<String, Chromosome> loadGenesFile(String fileName) throws FileNotFoundException {
        ArrayList<ArrayList<String>> arrrrr = loadArrayList(fileName);
        Map<String, Chromosome> chrs = getSorteredMap();
        int rowIndex = 1;
        while (arrrrr.size() != rowIndex) {
            Chromosome chr = new Chromosome(arrrrr.get(rowIndex).get(0));
            while (arrrrr.size() > rowIndex && chr.nameChrom.equals(arrrrr.get(rowIndex).get(0))) {
                chr.addInterval(new Interval(Integer.parseInt(arrrrr.get(rowIndex).get(1)),
                        Integer.parseInt(arrrrr.get(rowIndex).get(2)), arrrrr.get(rowIndex).get(3).toString()));
                rowIndex++;
            }
            chrs.put(chr.nameChrom, chr);
        }
        return chrs;
    }

    ;

    public static Map<String, Chromosome> loadFromListNameFile(String fileName) throws FileNotFoundException {
        ArrayList<String> listUser = new ArrayList<String>();
        Scanner s = new Scanner(new File(fileName));
        while (s.hasNext())
            listUser = new ArrayList<String>(Arrays.asList(s.nextLine().split(", ")));
        ArrayList<ArrayList<String>> dataGenom = loadArrayList(nameHardcoreFile);
        ArrayList<ArrayList<String>> arrrrr = new ArrayList<ArrayList<String>>();   // <-- only needed genes with full information
        Map<String, Chromosome> chrs = getSorteredMap();
        for (String nameGeneUser : listUser) {
            for (ArrayList<String> gene : dataGenom) {
                if (nameGeneUser.equals(gene.get(3))) {
                    arrrrr.add(new ArrayList<String>((gene)));
                }
            }
        }

        int rowIndex = 0;
        while (arrrrr.size() != rowIndex) {
            Chromosome chr = new Chromosome(arrrrr.get(rowIndex).get(0));
            while (arrrrr.size() > rowIndex && chr.nameChrom.equals(arrrrr.get(rowIndex).get(0))) {
                chr.addInterval(new Interval(Integer.parseInt(arrrrr.get(rowIndex).get(1)),
                        Integer.parseInt(arrrrr.get(rowIndex).get(2)), arrrrr.get(rowIndex).get(3).toString()));
                rowIndex++;
            }
            chrs.put(chr.nameChrom, chr);
        }
        return chrs;
    }

    private static ArrayList<ArrayList<String>> loadArrayList(String fileName) throws FileNotFoundException {
        ArrayList<ArrayList<String>> ret = new ArrayList<ArrayList<String>>();
        Scanner s = new Scanner(new File(fileName));
        while (s.hasNextLine())
            ret.add(new ArrayList<String>(Arrays.asList(s.nextLine().split("\t"))));
        return ret;
    }

    private static TreeMap<String, Chromosome> getSorteredMap() {
        return new TreeMap<String, Chromosome>(new java.util.Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                o1 = o1.replace("chr", "");
                o2 = o2.replace("chr", "");

                int n1 = 0;
                int n2 = 0;

                boolean o1isInt = true;
                boolean o2isInt = true;

                try {
                    n1 = Integer.parseInt(o1);
                } catch (NumberFormatException e) {
                    o1isInt = false;
                }

                try {
                    n2 = Integer.parseInt(o2);
                } catch (NumberFormatException e) {
                    o2isInt = false;
                }

                if (o1isInt && o2isInt) {
                    if (n1 > n2) return 1;
                    if (n1 < n2) return -1;
                    return 0;
                }

                if (o1isInt && !o2isInt) return -1;
                if (o2isInt && !o1isInt) return 1;

                return o1.compareTo(o2);
            }
        });
    }
}