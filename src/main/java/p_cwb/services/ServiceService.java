package p_cwb.services;

import p_cwb.dao.ServiceController;
import p_cwb.models.Service;

import java.util.List;

public final class ServiceService{
    private static ServiceController serviceController = new ServiceController();
    public static List<Service> getAll() {
        return serviceController.getAll();
    }

    public static List<Service> getAllWhere(String columnName, String value) {
        return serviceController.getAllWhere(columnName, value);
    }

    public static void update(Service entity) {
        serviceController.update(entity);
    }

    public static Service getById(Long id) {
        return serviceController.getById(id);
    }

    public static boolean deleteById(Long id) {
        return serviceController.deleteById(id);
    }

    public static boolean delete(Service entity) {
        return serviceController.delete(entity);
    }

    public static boolean save(Service entity) {
        return serviceController.save(entity);
    }
}
