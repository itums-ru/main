package p_cwb.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import p_cwb.models.Server;
import p_cwb.utils.SessionFactoryUtil;

import java.io.Serializable;
import java.util.List;

public class ServerController extends AbstractController<Server, Long> {
    public List<Server> getAll() {
        return super.getAll(Server.class.getSimpleName());
    }

    public Server getById(Long id) {
        return super.getById(Server.class, id);
    }

    public boolean deleteById(Long id) {
        return super.deleteById(Server.class, id);
    }

    public List<Server> getAllWhere(String columnName, String value) {
        return super.getAllWhere(Server.class.getSimpleName(), columnName, value);
    }

    @Override
    public void update(Server entity) {
        super.update(entity);
    }

    @Override
    public boolean delete(Server entity) {
        return super.delete(entity);
    }

    @Override
    public boolean save(Server entity) {
        return super.save(entity);
    }
}
