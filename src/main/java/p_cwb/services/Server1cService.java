package p_cwb.services;

import p_cwb.dao.Server1cController;
import p_cwb.models.Server1c;

import java.util.List;

public final class Server1cService{
    private static Server1cController server1cController = new Server1cController();
    public static List<Server1c> getAll() {
        return server1cController.getAll();
    }

    public static List<Server1c> getAllWhere(String columnName, String value) {
        return server1cController.getAllWhere(columnName, value);
    }

    public static void update(Server1c entity) {
        server1cController.update(entity);
    }

    public static Server1c getById(Long id) {
        return server1cController.getById(id);
    }

    public static boolean deleteById(Long id) {
        return server1cController.deleteById(id);
    }

    public static boolean delete(Server1c entity) {
        return server1cController.delete(entity);
    }

    public static boolean save(Server1c entity) {
        return server1cController.save(entity);
    }
}
