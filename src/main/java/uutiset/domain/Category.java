package uutiset.domain;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;
//Kategorian luokka.
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Category extends AbstractPersistable<Long>{
    
    private String name;
    private boolean nav;
    //Monen suhde moneen artikkeleiden kanssa.
    @ManyToMany
    private List<Article> articles;
}
