package com.stackroute.repository;

import com.stackroute.domain.Muzix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //Creates a repository by extending JpaRepository
public interface MuzixRepository extends JpaRepository<Muzix,Integer> {
    @Query("SELECT t FROM Track t WHERE name = ?1")
    List<Muzix> getTrackByName(String name);
}
