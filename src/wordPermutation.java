import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class wordPermutation {

    // Returns a stream of permutations of c.size()
    // elements taken r at a time without repetition.
    // Example: If  C={1,2} and r=2
    //      Output:  {(1,2), (2,1)}
    // This recursive function uses the chain property of streams
    public static <T> Stream<List<T>> permutations(List<T> c, int r){
        if (r==1){
            return c.stream()
                    .map(e -> Arrays.asList(e));
        } else
        if (r==2){
            return c.stream()
                    .flatMap(
                            e1 -> c.stream()  // e1: refers to an element of c
                                    .filter(e2 -> !e1.equals(e2)) // e2: refers to an element of c
                                    .map(e2 -> Arrays.asList(e1, e2))
                    );
        } else {
            return permutations(c, r-1)
                    .flatMap(
                            l -> c.stream()
                                    .filter( e -> l.contains(e) == false)
                                    .map(e -> {
                                        List<T> out = new ArrayList<>();
                                        out.addAll(l);
                                        out.add(e);
                                        return out;}
                                    )
                    );
        }
    }


    public static void main(String[] args) {

        // Name Permutations
        List<String> stringList = Arrays.asList("Joe", "Ana", "Pete", "Mark", "Lucy");
        List<List<String>> sp =
                permutations(stringList, 2).collect(Collectors.toList());
        System.out.println(sp);

        // Integers Permutations
        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        List<List<Integer>> np =
                permutations(numbers, 3).collect(Collectors.toList());
        System.out.println(np);

    }
}
