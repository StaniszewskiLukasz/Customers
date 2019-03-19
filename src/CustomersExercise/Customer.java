package CustomersExercise;

import java.math.BigDecimal;
import java.util.*;



public class Customer {
    private Integer id;
    private String name;
    private String surname;
    private Integer age;
    private BigDecimal wage;
    private static Integer counter = 1; // jak tu nie jest static to id dla każdego jest 1!!! Bo chyba main nie może korzystać z tej zmiennnej???

    public static Customer[] people = new Customer[]{
            new Customer("Anna", "Nowak   ", 33, "1200"),
            new Customer("Beata", "Kowalska", 22, "1200"),
            new Customer("Marek", " Nowak", 25, "1250"),
            new Customer("Adam", "Twardowski", 33, "4100"),
            new Customer("Monika  ", "Kos", 25, "2500"),
            new Customer("Adam ", "Rudy", 45, "3333"),
            new Customer("Marek", "Rudy", 15, 2210),
            new Customer("Adam", "Madej", 15, 3333), // dostajemy dwa typy danych ale żaden nam nie odpowiada
    };

    public static List<Customer> listOfPeople = new ArrayList<>();
    public static Map<Integer, Customer> mapOfPeople = new HashMap<>();
    public static Map<Integer, Customer> mapOfWages = new HashMap<>();

    {
        id = counter++;
    }

    public Customer(String name, String surname, Integer age, String wage) {
        this(name, surname, age, Integer.valueOf(wage)); // w pierwzym konstruktorze zamieniamy Stringa w zarobkach na Integera
    }

    public Customer(String name, String surname, Integer age, Integer wage) { // w tym knstruktorze zamieniamy Integera na BigDecimal
        this.name = name.trim();
        this.surname = surname.trim();
        this.age = age;
        this.wage = BigDecimal.valueOf(Integer.valueOf(wage));
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public BigDecimal getWage() {
        return wage;
    }

    public void setWage(BigDecimal wage) {
        this.wage = wage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", wage=" + wage +
                '}';
    }
}
