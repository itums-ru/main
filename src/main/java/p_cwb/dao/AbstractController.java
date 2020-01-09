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

    /**
     * Выполняет запрос вида SELECT * FROM таблица сущности
     * @param simpleClassName используется для переопределения в классах-наследниках
     * @return список значений
     */
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

    /**
     * Выполняет запрос вида SELECT * FROM таблица сущности WHERE columnName=value
     * @param simpleClassName используется для переопределения в классах наследниках
     * @param columnName имя столбца
     * @param value значение столбца
     * @return список значений
     */
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

    /**
     * Обновление объекта сущности
     * @param entity объект сущности
     */
    protected void update(E entity){
        try(Session s = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction t = s.beginTransaction();
            s.update(entity);
        }catch (Exception e){
            writeOnLogError(e);
        }
    }

    /**
     * Получении копии объекта сущности по ID
     * @param entityClass используется для переопределения в классах наследниках
     * @param id идентификатор объекта сущности
     * @return объет сущности
     */
    protected   E getById(Class<E> entityClass, K id){
        try(Session s = SessionFactoryUtil.getSessionFactory().openSession()){
            E entity = s.get(entityClass, (Serializable) id);
            s.close();
            return entity;
        }catch (Exception e){writeOnLogError(e);}
        return null;
    }

    /**
     * Удалении строки в таблице сущности по ID
     * @param entityClass используется для переопределения в классах наследниках
     * @param id идентификатор объекта сущности
     * @return успешно/безуспешно
     */
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

    /**
     * Удалении строки в таблице сущности
     * @param entity объект сущности
     * @return успешно/безуспешно
     */
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

    /**
     * Сохранение объекта сущности в таблицу сущности
     * @param entity объект сущности
     * @return успешно/безуспешно
     */
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
