package test;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Point;
import static java.awt.event.KeyEvent.*;
import javax.swing.*;
import org.fest.swing.core.*;
import org.fest.swing.finder.*;
import org.fest.swing.fixture.*;
import org.fest.swing.launcher.ApplicationLauncher;
import org.junit.*;
import secretary.*;
/**
 *
 * @author Kirill
 */
public class TestShowEvent {
    private Event event = new Event("2015-06-05", "12:00:00", "Стирка", "Забрать вещи из стирки");;
    private Robot robot;
    FrameFixture window;
    Point where1, where2;
    
    @Before
    public void setUp() {
        ApplicationLauncher app = ApplicationLauncher.application(MainFrame.class);
        app.start();
        
        robot = BasicRobot.robotWithCurrentAwtHierarchy();
        robot.settings().delayBetweenEvents(500);
        
        FrameFinder ff = WindowFinder.findFrame(MainFrame.class);
        window = ff.using(robot);
        where1 = new Point(770,430);
        where2 = new Point(770,515);
    }
    
    @After
    public void tearDown() {
        window.cleanUp();
    }
    
    @Test
    public void testShowEvent() {
        DB db = new DB("jdbc:derby://localhost:1527/TestDB", "kostroff", "x1439721");
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            System.out.println("InterruptedException Test");
        }
        robot.click(where1, MouseButton.LEFT_BUTTON, 1);
        robot.click(where2, MouseButton.LEFT_BUTTON, 1);
    }
}