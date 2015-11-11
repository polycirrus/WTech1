package by.bsuir.lab01.dao.factoryimpl;

import by.bsuir.lab01.dao.AuthorizationDao;
import by.bsuir.lab01.dao.DaoFactory;
import by.bsuir.lab01.dao.LibraryDao;
import by.bsuir.lab01.dao.file.FileAuthorizationDao;
import by.bsuir.lab01.dao.file.FileLibraryDao;

public final class FileDaoFactory extends DaoFactory{
	private final static FileDaoFactory instance = new FileDaoFactory();
	
	private FileDaoFactory(){}
	
	public final static FileDaoFactory getInstance(){
		return instance;
	}
	
	@Override
	public LibraryDao getLibraryDao() {
		return FileLibraryDao.getInstance();
	}

	@Override
	public AuthorizationDao getAuthorizationDao() {
		return FileAuthorizationDao.getInstance();
	}

}
