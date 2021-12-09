package koles.Private.liebrelibdesktop;

import Koles.Private.LiebreLib.LiebreLib;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LiebreLibMainForm {
    LiebreLib lib;
    private JPanel mainPanel;
    private JButton searchButton;
    private JTextField tagsTF;
    private JTextField filepathTF;
    private JLabel tagsLabel;
    private JLabel fileLabel;
    private JButton loadFileButton;

    public LiebreLibMainForm()
    {
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Search button clicked");

                String taglineTags = tagsTF.getText();

                if(taglineTags.length() > 0) {
                    System.out.println("Received following from tagline " + taglineTags);
                }
                else
                {
                    System.out.println("Tagline is empty");
                }
            }
        });

        loadFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //TODO: add sheets to form
                System.out.println("Load button clicked");

                String filename = filepathTF.getText();

                System.out.println("Entered path: " + filename);

                if(InitLiebreLib(filename, 0))
                {
                    System.out.println("Lib loaded");
                }
                else
                {
                    System.out.println("Load failed");
                }
            }
        });
    }

    //TODO: change boolean to exception
    private boolean InitLiebreLib(String filename, int sheet)
    {
        lib = new LiebreLib();

        if(!lib.Load(filename, sheet)) {
            return false;
        }

        lib.LoadTags();
        lib.HashTable();

        return true;
    }

    public void Show()
    {
        JFrame frame = new JFrame("Test");
        frame.setContentPane(new LiebreLibMainForm().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
