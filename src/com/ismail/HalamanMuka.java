package com.ismail;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.text.MessageFormat;

public class HalamanMuka {


    public HalamanMuka() {
        btnSimpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent click) {

                //    function insert data

                String idMahasiswaText = IDMahasiswa.getText();
                String nameText = name.getText();
                String kelasText = kelas.getText();
                String jumlahText = jumlah.getText().toString();
                String jurusanText = comboJurusan.getSelectedItem().toString();
                String pembayaranText = comboPembayaran.getSelectedItem().toString();
                try{
                    DBFunctions db = new DBFunctions();
                    Connection conn = db.connect_to_db("data_mahasiswa", "moivan", "moivan");
                    db.insertData(conn, "mahasiswa", idMahasiswaText, nameText, kelasText, jurusanText, pembayaranText, jumlahText);
                    System.out.println("Data inserted successfully");
                    tampilData();
                    JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
                } catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
        });

        btnHapus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //    function delete data
                try{
                    String idMahasiswaText = IDMahasiswa.getText().toString();
                    DBFunctions db = new DBFunctions();
                    Connection conn = db.connect_to_db("data_mahasiswa", "moivan", "moivan");
                    db.deleteData(conn, "mahasiswa", idMahasiswaText);
                    System.out.println("Data deleted successfully");
                    tampilData();
                    JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
                } catch (Exception ex){
                    System.out.println(ex.getMessage());
                }
            }
        });
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                int row = table1.getSelectedRow();
                System.out.println(row);

                IDMahasiswa.setText(table1.getValueAt(row, 0).toString());
                name.setText(table1.getValueAt(row, 1).toString());
                kelas.setText(table1.getValueAt(row, 2).toString());
//                Object objJurusan = table1.getValueAt(row, 3);
//                comboJurusan.setSelectedItem(objJurusan);
                comboJurusan.setSelectedItem((table1.getValueAt(row, 3).toString()));
                comboPembayaran.setSelectedItem(table1.getValueAt(row, 4).toString());
                jumlah.setText(table1.getValueAt(row, 5).toString());

            }
        });
        btnUbah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //    function update data
                try{
                    String idMahasiswaText = IDMahasiswa.getText();
                    String nameText = name.getText();
                    String kelasText = kelas.getText();
                    Integer jumlahInt = Integer.parseInt(jumlah.getText());
                    String jurusanText = comboJurusan.getSelectedItem().toString();
                    String pembayaranText = comboPembayaran.getSelectedItem().toString();
                    DBFunctions db = new DBFunctions();
                    Connection conn = db.connect_to_db("data_mahasiswa", "moivan", "moivan");
                    db.updateData(conn, "mahasiswa", nameText, kelasText, jurusanText, pembayaranText, jumlahInt, idMahasiswaText);
                    System.out.println("Data updated successfully");
                    tampilData();
                    JOptionPane.showMessageDialog(null, "Data berhasil diubah");
                } catch (Exception ex){
                    System.out.println(ex.getMessage());
                }
            }
        });
        btnCetak.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MessageFormat header = new MessageFormat("Data Mahasiswa");
                MessageFormat footer = new MessageFormat("Page {0,number,integer}");
                //    function cetak data
                try{
                   table1.print(JTable.PrintMode.FIT_WIDTH, header, footer);
                } catch (Exception ex){
                    System.out.println(ex.getMessage());
                }
            }
        });
    }

    public JPanel getHomePanel() {
        tampilData();
        return homePanel;
    }

    private DefaultTableModel tableModel;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;


    private JPanel homePanel;
    private JTextField IDMahasiswa;
    private JTextField name;
    private JTextField kelas;
    private JTextField jumlah;
    private JSpinner spinnerPembayaran;
    private JButton btnSimpan;
    private JButton btnHapus;
    private JButton btnUbah;
    private JButton btnCetak;
    private JTable table1;
    private JComboBox comboPembayaran;
    private JComboBox comboJurusan;
    private JScrollPane tableData;


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    //tampilkan data pada Jtable
    private  void tampilData(){
        DefaultTableModel tableModel = new DefaultTableModel();
        table1.setModel(tableModel);
        tableModel.addColumn("ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Kelas");
        tableModel.addColumn("Jurusan");
        tableModel.addColumn("Pembayaran");
        tableModel.addColumn("Jumlah");

        // retrieve connection
        try{
            DBFunctions db = new DBFunctions();
            Connection conn = db.connect_to_db("data_mahasiswa", "moivan", "moivan");
            tableModel.getDataVector().removeAllElements();
            ResultSet resultSet = db.selectData(conn, "mahasiswa");
            while (resultSet.next()){
                Object[] data = {
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("kelas"),
                        resultSet.getString("jurusan"),
                        resultSet.getString("pembayaran"),
                        resultSet.getString("jumlah")
                };
            tableModel.addRow(data);
            }
        } catch (SQLException err){
            throw new RuntimeException(err);
        }
    }

}
