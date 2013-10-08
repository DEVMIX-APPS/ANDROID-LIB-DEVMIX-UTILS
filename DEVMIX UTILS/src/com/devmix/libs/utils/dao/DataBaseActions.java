package com.devmix.libs.utils.dao;

import java.util.List;

/**
 * Created by echer on 04/06/13.
 */
public interface DataBaseActions {

    public long save(Object object);

    public Object saveReturn(Object object);

    public long saveIfNotExist(Object object);

    public Object saveIfNotExistReturn(Object object);

    public long update(Object object);

    public Object updateReturn(Object object);

    public Object load(int id);

    public long delete(Object object);
    
    @SuppressWarnings("rawtypes")
	public List list();


}
