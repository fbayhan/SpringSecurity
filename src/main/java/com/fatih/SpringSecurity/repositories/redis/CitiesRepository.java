package com.fatih.SpringSecurity.repositories.redis;


import com.fatih.SpringSecurity.models.redis.Cities;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitiesRepository extends CrudRepository<Cities, Long> {
}
