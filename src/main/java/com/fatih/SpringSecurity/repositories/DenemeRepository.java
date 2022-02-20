package com.fatih.SpringSecurity.repositories;

import com.fatih.SpringSecurity.models.Deneme;
import com.fatih.SpringSecurity.models.Deneme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DenemeRepository extends JpaRepository<Deneme, Long> {

    Deneme findById(long id);

}
