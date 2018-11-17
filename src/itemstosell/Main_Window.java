/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itemstosell;

import java.awt.HeadlessException;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.*;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrator
 */
public class Main_Window extends javax.swing.JFrame {

    /**
     * Creates new form Main_Window
     */
    public Main_Window() {
        initComponents();
        //getConnection();
        Show_Products_In_Table();
    }
    String ImgPath = null; 
    int pos = 0; 
            
    public Connection getConnection()
    {
        Connection con = null;
        try 
        {
     

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/products","root","");
            //JOptionPane.showMessageDialog(null, "Connected");

            return con; 
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("it does not work");
            //JOptionPane.showMessageDialog(null, "Not Connected");
            return null;
        }
    }
    // Check input fields 
    public boolean checkInputs()
    {
        if (txt_Name.getText()== null || txt_Price.getText() == null || txt_AddDate.getDate() == null)
        {
            return false; 
        }
        else
        {
            try 
            {
                Float.parseFloat(txt_Price.getText());
                return true; 
            }
            catch(Exception ex)
            {
             return false;    
            }

        }
    }
    //Resize images
    public ImageIcon ResizeImage(String imagePath, byte[] pic)
    {
        ImageIcon myImage = null; 
        if (imagePath !=null)
        {
            myImage = new ImageIcon(imagePath);          
        }
        else 
        {
            myImage = new ImageIcon(pic); 
        }
        Image img = myImage.getImage();
        Image img2 = img.getScaledInstance(lbl_image.getWidth(), lbl_image.getHeight(),Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(img2);
        return image; 
    
    
    }
    
    
    //Data in the JavaFramework 
    public ArrayList<Product> getProductList()
    {
            ArrayList<Product> productList = new ArrayList <Product>();
            Connection con = getConnection();
            String query = "Select * FROM items";
            
            Statement st;
            ResultSet rs;
        try {
            
            st = con.createStatement();
            rs = st.executeQuery(query); 
            Product product;
            
            while (rs.next())
            {
                product = new Product(rs.getInt("ID"),rs.getString("Name"),Float.parseFloat(rs.getString("Price")), rs.getString("add_date"), rs.getBytes("image"));
                productList.add(product); 
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    return productList;     
    }
    //Populating the table
    
    public void Show_Products_In_Table()
    {
        ArrayList<Product> list = getProductList();
        DefaultTableModel model = (DefaultTableModel)JTable_Products.getModel(); 
        model.setRowCount(0);
        Object[] row = new Object[4];
        for (int i = 0; i <list.size();i++)
        {
            row[0] = list.get(i).getID();
            row[1] = list.get(i).getName();
            row[2] = list.get(i).getPrice();
            row[3] = list.get(i).getAddDate();
            
            model.addRow(row);
                        
        }
    }
    
    public void ShowItem(int index)
    {
        txt_ID.setText(Integer.toString(getProductList().get(index).getID()));
        txt_Name.setText(getProductList().get(index).getName());
        txt_Price.setText(Float.toString(getProductList().get(index).getPrice()));
        try 
        {
            Date addDate = null;
            addDate = new SimpleDateFormat("yyyy-MM-dd").parse((String)getProductList().get(index).getAddDate());
            txt_AddDate.setDate(addDate);
        }catch(ParseException ex)
        {
            Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE,null,ex); 
            
        }
        lbl_image.setIcon(ResizeImage(null, getProductList().get(index).getImage()));
    }
    
    
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_ID = new javax.swing.JTextField();
        txt_Name = new javax.swing.JTextField();
        txt_Price = new javax.swing.JTextField();
        txt_AddDate = new com.toedter.calendar.JDateChooser();
        lbl_image = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTable_Products = new javax.swing.JTable();
        choose_Image = new javax.swing.JButton();
        btn_Insert = new javax.swing.JButton();
        btn_Delete = new javax.swing.JButton();
        btn_back = new javax.swing.JButton();
        btn_next = new javax.swing.JButton();
        btn_Last = new javax.swing.JButton();
        btn_Update = new javax.swing.JButton();
        brn_First = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("ID");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Name");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("AddDate");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Image");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Price");

        txt_ID.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_ID.setEnabled(false);
        txt_ID.setPreferredSize(new java.awt.Dimension(59, 50));
        txt_ID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_IDActionPerformed(evt);
            }
        });

        txt_Name.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_Name.setPreferredSize(new java.awt.Dimension(59, 50));
        txt_Name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_NameActionPerformed(evt);
            }
        });

        txt_Price.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_Price.setPreferredSize(new java.awt.Dimension(59, 50));

        txt_AddDate.setDateFormatString("yyyy-MM-dd");

        lbl_image.setBackground(new java.awt.Color(204, 255, 255));
        lbl_image.setOpaque(true);

        JTable_Products.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Add Date", "Price"
            }
        ));
        JTable_Products.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTable_ProductsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTable_Products);

        choose_Image.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        choose_Image.setText("Choose Image");
        choose_Image.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                choose_ImageActionPerformed(evt);
            }
        });

        btn_Insert.setIcon(new javax.swing.ImageIcon(getClass().getResource("/itemstosell/Icons/add2.png"))); // NOI18N
        btn_Insert.setText("Insert");
        btn_Insert.setIconTextGap(15);
        btn_Insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_InsertActionPerformed(evt);
            }
        });

        btn_Delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/itemstosell/Icons/Delete2.png"))); // NOI18N
        btn_Delete.setText("Delete");
        btn_Delete.setIconTextGap(15);
        btn_Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DeleteActionPerformed(evt);
            }
        });

        btn_back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/itemstosell/Icons/Back2.png"))); // NOI18N
        btn_back.setText("Back");
        btn_back.setIconTextGap(15);
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });

        btn_next.setIcon(new javax.swing.ImageIcon(getClass().getResource("/itemstosell/Icons/Next2.png"))); // NOI18N
        btn_next.setText("Next");
        btn_next.setIconTextGap(15);
        btn_next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nextActionPerformed(evt);
            }
        });

        btn_Last.setIcon(new javax.swing.ImageIcon(getClass().getResource("/itemstosell/Icons/Last2.png"))); // NOI18N
        btn_Last.setText("Last");
        btn_Last.setIconTextGap(15);
        btn_Last.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LastActionPerformed(evt);
            }
        });

        btn_Update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/itemstosell/Icons/Refresh2.png"))); // NOI18N
        btn_Update.setText("Update");
        btn_Update.setIconTextGap(15);
        btn_Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_UpdateActionPerformed(evt);
            }
        });

        brn_First.setIcon(new javax.swing.ImageIcon(getClass().getResource("/itemstosell/Icons/First2.png"))); // NOI18N
        brn_First.setText("First");
        brn_First.setIconTextGap(15);
        brn_First.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brn_FirstActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_image, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_Price, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(choose_Image, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(txt_AddDate, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_Insert)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_Delete)
                        .addGap(18, 18, 18)
                        .addComponent(btn_Update)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(brn_First)
                        .addGap(18, 18, 18)
                        .addComponent(btn_back)
                        .addGap(68, 68, 68)
                        .addComponent(btn_next)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_Last))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(txt_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(39, 39, 39)
                                .addComponent(jLabel3))
                            .addComponent(txt_AddDate, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txt_Price, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addComponent(jLabel4)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbl_image, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(choose_Image)
                                .addGap(25, 25, 25)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_Insert)
                            .addComponent(btn_Delete)
                            .addComponent(btn_Update))
                        .addGap(19, 19, 19))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_Last)
                            .addComponent(btn_next)
                            .addComponent(btn_back)
                            .addComponent(brn_First))
                        .addContainerGap(27, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_IDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_IDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_IDActionPerformed

    private void txt_NameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_NameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_NameActionPerformed

    private void choose_ImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_choose_ImageActionPerformed
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.images", "jpg", "png");
        file.addChoosableFileFilter(filter);
        int result = file.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION)
        {
            File selectedFile = file.getSelectedFile();
            String path = selectedFile.getAbsolutePath(); 
            lbl_image.setIcon(ResizeImage(path, null));
            ImgPath = path; 
        }
        else 
        {
            System.out.println("No File Selected at the moment");
        }
    }//GEN-LAST:event_choose_ImageActionPerformed

    private void btn_InsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_InsertActionPerformed
        if (checkInputs() && ImgPath != null )
        {
             try {
                Connection con = getConnection(); 
                PreparedStatement ps = con.prepareStatement("INSERT INTO items (Name, Price, add_date, image)" + "values(?,?,?,?)");
                ps.setString(1, txt_Name.getText());
                ps.setString(2, txt_Price.getText());
                SimpleDateFormat dateFormat  = new SimpleDateFormat("yyyy-MM-dd");
                String addDate = dateFormat.format(txt_AddDate.getDate());
                ps.setString(3, addDate);
                
                InputStream img = new FileInputStream (new File(ImgPath));
                ps.setBlob(4, img);
                ps.executeUpdate(); 
                Show_Products_In_Table();
                JOptionPane.showMessageDialog(null, "Data Inserted");

                
            } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            
            }
        }
        else 
        {
            
            JOptionPane.showMessageDialog(null, "One or More Fields are empty");
        }
    }//GEN-LAST:event_btn_InsertActionPerformed

    private void btn_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DeleteActionPerformed

        if (!txt_ID.getText().equals(""))
        {
            try {
                Connection con = getConnection ();
                PreparedStatement ps = con.prepareStatement("DELETE FROM items WHERE ID = ?");
                int id = Integer.parseInt(txt_ID.getText());
                ps.setInt(1,id);
                 ps.executeUpdate();
                 Show_Products_In_Table();

                //Windows pop up 
                JOptionPane.showMessageDialog(null, "Item Deleted");
            } catch (SQLException ex) {
                Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Item Not Deleted");
            }
        }
        else 
        {
            JOptionPane.showMessageDialog(null, "Product Not Deleted: Np ID to delete");
        }
        
    }//GEN-LAST:event_btn_DeleteActionPerformed

    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed
        pos--;
        if (pos<0)
        {
            pos = 0;   
        }
        ShowItem(pos);

    }//GEN-LAST:event_btn_backActionPerformed

    private void btn_nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nextActionPerformed

        pos++; 
        if (pos >= getProductList().size())
        {
            pos = getProductList().size()-1;
        }
        ShowItem(pos);
    }//GEN-LAST:event_btn_nextActionPerformed

    private void btn_LastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LastActionPerformed
        pos = getProductList().size()-1;
        ShowItem(pos);

    }//GEN-LAST:event_btn_LastActionPerformed

    private void btn_UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_UpdateActionPerformed
        if (checkInputs() && txt_ID.getText() != null)
        {
            String UpdateQuery = null; 
            PreparedStatement ps = null; 
            Connection con = getConnection();
            //Update without image 
            if (ImgPath == null)
            {
                try {
                    UpdateQuery = "UPDATE items SET Name = ?, Price = ?"
                            + "add_date = ? WHERE ID = ?";
                    ps = con.prepareStatement(UpdateQuery);
                    ps.setString(1, txt_Name.getText());
                    ps.setString(2, txt_Price.getText());
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String addDate = dateFormat.format(txt_AddDate.getDate());
                    
                    ps.setString(3, addDate);
                    ps.setInt(4, Integer.parseInt(txt_ID.getText()));
                    ps.executeUpdate();
                    Show_Products_In_Table();
                    
                    JOptionPane.showMessageDialog(null, "Item Updated");
                } catch (SQLException ex) {
                    Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            //with Image
            else
            {
                try 
                {
                    InputStream img = new FileInputStream(new File(ImgPath));
                    UpdateQuery = "UPDATE items SET Name = ?, Price = ?" 
                            + ", add_date = ?, image = ? WHERE ID = ?";
                    ps = con.prepareStatement(UpdateQuery);
                    ps.setString(1, txt_Name.getText());
                    ps.setString(2, txt_Price.getText());
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String addDate = dateFormat.format(txt_AddDate.getDate());
                    
                    ps.setString(3, addDate);
                    ps.setBlob(4,img);
                    ps.setInt(5, Integer.parseInt(txt_ID.getText()));
                    
                    ps.executeUpdate();
                    Show_Products_In_Table();

                    JOptionPane.showMessageDialog(null, "Item Updated");
                    //Show_Products_In_Table();
                }catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                    
                }
            }
        }
        else {JOptionPane.showMessageDialog(null, "One or more Fields are empty or wrong");}




    }//GEN-LAST:event_btn_UpdateActionPerformed

    private void brn_FirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brn_FirstActionPerformed
        pos = 0; 
        ShowItem(pos);
    }//GEN-LAST:event_brn_FirstActionPerformed

    private void JTable_ProductsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTable_ProductsMouseClicked

        int index = JTable_Products.getSelectedRow();
        ShowItem(index);



    }//GEN-LAST:event_JTable_ProductsMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main_Window().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable JTable_Products;
    private javax.swing.JButton brn_First;
    private javax.swing.JButton btn_Delete;
    private javax.swing.JButton btn_Insert;
    private javax.swing.JButton btn_Last;
    private javax.swing.JButton btn_Update;
    private javax.swing.JButton btn_back;
    private javax.swing.JButton btn_next;
    private javax.swing.JButton choose_Image;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_image;
    private com.toedter.calendar.JDateChooser txt_AddDate;
    private javax.swing.JTextField txt_ID;
    private javax.swing.JTextField txt_Name;
    private javax.swing.JTextField txt_Price;
    // End of variables declaration//GEN-END:variables
}
