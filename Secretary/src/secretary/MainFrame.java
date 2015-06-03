package secretary;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultListModel;
import javax.swing.Timer;

public class MainFrame extends javax.swing.JFrame {

    private static Timer timer;
    private static DB db;
    private static boolean DBisOn = false;
    private static Calendar cal;
    private static ArrayList<Event> eventsList;
    private static DefaultListModel listModel;
    private static Integer curPosition = -1;
    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
    }
    
    public static void setDB(DB db) {
        MainFrame.db = db;
    }

    public static Calendar getCal() {
        return cal;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCalendar = new com.toedter.calendar.JCalendar();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea = new javax.swing.JTextArea();
        jButtonAdd = new javax.swing.JButton();
        jButtonUpdate = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Электронный секретарь");
        setName("mainFrame"); // NOI18N
        setResizable(false);

        jCalendar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCalendarMouseClicked(evt);
            }
        });

        jList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jList);

        jTextArea.setColumns(20);
        jTextArea.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jTextArea.setLineWrap(true);
        jTextArea.setRows(5);
        jScrollPane2.setViewportView(jTextArea);

        jButtonAdd.setText("Добавить");
        jButtonAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonAddMouseClicked(evt);
            }
        });

        jButtonUpdate.setText("Редактировать");
        jButtonUpdate.setEnabled(false);
        jButtonUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonUpdateMouseClicked(evt);
            }
        });

        jButtonDelete.setText("Удалить");
        jButtonDelete.setEnabled(false);
        jButtonDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonDeleteMouseClicked(evt);
            }
        });

        jLabel1.setText("Статус подклчение к базе данных:");

        jLabel2.setText("Не подключена");
        jLabel2.setName(""); // NOI18N

        jMenu1.setText("Menu");

        jMenuItem1.setText("DB Connect");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);
        jMenu1.add(jSeparator1);

        jMenuItem2.setText("Exit");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(jCalendar, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jButtonAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jCalendar, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonUpdate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonDelete))
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jCalendarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCalendarMouseClicked
        jTextArea.setText("");
        jButtonUpdate.setEnabled(false);
        jButtonDelete.setEnabled(false);
        cal = jCalendar.getCalendar();
        
        StringBuilder sb = new StringBuilder("SELECT * FROM MAINTABLE WHERE DATE = ''").insert(38, Event.buildStringDate(cal));
        
        if (DBisOn) {
            try {
                ResultSet rs = db.executeQuery(sb.toString());
                int rows = 0;
                if (rs.last()) {
                    rows = rs.getRow();
                }
                rs.beforeFirst();
                eventsList = new ArrayList();
                listModel = new DefaultListModel();
                for (int i=0;i<rows;i++) {
                    eventsList.add(new Event(rs));
                    listModel.addElement(eventsList.get(i).getTime() + " " + eventsList.get(i).getName());
                }
                jList.setModel(listModel);
            } catch (SQLException e) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, e);
            }
        } else {
            FailForm ff = new FailForm();
            ff.setVisible(true);
        }
    }//GEN-LAST:event_jCalendarMouseClicked

    private void jListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListMouseClicked
        curPosition = jList.getSelectedIndex();
        if (curPosition != -1)
        {
            jButtonUpdate.setEnabled(true);
            jButtonDelete.setEnabled(true);
            Event selEvent = eventsList.get(curPosition);
            String s = selEvent.getName() + "\nсостоится [" + selEvent.getDate() + "] в [" + selEvent.getTime() + "]\n" + selEvent.getText();
            jTextArea.setText(s);
        }
    }//GEN-LAST:event_jListMouseClicked

    private void jButtonAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAddMouseClicked
        if (DBisOn) {
            AddFrame addFrame = new AddFrame();   
        } else {
            FailForm ff = new FailForm();
            ff.setVisible(true);
        }
    }//GEN-LAST:event_jButtonAddMouseClicked

    private void jButtonUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonUpdateMouseClicked
        if (curPosition > -1) {
            UpdateFrame updateFrame = new UpdateFrame(eventsList.get(curPosition));
        }
    }//GEN-LAST:event_jButtonUpdateMouseClicked

    private void jButtonDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonDeleteMouseClicked
        if (curPosition > -1) {
            try {
                eventsList.get(curPosition).delete();
                eventsList.remove(curPosition);
                listModel.remove(curPosition);
                jTextArea.setText("");
            } catch (SQLException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButtonDeleteMouseClicked

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        ConnectFrame conFrame = new ConnectFrame();
        conFrame.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    static class CheckEvent implements Runnable {
        public void run() {
            try {
                Calendar curDate;
                String curDateString, curTimeString;
                StringBuilder s;
                Event event;
                Pattern dayPattern = Pattern.compile("[0-9][0-9]$");
                Pattern monthPattern = Pattern.compile("-[0-9][0-9]-");
                Pattern yearPattern = Pattern.compile("[0-9][0-9][0-9][0-9]");
                Pattern hourPattern = Pattern.compile("^[0-9][0-9]");
                Pattern minPattern = Pattern.compile(":[0-9][0-9]:");
                Pattern secPattern = Pattern.compile("[0-9][0-9]$");
                
                int day, month, year, hour, min, sec;
                
                for(;;) {
                    if (DBisOn) {
                        curDate = Calendar.getInstance();
                        curDateString = Event.buildStringDate(curDate);
                        curTimeString = curDate.get(Calendar.HOUR_OF_DAY) + ":";
                        if (curDate.get(Calendar.MINUTE) < 10) {
                            curTimeString += "0" + curDate.get(Calendar.MINUTE) + ":";
                        } else {
                            curTimeString += curDate.get(Calendar.MINUTE) + ":";
                        }
                        
                        if (curDate.get(Calendar.SECOND) < 10) {
                            curTimeString += "0" + curDate.get(Calendar.SECOND);
                        } else {
                            curTimeString += curDate.get(Calendar.SECOND);
                        }
                        
                        s = new StringBuilder("select ID, DATE, TIME, NAME, TEXT from MAINTABLE where (DATE = (SELECT MIN(DATE) FROM MAINTABLE WHERE (DATE>='') AND (TIME>=''))) AND (TIME = (Select min(time) from maintable where (TIME>='') and (DATE>='')))");
                        s.insert(110, curDateString);
                        s.insert(133,curTimeString);
                        s.insert(205,curTimeString);
                        s.insert(228, curDateString);
                        
                        
                        ResultSet result = db.executeQuery(s.toString());
                        result.beforeFirst();
                        event = new Event(result);
                        if (event.getDate() == null) continue;
                        Matcher dayMatcher = dayPattern.matcher(event.getDate());
                        Matcher monthMatcher = monthPattern.matcher(event.getDate());
                        Matcher yearMatcher = yearPattern.matcher(event.getDate());
                        Matcher hourMatcher = hourPattern.matcher(event.getTime());
                        Matcher minMatcher = minPattern.matcher(event.getTime());
                        Matcher secMatcher = secPattern.matcher(event.getTime());
                        
                        dayMatcher.find();
                        monthMatcher.find();
                        yearMatcher.find();
                        hourMatcher.find();
                        minMatcher.find();
                        secMatcher.find();
                        
                        day = Integer.valueOf(dayMatcher.group());
                        month = Integer.valueOf(monthMatcher.group().replace("-", ""));
                        year = Integer.valueOf(yearMatcher.group());
                        hour = Integer.valueOf(hourMatcher.group());
                        min = Integer.valueOf(minMatcher.group().replace(":", ""));
                        sec = Integer.valueOf(secMatcher.group());
                        
                        
                        
                        if ((curDate.get(Calendar.DAY_OF_MONTH) == day)&&(curDate.get(Calendar.YEAR) == year)
                                &&(curDate.get(Calendar.MONTH) == month-1)&&(curDate.get(Calendar.HOUR_OF_DAY) == hour)
                                &&(curDate.get(Calendar.MINUTE) == min)&&(curDate.get(Calendar.SECOND) == sec)) {
                            EventForm ef = new EventForm(event);
                            ef.setVisible(true);
                        }
                        
                        
                        //System.out.println(day + " " + (month-1) + " " + year + " " + hour + " " + min + " " + sec);
                        //System.out.println(curDateString + " " + curTimeString);
                        //System.out.println(curDate.get(Calendar.DAY_OF_MONTH) + " " + curDate.get(Calendar.MONTH) + " " 
                        //        + curDate.get(Calendar.YEAR) + " " + curDate.get(Calendar.HOUR_OF_DAY) + " " + curDate.get(Calendar.MINUTE) + " " + curDate.get(Calendar.SECOND));
                    }
                    Thread.sleep(1000);
                }
            } catch(InterruptedException e){
                return;
            } catch (SQLException e) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, e);
            }
            //timePoint
        }
    }
    
    static class CheckDBListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (DB.getCon() != null) {
                jLabel2.setText("Подключена");
                DBisOn = true;
            } else {
                jLabel2.setText("Не подключена");
                DBisOn = false;
            }
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws SQLException {
        //db = new DB("jdbc:derby://localhost:1527/TestDB", "kostroff", "x1439721");
        CheckDBListener cdbl = new CheckDBListener();
        timer = new Timer(5000, cdbl);
        timer.start();
        CheckEvent ce = new CheckEvent();
        Thread myThread = new Thread(ce);
        myThread.start();
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonUpdate;
    private com.toedter.calendar.JCalendar jCalendar;
    private javax.swing.JLabel jLabel1;
    private static javax.swing.JLabel jLabel2;
    private javax.swing.JList jList;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTextArea jTextArea;
    // End of variables declaration//GEN-END:variables
}
