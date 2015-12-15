package ipint.glp.domain.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.datatype.DatatypeConfigurationException;

public class Test {

	private static final String PERSISTENCE_UNIT_NAME = "PU";
	private EntityManagerFactory emf;
	public EntityManager em;

	public Test() {
		emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = emf.createEntityManager();
	}

	public static void main(String[] args) throws DatatypeConfigurationException {

		/*
		 * // Les DAO UtilisateurDAO utilisateurDao = new UtilisateurDAO();
		 * ProfilDAO profilDao = new ProfilDAO(); ExperienceDAO experienceDao =
		 * new ExperienceDAO(); ArticleDAO articleDao = new ArticleDAO();
		 * GroupeDAO groupeDao = new GroupeDAO();
		 * 
		 * // Test Create Utilisateur Utilisateur utilisateur = new
		 * Utilisateur(); utilisateur.setNom("declerck");
		 * utilisateur.setPrenom("rodolphe");
		 * utilisateur.setEmail("rodolphe.declerck@gmail.com");
		 * utilisateurDao.create(utilisateur);
		 * 
		 * // Test Find Utilisateur Utilisateur trouve =
		 * utilisateurDao.find(utilisateur);
		 * System.out.println(trouve.getStatut());
		 * 
		 * // Test Update Utilisateur Utilisateur utilisateur2 = new
		 * Utilisateur(); utilisateur2.setStatut(Statut.DIPLOME);
		 * utilisateurDao.update(utilisateur, utilisateur2);
		 * System.out.println(trouve.getStatut());
		 * 
		 * // Test Lister Utilisateur for (Utilisateur u :
		 * utilisateurDao.lister()) { System.out.println(u.getNom()); }
		 * 
		 * // Test Supprimer Utilisateur // utilisateurDao.delete(utilisateur);
		 * 
		 * // Test Create Profil Profil profil = new Profil();
		 * profil.setCursus("miage"); profil.setCompetence("Scrum");
		 * profil.setCentreInteret("VolleyBall"); // TODO format telephone à
		 * voir // profil.setTelephone("0659774852"); profilDao.create(profil);
		 * 
		 * // Test Find Profil Profil profilTrouve = profilDao.find(profil);
		 * System.out.println(profilTrouve.getCentreInteret());
		 * 
		 * // Test Update Profil Profil profil2 = new Profil();
		 * profil2.setCompetence("JAVA"); profilDao.update(profil, profil2);
		 * System.out.println(profilTrouve.getCompetence());
		 * 
		 * // Test Lister Profil for (Profil p : profilDao.lister()) {
		 * System.out.println(p.getCursus()); }
		 * 
		 * // Test Supprimer Utilisateur // profilDao.delete(profil);
		 * 
		 * // Test Create Experience Experience experience = new Experience();
		 * experience.setAnneeDebut(2012); experience.setAnneFin(2012);
		 * experience.setEntreprise("Capgemini"); experience.setPoste(
		 * "Developpeur JAVA"); experience.setLieu("Lille");
		 * experience.setDescription("developpement de site de suivi de projets"
		 * ); experienceDao.create(experience);
		 * 
		 * // Test Find Experience Experience experienceTrouve =
		 * experienceDao.find(experience);
		 * System.out.println(experienceTrouve.getEntreprise());
		 * 
		 * // Test Update Experience Experience experience2 = new Experience();
		 * experience2.setEntreprise("CGI"); experienceDao.update(experience,
		 * experience2); System.out.println(experienceTrouve.getEntreprise());
		 * 
		 * // Test Lister Experience for (Experience p : experienceDao.lister())
		 * { System.out.println(p.getDescription()); }
		 * 
		 * // Test Supprimer Experience // experienceDao.delete(experience);
		 * 
		 * // Test Create Article Article article = new Article();
		 * article.setUtilisateur(utilisateur); GregorianCalendar
		 * datePublication = new GregorianCalendar(1993, 10, 27);
		 * XMLGregorianCalendar xgcal =
		 * DatatypeFactory.newInstance().newXMLGregorianCalendar(datePublication
		 * ); article.setDatePublication(datePublication); article.setContenu(
		 * "Hello world !"); articleDao.create(article);
		 * 
		 * // Test Find Article Article articleTrouve =
		 * articleDao.find(article);
		 * System.out.println(articleTrouve.getDatePublication().getTime());
		 * 
		 * // Test Update Article Article article2 = new Article();
		 * article2.setContenu("un article"); articleDao.update(article,
		 * article2); System.out.println(articleTrouve.getContenu());
		 * 
		 * // Test Lister Article for (Article p : articleDao.lister()) {
		 * System.out.println(p.getDatePublication().getTime());
		 * System.out.println(p.getUtilisateur().getEmail()); }
		 * 
		 * // Test Supprimer Article // articleDao.delete(article);
		 * 
		 * // Test Create Groupe Groupe groupe = new Groupe();
		 * groupe.setDescription("Groupe MIAGE FI");
		 * groupe.setNomGroupe("MIAGE"); groupeDao.create(groupe);
		 * 
		 * // Test Find Groupe Groupe groupeTrouve = groupeDao.find(groupe);
		 * System.out.println(groupeTrouve.getDescription());
		 * 
		 * // Test Update Groupe Groupe groupe2 = new Groupe();
		 * groupe2.setDescription("Groupe MIAGE FA"); ; groupeDao.update(groupe,
		 * groupe2); System.out.println(groupeTrouve.getDescription());
		 * 
		 * // Test Lister Groupe for (Groupe p : groupeDao.lister()) {
		 * System.out.println(p.getNomGroupe()); }
		 * 
		 * // Test Supprimer Groupe // groupeDao.delete(groupe);
		 */
	}

}
