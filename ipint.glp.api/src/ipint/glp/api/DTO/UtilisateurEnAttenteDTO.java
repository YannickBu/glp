package ipint.glp.api.DTO;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
public class UtilisateurEnAttenteDTO extends DTO implements Serializable {

	/**
	 * Identifiant de l'utilisateur en attente de validation.
	 */
	private Integer idUtilisateurEnAttente;

	/**
	 * Nom de l'utilisateur en attente de validation.
	 */
	private String nom;

	/**
	 * Prenom de l'utilisateur en attente de validation.
	 */
	private String prenom;

	/**
	 * Diplome obtenu de l'utilisateur en attente de validation.
	 */
	private String diplome;

	/**
	 * Annee du diplome obtenu de l'utilisateur en attente de validation.
	 */
	private int anneeDiplome;

	/**
	 * Groupe de l'utilisateur en attente de validation.
	 */
	private GroupeDTO groupePrincipal;

	/**
	 * Email de l'utilisateur en attente de validation.
	 */
	private String email;

	/**
	 * Date de naissance de l'utilisateur en attente de validation.
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateNaissance;

	/**
	 * @return the idUtilisateurEnAttente
	 */
	public Integer getIdUtilisateurEnAttente() {
		return idUtilisateurEnAttente;
	}

	/**
	 * @param idUtilisateurEnAttente
	 *            the idUtilisateurEnAttente to set
	 */
	public void setIdUtilisateurEnAttente(Integer idUtilisateurEnAttente) {
		this.idUtilisateurEnAttente = idUtilisateurEnAttente;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom
	 *            the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * @param prenom
	 *            the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * @return the diplome
	 */
	public String getDiplome() {
		return diplome;
	}

	/**
	 * @param diplome
	 *            the diplome to set
	 */
	public void setDiplome(String diplome) {
		this.diplome = diplome;
	}

	/**
	 * @return the anneeDiplome
	 */
	public int getAnneeDiplome() {
		return anneeDiplome;
	}

	/**
	 * @param anneeDiplome
	 *            the anneeDiplome to set
	 */
	public void setAnneeDiplome(int anneeDiplome) {
		this.anneeDiplome = anneeDiplome;
	}

	/**
	 * @return the groupePrincipal
	 */
	public GroupeDTO getGroupePrincipal() {
		return groupePrincipal;
	}

	/**
	 * @param groupePrincipal
	 *            the groupePrincipal to set
	 */
	public void setGroupePrincipal(GroupeDTO groupePrincipal) {
		this.groupePrincipal = groupePrincipal;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the dateNaissance
	 */
	public Date getDateNaissance() {
		return dateNaissance;
	}

	/**
	 * @param dateNaissance
	 *            the dateNaissance to set
	 */
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

}
