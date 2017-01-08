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

class StartGame extends JFrame implements ActionListener{
JButton b[][] = new JButton[3][3];
int puzznum = 0;
JButton b_a[] = new JButton[9];
String nameplay;
int i, j, a;
JLabel demo;
JButton sample,starB,np,con,ex, rep;
int x[] = {0, 1, -1, 0};
int y[] = {1, 0, 0, -1};
int count = 0;
TextField ut;
Icon samicon1=new ImageIcon("pic/main.jpg");

ImageIcon[] icons = new ImageIcon[9];

//picpuzzle constructor
StartGame(String name1){
super("8 Box Puzzle");
for (i = 0; i < 9; i++) {
            String path = "pic/" + (i + 1) + "" + ".jpg";
            icons[i] = new ImageIcon(path);
        }
 
nameplay = name1;

int carry = 80;
int k = 0;
for (i = 0; i < 3; i++) {
int bound = 10;
for (j = 0; j < 3; j++) {
b[i][j] = new JButton(icons[k]);
b_a[k] = new JButton(icons[k++]);

b[i][j].setBounds(bound, carry, 100 , 100);
bound = bound + 100;
}
carry = carry + 100;
}

sample=new JButton(samicon1);
sample.setBounds(380,100,200,200);

demo = new JLabel("Demo game , click on next puzzle to start playing");
demo.setBounds(5 ,45 , 400, 50);
demo.setForeground(Color.blue);
add(demo);
JLabel l1=new JLabel("Sample:");
l1.setBounds(330,200,70,20);
JLabel l2=new JLabel("NOTE::Swap your image with star");
l2.setBounds(5,15,500,20);
String greet = "Welcome" + " " + nameplay;
JLabel l3 = new JLabel(greet);
l3.setBounds(5,40 , 200 , 20);
np = new JButton("Next puzzle");
np.setBounds(380,320,200,20);
rep = new JButton("Play Again");
rep.setBounds(380 , 420, 200 , 20);
rep.setForeground(Color.blue);
np.setForeground(Color.red);
add(np);
add(rep);

JLabel cl = new JLabel("MOVES");
cl.setBounds(380,340,50,20);
add(cl);

ut = new TextField(4);
ut.setBounds(380,360,40,40);
add(ut);
starB=new JButton(icons[8]);
starB.setBounds(330,5,50,50);
for (i = 0; i < 3; i++) {
for (j= 0; j < 3; j++) {
add(b[i][j]);
b[i][j].addActionListener(this);
}
}
np.addActionListener(this);
rep.addActionListener(this);
add(sample);add(l1);add(l2); add(starB); add(l3);
sample.addActionListener(this);
this.setLayout(null);
this.setSize(600,500);
this.setVisible(true);
} 
// end of picpuzzle constructor

//next puzzle generator method
public void next_puzzle() {
puzznum++;
demo.setText("");
int x1 , y1, x2, y2;
int ran, done = 0;

while (done == 0) {
Random r = new Random();
ran = r.nextInt(75) % 15 + 5;
for (i = 0; i < ran; i++) {
x1 = r.nextInt(10) % 3;
y1 = r.nextInt(15) % 3;
x2 = r.nextInt(20) % 3;
y2 = r.nextInt(21) % 3;

Icon i1 = b[x1][y1].getIcon();
b[x1][y1].setIcon(b[x2][y2].getIcon());
b[x2][y2].setIcon(i1);
}

for (i = 0; i < 3; i++) {
for (j = 0; j < 3; j++) {
if (b[i][j].getIcon() == icons[8]) {
b[i][j].setIcon(b[2][2].getIcon());
b[2][2].setIcon(icons[8]);
break;
}
}
}
int k = 0;
for (i = 0; i < 3; i++) {
for (j = 0; j < 3; j++) {

Icon a2 = b[i][j].getIcon();
b_a[k++].setIcon(a2);
}
}
int i4 = 0,i3 = 0,j1,sum = 0;
for (i = 1; i < 8; i++) {
Icon i_a = b_a[i].getIcon();
for (j = 0; j < i; j++) {
Icon i_b = b_a[j].getIcon();
for (j1 = 0; j1 < 9; j1++) {
if (i_a == icons[j1])
i3 = j1;
if (i_b == icons[j1])
i4 = j1;
}
if (i4 > i3)
sum = sum + 1;
}
}

if (sum % 2 == 0) {
done = 1;
}
}
}
//end of next puzzle generator method

// update score of user
public void update_score() {
int ar[] = new int[6];
String name[] = new String[6];
 Scanner x =new Scanner(System.in);
    try {
    x = new Scanner(new File("input.txt"));
    }
    catch(Exception e) {
    }
    int i = 0;
    int j = 0;
    int tmp;
     String highscore = x.next();
     while(x.hasNext() != false) {
     String sr = x.next();
     String n = x.next();
     int score = x.nextInt();
    
    
     if(count < score && j == 0 && count != 0) {
     ar[i] = count;
    
     name[i++] = nameplay;
     j++;
     }
     ar[i] = score;
     name[i++] = n;
     
     }
    
     File f = new File("input.txt");
     try {
     FileWriter fw = new FileWriter(f);
     fw.write("HIGH_SCORE");
     fw.flush();
     for(j = 0; j < 5; j++) {
    
     fw.write(" "+(j+1)+" "+name[j]+" "+ar[j]);
    
     fw.flush();
     }
     fw.flush();
     fw.close();
     }
     catch(Exception e) {
    }
    
     
}
//end of update() funtion

//check() function to check if puzzle has been solved
public void check() {
int k = 0, c = 0;
for (i = 0 ; i < 3; i++) {
for (j = 0 ; j < 3; j++) {
Icon s1=b[i][j].getIcon();
if (s1 != icons[k]) {
c++;
break;
}
k++;
}
}
if (c == 0 && count != 0 && puzznum != 0) {
update_score();
JOptionPane.showMessageDialog(this, "You solved the puzzle in " + count + " moves ", "Congratulations !!"
                , JOptionPane.PLAIN_MESSAGE);
count = 0;

}
}

//action performed while playing game
public void actionPerformed(ActionEvent e){
if(e.getSource()==np){
 count = 0;
  ut.setText("");
 next_puzzle();
}
else if (e.getSource()==rep) {
int k = 0;
for (i = 0; i < 3; i++) {
for (j = 0; j < 3; j++) {
Icon a1 = b_a[k++].getIcon();
b[i][j].setIcon(a1);
}
}
count = 0;
String s2 = count + "";
ut.setText(s2);
}
else{
for(i = 0; i < 3; i++) {
for (j= 0; j < 3; j++) {
if(e.getSource()==b[i][j]){
    Icon s1=b[i][j].getIcon();
    int t_i;
    int t_j;
    for (a = 0; a < 4; a++) {
    t_i = i + x[a];
    t_j = j + y[a];
    if (t_i >= 0 && t_j >= 0 && t_i < 3 && t_j < 3) {
      if(b[t_i][t_j].getIcon()==icons[8]){
        b[t_i][t_j].setIcon(s1);
        b[i][j].setIcon(icons[8]);
     
        count++;
        String s2 = count + "";
        ut.setText(s2);
        break;
 
      }
  }
  }
  }
  }
  }
  check();
}


}//end of actionPerformed

}//end of 
