package uutiset.domain;

import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
//import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Article extends AbstractPersistable<Long> {

    private String title;
    private String lede;
    @Column(length = 100000)
    private String text;
    @ManyToMany
    private List<Author> authors;
//    @Lob
    @Column(length = 10000000)
    @Basic(fetch = FetchType.LAZY)
    private byte[] content;
    @ManyToMany(mappedBy = "articles")
    private List<Category> categories;
}
