package secretary;

import java.sql.*;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Event {

    private static Connection con;
    private String id = null;
    private String date = null;
    private String time = null;
    private String name = null;
    private String text = null;

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public static void setCon(Connection con) {
        Event.con = con;
    }

    Event(String date, String time, String name, String text) {
        this.date = date;
        this.time = time;
        this.name = name;
        this.text = text;
    }

    Event(ResultSet rs) throws SQLException {
        if (rs.next()) {
            this.id = rs.getString("ID");
            this.date = rs.getString("DATE");
            this.time = rs.getString("TIME");
            this.name = rs.getString("NAME");
            this.text = rs.getString("TEXT");
        }
    }

    static boolean checkTime(String time) {
        Pattern p1 = Pattern.compile("[01]\\d:[0-5]\\d:[0-5]\\d");
        Pattern p2 = Pattern.compile("2[0123]:[0-5]\\d:[0-5]\\d");
        Matcher m1 = p1.matcher(time);
        Matcher m2 = p2.matcher(time);
        if (!(m1.matches() || m2.matches())) {
            return false;
        } else {
            return true;
        }
    }

    static String buildStringDate(Calendar cal) {
        Integer day = cal.get(Calendar.DAY_OF_MONTH);
        Integer month = cal.get(Calendar.MONTH) + 1;
        Integer year = cal.get(Calendar.YEAR);
        return (year.toString() + "-" + month.toString() + "-" + day.toString());
    }

    // функция предназначена для добавления в БД строки, с полями, соответствующими значениям полей объекта
    public void insert() throws SQLException {
        try (Statement stmt = con.createStatement()) {
            // инкрементирование ID
            ResultSet rs = stmt.executeQuery("SELECT MAX(ID) AS ID FROM MAINTABLE");
            rs.next();
            Integer ID = rs.getInt("ID") + 1;

            // формирование строки SQL запроса
            StringBuilder sb = new StringBuilder("INSERT INTO MAINTABLE VALUES ( , '', '', '', '')");
            sb.insert(46, text).insert(42, name).insert(38, time).insert(34, date).insert(31, ID.toString());

            // выполнение SQL запроса
            stmt.executeUpdate(sb.toString());
        }
    }

    // заменяет значения полей записи с текущим ID на значения передаваемых аргументов
    public void update(String date, String time, String name, String text) throws SQLException {
        try (Statement stmt = con.createStatement()) {
            this.date = date;
            this.time = time;
            this.name = name;
            this.text = text;

            StringBuilder sb = new StringBuilder("UPDATE MAINTABLE SET DATE = '', TIME =  '', NAME = '', TEXT = '' WHERE ID = ");
            sb.append(id).insert(63, text).insert(52, name).insert(41, time).insert(29, date);
            stmt.executeUpdate(sb.toString());
        }
    }

    // заменяет значения полей записи с текущим ID на значения полей передаваемого объекта event

    public void update(Event event) throws SQLException {
        try (Statement stmt = con.createStatement()) {
            this.date = event.getDate();
            this.time = event.getTime();
            this.name = event.getName();
            this.text = event.getText();

            StringBuilder sb = new StringBuilder("UPDATE MAINTABLE SET DATE = '', TIME =  '', NAME = '', TEXT = '' WHERE ID = ");
            sb.append(id).insert(63, text).insert(52, name).insert(41, time).insert(29, date);
            stmt.executeUpdate(sb.toString());
        }
    }

    // удаляет запись с значением ID, равным полю id объекта
    public void delete() throws SQLException {
        try (Statement stmt = con.createStatement()) {
            String sb = "DELETE FROM MAINTABLE WHERE ID = " + id;
            stmt.executeUpdate(sb);
        }
    }
}
