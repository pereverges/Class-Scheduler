package com.prop.fib.horaris.presentation;


import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewLogin {

    private JFrame frameLogin = new JFrame("Generador Horaris");
    private JPanel panelLogin = new JPanel();
    private JLabel lblUsuari = new JLabel();
    private JLabel lblPassword = new JLabel();
    private JTextField txtUsuari = new JTextField();
    private JPasswordField password = new JPasswordField();
    private JButton btnLogin = new JButton();
    private JLabel lblError = new JLabel();

    private CtrlPresentacio ctrlPresentacio;

    public ViewLogin(CtrlPresentacio ctrlPresentacio) {
        this.ctrlPresentacio = ctrlPresentacio;
        inicialitzarComponents();
    }

    public void ferVisible() {
        frameLogin.pack();
        frameLogin.setVisible(true);
    }

    private void inicialitzarComponents() {
        iniFrameView();
        activarListeners();
    }

    private void iniFrameView() {
        frameLogin.setMinimumSize(new Dimension(260, 155));
        frameLogin.setPreferredSize(frameLogin.getMinimumSize());
        frameLogin.setResizable(false);
        frameLogin.setLocationRelativeTo(null);
        frameLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameLogin.add(panelLogin);
    }

    private void activarListeners() {

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ctrlPresentacio.isLoginOk(txtUsuari.getText(), new String(password.getPassword()))) {
                    lblError.setVisible(false);
                    frameLogin.dispose();
                    //ctrlPresentacio.obrirVistaPrincipal(ctrlPresentacio.imprimirDataUsuari());
                    ctrlPresentacio.cargarVistaUsuari();
                } else {
                    lblError.setText("Usuari o contraseña incorrecta.!");
                    lblError.setVisible(true);
                }
            }
        });
    }


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


    public JComponent $$$getRootComponent$$$() {
        return panelLogin;
    }
}
