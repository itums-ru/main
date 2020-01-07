import org.hibernate.Session;
import org.hibernate.Transaction;
import p_cwb.dao.ServerController;
import p_cwb.models.*;
import p_cwb.services.ServerService;
import p_cwb.utils.SessionFactoryUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws ParseException {


        Server s = ServerService.getById(2L);
        System.out.println(s.getServerId()+" "+s.getServer()+" "+s.getIp());



    }
}
