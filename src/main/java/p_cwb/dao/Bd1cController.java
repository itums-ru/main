package p_cwb.dao;

import p_cwb.models.Bd1c;

import java.util.List;

public class Bd1cController extends AbstractController<Bd1c, Long> {
    public List<Bd1c> getAll() {
        return super.getAll(Bd1c.class.getSimpleName());
    }

    public List<Bd1c> getAllWhere(String columnName, String value) {
        return super.getAllWhere(Bd1c.class.getSimpleName(), columnName, value);
    }

    @Override
    public void update(Bd1c entity) {
        super.update(entity);
    }

    public Bd1c getById(Long id) {
        return super.getById(Bd1c.class, id);
    }

    public boolean deleteById(Long id) {
        return super.deleteById(Bd1c.class, id);
    }

    @Override
    public boolean delete(Bd1c entity) {
        return super.delete(entity);
    }

    @Override
    public boolean save(Bd1c entity) {
        return super.save(entity);
    }
}
