package development.bulletinboard.utility;

import java.text.SimpleDateFormat;

public class Util {

    /**
     * Преобразуем таймштамп в нормальную дату
     * @param timeStamp - long, данные из базы
     * @return String дата в выбранном формате
     */
    public static String getTimeFromStamp(long timeStamp) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(timeStamp);
    }
}
