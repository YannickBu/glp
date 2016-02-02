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

	@OneToMany(mappedBy = "profil")
	private List<String> reseauxSociaux;

	public ProfilDTO() {
		this.diplomes = new ArrayList<DiplomeDTO>();
		this.experiences = new ArrayList<ExperienceDTO>();
		this.competence = new ArrayList<CompetenceDTO>();
		this.reseauxSociaux = new ArrayList<String>();
	}

	public Integer getIdProfil() {
		return idProfil;
	}

	public void setIdProfil(Integer idProfil) {
		this.idProfil = idProfil;
	}

	/*
	 * public String getCursus() { return cursus; }
	 * 
	 * public void setCursus(String cursus) { this.cursus = cursus; }
	 */
	public List<CompetenceDTO> getCompetence() {
		return competence;
	}

	public void setCompetence(List<CompetenceDTO> competence) {
		this.competence = competence;
	}

	public String getCentreInteret() {
		return centreInteret;
	}

	public void setCentreInteret(String centreInteret) {
		this.centreInteret = centreInteret;
	}

	public List<DiplomeDTO> getDiplomes() {
		return diplomes;
	}

	public void setDiplomes(List<DiplomeDTO> diplomes) {
		this.diplomes = diplomes;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public List<ExperienceDTO> getExperiences() {
		return experiences;
	}

	public void setExperiences(List<ExperienceDTO> experiences) {
		this.experiences = experiences;
	}

	@Override
	public String toString() {
		return "[ProfilDTO - id=" + this.idProfil + "]";
	}

	public Calendar getDateNaiss() {
		return dateNaiss;
	}

	public void setDateNaiss(Calendar dateNaiss) {
		this.dateNaiss = dateNaiss;
	}

	public String getCursus() {
		return cursus;
	}

	public void setCursus(String cursus) {
		this.cursus = cursus;
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

	public List<String> getReseauxSociaux() {
		return reseauxSociaux;
	}

	public void setReseauxSociaux(List<String> reseauxSociaux) {
		this.reseauxSociaux = reseauxSociaux;
	}

}
