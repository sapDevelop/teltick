package modell.interfaces;

import java.util.Vector;

import modell.entitaeten.interfaces.Fenster;

public interface DaoFenster {
	public Fenster getFenster(int fensterId);
	public Vector<Fenster> getAlleFenster();
}
