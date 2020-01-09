package cwbi_p;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Cwbi_cons implements ITemplateConfig, IType_file{
    private static final Logger LOGGER = LoggerFactory.getLogger("cwbi_p.Cwbi_cons");
    interface FuncForCheck{
        boolean func(String value);
    }
    /*
        ядро, на вход подаются файлы (пути до файлов,в которых содержатся пути до иб, либо сами иб)
        извлекается путь
        проверить содержимое файла по каждому пути (на этапе проверки они исправляются, если этого требует настройка конфигурации)
        далее запись в БД (пока БД нет)
     */



    //Список данных из одного файла
    private static ArrayList<Map<String, String>> cwbis=new ArrayList<>();
    //1. Набор массивов считанных файлов. внешний лист - это разные файлы, внутренний лист - это ИБ из одного файла
    private List<ArrayList<Map<String, String>>> Ar_v8i = new ArrayList<> ();
    //2. Набор массивов профилей. (массив содержит имя считанного профиля)
    private List<String> Profiles = new ArrayList<> ();
    //3. Набор массивов дисков. (массив имен дисков где располагается файл описания ИБ)
    private List<String> Discs = new ArrayList<> ();
    //4. Набор массивов серверов. (массив содержит имена серверов)
    private List<String> Servers = new ArrayList<> ();

    //Наборы приходят так:
    //
    private Map<
                    Map<
                            Map<
                                    List<
                                            ArrayList<
                                                 Map<String, String>
                                            >
                                    >,
                                    String
                            >,
                            String
                    >,
            String
            >  map = new HashMap<Map<Map<List<ArrayList<Map<String, String>>>, String>, String>, String>();

    /*
        вытаскиваем пути из файлов в лист paths
        для v8i путь сразу добавляется в список
        для cfg сначала извлекается все содержимое, затем
        пути добавляются по полю CommonInfoBases
     */

    public void parthHere(String... paths){
        for(String path: extractPaths(paths))
            checkAll(parseFiles(path));
        //printMap();

    }
    private static ArrayList<String> extractPaths(String... path){
        ArrayList<String> paths = new ArrayList<>();
        for (String p:path)
            paths.addAll(extractPaths(p));
        return paths;

    }
    private static ArrayList<String> extractPaths(String path){
        ArrayList<String> paths = new ArrayList<>();
        if(path.contains(".v8i"))
            paths.add(path);
        if(path.contains(".cfg")){
            try(BufferedReader cfgReader = new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_16))){
                StringBuilder fileContent= new StringBuilder();
                while (cfgReader.ready())
                    fileContent.append((char)cfgReader.read());
                Matcher matcher=Pattern.compile("CommonInfoBases=.+\\.v8i").matcher(fileContent);
                while (matcher.find()){
                    paths.add(fileContent.substring(matcher.start()+"CommonInfoBases=".length(), matcher.end()));
                }
            }catch (FileNotFoundException e){
                writeOnLogError(e);
            }catch (IOException e){
                writeOnLogError(e);
            }
        }
        return paths;
    }
    /*
        формируется большой список ключ-значение для каждой ИБ

     */
    public static Map parseFiles(String path)
    {
        /*
            это тоже самое, что и поля класса. все зависит от того, как будут приходить файлы
            если большим map, то это имитация этого. Если файлами, то это можно будет убрать
         */

        List<ArrayList<Map<String, String>>> ar_v8i = new ArrayList<>();
        Map<List<ArrayList<Map<String, String>>>, String> profiles = new HashMap<>();
        Map<Map<List<ArrayList<Map<String, String>>>, String>, String> discs = new HashMap<>();
        ArrayList<Map<String, String>> cwbis = new ArrayList<>();
        Map<Map<Map<List<ArrayList<Map<String, String>>>, String>, String>, String> map = new HashMap<>();

        try{
            StringBuilder s=new StringBuilder();
            s.append(readv8i(path));//извлекаем всю информацию из файла
            System.out.println("Извлекли из "+path+new Date());
            while (s.length() > 0) {
                Map<String, String> fieldsCwbi = new HashMap<>();
                //извлекаем из файла информацию об одной ИБ по [...[(скобкам), либо от скобки до конца файла, если запись последняя
                String subStr = s.substring(0, (s.indexOf("[", 2) != -1 ? s.indexOf("[", 2) : s.length()));
                //удаляем то что извлекли
                s.delete(0, (s.indexOf("[", 2) != -1 ? s.indexOf("[", 2) : s.length()));
                //формируем списки ключ-значение
                fieldsCwbi.put("Name", subStr.substring(subStr.indexOf('[') + 1, subStr.indexOf(']')));
                fieldsCwbi.put("Connect", extractValue(subStr, "Connect", "\n"));
                fieldsCwbi.put("ID", extractValue(subStr, "ID"));
                fieldsCwbi.put("OrderInList", extractValue(subStr, "OrderInList"));
                fieldsCwbi.put("Folder", (extractValue(subStr, "Folder").equals("")) ? "/" : extractValue(subStr, "Folder"));
                fieldsCwbi.put("OrderInTree", extractValue(subStr, "OrderInTree"));
                fieldsCwbi.put("External", (extractValue(subStr, "External").equals("") ? "0" : extractValue(subStr, "External")));
                fieldsCwbi.put("ClientConnectionSpeed", (extractValue(subStr, "ClientConnectionSpeed").equals("") ? "Normal" : extractValue(subStr, "ClientConnectionSpeed")));
                fieldsCwbi.put("App", (extractValue(subStr, "App").equals("") ? "Auto" : extractValue(subStr, "App")));
                fieldsCwbi.put("WA", extractValue(subStr, "WA"));
                fieldsCwbi.put("Version", extractValue(subStr, "Version"));
                fieldsCwbi.put("Path", path);
                cwbis.add(fieldsCwbi);
            }
            ar_v8i.add(cwbis);
            //откуда извлекать эту инфу пока не понятно, поэтому вот так
            profiles.put(ar_v8i, "Roman");
            discs.put(profiles, "C\\\\:users\\Roman");
            map.put(discs, "localhost");


        } catch (Exception e) {
            writeOnLogError(e);
        }
        return map;

    }
    //тут происходит разбор map(см. выше), проверка и занесение в бд(пока запись в поле класса)
    private static void checkUnicField(FuncForCheck funcForCheck, String key, Boolean config , Map<String, String> cwbi, String server, String profile){
        if(config) {//поле из шаблона настроек
            while (!funcForCheck.func(cwbi.get(key))) {
                switch (key) {
                    case "Name": cwbi.put(key, ITemplateConfig.generate_name(cwbi.get(key)));break;
                    case "ID": cwbi.put(key, ITemplateConfig.generate_id());break;
                    case "OrderInList": cwbi.put(key, ITemplateConfig.generate_oib());break;
                    case "OrderInTree": cwbi.put(key, ITemplateConfig.generate_tree());break;
                }
            }
            LOGGER.info("На сервере " + server + " у пользователя " + profile +" в файле "+cwbi.get("Path")+" в ИБ "+cwbi.get("Name") +" изменено поле "+key+" на "+cwbi.get(key));
        }
        else
            LOGGER.info("На сервере " + server + " у пользователя " + profile +" в файле "+cwbi.get("Path")+" в ИБ "+cwbi.get("Name") + " обнаружены повторяющиеся уникальное(ые) поля "+key+": " + cwbi.get(key));
    }
    public static void checkAll(Map< Map<Map<List<ArrayList<Map<String, String>>>, String>, String>, String>  map){
        String s="";
        for (Map.Entry<Map<Map<List<ArrayList<Map<String, String>>>, String>, String>, String> servers : map.entrySet()){
            //Servers.add(servers.getValue());
            for(Map.Entry<Map<List<ArrayList<Map<String, String>>>, String>, String> discs:servers.getKey().entrySet()){
                //Discs.add(discs.getValue());
                for(Map.Entry<List<ArrayList<Map<String, String>>>, String> profiles:discs.getKey().entrySet()){
                    //Profiles.add(profiles.getValue());
                    for (ArrayList<Map<String, String>> ar_8vi:profiles.getKey()){
                        //Ar_v8i.add(ar_8vi);
                        for(Map<String, String> cwbi:ar_8vi) {
                            //если все поля уникальны то заносим в базу
                            if(check_b_name(cwbi.get("Name"))&&check_b_id(cwbi.get("ID"))&&check_b_oib(cwbi.get("OrderInList"))&&check_b_tree(cwbi.get("OrderInTree"))) {
                                cwbis.add(cwbi); // тут функция занесения в базу ну или ещ куда-нибудь
                                System.out.println(cwbi.get("Name")+" добавлено в базу");
                                LOGGER.info(cwbi.get("Name") + " добавлено");

                            }
                            else {//проверка по каждому уникальному полю
                                if (!check_b_name(cwbi.get("Name")))//по имени
                                    checkUnicField(Cwbi_cons::check_b_name, "Name", auto_name, cwbi, servers.getValue(), profiles.getValue());
                                if (!check_b_id(cwbi.get("ID")))
                                    checkUnicField(Cwbi_cons::check_b_id, "ID", auto_id, cwbi, servers.getValue(), profiles.getValue());
                                if (!check_b_oib(cwbi.get("OrderInList")))
                                    checkUnicField(Cwbi_cons::check_b_oib, "OrderInList", auto_oib, cwbi, servers.getValue(), profiles.getValue());
                                if (!check_b_tree(cwbi.get("OrderInTree")))
                                    checkUnicField(Cwbi_cons::check_b_tree, "OrderInTree", auto_tree, cwbi, servers.getValue(), profiles.getValue());
                                if(check_b_name(cwbi.get("Name"))&&check_b_id(cwbi.get("ID"))&&check_b_oib(cwbi.get("OrderInList"))&&check_b_tree(cwbi.get("OrderInTree"))) {//попытка заноса после изменения полей
                                    System.out.println(cwbi.get("Name")+" добавлено в базу");
                                    cwbis.add(cwbi);
                                }
                            }

                        }
                    }
                }
            }
        }
        System.out.println(s);

    }
    //общее для всех чеков. пока поиск
    private static boolean check(String key, String value) {
        for(Map<String, String> mp:cwbis) {
            if(mp.get(key).toLowerCase().equals(value.toLowerCase())) {
                return false;

            }
        }
        return true;

    }
    private static boolean check_b_name(String name){
        return check("Name", name);
    }
    private static boolean check_b_id(String b_id){
        return check("ID", b_id);
    }
    private static boolean check_b_oib(String b_oib){
        return check("OrderInList", b_oib);
    }
    private static boolean check_b_tree(String b_tree){
        return check("OrderInTree", b_tree);
    }
    public static void printMap(){
        if(cwbis.size()!=0)
            for(Map<String, String> mp:cwbis)
                for (Map.Entry<String, String> item : mp.entrySet())
                    if(!item.getKey().equals("Path"))
                        System.out.println(item.getKey() + " = " + item.getValue());

    }

    public List<ArrayList<Map<String, String>>> getAr_v8i() {
        return Ar_v8i;
    }
    //извлекаем информацию из v8i
    private static String readv8i(String path)
    {
        StringBuilder s = new StringBuilder();
        try(BufferedReader v8iReader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"))){
            while (v8iReader.ready()) {
                s.append((char)v8iReader.read());
            }
        }
        catch (Exception e){
            writeOnLogError(e);
        }
        return s.toString();
    }

    public static String extractValue(String subStr, String key)//методы для вытаскивания значения полей из файлов(первый аргумент - текст с неповторяющимися полями, ключ - название поля)
    {
        return subStr.substring(subStr.indexOf(key)+key.length()+1, subStr.indexOf("\n", subStr.indexOf(key))-1);
    }
    public static String extractValue(String subStr, String key, String endChar)
    {
        return subStr.substring(subStr.indexOf(key)+key.length()+1, subStr.indexOf(endChar, subStr.indexOf(key)+key.length()+1));
    }
    public List<String> getProfiles() {
        return Profiles;
    }

    public List<String> getDiscs() {
        return Discs;
    }

    public List<String> getServers() {
        return Servers;
    }
    final static private void writeOnLogError(Exception e){
        LOGGER.error(e.toString());
        for(StackTraceElement s:e.getStackTrace())
            LOGGER.error("\t"+s);
    }

}
