package returnevolved.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
public class Recipe {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    private UUID id;

    @Column(length = 1000)
    private String recipeStory;

    @OneToMany
    private Set<RecipeNote> notes;

    @OneToMany
    private Set<Ingredient> ingredients;

    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    @ManyToMany
    private List<Category> categories;





}
