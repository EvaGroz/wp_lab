package mk.ukim.finki.wp.lab.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    protected String dishId;
    protected String name;
    protected String cuisine;
    protected int preparationTime;

    @ManyToOne
    @JoinColumn(name = "chef_id")
    private Chef chef;
    
    
    public Dish(String dishId, String name, String cuisine, int preparationTime) {
        this.dishId = dishId;
        this.name = name;
        this.cuisine = cuisine;
        this.preparationTime = preparationTime;
    }
    
    public Dish() {
    
    }
}
