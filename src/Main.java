import com.ismail.DBFunctions;
import com.ismail.HalamanMuka;

import javax.swing.*;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        DBFunctions db=new DBFunctions();
        Connection conn = db.connect_to_db("data_mahasiswa", "moivan", "moivan");
        db.createTable(db.connect_to_db("data_mahasiswa", "moivan", "moivan"), "mahasiswa");
//        db.selectData(conn, "mahasiswa");
        SwingUtilities.invokeLater(Main::createGUI);
    }

//    create GUI
    public static void createGUI(){
        HalamanMuka UI = new HalamanMuka();
        JPanel root = UI.getHomePanel();

        JFrame frame = new JFrame("Data Mahasiswa");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(root);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}