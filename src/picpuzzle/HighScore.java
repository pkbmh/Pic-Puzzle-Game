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
import java.io.*;

public class HighScore extends JFrame {

    HighScore() {
        super("HIGH SCORE ");
        this.setLayout(null);
        this.setSize(300, 300);
        this.setVisible(true);

        Scanner x = new Scanner(System.in);
        try {
            x = new Scanner(new File("input.txt"));
        } catch (Exception e) {
        }
        int i = 1;
        String hs = x.next();
        JLabel hss = new JLabel(hs);
        hss.setBounds(0, 0, 200, 50);
        add(hss);
        while (x.hasNext() != false && i <= 5) {
            String sr = x.next();
            String name = x.next();
            int sco = x.nextInt();
            JLabel ll = new JLabel(" " + sr + " " + name + " " + sco);
            ll.setBounds(0, (i * 20), 200, 50);
            add(ll);
            i++;
        }
    }
}
