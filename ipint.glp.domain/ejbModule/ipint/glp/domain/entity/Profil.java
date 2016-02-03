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
	// @OneToMany(mappedBy = "profil")
	// private List<String> reseauxSociaux;
	private String diplomePrincipal;
	private int anneeDiplome;
	private String mesAttentes;
	private String situation;

	public Profil() {
		this.competence = new ArrayList<Competence>();
		this.diplomes = new ArrayList<Diplome>();
		this.experiences = new ArrayList<Experience>();
		// this.reseauxSociaux = new ArrayList<String>();
	}

	public Integer getIdProfil() {
		return idProfil;
	}

	public void setIdProfil(Integer idProfil) {
		this.idProfil = idProfil;
	}

	/*
	 * public String getCursus() { return cursus; }
	 */

	/*
	 * public void setCursus(String cursus) { this.cursus = cursus; }
	 */

	public List<Competence> getCompetence() {
		return competence;
	}

	public void setCompetence(List<Competence> list) {
		this.competence = list;
	}

	public String getCentreInteret() {
		return centreInteret;
	}

	public void setCentreInteret(String centreInteret) {
		this.centreInteret = centreInteret;
	}

	public List<Diplome> getDiplomes() {
		return diplomes;
	}

	public void setDiplomes(List<Diplome> diplomes) {
		this.diplomes = diplomes;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public List<Experience> getExperiences() {
		return experiences;
	}

	public void setExperiences(List<Experience> experiences) {
		this.experiences = experiences;
	}

	/**
	 * @return the diplomePrincipal
	 */
	public String getDiplomePrincipal() {
		return diplomePrincipal;
	}

	/**
	 * @param diplomePrincipal
	 *            the diplomePrincipal to set
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
	 * @param anneeDiplome
	 *            the anneeDiplome to set
	 */
	public void setAnneeDiplome(int anneeDiplome) {
		this.anneeDiplome = anneeDiplome;
	}

	@Override
	public String toString() {
		return "[Profil - id=" + this.idProfil + "]";
	}

	// public List<String> getReseauxSociaux() {
	// return reseauxSociaux;
	// }

	// public void setReseauxSociaux(List<String> reseauxSociaux) {
	// this.reseauxSociaux = reseauxSociaux;
	// }

	/**
	 * @return the mesAttentes
	 */
	public String getMesAttentes() {
		return mesAttentes;
	}

	/**
	 * @param mesAttentes
	 *            the mesAttentes to set
	 */
	public void setMesAttentes(String mesAttentes) {
		this.mesAttentes = mesAttentes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((centreInteret == null) ? 0 : centreInteret.hashCode());
		result = prime * result + ((competence == null) ? 0 : competence.hashCode());
		result = prime * result + ((diplomes == null) ? 0 : diplomes.hashCode());
		result = prime * result + ((experiences == null) ? 0 : experiences.hashCode());
		result = prime * result + ((idProfil == null) ? 0 : idProfil.hashCode());
		// result = prime * result + ((reseauxSociaux == null) ? 0 :
		// reseauxSociaux.hashCode());
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
		// if (reseauxSociaux == null) {
		// if (other.reseauxSociaux != null)
		// return false;
		// } else if (!reseauxSociaux.equals(other.reseauxSociaux))
		// return false;
		if (telephone == null) {
			if (other.telephone != null)
				return false;
		} else if (!telephone.equals(other.telephone))
			return false;
		return true;
	}

	/**
	 * @return the situation
	 */
	public String getSituation() {
		return situation;
	}

	/**
	 * @param situation
	 *            the situation to set
	 */
	public void setSituation(String situation) {
		this.situation = situation;
	}

}