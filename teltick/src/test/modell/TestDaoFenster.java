package test.modell;

import java.util.Vector;

import modell.entitaeten.interfaces.Fenster;
import modell.factory.DaoFensterFactory;
import modell.interfaces.DaoFenster;

import org.junit.Assert;
import org.junit.Test;

public class TestDaoFenster {

	@Test
	public void InstanceDesDaoObjektesAnlegen(){
		DaoFenster dao = DaoFensterFactory.getInstance();
		Assert.assertNotNull(dao);
	}

	@Test
	public void SucheNachFenster0GibtInstance(){
		DaoFenster dao = DaoFensterFactory.getInstance();
		Fenster f = dao.getFenster(0);
		Assert.assertNotNull(f);
	}
	
	@Test
	public void GetAlleFensterTest(){
		DaoFenster dao = DaoFensterFactory.getInstance();
		Vector<Fenster> f = dao.getAlleFenster();
		Assert.assertEquals(f.size(), 1);
		System.out.println(f.get(0).getTitel());
		Assert.assertEquals(f.get(0).getTitel(), "Benutzerverwaltung");
	}
}
