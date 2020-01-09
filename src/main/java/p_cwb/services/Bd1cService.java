package p_cwb.services;

import p_cwb.dao.Bd1cController;
import p_cwb.models.Bd1c;

import java.util.List;

public final class Bd1cService{

    private static Bd1cController bd1cController = new Bd1cController();

    public static List<Bd1c> getAll() {
        return bd1cController.getAll();
    }

    public static List<Bd1c> getAllWhere(String columnName, String value) {
        return bd1cController.getAllWhere(columnName, value);
    }

    public static void update(Bd1c entity) {
        bd1cController.update(entity);
    }

    public static Bd1c getById(Long id) {
        return bd1cController.getById(id);
    }

    public static boolean deleteById(Long id) {
        return bd1cController.deleteById(id);
    }

    public static boolean delete(Bd1c entity) {
        return bd1cController.delete(entity);
    }

    public static boolean save(Bd1c entity) {
        return bd1cController.save(entity);
    }
}
