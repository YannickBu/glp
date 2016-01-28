package ipint.glp.domain.test;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ipint.glp.api.itf.ArticleService;
import junit.framework.TestCase;

public class ArticleImplTest extends TestCase {

	private EJBContainer container;
	private static InitialContext context;
	private static ArticleService articleService;

	@BeforeClass
	public void setUp() throws NamingException {
		//container = EJBContainer.createEJBContainer();
		context = new InitialContext();
		articleService = (ArticleService) context.lookup("java:global/ipint.glp.ear/ipint.glp.domain-0.0.1-SNAPSHOT/ArticleImpl");
	}

	@After
	public void tearDown() {
		container.close();
	}

	@Test
	public void testAjouter() throws Exception {

		assertNotNull(articleService);
	}

}
