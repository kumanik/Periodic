import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


class main_frame {

    main_frame() throws IOException {

        pt();
        int[] scr = {0, 0};


        //Frame
        JFrame frm = new JFrame("PERIODIC QUIZ");
        frm.setSize(600, 450);
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        frm.setLocationRelativeTo(null);

        //Generates random number b/w 1 and 118
        int[] at_no = {0};

        while (at_no[0] == 0) {
            at_no[0] = (int) (Math.random() * 118);
        }
        // this label shows the instructions
        JLabel lbl = new JLabel("<html>Welcome to Periodic Quiz : <br>You need to guess the symbol of the element of atomic number - " + at_no[0] + "</html",JLabel.CENTER);
        JLabel instrct = new JLabel("<html> Enter your answer in the following text field and press enter</html>", JLabel.CENTER);

        //this label displays answer status
        JLabel ans_chk = new JLabel();

        //icon
        ImageIcon ii = new ImageIcon("ico.png");
        JLabel icon = new JLabel("", ii, JLabel.CENTER);

        //Text Field
        JTextField txf = new JTextField(5);

        //evaluation of input
        txf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dia = new JDialog(frm);

                if (check_symbol(at_no[0], txf.getText())) {
                    ans_chk.setText("<html>It is the correct answer.<br> The Element is " + elem_nam[at_no[0]] + "<br></html>");
                    scr[0]++;
                } else {
                    ans_chk.setText("<html>It is the wrong answer.<br> The correct symbol is " + elem_sym[at_no[0]] + " <br> The Element is " + elem_nam[at_no[0]] + "<br></html>");
                    scr[1]++;
                }

                // Dialog box for next action
                dia.setLayout(new FlowLayout());
                dia.add(ans_chk);
                dia.setSize(200, 200);
                dia.setVisible(true);
                dia.setLocationRelativeTo(null);

                //button to close
                JButton cls = new JButton("Close");
                cls.setSize(66 , 20);
                cls.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frm.dispose();
                    }
                });

                //Button to play again
                JButton reset = new JButton("Once More");
                reset.setSize(66, 20);
                reset.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dia.dispose();
                        txf.setText("");
                        at_no[0] = random_at_num(at_no[0]);
                        lbl.setText("<html>Welcome to Periodic Quiz : <br>You need to guess the symbol of the element of atomic number - " + at_no[0] + "</html");
                    }
                });

                //Button to show score
                JButton score = new JButton("Show Score");
                score.setSize(66, 20);
                score.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JLabel sc = new JLabel("<html>Correct Answers:"+scr[0]+"<br>Wrong Answers:"+scr[1]+"</html>");
                        JDialog d1 = new JDialog(frm);
                        d1.setLocationRelativeTo(null);
                        d1.setSize(100, 150);
                        d1.setLayout(new FlowLayout());
                        d1.add(sc);
                        d1.setVisible(true);
                        JButton ok = new JButton("OK");
                        ok.setSize(50, 20);
                        d1.add(ok);
                        ok.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                d1.dispose();
                            }
                        });
                    }
                });

                dia.add(reset);
                dia.add(cls);
                dia.add(score);


            }
        });

        //adding all elements to main frame
        frm.add(Box.createRigidArea(new Dimension(200, 0)));
        frm.add(icon);
        frm.add(Box.createRigidArea(new Dimension(200, 0)));
        frm.add(lbl);
        frm.add(Box.createRigidArea(new Dimension(80, 0)));
        frm.add(instrct);
        frm.add(txf);
        frm.add(Box.createRigidArea(new Dimension(40, 0)));
        frm.add(ans_chk);


        //setting everything visible
        frm.setVisible(true);

    }


    private String[] elem_sym;
    private String[] elem_nam;

    public void  pt() throws IOException{
        elem_sym = new String[119];
        elem_nam = new String[119];
        //parses a file containing at nos, at symbols and full names stored as a list
        List<String> list = Files.readAllLines(Paths.get("atomic_table.txt"));
        int i;
        //feeds each line one at a time for extraction of data
        for(i = 0 ; i<118 ; i++) {
            String s = list.get(i);
            process_pt(s , i + 1);
        }
    }

    //separates data int different arrays
    public void process_pt(String line , int n){
        try(Scanner sc = new Scanner(line)){
            int at_no = sc.nextInt();
            elem_sym[n] = sc.next();
            elem_nam[n] = sc.next();
        }
    }

    //checking of input
    boolean check_symbol(int at_no , String sym){
        if(elem_sym[at_no].equals(sym))
            return true;
        return false;
    }

    //generates Atomic numbers
    int random_at_num(int n){
        int a=0 ;
        while (a==0 || a==n) a = (int) (Math.random() * 118);
        return a;
    }

}
