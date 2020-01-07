package p_cwb.services;

import p_cwb.dao.ClusterController;
import p_cwb.models.Cluster1c;

import java.util.List;

public final class ClusterService{
    private static ClusterController clusterController = new ClusterController();
    public static List<Cluster1c> getAll() {
        return clusterController.getAll();
    }

    public static List<Cluster1c> getAllWhere(String columnName, String value) {
        return clusterController.getAllWhere(columnName, value);
    }

    public static void update(Cluster1c entity) {
        clusterController.update(entity);
    }

    public static Cluster1c getById(Long id) {
        return clusterController.getById(id);
    }

    public static boolean deleteById(Long id) {
        return clusterController.deleteById(id);
    }

    public static boolean delete(Cluster1c entity) {
        return clusterController.delete(entity);
    }

    public static boolean save(Cluster1c entity) {
        return clusterController.save(entity);
    }
}
