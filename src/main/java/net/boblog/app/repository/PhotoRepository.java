package net.boblog.app.repository;

import net.boblog.app.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by dave on 16/6/21.
 */
@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {

}
