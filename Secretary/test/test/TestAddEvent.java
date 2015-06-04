package test;

import static java.awt.event.KeyEvent.*;
import javax.swing.*;
import org.fest.swing.core.*;
import org.fest.swing.finder.*;
import org.fest.swing.fixture.*;
import org.fest.swing.launcher.ApplicationLauncher;
import org.junit.*;
import secretary.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kirill
 */
public class TestAddEvent {
    private Event event = new Event("2015-06-05", "12:00:00", "Стирка", "Забрать вещи из стирки");;
    private Robot robot;
    FrameFixture window;
    
    @Before
    public void setUp() {
        ApplicationLauncher app = ApplicationLauncher.application(MainFrame.class);
        app.start();
        
        robot = BasicRobot.robotWithCurrentAwtHierarchy();
        robot.settings().delayBetweenEvents(100);
        
        FrameFinder ff = WindowFinder.findFrame(MainFrame.class);
        window = ff.using(robot);
    }
    
    @After
    public void tearDown() {
        window.cleanUp();
    }
    
    @Test
    public void testAddEvent() {
        DB db = new DB("jdbc:derby://localhost:1527/TestDB", "kostroff", "x1439721");
        try {
            Thread.sleep(2000);
        } catch(InterruptedException e) {
            System.out.println("InterruptedException Test");
        }
        window.button("jButtonAdd").click();
        FrameFinder ff1 = WindowFinder.findFrame(AddFrame.class);
        FrameFixture af = ff1.using(robot);
        
        robot.enterText("06.06.2015");
        robot.pressKey(VK_TAB);
        for (int i=0; i<8;i++) {
            robot.pressKey(VK_BACK_SPACE);
        }
        robot.enterText("12:00:00");
        robot.pressKey(VK_TAB);
        robot.enterText("Зарядка");
        robot.pressKey(VK_TAB);
        robot.enterText("Роботам нужна зарядка, поэтому надо зарядиться");
        
        af.button("addButton").click();
        
        FrameFinder ff2 = WindowFinder.findFrame(SuccessForm.class);
        FrameFixture sf = ff2.using(robot);
        
        JLabelFixture jlf = sf.label();
        jlf.requireText("Успех");
    }
}
