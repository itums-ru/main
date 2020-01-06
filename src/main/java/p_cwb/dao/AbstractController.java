package p_cwb.dao;

import java.util.List;

public abstract class AbstractController<E, K> {
    public abstract List<E> getAll();
    public abstract  List<E> getAllWhere(String columnName, String value);
    public abstract void update(E entity);
    public abstract E getById(K id);
    public abstract boolean deleteById(K id);
    public abstract boolean delete(E entity);
    public abstract boolean save(E entity);

}
