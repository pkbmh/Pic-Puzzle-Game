/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package picpuzzle;

/**
 *
 * @author pankajbirat
 */
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;

class Enter extends JFrame implements ActionListener {

    JButton ng = new JButton("NEW GAME");
    JButton hs = new JButton("HIGH SCORE");
    JButton ex = new JButton("EXIT");

    Enter() {
        super("NEW GAME");
        this.setTitle("NEW GAME");
        this.setLayout(null);
        this.setSize(200, 200);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ng = new JButton("NEW GAME");
        ng.setBounds(0, 0, 200, 50);
        add(ng);
        hs = new JButton("HIGH SCORE");
        hs.setBounds(0, 50, 200, 50);
        add(hs);
        ex = new JButton("EXIT");
        ex.setBounds(0, 100, 200, 50);
        add(ex);
        JLabel l1 = new JLabel("ENJOY GAME:");
        l1.setBounds(50, 150, 200, 50);
        add(l1);

        ng.addActionListener(this);
        hs.addActionListener(this);
        ex.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ng) {
            String name = (String) JOptionPane.showInputDialog("Please Enter your name");
            if (name.equals(null) || name.isEmpty()) {
                name = "guest";
            }
//System.out.println(name);
            new StartGame(name);

        } else if (e.getSource() == hs) {
            new HighScore();
        } else if (e.getSource() == ex) {
            System.exit(0);
        }
    }
}
