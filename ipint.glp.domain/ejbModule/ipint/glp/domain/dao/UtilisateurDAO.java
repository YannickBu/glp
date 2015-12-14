package ipint.glp.domain.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import ipint.glp.domain.entity.Utilisateur;

public class UtilisateurDAO extends DAO<Utilisateur> {

	private static final String PERSISTENCE_UNIT_NAME = "PU";
	private EntityManagerFactory emf;
	public EntityManager em;

	public UtilisateurDAO() {
		emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = emf.createEntityManager();
	}

	public Utilisateur create(final Utilisateur util) {

		em.getTransaction().begin();

		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setNom(util.getNom());
		utilisateur.setPrenom(util.getPrenom());
		utilisateur.setEmail(util.getEmail());

		em.persist(utilisateur);

		em.getTransaction().commit();

		return utilisateur;

	}

	public Utilisateur find(final Utilisateur utilisateurATrouver) {

		em.getTransaction().begin();

		Query q;

		if (utilisateurATrouver.getEmail() != null) {
			q = em.createQuery("select e from Utilisateur e where e.email = '" + utilisateurATrouver.getEmail() + "'");
		} else {
			q = em.createQuery("select e from Utilisateur e where e.idUtilisateur = '"
					+ utilisateurATrouver.getIdUtilisateur() + "'");

		}
		Utilisateur utilisateur = (Utilisateur) q.getSingleResult();

		em.getTransaction().commit();

		return utilisateur;

	}

	public void update(final Utilisateur ancienUtilisateur, final Utilisateur nouvelUtilisateur) {

		Utilisateur utilisateurMAJ = find(ancienUtilisateur);

		if (nouvelUtilisateur.getStatut() != null) {

			utilisateurMAJ.setStatut(nouvelUtilisateur.getStatut());
		}
		if (nouvelUtilisateur.getProfil() != null) {

			utilisateurMAJ.setProfil(nouvelUtilisateur.getProfil());
		}
		em.getTransaction().begin();

		em.persist(utilisateurMAJ);

		em.getTransaction().commit();

	}

	@Override
	public void delete(Utilisateur utilisateurASupprimer) {
		em.getTransaction().begin();
		if (utilisateurASupprimer.getIdUtilisateur() != null) {
			em.remove(utilisateurASupprimer);
		} else {
			if (utilisateurASupprimer.getEmail() != null) {
				Query q = em.createQuery(
						"delete from Utilisateur g where g.email = '" + utilisateurASupprimer.getEmail() + "'");
				q.executeUpdate();
			}
		}
		// TODO gérer les exceptions pour le cas où le nom et l'id sont tous les
		// deux à null

		em.getTransaction().commit();

	}

	@Override
	public List<Utilisateur> lister(Utilisateur obj) {
		em.getTransaction().begin();

		Query q = em.createQuery("select e from Utilisateur e");
		List<Utilisateur> lesUtilisateurs = q.getResultList();

		em.getTransaction().commit();

		return lesUtilisateurs;

	}

}