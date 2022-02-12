package returnevolved.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recipe {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    private UUID id;

    /** The title of the recipe */
    private String recipeTitle;

    /** A story about the recipe, such as where it's from family history etc */
    @Column(length = 1000)
    private String recipeStory;

    /** Any notes the recipe might contain */
    @OneToMany
    private Set<RecipeNote> notes;

    /** The ingredients required for the recipe */
    @OneToMany
    private Set<Ingredient> ingredients;

    /** The instructions steps for the recipe */
    @OneToMany
    private List<InstructionStep> instructionSteps;

    /** The difficulty of the recipe */
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    /** The list of categories this recipe has */
    @ManyToMany
    private List<Category> categories;

    /** The user who created and owns this recipe */
    @ManyToOne
    private User user;

    /** A public recipe can be viewed by any user */
    private Boolean publicRecipe;

    /** Shared users are who has access to this recipe */
    @ManyToMany
    private Set<User> sharedUsers;
}
