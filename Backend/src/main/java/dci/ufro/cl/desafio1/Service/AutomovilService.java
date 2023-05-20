package dci.ufro.cl.desafio1.Service;

import dci.ufro.cl.desafio1.Entity.Automovil;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service

public class AutomovilService {

    private final String[] BRANDS = {"Toyota", "Chevrolet", "Ford", "Nissan", "Mazda"};
    private final int[] YEARS = {2015, 2016, 2017, 2018, 2019, 2020, 2021, 2022, 2023};
    private final String[] COLORS = {"Rojo", "Azul", "Blanco", "Negro", "Gris"};
    private final double[] PRICES = {8000000, 9000000, 10000000, 11000000, 12000000, 13000000, 14000000, 15000000, 16000000, 17000000, 18000000, 19000000, 20000000, 21000000, 22000000, 23000000, 24000000, 25000000, 26000000, 27000000, 28000000, 29000000, 30000000};
    private final String[] TURBO = {"Si", "No"};
    private final String[] TYPES = {"Sedan", "Camioneta", "SUV"};
    private final double[] SEDAN_ENGINES = {1.4, 1.6, 2.0};
    private final double[] CAMIONETA_ENGINES = {2.4, 3.0, 4.0};
    private final double[] SUV_ENGINES = {1.8, 2.2, 2.8};
    private final int[] CABINS = {1, 2};
    private final String[] SUNROOF = {"Si", "No"};

    private List<Automovil> cars = new ArrayList<>();

    public List<Automovil> generateCars(int n) {
        cars.clear();
        for (int i = 0; i < n; i++) {
            Automovil car = new Automovil();
            car.setId(i + 1);
            car.setBrand(BRANDS[(int) (Math.random() * BRANDS.length)]);
            car.setYear(YEARS[(int) (Math.random() * YEARS.length)]);
            car.setColor(COLORS[(int) (Math.random() * COLORS.length)]);
            car.setPrice(PRICES[(int) (Math.random() * PRICES.length)]);
            car.setTurbo(TURBO[(int) (Math.random() * TURBO.length)].equals("Si"));
            car.setType(TYPES[(int) (Math.random() * TYPES.length)]);
            if (car.getType().equals("Sedan")){
                car.setEngine(SEDAN_ENGINES[(int) (Math.random() * SEDAN_ENGINES.length)]);
            } else if (car.getType().equals("Camioneta")) {
                car.setEngine(CAMIONETA_ENGINES[(int) (Math.random() * CAMIONETA_ENGINES.length)]);
                car.setCabins(CABINS[(int) (Math.random() * CABINS.length)]);
            } else {
                car.setEngine(SUV_ENGINES[(int) (Math.random() * SUV_ENGINES.length)]);
                car.setSunroof(SUNROOF[(int) (Math.random() * SUNROOF.length)].equals("Si"));
            }
            car.setPopularity(0);
            cars.add(car);
        }
        return cars;
    }

    public List<String> getColores() {
        return Arrays.asList(COLORS);
    }

    public List<String> getTipos() {
        return Arrays.asList(TYPES);
    }

    public List<Automovil> findByColor(String color) {
        List<Automovil> result = new ArrayList<>();
        for (Automovil car : cars) {
            if (car.getColor().equalsIgnoreCase(color)) {
                result.add(car);
            }
        }
        return result;
    }

    public List<Automovil> findByTypes(String tipo) {
        List<Automovil> result = new ArrayList<>();
        for (Automovil car : cars) {
            if (car.getType().equalsIgnoreCase(tipo)) {
                result.add(car);
            }
        }
        return result;
    }


    public List<Automovil> filterByPrice(double maxPrice) {
        List<Automovil> result = new ArrayList<>();
        for (Automovil car : cars) {
            if (car.getPrice() <= maxPrice) {
                result.add(car);
            }
        }
        return result;
    }
    }







