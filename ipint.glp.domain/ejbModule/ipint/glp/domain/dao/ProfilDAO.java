package ipint.glp.domain.dao;

import java.util.List;

import javax.persistence.Query;

import ipint.glp.domain.entity.Profil;

public class ProfilDAO extends DAO<Profil> {

	@Override
	public Profil find(Profil obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Profil create(Profil profil) {

		em.getTransaction().begin();

		em.persist(profil);

		em.getTransaction().commit();

		return profil;
	}

	@Override
	public void update(Profil ancienProfil, Profil noouvelProfil) {
		Profil profilMAJ = find(ancienProfil);

		if (noouvelProfil.getCentreInteret() != null) {
			profilMAJ.setCentreInteret(noouvelProfil.getCentreInteret());
		}
		if (noouvelProfil.getCompetence() != null) {
			profilMAJ.setCompetence(noouvelProfil.getCompetence());
		}
		if (noouvelProfil.getCursus() != null) {
			profilMAJ.setCursus(noouvelProfil.getCursus());
		}
		if (noouvelProfil.getExperiences() != null) {
			profilMAJ.setExperiences(noouvelProfil.getExperiences());
		}
		if (noouvelProfil.getTelephone() != null) {
			profilMAJ.setTelephone(noouvelProfil.getTelephone());
		}
		em.getTransaction().begin();

		em.persist(profilMAJ);

		em.getTransaction().commit();

	}

	@Override
	public void delete(Profil profilASupprimer) {

		em.getTransaction().begin();
		if (profilASupprimer.getIdProfil() != null) {
			em.remove(profilASupprimer);
		}
		// TODO gérer les exceptions pour le cas où l'id est null
		em.getTransaction().commit();

	}

	@Override
	public List<Profil> lister() {
		em.getTransaction().begin();

		Query q = em.createQuery("select e from Profil e");
		List<Profil> lesProfils = q.getResultList();

		em.getTransaction().commit();

		return lesProfils;

	}

}
