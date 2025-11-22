package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.repository.ChefRepository;
import mk.ukim.finki.wp.lab.repository.DishRepository;
import mk.ukim.finki.wp.lab.service.ChefService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChefServiceImpl implements ChefService {
    private final ChefRepository chefRepository;
    private final DishRepository dishRepository;

    public ChefServiceImpl(ChefRepository ch, DishRepository d) {
        this.chefRepository = ch;
        this.dishRepository = d;
    }

    @Override
    public List<Chef> listChefs() {
        return this.chefRepository.findAll();
    }

    @Override
    public Chef findById(Long id) {
        return this.chefRepository.findById(id).orElse(null);
    }



    @Override
    public Chef addDishToChef(Long chefId, String dishId) {
        Chef chef = this.findById(chefId);
        Dish dish = dishRepository.findByDishId(dishId);

        if (chef == null || dish == null) {
            return null;
        }
//        chef.getDishes().stream().filter(dishtmp -> dishtmp.getDishId().equals(dishId)).findFirst().orElse(chef.getDishes().add(dish));

        // gornoto zakomentirano ne raboti, ako sakam vaka da proveram dali go ima dishot treba vaka
//        for (Dish d : chef.getDishes()) {
//            if (d.getDishId().equals(dishId)) {
//                return chef;
//            }
//        }

        chef.getDishes().add(dish);
        chefRepository.save(chef);

        return chef;
    }


}
