package ipint.glp.api.itf;

public interface Service <T> {
	
	public abstract T creer(T obj);
	
	public abstract T trouver(T obj);
	
	public T modifier(T ancienObj, T nouvelObj);
	
	public void supprimer(T obj);
	
}
