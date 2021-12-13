package koles.Private.liebrelibdesktop;

import Koles.Private.LiebreLib.Constants;
import Koles.Private.LiebreLib.LiebreLib;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
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
    private JButton browseButton;
    private JScrollPane tagsScrollPane;
    private JTable table1;

    private void SearchButtonClicked(ActionEvent event)
    {
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
        table.Show(lines, lib);
    }

    private void LoadButtonClicked(ActionEvent event)
    {
        //TODO: add sheets to form
        System.out.println("Load button clicked");

        String filename = filepathTF.getText();

        System.out.println("Entered path: " + filename);

        JTable tagsTable;

        if(InitLiebreLib(filename, 0))
        {
            System.out.println("Lib loaded");

            String[] tags = lib.getTags();
            JCheckBox[] boxes = new JCheckBox[tags.length];
            Object[][] cells = new Object[tags.length][2];

            for(int i = 0; i < boxes.length; i++)
            {
                boxes[i] = new JCheckBox();
            }

            for(int i = 0; i < tags.length; i++)
            {
                cells[i][0] = tags[i];
                cells[i][1] = Boolean.TRUE && (i % 2 == 0);
            }

            String[] header = new String[] {"Тег", "Искать"};

            DefaultTableModel model = (DefaultTableModel) table1.getModel();
            CheckboxedTableModel newModel = new CheckboxedTableModel(cells, header);

            tagsTable = new JTable(newModel);
            table1.setModel(newModel);
            tagsTable.setBounds(30,40,200,300);
            searchButton.setEnabled(true);
        }
        else
        {
            JOptionPane.showMessageDialog(frame, "Cannot open file\n" + filename);
            System.out.println("Load failed");
            searchButton.setEnabled(false);
        }
    }

    public LiebreLibMainForm()
    {
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SearchButtonClicked(actionEvent);
            }
        });

        loadFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                LoadButtonClicked(actionEvent);
            }
        });

        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser chooser = new JFileChooser();
                if(chooser.showOpenDialog(frame) == 0)
                    filepathTF.setText(chooser.getSelectedFile().getAbsolutePath());
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

    JFrame frame;

    //TODO: add tags table
    //TODO: add multiple tags search

    public void Show()
    {
        frame = new JFrame("Test");
        frame.setContentPane(new LiebreLibMainForm().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
