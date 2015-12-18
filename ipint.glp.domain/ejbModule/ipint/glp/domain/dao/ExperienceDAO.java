package ipint.glp.domain.dao;

import ipint.glp.domain.entity.Experience;

public class ExperienceDAO extends DAO<Experience> {

//	@Override
//	public Experience find(Experience experienceATrouver) {
//		em.getTransaction().begin();
//		Experience experience = null;
//		if (experienceATrouver.getIdExperience() != null) {
//			Query q = em.createQuery(
//					"select e from Experience e where e.idExperience = '" + experienceATrouver.getIdExperience() + "'");
//			experience = (Experience) q.getSingleResult();
//		}
//		// TODO lever une exception si id = null
//		em.getTransaction().commit();
//		return experience;
//	}
//
//	@Override
//	public Experience create(Experience experience) {
//		em.getTransaction().begin();
//		em.persist(experience);
//		em.getTransaction().commit();
//		return experience;
//
//	}
//
//	@Override
//	public Experience update(Experience ancienneExperience, Experience nouvelleExperience) {
//		Experience experienceMAJ = find(ancienneExperience);
//
//		if (nouvelleExperience.getAnneeDebut() != null) {
//			experienceMAJ.setAnneeDebut(nouvelleExperience.getAnneeDebut());
//		}
//
//		if (nouvelleExperience.getAnneFin() != null) {
//			experienceMAJ.setAnneFin(nouvelleExperience.getAnneFin());
//		}
//
//		if (nouvelleExperience.getEntreprise() != null) {
//			experienceMAJ.setEntreprise(nouvelleExperience.getEntreprise());
//		}
//
//		if (nouvelleExperience.getPoste() != null) {
//			experienceMAJ.setPoste(nouvelleExperience.getPoste());
//		}
//
//		if (nouvelleExperience.getLieu() != null) {
//			experienceMAJ.setLieu(nouvelleExperience.getLieu());
//		}
//
//		if (nouvelleExperience.getDescription() != null) {
//			experienceMAJ.setDescription(nouvelleExperience.getDescription());
//		}
//		em.getTransaction().begin();
//
//		em.persist(experienceMAJ);
//
//		em.getTransaction().commit();
//		
//		return experienceMAJ;
//
//	}
//
//	@Override
//	public void delete(Experience experienceASupprimer) {
//		em.getTransaction().begin();
//		if (experienceASupprimer.getIdExperience() != null) {
//			em.remove(experienceASupprimer);
//		}
//		// TODO gérer les exceptions pour le cas où l'id est null
//		em.getTransaction().commit();
//
//	}
//
//	@Override
//	public List<Experience> lister() {
//		em.getTransaction().begin();
//
//		Query q = em.createQuery("select e from Experience e");
//		List<Experience> lesExperiences = q.getResultList();
//
//		em.getTransaction().commit();
//
//		return lesExperiences;
//	}

}