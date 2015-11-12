package kernSystem.interfaces;

/**
 * Interface für die Passwort-Chiffrierung
 */
public interface HashVerfahren {
	public String chiffriereText(String text);
}
