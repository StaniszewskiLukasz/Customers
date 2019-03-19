package CustomersExercise;

import java.math.BigDecimal;
import java.util.Objects;

public class CarOption {
    private Integer id;
    private String partsName;
    private BigDecimal partsPrice;
    public static Integer counter=1;



    public static CarOption[] items = new CarOption[]{
            new CarOption("Klima",1500),
            new CarOption("Radyjko",1200),
            new CarOption("Wycieraczki",100),
            new CarOption("Dywaniki",150)
    };

    {
        id=counter++;
    }

    public CarOption(String partsName, String partsPrice){
        this(partsName, Integer.valueOf(partsPrice));
    }

    public CarOption(String partsName,Integer partsPrice){
        this.partsName = partsName;
        this.partsPrice = BigDecimal.valueOf(partsPrice);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPartsName() {
        return partsName;
    }

    public void setPartsName(String partsName) {
        this.partsName = partsName;
    }

    public BigDecimal getPartsPrice() {
        return partsPrice;
    }

    public void setPartsPrice(BigDecimal partsPrice) {
        this.partsPrice = partsPrice;
    }

    public static CarOption[] getItems() {
        return items;
    }

    public static void setItems(CarOption[] items) {
        CarOption.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarOption carOption = (CarOption) o;
        return Objects.equals(id, carOption.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "CarOption{" +
                "id=" + id +
                ", partsName='" + partsName + '\'' +
                ", partsPrice=" + partsPrice +
                '}';
    }
}
