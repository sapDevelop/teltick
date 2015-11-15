package modell.interfaces;

public interface DaoBasis <returnTyp, idTyp>{
	public returnTyp getValue(idTyp id);
	public boolean speicherInDB(returnTyp r);
	public boolean loescheVonDB(returnTyp r);
	public boolean update(returnTyp r);
	public boolean indexVorhanden(idTyp id);
}
