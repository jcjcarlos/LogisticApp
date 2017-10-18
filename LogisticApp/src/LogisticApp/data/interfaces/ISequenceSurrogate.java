package LogisticApp.data.interfaces;

public interface ISequenceSurrogate {
	
	public abstract int generateKey(String sequenceName) throws Exception;
	
	public abstract void restoreKey(String sequenceName, int id) throws Exception;
	
}
