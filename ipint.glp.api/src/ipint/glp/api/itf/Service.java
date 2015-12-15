package ipint.glp.api.itf;

public interface Service <DTO> {
	
	public DTO creer(DTO obj);
	
	public DTO trouver(DTO obj);
	
	public DTO modifier(DTO ancienObj, DTO nouvelObj);
	
	public void supprimer(DTO obj);
	
}
