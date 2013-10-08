package com.devmix.libs.utils.dao;

import java.util.List;

/**
 * Created by echer on 04/06/13.
 */
public interface DataBaseActionsThrows{

    public long save(Object object) throws Exception;

    public Object saveReturn(Object object)throws Exception;

    public long saveIfNotExist(Object object)throws Exception;

    public Object saveIfNotExistReturn(Object object)throws Exception;

    public long update(Object object)throws Exception;

    public Object updateReturn(Object object)throws Exception;

    public Object load(int id)throws Exception;

    public long delete(Object object)throws Exception;
    
    @SuppressWarnings("rawtypes")
	public List list()throws Exception;


}
