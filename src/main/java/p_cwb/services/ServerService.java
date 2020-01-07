package p_cwb.services;

import p_cwb.dao.ServerController;
import p_cwb.models.Server;

import java.util.List;

public final class ServerService{
    private static ServerController serverController = new ServerController();
    public static List<Server> getAll() {
        return serverController.getAll();
    }

    public static Server getById(Long id) {
        return serverController.getById(id);
    }

    public static boolean deleteById(Long id) {
        return serverController.deleteById(id);
    }

    public static List<Server> getAllWhere(String columnName, String value) {
        return serverController.getAllWhere(columnName, value);
    }

    public static void update(Server entity) {
        serverController.update(entity);
    }

    public static boolean delete(Server entity) {
        return serverController.delete(entity);
    }

    public static boolean save(Server entity) {
        return serverController.save(entity);
    }
}
