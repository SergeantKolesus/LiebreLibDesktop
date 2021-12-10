package koles.Private.liebrelibdesktop;

import Koles.Private.LiebreLib.Constants;
import Koles.Private.LiebreLib.LiebreLib;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

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
                String[] tags;

                if(taglineTags.length() > 0) {
                    System.out.println("Received following from tagline " + taglineTags);
                }
                else
                {
                    System.out.println("Tagline is empty");
                }

                tags = taglineTags.split("\\/");
                ArrayList<Integer> lines = lib.FindByTags(tags, false);

                LiebreLibTableForm table = new LiebreLibTableForm();

//                ArrayList<Integer> lines = lib.getLines(taglineTags);

//                table.TempShow(new ArrayList<Integer>());
//                ArrayList<Integer> lines = new ArrayList<Integer>();
//                lines.add(1);
//                lines.add(2);
//                lines.add(3);
                table.Show(lines, lib);
//                table.Show(lines);
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
                    searchButton.setEnabled(true);
                }
                else
                {
                    System.out.println("Load failed");
                    searchButton.setEnabled(false);
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
