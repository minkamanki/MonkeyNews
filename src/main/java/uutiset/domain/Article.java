package uutiset.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;
//
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Article extends AbstractPersistable<Long> {

    private Date date;
    private String title;
    private String lede;
    @Column(length = 100000)
    private String text;
    //Monen suhde moneen kirjoittajien suhteen.
    @ManyToMany
    private List<Author> authors;
    //Yhden suhde yhteen kuvien kanssa.
    @OneToOne
    private Picture content;
    //Monen suhde moneen kategorioiden suhteen.
    @ManyToMany(mappedBy = "articles")
    private List<Category> categories;
    private int readCount;
}
