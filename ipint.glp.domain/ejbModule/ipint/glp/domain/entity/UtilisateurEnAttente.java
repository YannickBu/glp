package ipint.glp.domain.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Classe definissant un utilisateur en attente de validation par un modérateur.
 * 
 * @author declerck
 *
 */
@Entity
public class UtilisateurEnAttente implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Identifiant de l'utilisateur en attente de validation.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	private Integer anneeDiplome;

	/**
	 * Groupe de l'utilisateur en attente de validation.
	 */
	private Groupe groupePrincipal;

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
	public void setIdUtilisateurEnAttente(int idUtilisateurEnAttente) {
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
	public Groupe getGroupePrincipal() {
		return groupePrincipal;
	}

	/**
	 * @param groupePrincipal
	 *            the groupePrincipal to set
	 */
	public void setGroupePrincipal(Groupe groupePrincipal) {
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

	@Override
	public String toString(){
		return "[UtilisateurEnAttenteDTO - id="+this.idUtilisateurEnAttente+", email="+this.email+"]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((idUtilisateurEnAttente == null) ? 0 : idUtilisateurEnAttente.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UtilisateurEnAttente other = (UtilisateurEnAttente) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (idUtilisateurEnAttente == null) {
			if (other.idUtilisateurEnAttente != null)
				return false;
		} else if (!idUtilisateurEnAttente.equals(other.idUtilisateurEnAttente))
			return false;
		return true;
	}
	
	

}