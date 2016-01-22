package ipint.glp.api.DTO;

import java.io.Serializable;

import javax.persistence.ManyToOne;

public class ExperienceDTO extends DTO implements Serializable  {
	private static final long serialVersionUID = -1956002340542590931L;
	
	private Integer idExperience;
	@javax.validation.constraints.NotNull(message = "Veuillez remplir l'année de début")
	@javax.validation.constraints.DecimalMin(value = "1900", message = "Année impossible")
	@javax.validation.constraints.DecimalMax(value = "2100", message = "Année impossible")
	private Integer anneeDebut;
	@javax.validation.constraints.DecimalMin(value = "1900", message = "Année impossible")
	@javax.validation.constraints.DecimalMax(value = "2100", message = "Année impossible")
	private Integer anneFin;
	@javax.validation.constraints.NotNull(message = "Veuillez remplir le nom de l'entreprise")
	private String entreprise;
	@javax.validation.constraints.NotNull(message = "Veuillez remplir le poste")
	private String poste;
	@javax.validation.constraints.NotNull(message = "Veuillez remplir le lieu")
	private String lieu;
	private String description;
	private String region;
	private String pays;
	
	@ManyToOne
	private ProfilDTO profil;
	
	
	public ExperienceDTO() {
		super();
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public ProfilDTO getProfil() {
		return profil;
	}

	public void setProfil(ProfilDTO profil) {
		this.profil = profil;
	}

	public Integer getIdExperience() {
		return idExperience;
	}

	public void setIdExperience(Integer idExperience) {
		this.idExperience = idExperience;
	}

	public Integer getAnneeDebut() {
		return anneeDebut;
	}

	public void setAnneeDebut(Integer anneeDebut) {
		this.anneeDebut = anneeDebut;
	}

	public Integer getAnneFin() {
		return anneFin;
	}

	public void setAnneFin(Integer anneFin) {
		this.anneFin = anneFin;
	}

	public String getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(String entreprise) {
		this.entreprise = entreprise;
	}

	public String getPoste() {
		return poste;
	}

	public void setPoste(String poste) {
		this.poste = poste;
	}

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString(){
		return "[ExperienceDTO - id="+this.idExperience+", poste="+this.poste+"]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idExperience == null) ? 0 : idExperience.hashCode());
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
		ExperienceDTO other = (ExperienceDTO) obj;
		if (idExperience == null) {
			if (other.idExperience != null)
				return false;
		} else if (!idExperience.equals(other.idExperience))
			return false;
		return true;
	}

}
