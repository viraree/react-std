import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class genericCombinations {

    public static <T> Stream<List<T>> combinations(T[] arr) {

        final long N = (long) Math.pow(2, arr.length);

        return StreamSupport.stream(new Spliterators.AbstractSpliterator<List<T>>(N, Spliterator.SIZED) {

            long i = 1;

            @Override

            public boolean tryAdvance(Consumer<? super List<T>> action) {

                if(i < N) {

                    List<T> out = new ArrayList<T>(Long.bitCount(i));

                    for (int bit = 0; bit < arr.length; bit++) {

                        if((i & (1<<bit)) != 0) {

                            out.add(arr[bit]);

                        }

                    }

                    action.accept(out);

                    ++i;

                    return true;

                }

                else {

                    return false;

                }

            }

        }, false);

    }

    public static void main(String[] args) {

        Integer[] arr={3,5,9};

        
       Stream<List<Integer>> resStream= combinations(arr);
             //  .collect(Collectors.toList());;

        List<List<Integer>> res=resStream.collect(Collectors.toList());
        System.out.println("res:"+ res);

    }
}
