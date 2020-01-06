import org.hibernate.Session;
import org.hibernate.Transaction;
import p_cwb.dao.ServerController;
import p_cwb.models.*;
import p_cwb.utils.SessionFactoryUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws ParseException {
        /*Session session = SessionFactoryUtil.getSessionFactory().openSession();

        Transaction t = session.beginTransaction();
        Server server = new Server("app01", "1542");
        session.save(server);

        Service service=new Service();
        service.setDescription("testService");
        service.setServer(server);

        Bd1c bd1c = new Bd1c();
        bd1c.setSb_name("testBd1c");
        bd1c.setSb_date_begin(new SimpleDateFormat("yyyy.MM.dd").parse("2020.01.05"));

        Server1c server1c = new Server1c();
        server1c.setName("testServer1c");


        Cluster1c cluster1c = new Cluster1c();
        cluster1c.setClusterName("testCluster1c");
        cluster1c.setService(service);
        service.setCluster1c(cluster1c);
        cluster1c.addBd1c(bd1c);
        cluster1c.setService(service);
        server1c.setCluster1c(cluster1c);

        session.update(server);
        session.save(service);
        session.save(cluster1c);
        session.save(bd1c);
        session.save(server1c);
        t.commit();
        session.close();*/
        ServerController serverController = new ServerController();
        ArrayList<Server> list = (ArrayList<Server>)serverController.getAllWhere("ip", "1234");
        for(Server s:list)
            System.out.println(s.getServerId()+" "+s.getServer()+" "+s.getIp());



    }
}
