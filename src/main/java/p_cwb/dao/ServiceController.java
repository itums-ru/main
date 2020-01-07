package p_cwb.dao;

import p_cwb.models.Service;

import java.util.List;

public class ServiceController extends AbstractController<Service, Long> {
    public List<Service> getAll() {
        return super.getAll(Service.class.getSimpleName());
    }

    public List<Service> getAllWhere(String columnName, String value) {
        return super.getAllWhere(Service.class.getSimpleName(), columnName, value);
    }

    @Override
    public void update(Service entity) {
        super.update(entity);
    }
    public Service getById(Long id) {
        return super.getById(Service.class, id);
    }

    public boolean deleteById(Long id) {
        return super.deleteById(Service.class, id);
    }

    @Override
    public boolean delete(Service entity) {
        return super.delete(entity);
    }

    @Override
    public boolean save(Service entity) {
        return super.save(entity);
    }
}
