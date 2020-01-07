package p_cwb.dao;

import p_cwb.models.Server1c;

import java.util.List;

public class Server1cController extends AbstractController<Server1c, Long> {
    public List<Server1c> getAll() {
        return super.getAll(Server1c.class.getSimpleName());
    }

    public List<Server1c> getAllWhere(String columnName, String value) {
        return super.getAllWhere(Server1c.class.getSimpleName(), columnName, value);
    }

    @Override
    public void update(Server1c entity) {
        super.update(entity);
    }

    public Server1c getById(Long id) {
        return super.getById(Server1c.class, id);
    }

    public boolean deleteById(Long id) {
        return super.deleteById(Server1c.class, id);
    }

    @Override
    public boolean delete(Server1c entity) {
        return super.delete(entity);
    }

    @Override
    public boolean save(Server1c entity) {
        return super.save(entity);
    }
}
