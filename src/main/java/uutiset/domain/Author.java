package uutiset.domain;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;
//Kirjoittaja luokka.
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Author extends AbstractPersistable<Long> {

    private String name;
    //Monen suhde moneen artikkeleiden kanssa.
    @ManyToMany(mappedBy = "authors")
    private List<Article> articles; 
}
