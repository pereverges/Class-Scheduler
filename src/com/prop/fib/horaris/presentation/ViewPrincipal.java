package com.prop.fib.horaris.presentation;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.prop.fib.horaris.Enumerations.DiaSetmana;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class ViewPrincipal {
    private JFrame framePrincipal = new JFrame("Generador Horaris =)");
    private JPanel panelPrincipal = new JPanel();
    private JButton btnCarregarHoraris = new JButton();
    private JButton btnGenerarHorari = new JButton();
    private JList listHoraris = new JList<String>();
    private JLabel lblDadesUsuari = new JLabel();
    private JTable tblHorari;
    private JScrollPane spTblHorari;
    private JScrollPane spListHoraris;

    private CtrlPresentacio ctrlPresentacio;

    public ViewPrincipal(CtrlPresentacio ctrlPresentacio) {
        this.ctrlPresentacio = ctrlPresentacio;
        inicialitzarComponents();
    }

    public void ferVisble(String dadesUsuari) {
        lblDadesUsuari.setText(dadesUsuari);
        framePrincipal.pack();
        framePrincipal.setVisible(true);
        String[] fitxers = ctrlPresentacio.getListaDirHorarisGenerats();
        listHoraris.setListData(fitxers);
    }

    private void inicialitzarComponents() {
        iniFrameView();
        activarListeners();
        listHoraris.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    }

    private void iniFrameView() {
        framePrincipal.setMinimumSize(new Dimension(750, 600));
        framePrincipal.setPreferredSize(framePrincipal.getMinimumSize());
        framePrincipal.setResizable(false);
        framePrincipal.setLocationRelativeTo(null);
        framePrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        framePrincipal.add(panelPrincipal);
    }

    private void activarListeners() {
        btnCarregarHoraris.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filename = ctrlPresentacio.getIdUsuari() + '/' + listHoraris.getSelectedValue().toString();
                TreeMap<Integer, ArrayList<String>> dadesHorari = null;
                try {
                    dadesHorari = ctrlPresentacio.getTreeMapHorari(filename);
                    //imprimirTreeMapHorari(dadesHorari);
                    String[] colNames = DiaSetmana.getDies(ctrlPresentacio.getNumDiesPeriodeLectiu());
                    String[][] dadesTableHorari = getDataTableHorari(dadesHorari);
                    imprimirDataTableHorari(colNames, dadesTableHorari);
                    tblHorari.setModel(new DefaultTableModel(dadesTableHorari, colNames));
                    tblHorari.setCellSelectionEnabled(true);
                    ListSelectionModel select = tblHorari.getSelectionModel();
                    select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        });

        listHoraris.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting() == false) {
                    btnCarregarHoraris.setEnabled(true);
                }
            }
        });
    }

    private void imprimirDataTableHorari(String[] colNames, String[][] dataTable) {
        for (int i = 0; i < colNames.length; ++i)
            System.out.print("\t\t\t" + colNames[i]);
        System.out.println();
        for (int i = 0; i < dataTable.length; ++i) {
            for (int j = 0; j < dataTable[i].length; ++j)
                if (dataTable[i][j] != null) System.out.print(dataTable[i][j] + "\t\t");
            System.out.println();
        }
    }

    private String[][] getDataTableHorari(TreeMap<Integer, ArrayList<String>> dadesHorari) {
        int firstKey = dadesHorari.firstKey();
        int lastKey = dadesHorari.lastKey();
        int numCol = ctrlPresentacio.getNumDiesPeriodeLectiu() + 1;
        int horaInici = firstKey / 10;
        int numRow = (lastKey / 10) - horaInici + 1;
        String[][] dadesTableHorari = new String[numRow][numCol];

        for (int i = 0; i < numRow; ++i)
            dadesTableHorari[i][0] = (horaInici + i) + "-" + (horaInici + i + 1) + "h";

        for (Map.Entry<Integer, ArrayList<String>> entry : dadesHorari.entrySet()) {
            Integer clau = entry.getKey();
            ArrayList<String> lista = entry.getValue();
            int row = (clau.intValue() / 10) - horaInici;
            int col = clau.intValue() % 10;
            StringBuilder data = new StringBuilder();
            for (String s : lista) {
                data.append(s);
            }
            dadesTableHorari[row][col] = data.toString();
        }
        return dadesTableHorari;
    }


    private void imprimirTreeMapHorari(TreeMap<Integer, ArrayList<String>> dadesHorari) {
        for (Map.Entry<Integer, ArrayList<String>> entry : dadesHorari.entrySet()) {
            Integer clau = entry.getKey();
            ArrayList<String> lista = entry.getValue();
            for (String s : lista) {
                System.out.println(clau + " => " + s);
            }
        }
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new GridLayoutManager(5, 4, new Insets(10, 15, 10, 15), -1, -1));
        panelPrincipal.setEnabled(true);
        lblDadesUsuari = new JLabel();
        lblDadesUsuari.setText("");
        panelPrincipal.add(lblDadesUsuari, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(74, 0), null, 0, false));
        btnCarregarHoraris = new JButton();
        btnCarregarHoraris.setEnabled(false);
        btnCarregarHoraris.setText("Carregar Horaris");
        btnCarregarHoraris.setVerticalAlignment(0);
        btnCarregarHoraris.setVisible(true);
        panelPrincipal.add(btnCarregarHoraris, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(75, 30), null, 0, false));
        spTblHorari = new JScrollPane();
        spTblHorari.setVisible(true);
        panelPrincipal.add(spTblHorari, new GridConstraints(4, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        spTblHorari.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(-16777216)), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, this.getFont(null, -1, -1, spTblHorari.getFont())));
        tblHorari = new JTable();
        tblHorari.setAutoCreateRowSorter(false);
        tblHorari.setAutoResizeMode(2);
        tblHorari.setEnabled(true);
        tblHorari.setRowHeight(30);
        tblHorari.setRowMargin(5);
        tblHorari.setRowSelectionAllowed(true);
        tblHorari.setShowVerticalLines(true);
        tblHorari.setSurrendersFocusOnKeystroke(false);
        tblHorari.setVisible(true);
        spTblHorari.setViewportView(tblHorari);
        btnGenerarHorari = new JButton();
        btnGenerarHorari.setText("Generar horari nou");
        panelPrincipal.add(btnGenerarHorari, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(75, 30), null, 0, false));
        spListHoraris = new JScrollPane();
        spListHoraris.setVisible(true);
        panelPrincipal.add(spListHoraris, new GridConstraints(1, 2, 3, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(120, 130), null, 0, false));
        spListHoraris.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Triar un horari generat"));
        listHoraris = new JList();
        final DefaultListModel defaultListModel1 = new DefaultListModel();
        listHoraris.setModel(defaultListModel1);
        listHoraris.setVisible(true);
        spListHoraris.setViewportView(listHoraris);
        final JButton button1 = new JButton();
        button1.setText("Modificar escenari");
        panelPrincipal.add(button1, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font getFont(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panelPrincipal;
    }
}
