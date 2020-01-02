package cwc_cons;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Cwc implements ILogging {
    public enum Keys1{
        id(0),             //id
        sb_name(1),       //наименование базы в кластере
        comment(2),       //комментарий
        sb_type_server(3),//тип сервера
        sb_server(4),     //сервер БД
        sbd_name(5),      //имя базы на сервере БД
        sb_user_name(6),  //пользователь, от имени которого происходит подключение к серверу БД
        hash(7),         //хеш пароля
        sb_block(10),
        sb_order(16),
        sb_lic(17);
        private int index;
        Keys1(int index){
            this.index=index;
        }

        public int getIndex() {
            return index;
        }
    }

    private Tree tree;

    public Cwc() {
        tree=new Tree();
    }
    public Cwc(String rootname) {
        tree=new Tree(rootname);
    }
    private static class Node{

        private Object key;
        private String data;
        private List<Node> nodes;


        public void setData(String data) {
            this.data = data;
        }

        public Object getKey() {
            return key;
        }

        public String getData() {
            return data;
        }

        public void setKey(Object key) {
            this.key = key;
        }

        public void setNodes(List<Node> nodes) {
            this.nodes = nodes;
        }

        public List<Node> getNodes() {
            return nodes;
        }

        /**
         * Назвеание(Например имя сервера)
         * @param key
         */
        public Node(Object key){
            this.key=key;
            nodes=new ArrayList<>();
        }

        /**
         * @param key
         * @param data данные, хранящиеся в узле
         */
        public Node(String key, String data){
            this.key=key;
            this.data=data;
            nodes=new ArrayList<>();
        }
    }
    private static class Tree{
        Node root;
        public Tree(String rootName){
            root = new Node(rootName);
        }
        public Tree(){
            root=new Node("root");
        }
        public void change(Server1c server1c, Services services, Cluster1C cluster1C, Bd1C bd1C){
            delete(bd1C);
            insert(server1c, services, cluster1C, bd1C);
        }
        public void  change(Server1c server1c, Services services, Cluster1C cluster1C, Server1c server){
            delete(server);
            insert(server1c, services, cluster1C, server);
        }
        public void delete(Object key){
            delRec(key, root);
        }
        private void delRec(Object key, Node node){
            Iterator<Node> nodeIterator;
            if(!node.nodes.isEmpty()) {
                nodeIterator= node.nodes.iterator();//создаем итератор
                while (nodeIterator.hasNext()) {//пока в списке есть элементы
                    Node current = nodeIterator.next();//получаем следующий элемент
                    if (current.key.equals(key)) {//если совпадает с ключом
                        LOGGER.info("Объект "+current.getKey()+" удален");
                        nodeIterator.remove();
                        return;
                    } else {
                        delRec(key, current);
                    }
                }
            }
        }
        /**
         * Поиск элемента в дереве по ключу()
         * @param key
         * @return
         */
        public Node find(Object key){
            return findRec(key, root);
        }
        /**
         * Рекурсивный поиск с указанием узла начала поиска
         * @param key
         * @param node
         * @return искомый узел
         */
        private Node findRec(Object key, Node node) {
            Node required=null;
                if(!node.nodes.isEmpty())
                for(Node current: node.nodes)
                    if(current.key.equals(key))
                        return current;
                    else {
                        required = findRec(key, current);
                        if(required!=null) break;
                    }

            return required;
        }

        public void insert(Server1c server1c, Services services, Cluster1C cluster1C, Bd1C bd1C){
            if(check(insert(server1c, services, cluster1C), bd1C)==null) {
                insert(server1c, services, cluster1C).nodes.add(new Node(bd1C));
                LOGGER.info("База данных "+bd1C+" добавлена к кластеру "+insert(server1c, services, cluster1C).getKey());
            }
            else
                LOGGER.info("База данных "+bd1C+" уже была ранее добавлена к кластеру "+insert(server1c, services, cluster1C).getKey());
        }
        public void insert(Server1c server1c, Services services, Cluster1C cluster1C, Server1c server){
            if(check(insert(server1c, services, cluster1C), server)==null) {
                insert(server1c, services, cluster1C).nodes.add(new Node(server));
                LOGGER.info("Сервер " + server + " добавлен в дерево к кластеру " + insert(server1c, services, cluster1C).getKey());
            }
            else{
                LOGGER.info("Сервер " + server + " уже был ранее добавлен в дерево к кластеру " + insert(server1c, services, cluster1C).getKey());
            }
        }
        private Node insert(Server1c server1c, Services services, Cluster1C cluster1C){
            Node buf;
            if(check(root, server1c)!=null){
                buf=check(root, server1c);//Запоминаем сервер
                if(check(buf, services)!=null){
                    buf=check(buf, services);//запоминаем имя службы
                    if(check(buf, cluster1C)!=null){
                        return check(buf, cluster1C);//возвращаем кластер
                    }
                    else{
                        buf.nodes.add(new Node(cluster1C));
                        LOGGER.info("Кластер "+cluster1C+" добавлено в дерево к службе "+buf.getKey());
                        return insert(server1c, services ,cluster1C);
                    }
                }
                else{
                    buf.nodes.add(new Node(services));
                    LOGGER.info("Служба  "+services+" добавлено в дерево на сервер "+buf.getKey());
                    return insert(server1c, services ,cluster1C);
                }
            }
            else{
                root.nodes.add(new Node(server1c));
                LOGGER.info("Сервер1C "+server1c+" добавлен в дерево");
                return insert(server1c, services ,cluster1C);
            }
        }
        /**
         * Вставка в дерево. При повторной вставке данные файла перезапиываются
         * Алгоритм: если узел с таким ключом есть, то заходим в него и идем дальше. если нет, то записываем новый
         * узел и потом рекурсивно попадаем в него. Алгоритм повторятеся, пока не запишутся данные файла.
         * @param server
         * @param serviceName
         * @param serviceDesc
         * @param typeFile
         * @param data
         */

        /**
         * Проверка на содержание в узле в списке узлов узла с указанным ключом
         * @param node
         * @param obj
         * @return узел с ключом
         */
        private Node check(Node node, Object obj){
            for(Node n:node.getNodes())
                if(n.getKey().equals(obj))
                    return n;
            return null;
        }



    }

    /**
     *
     * @param reestre строка реестера службы
     * @param key ключи, используются только -regport, -port, -range
     * @return следующее за ключом через пробел значение
     */
    public static String extract_reestre(String reestre, String key){
        String[] val = reestre.split(" ");
        for(int i=0;i<val.length;i++){
            if(val[i].equals(key))
                return val[i+1];
        }
        return null;
    }

    /**
     * изменяет содержание файла 1CV8C.lst
     * @param bases <id базы<ключ поля, значение поля>>
     * @param content содержание файла
     * @return новое содержание
     */
    public static String change_1CV8Clst(Map<String, Map<String, String>> bases , String content){
        //ищем в подстроках по id,  затем выделяем нужную строку и нужные поля изменяем,
        content = content.replace(System.lineSeparator(), "");
        StringBuilder sb=new StringBuilder(content.length());
        try {
            for (Map.Entry<String, Map<String, String>> id:bases.entrySet()) {
                Matcher dataBase = Pattern.compile("\\{(-|\\w){32,}," +              //{41f48524-75f1-4355-9653-a724ce9400bc,
                        "\"\\w+?\"," +                                          //"gilev_sql",
                        "\"(\\w|[а-яА-Я]|\\s)*?\"," +                           //"",
                        "\"\\w+?\"," +                                          //"MSSQLServer",
                        "\"(\\w|\\u005C)+?\"," +                                //"localhost",
                        "(\"|\\w)+?," +                                         //"gilev_sql",
                        "(\"|\\w)+?," +                                         //"sa",
                        ".+?," +                                                //"KHrBy9yvvJjZVR3p6zORfbP6MTHk2Nur/M5VADQKQdc=",
                        "\"((\\w|\\u005C|=|[а-яА-Я]|:)+?;){10,}(\\w|=|-|:)+?\"," +//"CrSQLDB=Y;DB=gilev_sql;DBMS=MSSQLServer;DBSrvr=localhost;DBUID=sa;LIC=3324165431229194515;LicDstr=Y;Locale=ru_RU;Ref=gilev_sql;SLev=0;SQLYOffs=2000;Srvr=localhost:2541;SUsr=",
                        "\\d+?," +                                              //0,
                        "\\{\\d+?,\\d+?,\\d+?,\".*?\",\".*?\",\".*?\"}," +      //{0,00010101000000,00010101000000,"","",""},
                        "\\d,\\d,\".*?\",\\d,\".*?\",\".*?\",\\d+?}")           //0,1,"",0,"","",4}
                        .matcher(content);
                while (dataBase.find()) {
                    if (dataBase.group().contains(id.getKey())) {
                        //System.out.println(dataBase.group());
                        String[] str = dataBase.group().split(",");//разбиваем найденную базу
                        for (Map.Entry<String, String> params : id.getValue().entrySet()) {//идем по ключам
                            str[Keys1.valueOf(params.getKey()).getIndex()] = str[Keys1.valueOf(params.getKey()).getIndex()].contains("\"") ?
                                    ("\"" + params.getValue() + "\"") :
                                    (str[Keys1.valueOf(params.getKey()).getIndex()].contains("{") ?
                                            ("{" + params.getValue()) : params.getValue());
                        }
                        StringBuilder buf = new StringBuilder();
                        //System.out.print(Arrays.toString(str));
                        for (String s : str)
                            buf.append(s + ",");

                        content= content.replace(dataBase.group(), buf.substring(0, buf.length() - 1));
                    }
                }
            }
            for(int i=0;i<content.length();i++) {
                if (content.toCharArray()[i] == '{')
                    sb.append(System.lineSeparator());
                sb.append(content.toCharArray()[i]);
            }
        }catch (Exception e){
            LOGGER.error(e.toString());
            for(StackTraceElement s:e.getStackTrace())
                LOGGER.error("\t"+s.toString()+" "+e.toString());
        }
        return sb.toString();

    }



    /**
     * добавляет содержимое файла 1cv8wsrv.lst  в дерево
     * @param content содержание файла
     *                отсюда вытаскиеваем название сервера, название кластера, серверы кластара
     * @param reestre строка реестера службы
     *                отсюда службу
     */
    private void extract_1cv8wsrv(String content, String reestre, boolean check){
        //reestre="\"C:\\Program Files\\1cv8\\8.3.14.1779\\bin\\ragent.exe\" -srvc -agent -regport 2541 -port 2540 -range 2560:2591 -d \"C:\\Program Files\\1cv8\\srvinfo\"";
        content=content.replace(System.lineSeparator(), "");
        try {
            Services service = new Services(reestre.substring(0, reestre.indexOf(".exe") +".exe".length()).replace("\"", ""));
            //{c56229a6-4f3c-4856-a5ce-07fdb324d3e8,"Локальный кластер",1541,"aguskov-srv01",0,0,0,0,0,0,0,{1,{"aguskov-srv01",1541}},0,0,0}
            Matcher m = Pattern.compile("\\{(\\w|-){31,}," +//c56229a6-4f3c-4856-a5ce-07fdb324d3e8,
                    "(\"|[а-яА-Я]|\\w|\\s)+?," +                 //"Локальный кластер",
                    "\\d+?," +                              //1541,
                    "(\"|\\w|-)+?,"+                        //"aguskov-srv01",
                    "((\\d+?),){7}" +                       //0,0,0,0,0,0,0,
                    "\\{(\"|\\w|,|}|\\{|-)+?}," +           //{1,{"aguskov-srv01",1541}}
                    "((\\d+?),){2}" +                       //,0,0,
                    "\\d+?}")                               //0}
                    .matcher(content);
            while (m.find()) {
                String[] str2 = m.group().split(",");//разделяем строку кластера по запятым
                Server1c server1c = new Server1c(str2[3].replace("\"", ""), Integer.parseInt(str2[2]));//в сервер кладем имя, порт
                Cluster1C cluster1C = new Cluster1C(str2[1].replace("\"", ""), server1c.getName(), server1c.getIpport());//имя, компьютер, порт
                Matcher ma = Pattern.compile("\\{(\"|\\w|-)+,\\d+}").matcher(m.group());
                while (ma.find()) {
                    int port = Integer.parseInt(ma.group().split(",")[1].replace("}", ""));
                    if (port != Integer.parseInt(extract_reestre(reestre, "-regport")))
                        LOGGER.error(ma.group() + " Кластер настроен неверно. Не совпадают порты в службе(" + extract_reestre(reestre, "-regport") + ") и кластере(" + port + ")");

                    Server1c ser = new Server1c(ma.group().split(",")[0].replaceAll("[{\"]", ""),//имя
                            Integer.parseInt(ma.group().split(",")[1].replace("}", "")));//порт
                    tree.insert(server1c, service, cluster1C, ser);// к вызываещему объекту в tree добавлям
                }
            }

        }catch (Exception e){
            LOGGER.error(e.toString());
            for(StackTraceElement s:e.getStackTrace())
                LOGGER.error("\t"+s.toString()+" "+e.toString());
        }
    }

    /**
     *Добавляет в дерево данные фалйла 1CV8C.lst
     * @param content содержание файла
     *                извлекаеотся Serrver1c, Cluster1c, Bd1c, проверяются порты
     * @param reestre строка реестера службы
     * @throws Exception
     */
    public void extract_1CV8Clst(String content, String reestre) throws Exception {
        content = content.replace(System.lineSeparator(), "");
        try {
            Services service = new Services(reestre.substring(0, reestre.indexOf(".exe") + ".exe".length()).replace("\"", ""));
            //{c56229a6-4f3c-4856-a5ce-07fdb324d3e8,"Локальный кластер",1541,"aguskov-srv01",0,0,0,0,0,0,0,{1,{"aguskov-srv01",1541}},0,0,0}
            Matcher server = Pattern.compile("\\{(\\w|-){31,}," +//c56229a6-4f3c-4856-a5ce-07fdb324d3e8,
                    "(\"|[а-яА-Я]|\\w|\\s)+?," +                 //"Локальный кластер",
                    "\\d+?," +                              //1541,
                    "(\"|\\w|-)+?," +                        //"aguskov-srv01",
                    "((\\d+?),){7}" +                       //0,0,0,0,0,0,0,
                    "\\{(\"|\\w|,|}|\\{|-)+?}," +           //{1,{"aguskov-srv01",1541}}
                    "((\\d+?),){2}" +                       //,0,0,
                    "\\d+?}")                               //0}
                    .matcher(content);
            while (server.find()) {
                String[] str2 = server.group().split(",");//разделяем строку кластера по запятым
                Server1c server1c = new Server1c(str2[3].replace("\"", ""), Integer.parseInt(str2[2]));//в сервер кладем имя, порт
                Cluster1C cluster1C = new Cluster1C(str2[1].replace("\"", ""), server1c.getName(), server1c.getIpport());//имя, компьютер, порт
                Matcher serverCluster = Pattern.compile("\\{(\"|\\w|-)+,\\d+}").matcher(server.group());
                while (serverCluster.find()) {
                    int port = Integer.parseInt(serverCluster.group().split(",")[1].replace("}", ""));
                    if (port != Integer.parseInt(extract_reestre(reestre, "-regport")))
                        LOGGER.error(serverCluster.group() + " Кластер настроен неверно. Не совпадают порты в службе(" + extract_reestre(reestre, "-regport") + ") и кластере(" + port + ")");

                    Server1c ser = new Server1c(serverCluster.group().split(",")[0].replaceAll("[{\"]", ""),//имя
                            Integer.parseInt(serverCluster.group().split(",")[1].replace("}", "")));//порт
                    tree.insert(server1c, service, cluster1C, ser);// к вызываещему объекту в tree добавлям
                }
                //{41f48524-75f1-4355-9653-a724ce9400bc,"gilev_sql","","MSSQLServer","localhost","gilev_sql","sa","KHrBy9yvvJjZVR3p6zORfbP6MTHk2Nur/M5VADQKQdc=","CrSQLDB=Y;DB=gilev_sql;DBMS=MSSQLServer;DBSrvr=localhost;DBUID=sa;LIC=3324165431229194515;LicDstr=Y;Locale=ru_RU;Ref=gilev_sql;SLev=0;SQLYOffs=2000;Srvr=localhost:2541;SUsr=",0,{0,00010101000000,00010101000000,"","",""},0,1,"",0,"","",39}
                Matcher dataBase = Pattern.compile("\\{(-|\\w){32,}," +              //{41f48524-75f1-4355-9653-a724ce9400bc,
                        "\"\\w+?\"," +                                          //"gilev_sql",
                        "\"(\\w|[а-яА-Я]|\\s)*?\"," +                           //"",
                        "\"\\w+?\"," +                                          //"MSSQLServer",
                        "\"(\\w|\\u005C)+?\"," +                                //"localhost",
                        "(\"|\\w)+?," +                                         //"gilev_sql",
                        "(\"|\\w)+?," +                                         //"sa",
                        ".+?," +                                                //"KHrBy9yvvJjZVR3p6zORfbP6MTHk2Nur/M5VADQKQdc=",
                        "\"((\\w|\\u005C|=|[а-яА-Я]|:)+?;){10,}(\\w|=|-|:)+?\"," +//"CrSQLDB=Y;DB=gilev_sql;DBMS=MSSQLServer;DBSrvr=localhost;DBUID=sa;LIC=3324165431229194515;LicDstr=Y;Locale=ru_RU;Ref=gilev_sql;SLev=0;SQLYOffs=2000;Srvr=localhost:2541;SUsr=",
                        "\\d+?," +                                              //0,
                        "\\{\\d+?,\\d+?,\\d+?,\".*?\",\".*?\",\".*?\"}," +      //{0,00010101000000,00010101000000,"","",""},
                        "\\d,\\d,\".*?\",\\d,\".*?\",\".*?\",\\d+?}")           //0,1,"",0,"","",4}
                        .matcher(content);
                while (dataBase.find()) {
                    if (dataBase.group().toLowerCase().contains("администратор"))//нужно ли пропускать эту строку?
                        continue;
                    String[] str = dataBase.group().replaceAll("\"", "").split(",");
                    String id = str[0].replace("{", ""),
                            sb_name = str[1],
                            comment = str[2],
                            sb_type_server = str[3],
                            sb_server = str[4],
                            sbd_name = str[5],
                            sb_user_name = str[6],
                            hash = str[7],
                            sb_block = str[10].replace("{", ""),
                            sb_order = str[16],
                            sb_lic = str[17];
                    //если нужно обработать строку с админом
                    /*str = dataBase.group().substring(dataBase.group().indexOf("}")).split(",");
                    String sb_order = str[1],
                            sb_lic = str[2];*/
                    if (!sb_name.equals(sbd_name))
                        LOGGER.info("Наименование БД в кластере 1с(" + sb_name + ") и на сервере БД(" + sbd_name + ") различаются, рекомендуется привести к одному наименованию");

                    tree.insert(server1c, service, cluster1C, new Bd1C(sb_name, sb_server, Type_bd.valueOf(sb_type_server), sbd_name, sb_user_name, sb_lic.equals("1"), sb_block.equals("1"), sb_order.equals("1")));
                    //System.out.println(id+", "+sb_name+", "+comment+", "+sb_type_server+", "+sb_server+", "+sbd_name+", "+sb_user_name+", "+hash+", "+sb_block+", "+sb_order+", "+sb_lic);
                }
                //{b087a1d0-91f7-4858-b475-f4152575772d,"Центральный сервер",2540,"app01",1,{1,{2560,2591}},"","/a8X42IKXaqAMemoXc+jSQ==",0,0,8,128,1000,1,0,1,0,1,2541}
                Matcher ports = Pattern.compile("\\{(-|\\w){32,},\"(\\w|[а-яА-Я]|\\s|-)+?\",\\d+?,\"(\\w|-)+?\",\\d,\\{\\d+?,(\\{\\d+?,\\d+?}(,)?)+?},\".*?\",\".+?\",(\\d+?,){10,}\\d+?}").matcher(content);
                while (ports.find()) {
                    int port = Integer.parseInt(ports.group().split(",")[2]);
                    if (port != Integer.parseInt(extract_reestre(reestre, "-port")))
                        LOGGER.error("Кластер " + cluster1C.getClusterName() + " настроен не верно, различаются порты в службе(" + extract_reestre(reestre, "-port") + ") и конфигураторе(" + port + "): порт управления");
                    Matcher range = Pattern.compile("\\{\\d+?,\\d+?}").matcher(ports.group());
                    while (range.find()) {
                        int port1conf = Integer.parseInt(range.group().replaceAll("[}{]", "").split(",")[0]),//диапазон рабочих портов
                                port2conf = Integer.parseInt(range.group().replaceAll("[}{]", "").split(",")[1]),
                                port1Service = Integer.parseInt(extract_reestre(reestre, "-range").split(":")[0]),
                                port2Service = Integer.parseInt(extract_reestre(reestre, "-range").split(":")[1]);
                        if (port1conf >= port1Service && port1conf <= port2Service
                                || port2conf <= port2Service && port2conf >= port1Service
                                || port1conf <= port1Service && port2conf >= port2Service
                                || port1conf >= port1Service && port2conf <= port2Service)
                            LOGGER.error("кластер настроен не верно, совпадают или пересекаются порты в службе(" + extract_reestre(reestre, "-range") + ") и конфигураторе" + range.group() + ": диапазон рабочих портов");
                    }
                }
            }
        }catch (Exception e){
            LOGGER.error(e.toString());
            for(StackTraceElement s:e.getStackTrace())
                LOGGER.error("\t"+s.toString()+" "+e.toString());
        }

    }


}

