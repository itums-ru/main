package p_cwb.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import p_cwb.models.Server;
import p_cwb.utils.SessionFactoryUtil;

import java.io.Serializable;
import java.util.List;

public class ServerController extends AbstractController<Server, Long> {
    @Override
    public List<Server> getAll() {
        try(Session s = SessionFactoryUtil.getSessionFactory().openSession()){
            List<Server> list = s.createQuery("From " + Server.class.getSimpleName()).getResultList();
            s.close();
            return list;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    @Override
    public void update(Server entity) {
        try(Session s = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction t = s.beginTransaction();
            s.update(entity);
            t.commit();
        }catch (Exception e){
            //логировщик сюда
        }

    }

    @Override
    public Server getById(Long id) {
        try(Session s = SessionFactoryUtil.getSessionFactory().openSession()){
            Server server = s.get(Server.class, id);
            s.close();
            return server;
        }catch (Exception e){}
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        try(Session s=SessionFactoryUtil.getSessionFactory().openSession()){
            Server server = s.load(Server.class, id);
            Transaction t = s.beginTransaction();
            s.delete(server);
            t.commit();
            s.close();
            return !s.contains(server);
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    @Override
    public boolean delete(Server entity) {
        try(Session s=SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction t = s.beginTransaction();
            s.delete(entity);
            t.commit();
            s.close();
            return !s.contains(entity);
        }catch (Exception e){

        }
        return false;
    }

    @Override
    public boolean save(Server entity) {
        try(Session s=SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction t = s.beginTransaction();
            s.save(entity);
            t.commit();
            return s.contains(entity);
        }catch (Exception e){

        }
        return false;
    }

    @Override
    public List<Server> getAllWhere(String columnName, String value) {
        try(Session s = SessionFactoryUtil.getSessionFactory().openSession()){
            List<Server> list = s.createQuery("From " + Server.class.getSimpleName()+" where "+columnName+"=\'"+value+"\'").getResultList();
            s.close();
            return list;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
}
