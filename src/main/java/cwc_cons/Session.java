package cwc_cons;

import java.util.Date;

public class Session {
    String AppID;                     //Содержит идентификатор приложения, установившего сеанс.
    int blockedByDBMS;                //Содержит номер сеанса, который является причиной ожидания транзакционной блокировки, в том случае, если сеанс исполняет запрос к СУБД и ожидает транзакционную блокировку, установленную другим сеансом. Иначе - 0.
    int blockedByLS;                  //Содержит номер сеанса, который является причиной ожидания управляемой транзакционной блокировки, в случае, если сеанс выполняет установку управляемых транзакционных блокировок и ожидает блокировки, установленные другим сеансом. Иначе - 0.
    int bytesAll;                     //Содержит объем данных, переданных между сервером 1С:Предприятия и клиентским приложением данного сеанса с момента начала сеанса, в байтах.
    int bytesLast5Min;                //Содержит объем данных, переданных между сервером 1С:Предприятия и клиентским приложением данного сеанса за последние 5 минут, в байтах.
    int callsAll;                     //Содержит количество вызовов сервера 1С:Предприятия от имени данного сеанса с момента начала сеанса.
    int callsLast5Min;                //Содержит количество вызовов сервера 1С:Предприятия от имени данного сеанса за последние 5 минут.
    String connection;                //тут должен быть класс Connection; Содержит описание соединения, которому назначен сеанс.
    int cpuTimeAll;                   //Содержит время, которое затрачено процессором на обработку серверных вызовов с момента начала сеанса, в миллисекундах.
    int cpuTimeCurrent;               //Содержит время, затраченное процессором на обработку текущего серверного вызова, в миллисекундах.
    int cpuTimeLast5Min;              //Время, которое затрачено процессором на обработку серверных вызовов сеанса за последние 5 минут, в миллисекундах.
    String CurrentServiceName;        //Идентификатор сервиса кластера, который вызывается в данный момент, или пустая строка, если вызов сервиса кластера не выполняется.
    int dbmsBytesAll;                 //Содержит количество данных, переданных и полученных от СУБД от имени данного сеанса с момента начала сеанса, в байтах.
    int dbmsBytesLast5Mi;             //Содержит количество данных, переданных и полученных от СУБД от имени данного сеанса за последние 5 минут, в байтах.
    String dbProcInfo;                //Содержит номер соединения с СУБД в терминах СУБД в том случае, если в момент получения списка выполняется запрос к СУБД, открыта транзакция или определены временные таблицы
    int dbProcTook;                   //Содержит время соединение с СУБД с момента захвата в миллисекундах.0 - соединение не захвачено.
    Date dbProcTookAt;                //Содержит момент времени, когда соединение с СУБД было захвачено данным сеансом последний раз.
    int durationAll;                  //Содержит время исполнения вызовов сервера 1С:Предприятия от имени данного сеанса с момента начала сеанса, в секундах
    int durationAllDBMS;              //Содержит суммарное время исполнения запросов к СУБД от имени данного сеанса с момента начала сеанса, в миллисекундах
    int durationAllService;           //Время в миллисекундах, которое затрачено сеансом на вызовы сервисов кластера с момента начала сеанса.
    int durationCurrent;              //Содержит интервал времени в миллисекундах, прошедший с момента начала обращения, в случае, если сеанс выполняет обращение к серверу 1С:Предприятия. Иначе – 0.
    int durationCurrentDBMS;          //Содержит интервал времени в миллисекундах, прошедший с момента начала выполнения запроса, в случае, если сеанс выполняет запрос к СУБД. Иначе – 0.
    int durationCurrentService;       //Содержит время, в течение которого сеанс выполняет текущий вызов сервиса кластера.0, если в данный момент вызов сервиса кластера не выполняется, в миллисекундах.
    int durationLast5Min;             //Содержит время исполнения вызовов сервера 1С:Предприятия от имени данного сеанса за последние 5 минут, в миллисекундах
    int durationLast5MinDBMS;         //Содержит время исполнения вызовов сервера 1С:Предприятия от имени данного сеанса за последние 5 минут, в миллисекундах.
    int durationLast5MinService;      //Содержит время, которое затрачено сеансом на вызовы сервисов кластера за последние 5 минут, в миллисекундах.
    boolean hibernate;                //Сеанс находится в спящем режиме
    int hibernateSessionTerminateTime;//Интервал времени в секундах, по истечении которого спящий сеанс завершается.
    String host;                      //Содержит имя или адрес компьютера, установившего сеанс.
    int inBytesAll;                   //Содержит количество данных в байтах, прочитанных с диска сеансом с момента начала сеанса.
    int inBytesCurrent;               //Содержит количество данных в байтах, прочитанных с диска с начала выполнения текущего вызова.
    int inBytesLast5Min;              //Содержит количество данных в байтах, прочитанных с диска сеансом за последние 5 минут.
    Bd1C infoBase;                    //Содержит описание информационной базы, с которой установлен сеанс
    Date lastActiveAt;                //Дата/ время последней активности сеанса.
    String license;                   // ДОЛЖЕН БЫТЬ ТИП ЛИЦЕНЗИЯ
    String locale;                    //Содержит идентификатор языка интерфейса. Определяет язык интерфейса и сообщений платформы.
    int memoryAll;                    //Содержит объем памяти в байтах, занятый в процессе вызовов с момента начала сеанса
    int memoryCurrent;                //Содержит объем памяти в байтах, занятый с начала выполнения текущего вызова. Если в данный момент вызов не выполняется, содержит 0.
    int memoryLast5Min;               //Содержит объем памяти в байтах, занятый в процессе вызовов за последние 5 минут.
    int outBytesAll;                  //Содержит количество данных в байтах, записанных на диск сеансом с момента начала сеанса.
    int outBytesCurrent;              //Содержит количество данных в байтах, записанных на диск с начала выполнения текущего вызова.
    int outBytesLast5Min;             //Содержит количество данных в байтах, записанных на диск сеансом за последние 5 минут.
    int passiveSessionHibernateTime;  //Интервал времени в секундах, по истечении которого неактивный сеанс переводится в спящий режим.
    String process;                   //ТИП - РАБОЧИЙ ПРОЦЕСС; Содержит рабочий процесс, с которым установлено соединение, если сеанс назначен соединению.
    int sessionID;                    //Содержит номер сеанса. Целое число, уникальное среди всех сеансов данной информационной базы.
    Date startedAt;                   //Дата/ время начала сеанса
    String userName;                  //Содержит имя аутентифицированного пользователя информационной базы.
}
