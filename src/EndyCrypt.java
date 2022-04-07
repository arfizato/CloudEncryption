import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;

public class EndyCrypt implements ActionListener {
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;

    static JButton cryptBtn;
    static JButton decryptBtn;
    static JEditorPane inputTxt;
    static JEditorPane outputTxt;

    public void addComponentsToPane(Container pane) throws IOException {
        if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }


        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();/*
        if (shouldFill) {
            c.fill = GridBagConstraints.HORIZONTAL;
        }*/
            inputTxt = new JEditorPane();
        c.fill = GridBagConstraints.BOTH ;
        c.weightx = 1;
        c.weighty=4;
        c.gridx = 0;
        c.gridy = 0;
        inputTxt.setAlignmentY(1F);
        pane.add(inputTxt, c);

         outputTxt = new JEditorPane();
        c.weightx= 1;
        c.weighty=4;
        c.fill = GridBagConstraints.BOTH ;
        c.gridx = 2;
        c.gridy = 0;
        outputTxt.setEditable(false);
        pane.add(outputTxt, c);

        cryptBtn = new JButton("Crypt");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.gridx = 0;
        c.gridy = 1;
        pane.add(cryptBtn, c);
        cryptBtn.addActionListener(this);

        decryptBtn = new JButton("Decrypt");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.gridx = 2;
        c.gridy = 1;
        pane.add(decryptBtn, c);
        decryptBtn.addActionListener(this);

        JLabel invispanel = new JLabel("");
        c.weightx= 0.1;
        c.gridy=0;
        c.gridx=1;
        pane.add(invispanel, c);

    }


    private static void createAndShowGUI() throws IOException {
        JFrame frame = new JFrame("GridBagLayoutDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        EndyCrypt ed= new EndyCrypt();
        ed.addComponentsToPane(frame.getContentPane());

        frame.pack();
        frame.setBounds(-800, 20, 800,200);
        frame.setVisible(true);
    }

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel( new FlatDarkLaf() );
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    createAndShowGUI();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        String input = inputTxt.getText().toString();

        if(e.getSource()==cryptBtn){
            outputTxt.setText(encrypt(input));
        }else if(e.getSource()==decryptBtn){
            outputTxt.setText(decrypt(input));
        }

    }
    public static String encrypt(String input){
        Random random = new Random();
        int r= random.nextInt(input.length())+1;
        String result=""+input.charAt(0);
        char ch;
        for(int a=0;a<input.length();a++){
            int x=Integer.valueOf(input.charAt(a))+r;
            if (x>126){
                x=33+(x-126);
            }

            result+=  Character.toString(x)     ;
        }

        return result;
    }
    public static String decrypt(String input){
        int r = Integer.valueOf(input.charAt(1))- Integer.valueOf(input.charAt(0));
        String result = ""+input.charAt(0);
        for(int a=2;a<input.length();a++){
            int x= Integer.valueOf(input.charAt(a))-r;
            if (x<33){
                x=126+(x-33);
            }
            result+= Character.toString(x);
        }
        return result;
    }
}  