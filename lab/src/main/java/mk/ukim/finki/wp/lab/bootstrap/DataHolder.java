package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Chef> chefs = new ArrayList<>();
    public static List<Dish> dishes = new ArrayList<>();
    private static long idChef=1;
    private static long idDish=1;

    public long idChef(){
        return idChef++;
    }
    public long idDish(){
        return idDish++;
    }

    @PostConstruct
    public void init() {
        dishes.add(new Dish(""+idDish(), "torta", "desert", 100));
        dishes.add(new Dish(""+idDish(), "burito", "western", 20));
        dishes.add(new Dish(""+idDish(), "oriz", "indian", 70));
        dishes.add(new Dish(""+idDish(), "supa", "europian", 30));
        dishes.add(new Dish(""+idDish(), "pizza", "italian", 60));

        chefs.add(new Chef(idChef(), "Alek", "Grozdanoski", "Mnogu saka sushi", new ArrayList<>(List.of(dishes.get(0)))));
        chefs.add(new Chef(idChef(), "Eva", "Grozdanoska", "Ima golemi ushi", new ArrayList<>(List.of(dishes.get(1)))));
        chefs.add(new Chef(idChef(), "Petar", "Markovki", "Petar ja saka Tea", new ArrayList<>(List.of(dishes.get(2)))));
        chefs.add(new Chef(idChef(), "Tea", "Nikolova", "Tea ne go saka Petar", new ArrayList<>(List.of(dishes.get(3)))));
        chefs.add(new Chef(idChef(), "Ognen", "Sokoloski", "Jade ptici", new ArrayList<>(List.of(dishes.get(4)))));
    }
}
