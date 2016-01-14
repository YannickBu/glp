package ipint.glp.domain.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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
//	@javax.validation.constraints.NotNull(message = "Veuillez remplir le champ cursus")
	private String cursus;
	@OneToMany(mappedBy = "profil")
	private List<Diplome> diplomes;
	@OneToMany(mappedBy = "profil")
	private List<Competence> competence;
	private String centreInteret;
	//TODO peut être le mettre en unique non ?
//	@javax.validation.constraints.Pattern(regexp = "#^0[1-9][0-9]{10}$#", message = "Téléphone invalide")
	private String telephone;
	@OneToMany(mappedBy = "profil")
	private List<Experience> experiences;

	public Profil() {
		this.competence = new ArrayList<Competence>();
		this.diplomes = new ArrayList<Diplome>();
		this.experiences = new ArrayList<Experience>();
	}

	public Integer getIdProfil() {
		return idProfil;
	}

	public void setIdProfil(Integer idProfil) {
		this.idProfil = idProfil;
	}

	public String getCursus() {
		return cursus;
	}

	public void setCursus(String cursus) {
		this.cursus = cursus;
	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idProfil == null) ? 0 : idProfil.hashCode());
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
		if (idProfil == null) {
			if (other.idProfil != null)
				return false;
		} else if (!idProfil.equals(other.idProfil))
			return false;
		return true;
	}
}