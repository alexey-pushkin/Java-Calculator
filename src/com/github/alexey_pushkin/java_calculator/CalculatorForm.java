package com.github.alexey_pushkin.java_calculator;
// todo new icon
// +todo license
// +todo how another projects are uploaded
// todo code review + comments
// todo find all todos
// todo test how everything works
// todo review my github profile
// todo add build .bat
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.text.DecimalFormat;

public class CalculatorForm {

    private JPanel panel;
    private JTextField resultField;
    private JButton a7Button;
    private JButton a4Button;
    private JButton a8Button;
    private JButton a5Button;
    private JButton a9Button;
    private JButton a6Button;
    private JButton a1Button;
    private JButton a2Button;
    private JButton a3Button;
    private JButton a0Button;
    private JButton dotButton;
    private JButton divideButton;
    private JButton multiplyButton;
    private JButton subtractButton;
    private JButton addButton;
    private JButton resultButton;
    private JButton backspaceButton;
    private JButton ceButton;
    private JButton cButton;
    private JButton squareRootButton;

    private Calculator calculator;
    private Boolean isOverrideInput = false;
    private Boolean isResultSkipped = false;

    public CalculatorForm() {
        calculator = new Calculator();
        initListeners();
    }

    public static void main(String[] args) {
        initFrame();
    }

    public static JFrame initFrame() {
        final JFrame frame = new JFrame("Calculator");
        CalculatorForm form = new CalculatorForm();

        setIcon(frame);
        frame.setContentPane(form.panel);
        frame.setLocation(800, 300);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        initKeyListener(frame, form);

        return frame;
    }

