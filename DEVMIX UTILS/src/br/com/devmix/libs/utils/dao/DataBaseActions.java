package br.com.devmix.libs.utils.dao;

/**
 * Created by echer on 04/06/13.
 */
public interface DataBaseActions {

    public long save(Object object);

    public Object saveIfNotExist(Object object);

    public long update(Object object);

    public Object load(int id);

    public long delete(Object object);


}
