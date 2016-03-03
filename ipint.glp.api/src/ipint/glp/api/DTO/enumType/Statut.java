package ipint.glp.api.DTO.enumType;

public enum Statut {

	DIPLOME("diplome"), ETUDIANT("etudiant"), PERSONNEL("personnel");
	

	private String name = "";

	Statut(String name) {
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
}
