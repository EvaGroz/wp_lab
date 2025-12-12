package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Chef {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String firstName;
    protected String lastName;
    protected ChefGender gender;
    protected String bio;

    @OneToMany(mappedBy = "chef", cascade = CascadeType.REMOVE)
    protected List<Dish> dishes;
    
    public Chef(List<Dish> dishes, String bio, String firstName, String lastName, ChefGender gender) {
        this.dishes = dishes;
        this.bio = bio;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }
    
    public Chef() {
    
    }
    
    public void saveDish(Dish dish) {
        dishes.add(dish);
        dish.setChef(this);
    }
}
