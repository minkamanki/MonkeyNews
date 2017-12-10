package uutiset.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import  uutiset.domain.Picture;

public interface PictureRepository  extends JpaRepository<Picture, Long>{
    //Liittää Long tyyppisen id:n kuvaan.
}
