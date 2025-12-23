package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor; //za enum dop
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DishRepository extends JpaRepository<Dish, Long>{
    Dish findByDishId(String dishId);
    List<Dish> findAllByChef_Id(Long chefId);
}
