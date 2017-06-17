package br.com.tgolopes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import br.com.tgolopes.entity.TorcedorCampanha;
import br.com.tgolopes.repository.TorcedorCampanhaRepository;

@Service
@Validated
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class TorcedorCampanhaService {
	
	@Autowired
	private TorcedorCampanhaRepository torcedorCampanhaRepository;
	
	@Transactional
	public TorcedorCampanha salvar(final TorcedorCampanha torcedorCampanha){
		return this.torcedorCampanhaRepository.save(torcedorCampanha);
	}
	
	@Transactional
	public void deletar(final TorcedorCampanha torcedorCampanha) {
		this.torcedorCampanhaRepository.delete(torcedorCampanha);
	}
	
	public TorcedorCampanha pesquisarPorId(final Long id) {
		return this.torcedorCampanhaRepository.findOne(id);
	}
	
	public List<TorcedorCampanha> pesquisarTodas(){
		return this.torcedorCampanhaRepository.findAll();
	}
	
	public List<TorcedorCampanha> buscarPorIdCampanha(Long idCampanha){
		return this.torcedorCampanhaRepository.findByIdCampanha(idCampanha);
	}
	
	public List<TorcedorCampanha> buscarPorEmail(String email){
		return this.torcedorCampanhaRepository.findByEmail(email);
	}
}