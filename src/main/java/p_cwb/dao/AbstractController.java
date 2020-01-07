package p_cwb.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import p_cwb.utils.SessionFactoryUtil;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractController<E, K> {
    private static final Logger LOGGER = LoggerFactory.getLogger("p_cwb.dao.AbstractController");
    protected List<E> getAll(String simpleClassName){
        try(Session s = SessionFactoryUtil.getSessionFactory().openSession()){
            List<E> list = s.createQuery("From " + simpleClassName).getResultList();
            s.close();
            return list;
        }catch (Exception e){
            writeOnLogError(e);
        }
        return null;
    }
    protected List<E> getAllWhere(String simpleClassName, String columnName, String value){
        try(Session s = SessionFactoryUtil.getSessionFactory().openSession()){
            List<E> list = s.createQuery("From " + simpleClassName+" where "+columnName+"=\'"+value+"\'").getResultList();
            s.close();
            return list;
        }catch (Exception e){
            writeOnLogError(e);
        }
        return null;
    }
    protected void update(E entity){
        try(Session s = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction t = s.beginTransaction();
            s.update(entity);
        }catch (Exception e){
            writeOnLogError(e);
        }
    }
    protected   E getById(Class<E> entityClass, K id){
        try(Session s = SessionFactoryUtil.getSessionFactory().openSession()){
            E entity = s.get(entityClass, (Serializable) id);
            s.close();
            return entity;
        }catch (Exception e){writeOnLogError(e);}
        return null;
    }
    protected boolean deleteById(Class<E> entityClass,K id){
        try(Session s=SessionFactoryUtil.getSessionFactory().openSession()){
            E entity = s.load(entityClass, (Serializable) id);
            Transaction t = s.beginTransaction();
            s.delete(entity);
            t.commit();
            s.close();
            return !s.contains(entity);
        }catch (Exception e){
            writeOnLogError(e);
        }
        return false;
    }
    protected boolean delete(E entity){
        try(Session s= SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction t = s.beginTransaction();
            s.delete(entity);
            t.commit();
            s.close();
            return !s.contains(entity);
        }catch (Exception e){
            writeOnLogError(e);
        }
        return false;
    }
    protected boolean save(E entity){
        try(Session s=SessionFactoryUtil.getSessionFactory().openSession()){
            Transaction t = s.beginTransaction();
            s.save(entity);
            t.commit();
            return s.contains(entity);
        }catch (Exception e){
            writeOnLogError(e);
        }
        return false;
    }
    final private void writeOnLogError(Exception e){
        LOGGER.error(e.toString());
        for(StackTraceElement s:e.getStackTrace())
            LOGGER.error("\t"+s);
    }

}
