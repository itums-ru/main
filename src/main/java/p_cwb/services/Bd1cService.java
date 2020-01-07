package p_cwb.services;

import p_cwb.dao.Bd1cController;
import p_cwb.models.Bd1c;

import java.util.List;

public final class Bd1cService{

    private static Bd1cController bd1cController = new Bd1cController();

    public static List<Bd1c> getAll() {
        return bd1cController.getAll();
    }

    public List<Bd1c> getAllWhere(String columnName, String value) {
        return bd1cController.getAllWhere(columnName, value);
    }

    public void update(Bd1c entity) {
        bd1cController.update(entity);
    }

    public Bd1c getById(Long id) {
        return bd1cController.getById(id);
    }

    public boolean deleteById(Long id) {
        return bd1cController.deleteById(id);
    }

    public boolean delete(Bd1c entity) {
        return bd1cController.delete(entity);
    }

    public boolean save(Bd1c entity) {
        return bd1cController.save(entity);
    }
}
