package domaine;

public interface Query {

	/**
	 * la méthode geturl va renvoyer l'url
	 * @return l'url qui est demandé
	 */
	String getUrl();

	/**
	 * la méthode setUrl va modifier l'url passé en paramètre
	 * @param url
	 */
	void setUrl(String url);

	/**
	 * va renvoyer si l'url est un get ou un post
	 * @return la méthode
	 */
	QueryMethod getMethod();

	/**
	 * modifier la methode
	 * @param method
	 */
	void setMethod(QueryMethod method);

	/**
	 * @deprecated
	 * modifie la valeur
	 * @param method
	 * @return
	 * @throws IllegalArgumentException
	 */
	String setMethod(String method) throws IllegalArgumentException;

	/**
	 * dit qu'une méthoe peut être soit un get ou un post
	 */
	public enum QueryMethod {
		GET, POST;
	}

}