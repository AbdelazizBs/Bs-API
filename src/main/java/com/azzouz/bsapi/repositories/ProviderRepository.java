package com.azzouz.bsapi.repositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.azzouz.bsapi.entities.Provider;
@Repository
public interface ProviderRepository extends CrudRepository<Provider, Long> {
}