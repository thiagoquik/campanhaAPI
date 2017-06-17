package br.com.tgolopes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.tgolopes.entity.Campanha;

@Repository
public interface CampanhaRepository extends JpaRepository<Campanha, Long>{
	List<Campanha> findByIdTimeCoracao(Long codigoTime);
}
