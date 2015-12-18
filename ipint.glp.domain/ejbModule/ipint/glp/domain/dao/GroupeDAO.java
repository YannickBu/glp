package ipint.glp.domain.dao;

import javax.persistence.Query;

import ipint.glp.domain.entity.Groupe;

public class GroupeDAO extends DAO<Groupe> {

//	private static final String PERSISTENCE_UNIT_NAME = "PU";
//	private EntityManagerFactory emf;
//	public EntityManager em;
//
//	public GroupeDAO() {
//		emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
//		em = emf.createEntityManager();
//	}
//
//	public Groupe create(final Groupe groupe) {
//
//		em.getTransaction().begin();
//
//		em.persist(groupe);
//
//		em.getTransaction().commit();
//
//		return groupe;
//
//	}
//
	public Groupe find(final Groupe groupeATrouver) {
		Groupe groupe = null;

		if (groupeATrouver.getNomGroupe() != null) {
			Query q = em.createQuery("select g from Groupe g where g.nomGroupe = '" + groupeATrouver.getNomGroupe() + "'");
			groupe = (Groupe) q.getSingleResult();
		} else {
			groupe = em.find(Groupe.class, groupeATrouver.getIdGroupe());
		}
		
		return groupe;

	}
//
//	public Groupe update(final Groupe ancienGroupe, final Groupe nouveauGroupe) {
//
//		Groupe groupeMAJ = find(ancienGroupe);
//
//		if (nouveauGroupe.getDescription() != null) {
//
//			groupeMAJ.setDescription(nouveauGroupe.getDescription());
//		}
//		if (ancienGroupe.getUtilisateurResponsable() != null) {
//
//			groupeMAJ.setUtilisateurResponsable(nouveauGroupe.getUtilisateurResponsable());
//		}
//		em.getTransaction().begin();
//
//		em.persist(groupeMAJ);
//
//		em.getTransaction().commit();
//		
//		return groupeMAJ;
//
//	}
//
//	@Override
//	public void delete(Groupe groupeASupprimer) {
//
//		em.getTransaction().begin();
//		if (groupeASupprimer.getIdGroupe() != null) {
//			em.remove(groupeASupprimer);
//		} else {
//			if (groupeASupprimer.getNomGroupe() != null) {
//				Query q = em.createQuery(
//						"delete from Groupe g where g.nomGroupe = '" + groupeASupprimer.getNomGroupe() + "'");
//				q.executeUpdate();
//			}
//		}
//		// TODO gérer les exceptions pour le cas où le nom et l'id sont tous les
//		// deux à null
//
//		em.getTransaction().commit();
//	}
//
//	@Override
//	public List<Groupe> lister() {
//		em.getTransaction().begin();
//
//		Query q = em.createQuery("select e from Groupe e");
//		List<Groupe> lesGroupes = q.getResultList();
//
//		em.getTransaction().commit();
//
//		return lesGroupes;
//
//	}

}