    public static void setIcon(JFrame frame) {
        try {
            Image image = ImageIO.read(CalculatorForm.class.getResource("images/icon.png"));
            frame.setIconImage(image);
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }

    private void numericButtonClicked(String s) {
        resultField.setText(isOverrideInput ? s : resultField.getText() + s);
        isOverrideInput = false;
        calculator.addValue(Double.valueOf(resultField.getText()));
    }

    private String format(double d) {
        DecimalFormat df;
        if (d > 99999999999d) {
            df = new DecimalFormat("#.########E00");
        } else {
            df = new DecimalFormat("###,###,###,###,###.###############");
        }
        return String.format("%.14s", df.format(d));
    }

    private void resultButtonClick() {
        Double result = calculator.getResult();
        resultField.setText(result != null ? format(result) : "");
        isOverrideInput = true;
        isResultSkipped = false;
    }

    private void initListeners() {
        a0Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                numericButtonClicked("0");
            }
        });
        a1Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                numericButtonClicked("1");
            }
        });
        a2Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                numericButtonClicked("2");
            }
        });
        a3Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                numericButtonClicked("3");
            }
        });
        a4Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                numericButtonClicked("4");
            }
        });
        a5Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                numericButtonClicked("5");
            }
        });
        a6Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                numericButtonClicked("6");
            }
        });
        a7Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                numericButtonClicked("7");
            }
        });
        a8Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                numericButtonClicked("8");
            }
        });
        a9Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                numericButtonClicked("9");
            }
        });

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (isResultSkipped) {
                    resultButtonClick();
                }
                calculator.setOperation(Calculator.Operation.ADD);
                isOverrideInput = true;
                isResultSkipped = true;
            }
        });
        subtractButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (isResultSkipped) {
                    resultButtonClick();
                }
                calculator.setOperation(Calculator.Operation.SUBTRACT);
                isOverrideInput = true;
                isResultSkipped = true;
            }
        });
        divideButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (isResultSkipped) {
                    resultButtonClick();
                }
                calculator.setOperation(Calculator.Operation.DIVIDE);
                isOverrideInput = true;
                isResultSkipped = true;
            }
        });
        multiplyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (isResultSkipped) {
                    resultButtonClick();
                }
                calculator.setOperation(Calculator.Operation.MULTIPLY);
                isOverrideInput = true;
                isResultSkipped = true;
            }
        });

        resultButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultButtonClick();
            }
        });


        ceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculator.clearEntry();
                resultField.setText("");
            }
        });
        cButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculator.clearAll();
                resultField.setText("");
            }
        });

        squareRootButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculator.setOperation(Calculator.Operation.SQRT);
                resultButtonClick();
            }
        });

        backspaceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String s = resultField.getText();
                if (s.length() > 0) {
                    resultField.setText(s.substring(0, s.length() - 1));
                }
                calculator.addValue(Double.valueOf(resultField.getText()));
            }
        });

        dotButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String s = resultField.getText(); // todo here dot button logic
                if (s.length() > 0 && !s.contains(",")) {
                    resultField.setText(s + ",");
                }
                calculator.addValue(Double.valueOf(resultField.getText()));
            }
        });
    }

    private static void initKeyListener(JFrame frame, final CalculatorForm form) {
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                switch (e.getKeyChar()) {
                    case KeyEvent.VK_0:
                        form.a0Button.doClick();
                        break;
                    case KeyEvent.VK_1:
                        form.a1Button.doClick();
                        break;
                    case KeyEvent.VK_2:
                        form.a2Button.doClick();
                        break;
                    case KeyEvent.VK_3:
                        form.a3Button.doClick();
                        break;
                    case KeyEvent.VK_4:
                        form.a4Button.doClick();
                        break;
                    case KeyEvent.VK_5:
                        form.a5Button.doClick();
                        break;
                    case KeyEvent.VK_6:
                        form.a6Button.doClick();
                        break;
                    case KeyEvent.VK_7:
                        form.a7Button.doClick();
                        break;
                    case KeyEvent.VK_8:
                        form.a8Button.doClick();
                        break;
                    case KeyEvent.VK_9:
                        form.a9Button.doClick();
                        break;

                    case KeyEvent.VK_BACK_SPACE:
                        form.backspaceButton.doClick();
                        break;
                    case KeyEvent.VK_ENTER:
                        form.resultButton.doClick();
                        break;
                    case '+':
                        form.addButton.doClick();
                        break;
                    case '-':
                        form.subtractButton.doClick();
                        break;
                    case '*':
                        form.multiplyButton.doClick();
                        break;
                    case '/':
                        form.divideButton.doClick();
                        break;
                    case '.':
                        form.dotButton.doClick();
                        break;
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
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
        panel = new JPanel();
        panel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(6, 4, new Insets(5, 5, 5, 5), -1, -1));
        resultField = new JTextField();
        resultField.setAutoscrolls(true);
        resultField.setColumns(0);
        resultField.setFocusable(false);
        resultField.setFont(new Font(resultField.getFont().getName(), resultField.getFont().getStyle(), 28));
        resultField.setHorizontalAlignment(4);
        resultField.setName("resultField");
        panel.add(resultField, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        a7Button = new JButton();
        a7Button.setFocusable(false);
        a7Button.setName("a7Button");
        a7Button.setText("7");
        panel.add(a7Button, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, 30), new Dimension(50, 30), new Dimension(50, 30), 0, false));
        a4Button = new JButton();
        a4Button.setFocusable(false);
        a4Button.setName("a4Button");
        a4Button.setText("4");
        panel.add(a4Button, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, 30), new Dimension(50, 30), new Dimension(50, 30), 0, false));
        a5Button = new JButton();
        a5Button.setFocusable(false);
        a5Button.setName("a5Button");
        a5Button.setText("5");
        panel.add(a5Button, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, 30), new Dimension(50, 30), new Dimension(50, 30), 0, false));
        a6Button = new JButton();
        a6Button.setFocusable(false);
        a6Button.setName("a6Button");
        a6Button.setText("6");
        panel.add(a6Button, new com.intellij.uiDesigner.core.GridConstraints(3, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, 30), new Dimension(50, 30), new Dimension(50, 30), 0, false));
        a2Button = new JButton();
        a2Button.setFocusable(false);
        a2Button.setName("a2Button");
        a2Button.setText("2");
        panel.add(a2Button, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, 30), new Dimension(50, 30), new Dimension(50, 30), 0, false));
        a3Button = new JButton();
        a3Button.setFocusable(false);
        a3Button.setName("a3Button");
        a3Button.setText("3");
        panel.add(a3Button, new com.intellij.uiDesigner.core.GridConstraints(4, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, 30), new Dimension(50, 30), new Dimension(50, 30), 0, false));
        a0Button = new JButton();
        a0Button.setFocusable(false);
        a0Button.setName("a0Button");
        a0Button.setText("0");
        panel.add(a0Button, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, 30), new Dimension(50, 30), new Dimension(50, 30), 0, false));
        dotButton = new JButton();
        dotButton.setFocusable(false);
        dotButton.setLabel(".");
        dotButton.setName("dotButton");
        dotButton.setText(".");
        panel.add(dotButton, new com.intellij.uiDesigner.core.GridConstraints(5, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, 30), new Dimension(50, 30), new Dimension(50, 30), 0, false));
        multiplyButton = new JButton();
        multiplyButton.setFocusable(false);
        multiplyButton.setName("multiplyButton");
        multiplyButton.setText("*");
        panel.add(multiplyButton, new com.intellij.uiDesigner.core.GridConstraints(3, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, 30), new Dimension(50, 30), new Dimension(50, 30), 0, false));
        subtractButton = new JButton();
        subtractButton.setFocusable(false);
        subtractButton.setName("subtractButton");
        subtractButton.setText("-");
        panel.add(subtractButton, new com.intellij.uiDesigner.core.GridConstraints(4, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, 30), new Dimension(50, 30), new Dimension(50, 30), 0, false));
        addButton = new JButton();
        addButton.setFocusable(false);
        addButton.setName("addButton");
        addButton.setText("+");
        panel.add(addButton, new com.intellij.uiDesigner.core.GridConstraints(5, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, 30), new Dimension(50, 30), new Dimension(50, 30), 0, false));
        a1Button = new JButton();
        a1Button.setFocusable(false);
        a1Button.setName("a1Button");
        a1Button.setText("1");
        panel.add(a1Button, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, 30), new Dimension(50, 30), new Dimension(50, 30), 0, false));
        resultButton = new JButton();
        resultButton.setFocusable(false);
        resultButton.setName("resultButton");
        resultButton.setText("=");
        panel.add(resultButton, new com.intellij.uiDesigner.core.GridConstraints(5, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, 30), new Dimension(50, 30), new Dimension(50, 30), 0, false));
        backspaceButton = new JButton();
        backspaceButton.setFocusable(false);
        backspaceButton.setFont(new Font(backspaceButton.getFont().getName(), backspaceButton.getFont().getStyle(), backspaceButton.getFont().getSize()));
        backspaceButton.setName("backspaceButton");
        backspaceButton.setText("←");
        panel.add(backspaceButton, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, 30), new Dimension(50, 30), new Dimension(50, 30), 0, false));
        a8Button = new JButton();
        a8Button.setFocusable(false);
        a8Button.setName("a8Button");
        a8Button.setText("8");
        panel.add(a8Button, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, 30), new Dimension(50, 30), new Dimension(50, 30), 0, false));
        a9Button = new JButton();
        a9Button.setFocusable(false);
        a9Button.setName("a9Button");
        a9Button.setText("9");
        panel.add(a9Button, new com.intellij.uiDesigner.core.GridConstraints(2, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, 30), new Dimension(50, 30), new Dimension(50, 30), 0, false));
        divideButton = new JButton();
        divideButton.setFocusable(false);
        divideButton.setName("divideButton");
        divideButton.setText("/");
        panel.add(divideButton, new com.intellij.uiDesigner.core.GridConstraints(2, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, 30), new Dimension(50, 30), new Dimension(50, 30), 0, false));
        ceButton = new JButton();
        ceButton.setFocusable(false);
        ceButton.setName("ceButton");
        ceButton.setText("CE");
        panel.add(ceButton, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, 30), new Dimension(50, 30), new Dimension(50, 30), 0, false));
        cButton = new JButton();
        cButton.setFocusable(false);
        cButton.setName("cButton");
        cButton.setSelected(true);
        cButton.setText("C");
        panel.add(cButton, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, 30), new Dimension(50, 30), new Dimension(50, 30), 0, false));
        squareRootButton = new JButton();
        squareRootButton.setFocusable(false);
        squareRootButton.setName("squareRootButton");
        squareRootButton.setText("√");
        panel.add(squareRootButton, new com.intellij.uiDesigner.core.GridConstraints(1, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, 30), new Dimension(50, 30), new Dimension(50, 30), 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }
}
