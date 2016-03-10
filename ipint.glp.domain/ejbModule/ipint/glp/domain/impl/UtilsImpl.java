package ipint.glp.domain.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ipint.glp.api.DTO.PaysDTO;
import ipint.glp.api.DTO.RegionDTO;
import ipint.glp.api.DTO.VilleDTO;
import ipint.glp.api.exception.MetierException;
import ipint.glp.api.itf.UtilsService;
import ipint.glp.domain.entity.Pays;
import ipint.glp.domain.entity.Region;
import ipint.glp.domain.entity.Ville;
import ipint.glp.domain.util.MappingToDTO;

/**
 * Gestion des pays et région
 * 
 * @author mame
 *
 */
@Stateless
public class UtilsImpl implements UtilsService{

	@PersistenceContext(unitName = "PU")
	private EntityManager em;

	public UtilsImpl() {
		super();
	}

	public UtilsImpl(EntityManager em) {
		super();
		this.em = em;
	}

	@Override
	public List<PaysDTO> listerPaysDuMonde() throws MetierException {
		Query q = em.createQuery("select p from Pays p");
		List<Pays> pays = q.getResultList();
		List<PaysDTO> paysDto = new ArrayList<PaysDTO>();
		for(Pays pay : pays){
			paysDto.add(MappingToDTO.paysToPaysDTO(pay));
		}
		return paysDto;
	}

	@Override
	public List<RegionDTO> listerRegions() throws MetierException {
		//on n'affiche les régions que si le pays est la France (F)
		Query q = em.createQuery("select r from Region r where r.pays = 'F'");
		List<Region> regions = q.getResultList();
		List<RegionDTO> regionDto = new ArrayList<RegionDTO>();
		for(Region r : regions){
			regionDto.add(MappingToDTO.regionToRegionDTO(r));
		}
		return regionDto;
	}

	@Override
	public List<VilleDTO> listerVilles() throws MetierException {
		Query q = em.createQuery("select v from Ville v");
		List<Ville> villes = q.getResultList();
		List<VilleDTO> villeDto = new ArrayList<VilleDTO>();
		for(Ville v : villes){
			villeDto.add(MappingToDTO.villeToVilleDTO(v));
		}
		return villeDto;
	}
	
	
}
