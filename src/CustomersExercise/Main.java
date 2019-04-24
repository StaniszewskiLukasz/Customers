package CustomersExercise;



import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import static CustomersExercise.Customer.*;


public class Main {
    public static List<String> theRichestCustomer = new ArrayList<>();
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
        System.out.println(wagesMapWithStatistics(listOfPeople));
        System.out.println();
        System.out.println("6. Metoda: mapOfWagesMap()");
        System.out.println("Drukujemy HashMapę mapy <imię,<zarobki,liczba_osób_z_takimi_zarobkami>>");
        System.out.println(mapOfWagesMap(listOfPeople));
        System.out.println();
        System.out.println("7. Metoda: mapOfWagesSum()");
        System.out.println("Drukujemy Hashmapę <imię,<suma_zarobków_osób_o_taki_imieniu>>");
        System.out.println(mapOfWagesSum(people));
        System.out.println();
        System.out.println("8. Metoda: howMuchMoneyCustomerWillMissing()");
        System.out.println("Drukujemy ile zabraknie poszczególnym klientom do " +
                "wybrania wszystkich elementów wyposażenia");
        howMuchMoneyCustomerWillMissing();
        System.out.println();
        System.out.println("9. Metoda: changeTableToListOfOptions()");
        System.out.println("Zmieniamy tablicę na listę opcji do kupienia");
        changeTableToListOfOptions();
        System.out.println();
        System.out.println("10. Metoda: mapOfCarOptionsCreate()");
        System.out.println("Tworzymy mapę z listą opcji do kupienia");
        mapOfCarOptionsCreate(CarOption.listOfOptions);
        System.out.println();
        System.out.println("11. Metoda: whichCarOptionCustomerCanBuy()");
        System.out.println("Wyświetlamy wyposażenie na które stać poszczególnych klientów(według kolejności ich preferencji");
        whichCarOptionCustomerCanBuy();
        System.out.println();
        System.out.println("12. Metoda: printCustomerWithTheBigestRestOfMoney()");
        System.out.println("Wyświetlamy klienta, którego stać na wszystkie elementy i zostanie mu najwięcej pięniędzy");
        printCustomerWithTheBigestRestOfMoney();
        System.out.println();


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

    private static void changeTableToListOfOptions() {
        CarOption.listOfOptions.addAll(Arrays.asList(CarOption.getItems()));
    }

