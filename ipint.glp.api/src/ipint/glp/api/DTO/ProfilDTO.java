package ipint.glp.api.DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class ProfilDTO extends DTO implements Serializable {
	private static final long serialVersionUID = 1194522427659253560L;

	private Integer idProfil;
	@Temporal(TemporalType.DATE)
	@javax.validation.constraints.Past(message = "Date de naissance invalide")
	private Calendar dateNaiss;
	@javax.validation.constraints.NotNull(message = "Veuillez remplir le champ cursus")
	private String cursus;
	@OneToMany(mappedBy = "profil")
	private List<DiplomeDTO> diplomes;
	@OneToMany(mappedBy = "profil")
	private List<CompetenceDTO> competence;
	private String centreInteret;
	@javax.validation.constraints.Pattern(regexp = "#^0[1-9][0-9]{8}$#", message = "Téléphone invalide")
	private String telephone;
	@OneToMany(mappedBy = "profil")
	private List<ExperienceDTO> experiences;
	private String diplomePrincipal;
	private int anneeDiplome;
	private String mesAttentes;
	private String situation;
	//lieu de la situation / exemple si recherche de stage, c'est le lieu où l'on cherche et si en activité, le lieu de l'entreprise actuelle
	private String lieuSituation;

	@OneToMany(mappedBy = "profil")
	private List<ReseauSocialDTO> reseauxSociaux;

	public ProfilDTO() {
		this.diplomes = new ArrayList<DiplomeDTO>();
		this.experiences = new ArrayList<ExperienceDTO>();
		this.competence = new ArrayList<CompetenceDTO>();
		this.reseauxSociaux = new ArrayList<ReseauSocialDTO>();
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
	 * @return the dateNaiss
	 */
	public Calendar getDateNaiss() {
		return dateNaiss;
	}

	/**
	 * @param dateNaiss the dateNaiss to set
	 */
	public void setDateNaiss(Calendar dateNaiss) {
		this.dateNaiss = dateNaiss;
	}

	/**
	 * @return the cursus
	 */
	public String getCursus() {
		return cursus;
	}

	/**
	 * @param cursus the cursus to set
	 */
	public void setCursus(String cursus) {
		this.cursus = cursus;
	}

	/**
	 * @return the diplomes
	 */
	public List<DiplomeDTO> getDiplomes() {
		return diplomes;
	}

	/**
	 * @param diplomes the diplomes to set
	 */
	public void setDiplomes(List<DiplomeDTO> diplomes) {
		this.diplomes = diplomes;
	}

	/**
	 * @return the competence
	 */
	public List<CompetenceDTO> getCompetence() {
		return competence;
	}

	/**
	 * @param competence the competence to set
	 */
	public void setCompetence(List<CompetenceDTO> competence) {
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
	public List<ExperienceDTO> getExperiences() {
		return experiences;
	}

	/**
	 * @param experiences the experiences to set
	 */
	public void setExperiences(List<ExperienceDTO> experiences) {
		this.experiences = experiences;
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

	/**
	 * @return the reseauxSociaux
	 */
	public List<ReseauSocialDTO> getReseauxSociaux() {
		return reseauxSociaux;
	}

	/**
	 * @param reseauxSociaux the reseauxSociaux to set
	 */
	public void setReseauxSociaux(List<ReseauSocialDTO> reseauxSociaux) {
		this.reseauxSociaux = reseauxSociaux;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
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
		result = prime * result + ((cursus == null) ? 0 : cursus.hashCode());
		result = prime * result + ((dateNaiss == null) ? 0 : dateNaiss.hashCode());
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
		ProfilDTO other = (ProfilDTO) obj;
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
		if (cursus == null) {
			if (other.cursus != null)
				return false;
		} else if (!cursus.equals(other.cursus))
			return false;
		if (dateNaiss == null) {
			if (other.dateNaiss != null)
				return false;
		} else if (!dateNaiss.equals(other.dateNaiss))
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
