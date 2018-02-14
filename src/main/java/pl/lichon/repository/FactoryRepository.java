package pl.lichon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.lichon.entity.Factory;

public interface FactoryRepository extends JpaRepository<Factory, Long> {

}