    private static Map mapOfCarOptionsCreate(List<CarOption> list) {
        for (CarOption carOption : list) {
            CarOption.mapOfCarOptions.put(carOption.getPartsName(), carOption.getPartsPrice());
        }
        return CarOption.mapOfCarOptions;
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
        for (Customer person : table) {
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

    private static Map<BigDecimal, Integer> wagesMapWithStatistics(List<Customer> someList) {
        Map<BigDecimal, Integer> resultMap = new HashMap<>();
        for (int i = 0; i < someList.size(); i++) {
            int counter = 0;
            BigDecimal wage = someList.get(i).getWage();
            for (int j = 0; j < someList.size(); j++) {
                BigDecimal numberOfWage = someList.get(j).getWage();
                if (numberOfWage.equals(wage)) {
                    counter++;
                }
            }
            resultMap.put(someList.get(i).getWage(), counter);
        }
        return resultMap;
    }


    private static Map<String, Map<BigDecimal, Integer>> mapOfWagesMap(List<Customer> someList) {
        Map<String, Map<BigDecimal, Integer>> resultMap = new HashMap<>();
        for (Customer person : someList) {
            Integer counter = 0;
            BigDecimal wage = person.getWage();
            for (int i = 0; i < someList.size(); i++) {
                BigDecimal numberOfWage = someList.get(i).getWage();
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


    private static Map<String, BigDecimal> mapOfWagesSum(Customer[] table) {
        Map<String, BigDecimal> resultMap = new HashMap<>();
        for (Customer person : table) {
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

    private static void howMuchMoneyCustomerWillMissing() {
        BigDecimal sum = BigDecimal.ZERO;
        List<BigDecimal> decimalList = Arrays
                .stream(CarOption.getItems())
                .map(CarOption::getPartsPrice)
                .collect(Collectors.toList());

        for (int i = 0; i < decimalList.size(); i++) {
            sum = sum.add(decimalList.get(i));
        }

        for (int i = 0; i < listOfPeople.size(); i++) {
            BigDecimal howMuchWillMissing = listOfPeople.get(i).getWage().subtract(sum);
            if (howMuchWillMissing.intValue() < 0) {
                System.out.println("Klientowi o id: " + listOfPeople.get(i).getId() + " zabraknie " +
                        "do kupienia wszystkich elementów wyposażenia: " + howMuchWillMissing);
            } else {
                System.out.println("Klientowi o id: " + listOfPeople.get(i).getId() + " zostanie " +
                        "po zakupieniu wszystkich elementów wyposażenia: " + howMuchWillMissing + "zł");
            }
        }
    }

   /* public static void populateMapOfPreferences(Customer[] table) {
        Map<Integer, List<String>> resultMap = new HashMap<>();
        for (Customer person : table) {
            resultMap.put(person.getId(), person.getPreferences());
        }
        mapOfCustomersPreferences = resultMap;
    }*/

    private static void whichCarOptionCustomerCanBuy() {
        for (int i = 1; i <= mapOfPeople.size(); i++) {// iterujemy listę ludzi i pobieramy po kolej klientów
            BigDecimal wage = mapOfPeople.get(i).getWage(); // z profilu konkretnego klienta pobieramy zarobki
            List<String> preferencesList = mapOfPeople.get(i).getPreferences(); // z profilu konkretnego klienta pobieramy listę preferencji
            List<String> improvePreferencesList = improvePreferencesList(preferencesList); // poprawiamy listę by nie zawierała liczb a odpowiednią liczbą elementów
            goShopping(i, wage, improvePreferencesList);
        }
    }

    private static void goShopping(int i, BigDecimal wage, List<String> improvePreferencesList) {
        for (int j = 0; j < improvePreferencesList.size(); j++) { // iterujemy listę preferencji
            if (CarOption.mapOfCarOptions.containsKey(improvePreferencesList.get(j))) { // porównujemy listę preferencji z dostępnymi opcjami wyposażenia
                if (wage.intValue() - CarOption.mapOfCarOptions.get(improvePreferencesList.get(j)).intValue() >= 0) { // patrzymy czy zarobki pozwolą na zakup
                    wage = wage.subtract(CarOption.mapOfCarOptions.get(improvePreferencesList.get(j))); // jeśli tak od pensji odejmujemy koszt wyposażenia
                    System.out.println("Klient o id: " + mapOfPeople.get(i).getId() + " może kupić " + improvePreferencesList.get(j)); // wyświetlamy
                    whoIsTheRichestCustomer(i, wage, improvePreferencesList, j);
                } else { // jeśli pensja poczatkowa lub po zakupie wcześniejszych opcji jest za niska na kolejny zakup kończymy dalsze zakupy
                    System.out.println("Klienta o id: " + mapOfPeople.get(i).getId() + " nie stać na " + improvePreferencesList.get(j) + ".");
                    j = improvePreferencesList.size();
                }
            }
        }
    }

    private static void whoIsTheRichestCustomer(int i, BigDecimal wage, List<String> improvePreferencesList, int j) {
        int customerIdWithLotOfMoney = 0;
        int secondCustomerIdWithLotOfMoney = 0;
        BigDecimal wageOfCustomerWithLotOfMoney = BigDecimal.ZERO;
        if (j == improvePreferencesList.size() - 1) {
            if (wage.intValue() > wageOfCustomerWithLotOfMoney.intValue()) {
                wageOfCustomerWithLotOfMoney = wage;
                customerIdWithLotOfMoney = mapOfPeople.get(i).getId();
            } else if (wage.intValue() == wageOfCustomerWithLotOfMoney.intValue()) {
                secondCustomerIdWithLotOfMoney = mapOfPeople.get(i).getId();
            }
            if(mapOfPeople.size()==i) {
                if (secondCustomerIdWithLotOfMoney > 0) {
                    theRichestCustomer.add("Klienci: " + mapOfPeople.get(customerIdWithLotOfMoney)
                            + " i " + mapOfPeople.get(secondCustomerIdWithLotOfMoney)
                            + " są równie bogaci. Zostało im " + wageOfCustomerWithLotOfMoney + "zł");

                } else {
                    theRichestCustomer.add("Najbogatszy jest klient: "
                            + mapOfPeople.get(customerIdWithLotOfMoney) + " i zostało mu "
                            + wageOfCustomerWithLotOfMoney + "zł");
                }
            }
        }
    }

    private static List improvePreferencesList(List<String> preferencesList) {
        String oneOptionOfPreferencesList = null;
        String number = null;
        int numberOfRepetition = 0;
        int numberOfIndex = 0;
        for (int i = 0; i < preferencesList.size(); i++) { //iterujemy listę preferencji na której są liczby w String
            Pattern pattern = Pattern.compile("(.*)\\:(\\d)"); //oddzielamy liczbę od nazwy wyposażenia
            Matcher matcher = pattern.matcher(preferencesList.get(i));
            if (matcher.matches()) { //sprawdzamy czy dla danego elementu na liścię regex działą
                oneOptionOfPreferencesList = matcher.group(1); // przypisujemy do zmiennej nazwę be liczby
                number = matcher.group(2); // przypisujemy do zmiennej liczbę ile razy dane wyposażenie ma być kupione
                numberOfRepetition = Integer.parseInt(number); // zamieniamy liczbę String na int
                numberOfIndex = i; //przypisujemy do zmiennej numer indeksu pod którym wystąpiła nazwa połączona z liczbą
            }
        }
        List<String> improvePreferencesList = new ArrayList<>(preferencesList); //kopiujemu liste poniewaz oryginalna jest static
        if (numberOfRepetition > 0) { // tworzymy warunek by lista była modyfikowana tylko wtedy gdy w oryginalnej wystąpiła liczba
            improvePreferencesList.remove(numberOfIndex); //usuwamy z kopii oryginalnej listy element połączony z liczbą
            for (int j = 0; j < numberOfRepetition; j++) {
                improvePreferencesList.add(numberOfIndex, oneOptionOfPreferencesList); // w miejscu gdzie był wykasowany element wklejamy element bez liczy tyle razy ile wskazywała liczba
            }
        }
        return improvePreferencesList;// zwracamy poprawiona listę
    }


    private static void printCustomerWithTheBigestRestOfMoney() {
        System.out.println(theRichestCustomer);
    }

}
