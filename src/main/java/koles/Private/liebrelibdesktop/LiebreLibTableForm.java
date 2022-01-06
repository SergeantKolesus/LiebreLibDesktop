package koles.Private.liebrelibdesktop;

import Koles.Private.LiebreLib.Constants;
import Koles.Private.LiebreLib.LiebreLib;

import javax.swing.*;
import java.util.ArrayList;

public class LiebreLibTableForm {
    private JPanel TablePanel;
    private JScrollPane tableScrollPane;
    private JTable table1;

    public LiebreLibTableForm()
    {

    }

    JFrame f;
    JFrame frame;

    public void TempShow(ArrayList<Integer> lines)
    {

        f=new JFrame();
        String data[][]={ {"101","Amit","670000"},
                {"102","Jai","780000"},
                {"101","Sachin","700000"}};
        String column[]={"ID","NAME","SALARY"};
        JTable jt=new JTable(data,column);
//        jt.setBounds(30,40,200,300);
        JScrollPane sp=new JScrollPane(jt);
        f.add(sp);
//        f.setSize(300,400);
        f.setVisible(true);
    }

    public void Show(ArrayList<Integer> lines, LiebreLib lib)
    {
        frame = new JFrame("Dictionary");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ArrayList<String[]> linesContent = new ArrayList<String[]>();
        for(int line : lines)
        {
            linesContent.add(lib.GetLine(line));
        }

        JTable table;
        Object[][] data = new Object[linesContent.size()][];
        data = linesContent.toArray(data);

        String[] columnNames = new String[Constants.tableColumnNames.keySet().size()];
        columnNames = Constants.tableColumnNames.keySet().toArray(columnNames);

        table = new JTable(data, columnNames);
//        table.setBounds(30,40,200,300);
        JScrollPane tableScrollPane1 = new JScrollPane(table);
        frame.add(tableScrollPane1);
        frame.pack();
//        frame.setSize(300,400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
