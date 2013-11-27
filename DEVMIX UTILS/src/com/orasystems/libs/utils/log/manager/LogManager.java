package com.orasystems.libs.utils.log.manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.util.Log;

import com.googlecode.androidannotations.annotations.EBean;
import com.googlecode.androidannotations.annotations.RootContext;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.orasystems.libs.utils.dao.DataBaseActionsThrows;
import com.orasystems.libs.utils.log.model.LogDB;

@EBean
public class LogManager implements DataBaseActionsThrows {

	private DataBaseHelperLibs helper;

	private Dao<LogDB, Integer> daoLog = null;

	@RootContext
	Activity activity;

	@Override
	public long save(Object object) throws Exception {
		return getDaoLog().create((LogDB) object);
	}

	@Override
	public Object saveReturn(Object object) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long saveIfNotExist(Object object) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object saveIfNotExistReturn(Object object) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long update(Object object) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object updateReturn(Object object) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object load(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long delete(Object object)  throws Exception{
		// TODO Auto-generated method stub
		return 0;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List list() throws Exception{
		return getDaoLog().queryForAll();
	}

	@SuppressWarnings("rawtypes")
	public List list(Date dataInicial, Date dataFinal, String tipo,
			String metodo) throws Exception {
		QueryBuilder<LogDB, Integer> logDBQuery = getDaoLog().queryBuilder();
		if (tipo != null && tipo.length() > 0) {
			if (metodo != null && metodo.length() > 0) {
				logDBQuery.where().eq(LogDB.COLUMN_METODO, metodo).and().eq(LogDB.COLUMN_TIPO, tipo).and().between(LogDB.COLUMN_DATA, dataInicial, dataFinal);
			} else{
				logDBQuery.where().eq(LogDB.COLUMN_TIPO, tipo).and().between(LogDB.COLUMN_DATA, dataInicial, dataFinal);
			}
		}else if (metodo != null && metodo.length() > 0) {
			logDBQuery.where().eq(LogDB.COLUMN_METODO, metodo).and().between(LogDB.COLUMN_DATA, dataInicial, dataFinal);
		}else{
			logDBQuery.where().between(LogDB.COLUMN_DATA, dataInicial, dataFinal);
		}
		Log.i("", logDBQuery.prepareStatementInfo().getStatement());
		return getDaoLog().query(logDBQuery.prepare());
	}
	
	public ArrayList<String> listGroupByMetodo() throws Exception {
		List<LogDB> logDBs = getDaoLog().queryBuilder()
				.groupBy(LogDB.COLUMN_METODO).query();
		ArrayList<String> retorno = new ArrayList<String>();
		retorno.add("Todos");
		for (LogDB lDB : logDBs) {
			retorno.add(lDB.getMetodo());
		}
		return retorno;
	}

	public synchronized Dao<LogDB, Integer> getDaoLog() throws SQLException {
		if (helper == null)
			helper = new DataBaseHelperLibs(activity);
		daoLog = helper.getDao(LogDB.class);
		return daoLog;
	}

	public void setDaoLog(Dao<LogDB, Integer> daoLog) {
		this.daoLog = daoLog;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List iteraDadosLista(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object iteraDadosObject(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object carregaDados(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
