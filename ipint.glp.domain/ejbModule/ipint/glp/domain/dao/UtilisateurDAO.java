package ipint.glp.domain.dao;

import javax.persistence.Query;

import ipint.glp.domain.entity.Utilisateur;

public class UtilisateurDAO extends DAO<Utilisateur> {

//	private static final String PERSISTENCE_UNIT_NAME = "PU";
//	private EntityManagerFactory emf;
//	public EntityManager em;
//
//	public UtilisateurDAO() {
//		emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
//		em = emf.createEntityManager();
//	}
//
//	public Utilisateur create(final Utilisateur util) {
//
//		em.getTransaction().begin();
//
//		em.persist(util);
//
//		em.getTransaction().commit();
//
//		return util;
//
//	}

	public Utilisateur find(final Utilisateur utilisateurATrouver) {
		Utilisateur utilisateur = null;

		if (utilisateurATrouver.getEmail() != null) {
			Query q = em.createQuery("select e from Utilisateur e where e.email = '" + utilisateurATrouver.getEmail() + "'");
			utilisateur = (Utilisateur) q.getSingleResult();
		} else {
			utilisateur = em.find(Utilisateur.class, utilisateurATrouver.getIdUtilisateur());
		}

		return utilisateur;

	}
//
//	public Utilisateur update(final Utilisateur ancienUtilisateur, final Utilisateur nouvelUtilisateur) {
//
//		Utilisateur utilisateurMAJ = find(ancienUtilisateur);
//
//		if (nouvelUtilisateur.getStatut() != null) {
//
//			utilisateurMAJ.setStatut(nouvelUtilisateur.getStatut());
//		}
//		if (nouvelUtilisateur.getProfil() != null) {
//
//			utilisateurMAJ.setProfil(nouvelUtilisateur.getProfil());
//		}
//		em.getTransaction().begin();
//
//		em.persist(utilisateurMAJ);
//
//		em.getTransaction().commit();
//		
//		return utilisateurMAJ;
//
//	}
//
//	@Override
//	public void delete(Utilisateur utilisateurASupprimer) {
//		em.getTransaction().begin();
//		if (utilisateurASupprimer.getIdUtilisateur() != null) {
//			em.remove(utilisateurASupprimer);
//		} else {
//			if (utilisateurASupprimer.getEmail() != null) {
//				Query q = em.createQuery(
//						"delete from Utilisateur g where g.email = '" + utilisateurASupprimer.getEmail() + "'");
//				q.executeUpdate();
//			}
//		}
//		// TODO gérer les exceptions pour le cas où le nom et l'id sont tous les
//		// deux à null
//
//		em.getTransaction().commit();
//
//	}
//
//	@Override
//	public List<Utilisateur> lister() {
//		em.getTransaction().begin();
//
//		Query q = em.createQuery("select e from Utilisateur e");
//		List<Utilisateur> lesUtilisateurs = q.getResultList();
//
//		em.getTransaction().commit();
//
//		return lesUtilisateurs;
//
//	}

}