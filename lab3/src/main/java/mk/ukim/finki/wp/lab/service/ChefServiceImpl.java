package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.ChefGender;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.repository.ChefRepository;
import mk.ukim.finki.wp.lab.repository.DishRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChefServiceImpl implements ChefService {
    
    private final ChefRepository chefRepository;
    private final DishRepository dishRepository;

    public ChefServiceImpl(ChefRepository chefRepository, DishRepository dishRepository) {
        this.chefRepository = chefRepository;
        this.dishRepository = dishRepository;
    }
    
    @Override
    public List<Chef> listChefs() {
        return chefRepository.findAll();
    }
    
    @Override
    public Chef findById(Long id) {
        return chefRepository.findById(id).orElse(null);
    }
    
    @Override
    public Chef addDishToChef(Long chefId, Long dishId) {
        Chef chef = chefRepository.findById(chefId).orElse(null);
        Dish dish = dishRepository.findById(dishId).orElse(null);
        assert chef != null;
        chef.saveDish(dish);
        chefRepository.save(chef);
        return chef;
    }

    @Override
    public Chef create(List<Dish> dishes, String bio, String firstName, String lastName, ChefGender gender) {
        Chef c = new Chef(dishes, bio, firstName, lastName, gender);
        chefRepository.save(c);
        return c;
    }

    @Override
    public Chef update(Long id, List<Dish> dishes, String bio, String firstName, String lastName, ChefGender gender) {
        Chef c = chefRepository.findById(id).orElse(null);
        c.setFirstName(firstName);
        c.setLastName(lastName);
        c.setBio(bio);
        c.setDishes(dishes);
        c.setGender(gender);

        chefRepository.save(c);

        return c;
    }

    @Override
    public void delete(Long id) {
        chefRepository.deleteById(id);
    }
}
