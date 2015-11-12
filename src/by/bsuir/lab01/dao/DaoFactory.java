package by.bsuir.lab01.dao;

import by.bsuir.lab01.dao.factoryimpl.FileDaoFactory;

public abstract class DaoFactory {
	private static final String DAO_TYPE = "file"; //you must read it from property file
	
	public static DaoFactory getDaoFactory() throws DaoException {
            switch (DAO_TYPE){
                case "file":
                        return FileDaoFactory.getInstance();
                default:
                    throw new DaoException("Could not resolve DAO type. Check the configuration file.");
            }
	}
	
	public abstract LibraryDao getLibraryDao();
	public abstract AuthorizationDao getAuthorizationDao();
}
