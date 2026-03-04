import java.util.*;
import java.util.stream.Collectors;

public class Cars {

    public static void main(String[] args) {

        System.out.println("Машины, выпущенные после 2015 года");
        int year;
        int[] carYear = new int[50];
        Random random = new Random();
        for (int i = 0; i < carYear.length; i++) {
            carYear[i] = random.nextInt(26) + 2000;
            if(carYear[i] > 2015){
                year =  carYear[i];
                System.out.println("Car N" + i + " | " + year);
            }
        }

        List<String> carsModel = List.of("Toyota Camry","BMW X5","Tesla Cybertruck","Ford Focus","Lada Vesta",
                "Toyota Camry","Tesla Roadster","Toyota Corolla");
        Set<String> uniqueModels = new HashSet<>(carsModel);
        List<String> sortedUniq = new ArrayList<>(uniqueModels);
        sortedUniq.sort(Collections.reverseOrder());

        System.out.println("\nМодели после удаления дубликатов и сортировки в обратном порядке");
        for (String models : sortedUniq) {
            System.out.println(models);
        }

        List<String> tesla_check = new ArrayList<>(sortedUniq);
        for (int i = 0; i < tesla_check.size(); i++) {
            if (tesla_check.get(i).contains("Tesla")) {
                tesla_check.set(i, "ELECTRO_CAR");
            }
        }

        System.out.println("\nМодели после замены Tesla на ELECTRO_CAR");
        for (String electro : tesla_check) {
            System.out.println(electro);
        }

        Set<Car> carSet = new HashSet<>();

        Car car1 = new Car("VIN123", "Camry", "Toyota", 2022, 50000, 25000);
        Car car2 = new Car("VIN456", "X5", "BMW", 2021, 30000, 60000);
        Car car3 = new Car("VIN123", "Corolla", "Toyota", 2020, 70000, 20000);
        Car car4 = new Car("VIN789", "Cybertruck", "Tesla", 2023, 10000, 45000);
        Car car5 = new Car("VIN457", "X5", "BMW", 2023, 20000, 65000);
        Car car6 = new Car("VIN489", "Focus", "Ford", 2020, 10000, 70000);
        Car car7 = new Car("VIN489", "Focus", "Ford", 2020, 10000, 70000);

        carSet.add(car1);
        carSet.add(car2);
        carSet.add(car3);
        carSet.add(car4);
        carSet.add(car5);
        carSet.add(car6);
        carSet.add(car7);

        System.out.println("\nАвтомобили без дубликатов");
        for (Car car : carSet) {
            System.out.println(car);
        }

        List<Car> carList = new ArrayList<>(carSet);
        Collections.sort(carList);

        System.out.println("\nАвтомобили, отсортированные по году выпуска");
        for (Car car : carList) {
            System.out.println(car);
        }

        System.out.println("\nMашины с пробегом меньше 50000 км");
        carList.stream().filter(car -> car.mileage < 50000).forEach(System.out::println);

        System.out.println("\nЦена отсортирована по убыванию");
        carList.stream().sorted(Comparator.comparingInt(Car::getPrice).reversed()).forEach(System.out::println);

        System.out.println("\nТоп-3 дорогих машин");
        carList.stream().sorted(Comparator.comparingInt(Car::getPrice).reversed()).limit(3).forEach(System.out::println);

        double averageMileage = carList.stream()
                .mapToInt(Car::getMileage)
                .average()
                .orElse(0);
        System.out.println("\nСредний пробег всех машин: " + averageMileage);
        Map<String, List<Car>> carsByManufacturer = carList.stream()
                .collect(Collectors.groupingBy(Car::getManufacturer));

        System.out.println("\nГруппировка машин по производителю");
        carsByManufacturer.forEach((manufacturer, cars) -> {
            System.out.println("Производитель: " + manufacturer);
            cars.forEach(System.out::println);
        });
    }

    static class Car implements Comparable<Car> {
        public String VIN;
        public String model;
        public String manufacturer;
        public int yearProduction;
        public int mileage;
        public int price;

        public Car(String VIN, String model, String manufacturer, int yearProduction, int mileage, int price)  {
            this.VIN = VIN;
            this.model = model;
            this.manufacturer = manufacturer;
            this.yearProduction = yearProduction;
            this.mileage = mileage;
            this.price = price;
        }

        public int getPrice(){
            return price;
        }
        public int getMileage(){
            return mileage;
        }
        public String getManufacturer(){
            return manufacturer;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Car car = (Car) o;
            return VIN.equals(car.VIN);
        }

        @Override
        public int hashCode() {
            return Objects.hash(VIN);
        }

        @Override
        public String toString() {
            return "Car: " +
                    VIN +
                    ", " + model +
                    ", " + manufacturer +
                    ", " + yearProduction +
                    ", " + mileage +
                    ", " + price;
        }

        @Override
        public int compareTo(Car other) {
            return Integer.compare(other.yearProduction, this.yearProduction);
        }
    }


}
