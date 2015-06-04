package test;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.concurrent.TimeUnit;
import junit.framework.TestCase;
import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.launcher.ApplicationLauncher;
import org.junit.Assert;
import secretary.*;
import org.junit.*;
import javax.swing.*;
import org.fest.swing.*;
import org.fest.swing.core.*;
import org.fest.swing.finder.*;
import org.fest.swing.fixture.*;

/**
 *
 * @author Kirill
 */
public class TestDBConnect extends TestCase{
    private Event event = new Event("2015-06-05", "12:00:00", "Стирка", "Забрать вещи из стирки");;
    //private FrameFixture mainFrame;
    private Robot robot;
    FrameFixture window;
    
    @Before
    public void setUp() {
        ApplicationLauncher app = ApplicationLauncher.application(MainFrame.class);
        app.start();
        
        robot = BasicRobot.robotWithCurrentAwtHierarchy();
        robot.settings().delayBetweenEvents(500);
        
        FrameFinder ff = WindowFinder.findFrame(MainFrame.class);
        window = ff.using(robot);
    }
    
    @After
    public void tearDown() {
        window.cleanUp();
    }
    
    @Test
    public void testDBConnection() {
        //Проверка на подключение к БД
        window.menuItemWithPath("Menu", "DB Connect").click();
        FrameFinder ff1 = WindowFinder.findFrame(ConnectFrame.class);
        FrameFixture cf = ff1.using(robot);
        cf.button().click();
        FrameFinder ff2 = WindowFinder.findFrame(SuccessForm.class);
        FrameFixture sf = ff2.using(robot);
        JLabelFixture jlf = sf.label();
        jlf.requireText("Успех");
    }
}
