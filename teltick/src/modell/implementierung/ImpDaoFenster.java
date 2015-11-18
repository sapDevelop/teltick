package modell.implementierung;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modell.entitaeten.interfaces.Fenster;
import modell.factory.HSqlDbZugriffFactory;
import modell.factory.RowMappingFensterSingletonFactory;
import modell.factory.RowMappingMitarbeiterSingletonFactory;
import modell.interfaces.DBZugriff;
import modell.interfaces.DaoFenster;

public class ImpDaoFenster implements DaoFenster{

	@Override
	public Fenster getFenster(int fensterId) {
		Fenster f = null;
		DBZugriff dbZugriff1 = HSqlDbZugriffFactory.getInstance();
		
		try {
			Connection verbindung = dbZugriff1.verbinden();
			
			String abfrage = "select * from fenster where fenster_id=?";
			PreparedStatement pstmt = verbindung.prepareStatement(abfrage);
			pstmt.setInt(1,fensterId);			
			ResultSet result = pstmt.executeQuery();
			while(result.next()){
				f =  RowMappingFensterSingletonFactory.getInstance().mapRow(result);
			}
			
			verbindung.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TODO Auto-generated method stub
		return f;
	}

	

}
