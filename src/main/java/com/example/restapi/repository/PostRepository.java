package com.example.restapi.repository;

import com.example.restapi.model.Post;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("Select p From Post p")
    List<Post> findAllPosts(Pageable page);

}
