import java.util.ArrayList;
import java.util.Map;

public class Interval {
    int a;
    int b;
    String name;
    Interval(int a, int b, String name)
    {
        this.a = a;
        this.b = b;
        this.name = name;
    }
    int getLengts ()
    {
        return b-a;
    }
    boolean isInsideGene(Interval other)
    {
        return a >= other.a && b <= other.b;
    }
    boolean isInsideDomain(Interval other)
    {
        return a <= other.a && b >= other.b;
    }
    boolean Compare(Interval other)
    {
        return Compare(other, 0);
    }
    boolean isRightBorder(Interval other)
    {
        return (a < other.a && other.a < b && b < other.b);
    }
    boolean isLeftBorder(Interval other)
    {
        return (other.a < a && a < other.b && other.b < b);
    }
    boolean isRightBorder2(Interval domain) // <----- domain
    {
        return (a > domain.a && a < domain.b && b > domain.b);
    }
    boolean isLeftBorder2(Interval domain)
    {
        return (domain.a > a && domain.a < b && domain.b > b);
    }
    boolean isIntersected (Interval other)
    {
        return (a >= other.a && a <= other.b )&& (b <= other.b && b >= other.a);
    }
    boolean isNeighbour(Interval other)
    {
        return a == other.b || b == other.a;
    }
    boolean closerToTheLeft(Interval other, int epsilon) //<--- other = domain
    {
        //return ((other.a - a) <= epsilon) && (other.b < (b-a)/2);
        return (Math.abs(a-other.a) <= epsilon) && (b < other.a + (other.b - other.a) /2);
    }
    boolean closerToTheRight(Interval other, int epsilon)
    {
        //return ((b - other.b) <= epsilon) && (other.a > (b-a)/2);
        return (Math.abs(other.b - b) <= epsilon) && (a > other.a + (other.b - other.a) /2);
    }
    public String toString ()
    {
        return "[" + a + "-" + b + "]";
    }
    boolean Compare(Interval other, int epsilon)
    {
        return Math.abs(a-other.a) <= epsilon && Math.abs(b - other.b) <= epsilon;
    }
    boolean closerGenes (Interval gene, int epsilon)
    {
        if (b < gene.a && Math.abs(gene.a - b) > epsilon) // |//////|  |______|
            return false;
        if (gene.b < a && Math.abs(gene.b - a) > epsilon) // |______|  |//////|
            return false;
        return true;
    }
}
