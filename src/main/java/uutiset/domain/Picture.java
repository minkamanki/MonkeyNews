package uutiset.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

//Kuvan luokka
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Picture extends AbstractPersistable<Long> {

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] content;
}
