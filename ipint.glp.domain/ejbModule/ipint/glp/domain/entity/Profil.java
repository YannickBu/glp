package ipint.glp.domain.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Profil {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idProfil;
	// @Temporal(TemporalType.DATE)
	// @javax.validation.constraints.Past(message="Date de naissance invalide")
	// private Calendar dateNaiss;
	// @javax.validation.constraints.NotNull(message = "Veuillez remplir le
	// champ cursus")
	// private String cursus;
	@OneToMany(mappedBy = "profil")
	private List<Diplome> diplomes;
	@OneToMany(mappedBy = "profil")
	private List<Competence> competence;
	private String centreInteret;
	// TODO peut être le mettre en unique non ?
	// @javax.validation.constraints.Pattern(regexp = "#^0[1-9][0-9]{10}$#",
	// message = "Téléphone invalide")
	private String telephone;
	@OneToMany(mappedBy = "profil")
	private List<Experience> experiences;
	@OneToMany(mappedBy = "profil")
	private List<ReseauSocial> reseauxSociaux;
	private String diplomePrincipal;
	private int anneeDiplome;
	private String mesAttentes;
	private String situation;
	//lieu de la situation / exemple si recherche de stage, c'est le lieu où l'on cherche et si en activité, le lieu de l'entreprise actuelle
	private String lieuSituation;

	public Profil() {
		this.competence = new ArrayList<Competence>();
		this.diplomes = new ArrayList<Diplome>();
		this.experiences = new ArrayList<Experience>();
		this.reseauxSociaux = new ArrayList<ReseauSocial>();
	}

	/**
	 * @return the idProfil
	 */
	public Integer getIdProfil() {
		return idProfil;
	}

	/**
	 * @param idProfil the idProfil to set
	 */
	public void setIdProfil(Integer idProfil) {
		this.idProfil = idProfil;
	}

	/**
	 * @return the diplomes
	 */
	public List<Diplome> getDiplomes() {
		return diplomes;
	}

	/**
	 * @param diplomes the diplomes to set
	 */
	public void setDiplomes(List<Diplome> diplomes) {
		this.diplomes = diplomes;
	}

	/**
	 * @return the competence
	 */
	public List<Competence> getCompetence() {
		return competence;
	}

	/**
	 * @param competence the competence to set
	 */
	public void setCompetence(List<Competence> competence) {
		this.competence = competence;
	}

	/**
	 * @return the centreInteret
	 */
	public String getCentreInteret() {
		return centreInteret;
	}

	/**
	 * @param centreInteret the centreInteret to set
	 */
	public void setCentreInteret(String centreInteret) {
		this.centreInteret = centreInteret;
	}

	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * @return the experiences
	 */
	public List<Experience> getExperiences() {
		return experiences;
	}

	/**
	 * @param experiences the experiences to set
	 */
	public void setExperiences(List<Experience> experiences) {
		this.experiences = experiences;
	}

	/**
	 * @return the reseauxSociaux
	 */
	public List<ReseauSocial> getReseauxSociaux() {
		return reseauxSociaux;
	}

	/**
	 * @param reseauxSociaux the reseauxSociaux to set
	 */
	public void setReseauxSociaux(List<ReseauSocial> reseauxSociaux) {
		this.reseauxSociaux = reseauxSociaux;
	}

	/**
	 * @return the diplomePrincipal
	 */
	public String getDiplomePrincipal() {
		return diplomePrincipal;
	}

	/**
	 * @param diplomePrincipal the diplomePrincipal to set
	 */
	public void setDiplomePrincipal(String diplomePrincipal) {
		this.diplomePrincipal = diplomePrincipal;
	}

	/**
	 * @return the anneeDiplome
	 */
	public int getAnneeDiplome() {
		return anneeDiplome;
	}

	/**
	 * @param anneeDiplome the anneeDiplome to set
	 */
	public void setAnneeDiplome(int anneeDiplome) {
		this.anneeDiplome = anneeDiplome;
	}

	/**
	 * @return the mesAttentes
	 */
	public String getMesAttentes() {
		return mesAttentes;
	}

	/**
	 * @param mesAttentes the mesAttentes to set
	 */
	public void setMesAttentes(String mesAttentes) {
		this.mesAttentes = mesAttentes;
	}

	/**
	 * @return the situation
	 */
	public String getSituation() {
		return situation;
	}

	/**
	 * @param situation the situation to set
	 */
	public void setSituation(String situation) {
		this.situation = situation;
	}

	public String getLieuSituation() {
		return lieuSituation;
	}

	public void setLieuSituation(String lieuSituation) {
		this.lieuSituation = lieuSituation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + anneeDiplome;
		result = prime * result + ((centreInteret == null) ? 0 : centreInteret.hashCode());
		result = prime * result + ((competence == null) ? 0 : competence.hashCode());
		result = prime * result + ((diplomePrincipal == null) ? 0 : diplomePrincipal.hashCode());
		result = prime * result + ((diplomes == null) ? 0 : diplomes.hashCode());
		result = prime * result + ((experiences == null) ? 0 : experiences.hashCode());
		result = prime * result + ((idProfil == null) ? 0 : idProfil.hashCode());
		result = prime * result + ((lieuSituation == null) ? 0 : lieuSituation.hashCode());
		result = prime * result + ((mesAttentes == null) ? 0 : mesAttentes.hashCode());
		result = prime * result + ((reseauxSociaux == null) ? 0 : reseauxSociaux.hashCode());
		result = prime * result + ((situation == null) ? 0 : situation.hashCode());
		result = prime * result + ((telephone == null) ? 0 : telephone.hashCode());
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
		Profil other = (Profil) obj;
		if (anneeDiplome != other.anneeDiplome)
			return false;
		if (centreInteret == null) {
			if (other.centreInteret != null)
				return false;
		} else if (!centreInteret.equals(other.centreInteret))
			return false;
		if (competence == null) {
			if (other.competence != null)
				return false;
		} else if (!competence.equals(other.competence))
			return false;
		if (diplomePrincipal == null) {
			if (other.diplomePrincipal != null)
				return false;
		} else if (!diplomePrincipal.equals(other.diplomePrincipal))
			return false;
		if (diplomes == null) {
			if (other.diplomes != null)
				return false;
		} else if (!diplomes.equals(other.diplomes))
			return false;
		if (experiences == null) {
			if (other.experiences != null)
				return false;
		} else if (!experiences.equals(other.experiences))
			return false;
		if (idProfil == null) {
			if (other.idProfil != null)
				return false;
		} else if (!idProfil.equals(other.idProfil))
			return false;
		if (lieuSituation == null) {
			if (other.lieuSituation != null)
				return false;
		} else if (!lieuSituation.equals(other.lieuSituation))
			return false;
		if (mesAttentes == null) {
			if (other.mesAttentes != null)
				return false;
		} else if (!mesAttentes.equals(other.mesAttentes))
			return false;
		if (reseauxSociaux == null) {
			if (other.reseauxSociaux != null)
				return false;
		} else if (!reseauxSociaux.equals(other.reseauxSociaux))
			return false;
		if (situation == null) {
			if (other.situation != null)
				return false;
		} else if (!situation.equals(other.situation))
			return false;
		if (telephone == null) {
			if (other.telephone != null)
				return false;
		} else if (!telephone.equals(other.telephone))
			return false;
		return true;
	}

}