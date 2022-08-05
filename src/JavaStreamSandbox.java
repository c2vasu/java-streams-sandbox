import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class JavaStreamSandbox {
    public static void main (String[] args){
        // Step 1. Read CSV file and Serialize to DTO (Person) as array list
        System.out.println("Step 1. Read CSV file and Serialize to DTO (Person) as array list");
        System.out.println("---------------");
        List<Person> personList = readCSV();
        // Step 2. Everytime program runs in random order
        System.out.println("Step 2. Everytime program runs based on random order of the (Person) as array list");
        System.out.println("---------------");
        List<Person> shuffledPersonList = personList;
        Collections.shuffle(shuffledPersonList);
        // Step 3. Key-value pairs for 2 entries
        System.out.println("Step 3. (Non-unique) Key-value pair of 2 entries");
        System.out.println("---------------");
        String delimiter = " - ";
        nonUniquePairs(personList.stream()).forEach(obj -> System.out.println(obj.stream().map(Person::toString).collect(Collectors.joining(delimiter))));
        // Step 3. Key-value pairs for 2 unique entries
        System.out.println("---------------"); 
        System.out.println("Step 3. (Unique) Key-value pair of 2 entries");
        System.out.println("---------------");
        uniquePairs(personList.stream()).forEach(obj -> System.out.println(obj.stream().map(Person::toString).collect(Collectors.joining(delimiter))));
    }

    public static List<Person> readCSV(){
        List<Person> personList = new ArrayList<>();
        String line = null;
        String splitBy = ",";
        try {
            URL path = JavaStreamSandbox.class.getResource("people.csv");
            BufferedReader reader = new BufferedReader(new FileReader(new File(path.getFile())));
            reader.readLine(); // this will read the first line
            while ((line = reader.readLine()) != null) {
                String[] personCsv = line.split(splitBy);
                Person personObj = new Person();
                personObj.setFirstname(personCsv[0]);
                personObj.setLastname(personCsv[1]);
                personObj.setEmail(personCsv[2]);
                personList.add(personObj);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return personList;
    }

    public static <T> Stream<List<T>> nonUniquePairs(Stream<T> stream) {
        Iterator<T> iterator = stream.iterator();
        if (iterator.hasNext()) {
            T first = iterator.next();
            if (iterator.hasNext()) {
                return Stream.iterate(List.of(first, iterator.next()),prev -> iterator.hasNext() ? List.of(prev.get(1), iterator.next()) : null).takeWhile(prev -> prev != null);
            }
        }
        return Stream.empty();
    }

    public static <T> Stream<List<T>> uniquePairs(Stream<T> stream) {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(helper(stream.iterator()), Spliterator.ORDERED),false);
    }

    public static <T> Iterator<List<T>> helper(Iterator<T> iterator) {
        return new Iterator<List<T>>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public List<T> next() {
                T first = iterator.next();
                if (iterator.hasNext()) {
                    return Arrays.asList(first, iterator.next());
                } else {
                    return Collections.singletonList(first);
                }
            }
        };
    }


}
