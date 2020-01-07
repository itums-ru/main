package p_cwb.dao;

import p_cwb.models.Cluster1c;

import java.util.List;

public class ClusterController extends AbstractController<Cluster1c, Long> {
    public List<Cluster1c> getAll() {
        return super.getAll(Cluster1c.class.getSimpleName());
    }

    public List<Cluster1c> getAllWhere(String columnName, String value) {
        return super.getAllWhere(Cluster1c.class.getSimpleName(), columnName, value);
    }

    @Override
    public void update(Cluster1c entity) {
        super.update(entity);
    }

    public Cluster1c getById(Long id) {
        return super.getById(Cluster1c.class, id);
    }

    public boolean deleteById(Long id) {
        return super.deleteById(Cluster1c.class, id);
    }

    @Override
    public boolean delete(Cluster1c entity) {
        return super.delete(entity);
    }

    @Override
    public boolean save(Cluster1c entity) {
        return super.save(entity);
    }
}
