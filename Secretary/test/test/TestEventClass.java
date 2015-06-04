package test;

import org.junit.Assert;
import org.junit.Test;
import secretary.Event;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kirill
 */
public class TestEventClass {
    private Event event = new Event("2015-06-05", "12:00:00", "Стирка", "Забрать вещи из стирки");;
    
    //Тестирование класса Event
    @Test
    public void testEventInit() {
        //Проверка корректного срабатывания конструктора и геттеров
        Assert.assertTrue(event.getDate().equals("2015-06-05"));
        Assert.assertTrue(event.getTime().equals("12:00:00"));
        Assert.assertTrue(event.getName().equals("Стирка"));
        Assert.assertTrue(event.getText().equals("Забрать вещи из стирки"));
    }
}
