package net.boblog.app.repository;

import net.boblog.app.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dave on 16/6/20.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("from Category c where c.parentId is null")
    Page<Category> getRoots(Pageable pageable);

    @Query("from Category c where c.parentId = ?1")
    Page<Category> getChild(Long parentId, Pageable pageable);

    @Query("from Category c where c.leftId > ?1 and c.rightId < ?2")
    Page<Category> getDescendents(Long leftId, Long rightId, Pageable pageable);

    @Query("from Category c where c.leftId <= ?1 and c.rightId >= ?2")
    List<Category> getPath(Long leftId, Long rightId);


}
