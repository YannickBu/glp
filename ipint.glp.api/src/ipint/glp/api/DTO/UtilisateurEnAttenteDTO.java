package ipint.glp.api.DTO;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
public class UtilisateurEnAttenteDTO extends DTO implements Serializable {

	/**
	 * Identifiant de l'utilisateur en attente de validation.
	 */
	private Integer idUtilisateurEnAttente;

	/**
	 * Nom de l'utilisateur en attente de validation.
	 */
	@Pattern(regexp="[A-Za-z]+",message="Le nom saisi est invalide")
	private String nom;

	/**
	 * Prenom de l'utilisateur en attente de validation.
	 */
	@Pattern(regexp="[A-Za-z]+",message="Le prénom saisi est invalide")
	private String prenom;

	/**
	 * Diplome obtenu de l'utilisateur en attente de validation.
	 */
	@NotNull(message="Le dernier diplôme obtenu doit être saisi")
	@Size(min=1,message="Le dernier diplôme obtenu doit être saisi")
	private String diplome;

	/**
	 * Annee du diplome obtenu de l'utilisateur en attente de validation.
	 */
	@NotNull(message="L''année du diplôme doit être saisie")
	@Min(value=1950,message="L''année du diplôme est invalide")
	private Integer anneeDiplome;

	/**
	 * Email de l'utilisateur en attente de validation.
	 */
	@NotNull(message="L''email doit être saisi")
	@Size(min=1,message="L''email doit être saisi")
	private String email;

	/**
	 * Date de naissance de l'utilisateur en attente de validation.
	 */
	@NotNull(message="La date de naissance doit être saisie")
	@Past(message="La date de naissance est invalide")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateNaissance;

	/**
	 * Groupe de l'utilisateur en attente de validation.
	 */
	private GroupeDTO groupePrincipal;

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
	public Integer getAnneeDiplome() {
		return anneeDiplome;
	}

	/**
	 * @param anneeDiplome
	 *            the anneeDiplome to set
	 */
	public void setAnneeDiplome(Integer anneeDiplome) {
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