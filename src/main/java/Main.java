import org.hibernate.Session;
import org.hibernate.Transaction;
import p_cwb.dao.ServerController;
import p_cwb.models.*;
import p_cwb.services.Bd1cService;
import p_cwb.services.ServerService;
import p_cwb.utils.SessionFactoryUtil;
import p_cwb.models.Bd1c.*;
import p_cwb.models.Bd1c.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        Bd1c bd1c = new Bd1c();
        bd1c.setSb_type_server(Type_bd.PostgreSQL);
        Bd1cService.save(bd1c);

    }
}
