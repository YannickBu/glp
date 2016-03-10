package ipint.glp.domain.test;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import ipint.glp.api.itf.UtilisateurService;
import ipint.glp.domain.impl.UtilisateurImpl;
import junit.framework.TestCase;

public class ArticleImplTest extends TestCase {

	private EJBContainer container;
	private static InitialContext context;
	private static UtilisateurService utilisateurService;

	@BeforeClass
	public void setUp() throws NamingException {

		InitialContext ic = new InitialContext();
	}

	@After
	public void tearDown() {
		container.close();
	}

	@EJB(beanName = "java:global/ipint.glp.ear/ipint.glp.domain-0.0.1-SNAPSHOT/UtilisateurImpl")
	@Test
	public void testAjouter() throws Exception {

		assertNotNull(utilisateurService);
	}

}
