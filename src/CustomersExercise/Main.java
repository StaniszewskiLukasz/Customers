package CustomersExercise;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static CustomersExercise.Customer.*;

public class Main {



    public static void main(String[] args) {
        System.out.println("1. Metoda: changeTableTolistOfPeople()");
        changeTableTolistOfPeople();
        System.out.println();
        System.out.println("2. Metoda: createListNamesAndSurnamesFromTable()");
        System.out.println(createListNamesAndSurnamesFromTable(people));
        System.out.println();
        System.out.println("3. Metoda: createMapOfPeople()");
        System.out.println(createMapOfPeople(listOfPeople));
        System.out.println();
        System.out.println("4. Metoda: createMapOfPeopleWages()");
        System.out.println("Drukujemy HashMapę z tabelą kto zarabia daną kwotę i wypisaniem ich nazwisk oraz id");
        System.out.println(createMapOfPeopleWages(people));
        System.out.println();
        System.out.println("5. Metoda: wagesMapWithStatistics()");
        System.out.println("Drukujemy HashMapę z tabelą ile osób zarabia dana kwotę");
        System.out.println(wagesMapWithStatistics());
        System.out.println();
        System.out.println("6. Metoda: mapOfWagesMap()");
        System.out.println("Drukujemy HashMapę mapy <imię,<zarobki,liczba_osób_z_takimi_zarobkami>>");
        System.out.println(mapOfWagesMap());
        System.out.println();
        System.out.println("7. Metoda: mapOfWagesSum()");
        System.out.println("Drukujemy Hashmapę <imię,<suma_zarobków_osób_o_taki_imieniu>>");
        System.out.println(mapOfWagesSum());


    }

//    public static Integer createId() {  // tu kminiłem jakąś głupią metodę dla podwyższania id, wystarczy to zrobić przez blok incjalizujący
//        Integer value = 0;
//        for (int i = 1; i <= people.length; i++) {
//            people[i].setId(i);
//        }
//        return value;
//    }

    /*private static Customer[] trimTable(){ // tu kimniłem głupią metodę do trimowania a wystarczy w konstruktorze dopisać trim();
        Customer[] functional = new Customer[table.length];
        for (Customer person : people) {
            person.getName().trim();

        }
        return
        }*/

    private static void changeTableTolistOfPeople() {
        listOfPeople.addAll(Arrays.asList(people));
        for (int i = 0; i < listOfPeople.size(); i++) {
            System.out.println(listOfPeople.get(i));
//        System.out.println(listOfPeople);  // tak nie zadziała terzeba odwołać się do indeksu i przeiterować
        }
    }

    private static List<String> createListNamesAndSurnamesFromTable(Customer[] table) {
        return Arrays.stream(table)
                .map(person -> person.getName() + " " + person.getSurname())
                .collect(Collectors.toList());
    }

    private static Map createMapOfPeople(List<Customer> table) {
        for (Customer person : table) {
            mapOfPeople.put(person.getId(), person);
        }
        return mapOfPeople;
    }

    private static Map<BigDecimal, List<String>> createMapOfPeopleWages(Customer[] table) {
        Map<BigDecimal, List<String>> resultMap = new HashMap<>();
        for (Customer person : people) {
            if (resultMap.containsKey(person.getWage())) {
                List<String> functionalList = resultMap.get(person.getWage());
                functionalList.add(person.getId() + " " + person.getName().trim() + " " + person.getSurname().trim());
            } else {
                List<String> functionalOtherList = new ArrayList<>();
                functionalOtherList.add(person.getId() + " " + person.getName().trim() + " " + person.getSurname().trim());
                resultMap.put(person.getWage(), functionalOtherList);
            }
        }
        return resultMap;
    }

    private static Map<BigDecimal, Integer> wagesMapWithStatistics() {
        Map<BigDecimal, Integer> resultMap = new HashMap<>();
        for (int i = 0; i < listOfPeople.size(); i++) {
            int counter = 0;
            BigDecimal wage = listOfPeople.get(i).getWage();
            for (int j = 0; j < listOfPeople.size(); j++) {
                BigDecimal numberOfWage = listOfPeople.get(j).getWage();
                if (numberOfWage.equals(wage)) {
                    counter++;
                }
            }
            resultMap.put(listOfPeople.get(i).getWage(), counter);
        }
        return resultMap;
    }


    private static Map<String, Map<BigDecimal, Integer>> mapOfWagesMap() {
        Map<String, Map<BigDecimal, Integer>> resultMap = new HashMap<>();
        for (Customer person : people) {
            Integer counter = 0;
            BigDecimal wage = person.getWage();
            for (int i = 0; i < listOfPeople.size(); i++) {
                BigDecimal numberOfWage = listOfPeople.get(i).getWage();
                if (numberOfWage.equals(wage)) {
                    counter++;
                }
            }
            if (resultMap.containsKey(person.getName().trim())) {
                Map<BigDecimal, Integer> functionalMap = resultMap.get(person.getName().trim());
                functionalMap.put(person.getWage(), counter);
            } else {
                Map<BigDecimal, Integer> functionalMap = new HashMap<>();
                functionalMap.put(person.getWage(), counter);
                resultMap.put(person.getName().trim(), functionalMap);
            }
        }
        return resultMap;
    }


    private static Map<String,BigDecimal> mapOfWagesSum() {
        Map<String,BigDecimal> resultMap = new HashMap<>();
        for (Customer person : people) {
            BigDecimal wageFirst = person.getWage();
            if (resultMap.containsKey(person.getName().trim())) {
                BigDecimal wageSecond = resultMap.get(person.getName().trim());
                resultMap.put(person.getName().trim(), wageSecond.add(wageFirst));
            } else {
                resultMap.put(person.getName().trim(), person.getWage());
            }
        }
        return resultMap;
    }

}
