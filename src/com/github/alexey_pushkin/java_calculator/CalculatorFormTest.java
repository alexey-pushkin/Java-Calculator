package com.github.alexey_pushkin.java_calculator;

import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CalculatorFormTest {

    JButton a0Button;
    JButton a1Button;
    JButton a2Button;
    JButton a3Button;
    JButton a4Button;
    JButton a5Button;
    JButton a6Button;
    JButton a7Button;
    JButton a8Button;
    JButton a9Button;

    JButton addButton;
    JButton subtractButton;
    JButton multiplyButton;
    JButton divideButton;
    JButton squareRootButton;
    JButton resultButton;

    JButton dotButton;
    JButton cButton;
    JButton ceButton;
    JButton backspaceButton;

    JTextField resultField;

    @Before
    public void init() throws Exception {

        JFrame frame = CalculatorForm.initFrame();

        assertNotNull(frame);

        a0Button = (JButton) getChildNamed(frame, "a0Button");
        a1Button = (JButton) getChildNamed(frame, "a1Button");
        a2Button = (JButton) getChildNamed(frame, "a2Button");
        a3Button = (JButton) getChildNamed(frame, "a3Button");
        a4Button = (JButton) getChildNamed(frame, "a4Button");
        a5Button = (JButton) getChildNamed(frame, "a5Button");
        a6Button = (JButton) getChildNamed(frame, "a6Button");
        a7Button = (JButton) getChildNamed(frame, "a7Button");
        a8Button = (JButton) getChildNamed(frame, "a8Button");
        a9Button = (JButton) getChildNamed(frame, "a9Button");

        addButton = (JButton) getChildNamed(frame, "addButton");
        subtractButton = (JButton) getChildNamed(frame, "subtractButton");
        multiplyButton = (JButton) getChildNamed(frame, "multiplyButton");
        divideButton = (JButton) getChildNamed(frame, "divideButton");
        squareRootButton = (JButton) getChildNamed(frame, "squareRootButton");
        resultButton = (JButton) getChildNamed(frame, "resultButton");

        dotButton = (JButton) getChildNamed(frame, "dotButton");
        cButton = (JButton) getChildNamed(frame, "cButton");
        ceButton = (JButton) getChildNamed(frame, "ceButton");
        backspaceButton = (JButton) getChildNamed(frame, "backspaceButton");

        resultField = (JTextField) getChildNamed(frame, "resultField");
    }

    @Test
    public void mess() {

        // +=
        c();
        add();
        result();
        assertEquals("", getResult());

        // +3=3
        c();
        add();
        a3();
        result();
        assertEquals("3", getResult());

        // -2=-2
        c();
        subtract();
        a2();
        result();
        assertEquals("-2", getResult());

    }

    @Test
    public void operations() {
        c();

        // 1+2=3
        a1Button.doClick();
        addButton.doClick();
        a2Button.doClick();
        resultButton.doClick();
        assertEquals("3", resultField.getText());

        // 23+11=34
        a2Button.doClick();
        a3Button.doClick();
        addButton.doClick();
        a1Button.doClick();
        a1Button.doClick();
        resultButton.doClick();
        assertEquals("34", getResult());

        c();

        // 3+11-40=-26
        a3();
        add();
        a1();
        a1();
        subtract();
        assertEquals("14", getResult());
        a4();
        a0();
        result();
        assertEquals("-26", getResult());

        c();

        // -5+13=8
        subtract();
        a5();
        add();
        a1();
        a3();
        result();
        assertEquals("8", getResult());

        // 10/20=0.5
        a1();
        a0();
        divide();
        a2();
        a0();
        result();
        assertEquals("0,5", getResult());

        // 12√3.464101615137
        a1();
        a2();
        sqrt();
        assertEquals("3,464101615137", getResult());

        // 25√5
        a2();
        a5();
        sqrt();
        assertEquals("5", getResult());

        // +7=12
        add();
        a7();
        result();
        assertEquals("12", getResult());

        c();
        // -5*25←*100←←+7=-3
        subtract();
        a5();
        multiply();
        a2();
        a5();
        backspace();
        multiply();
        a1();
        a0();
        a0();
        backspace();
        backspace();
        add();
        a7();
        result();
        assertEquals("-3", getResult());

        // 111111*111111=12345654321
        a1();
        a1();
        a1();
        a1();
        a1();
        a1();
        multiply();
        a1();
        a1();
        a1();
        a1();
        a1();
        a1();
        result();
        assertEquals("12 345 654 321", getResult());

        // 1111111*1111111=1234567654321
        a1();
        a1();
        a1();
        a1();
        a1();
        a1();
        a1();
        multiply();
        a1();
        a1();
        a1();
        a1();
        a1();
        a1();
        a1();
        result();
        assertEquals("1,23456765E12", getResult());

        // 12.3 CE 6.89*3.1=21,359
        a1();
        a2();
        dot();
        a3();
        ce();
        assertEquals("", getResult());
        a6();
        dot();
        a8();
        a9();
        multiply();
        a3();
        dot();
        a1();
        result();
        assertEquals("21,359", getResult());
    }


    public static Component getChildNamed(Component parent, String name) {
        if (name.equals(parent.getName())) {
            return parent;
        }

        if (parent instanceof Container) {
            Component[] children = ((Container) parent).getComponents();

            for (int i = 0; i < children.length; ++i) {
                Component child = getChildNamed(children[i], name);
                if (child != null) {
                    return child;
                }
            }
        }

        return null;
    }

    private void a0() {a0Button.doClick();}
    private void a1() {a1Button.doClick();}
    private void a2() {a2Button.doClick();}
    private void a3() {a3Button.doClick();}
    private void a4() {a4Button.doClick();}
    private void a5() {a5Button.doClick();}
    private void a6() {a6Button.doClick();}
    private void a7() {a7Button.doClick();}
    private void a8() {a8Button.doClick();}
    private void a9() {a9Button.doClick();}

    private void add() {addButton.doClick();}
    private void subtract() {subtractButton.doClick();}
    private void multiply() {multiplyButton.doClick();}
    private void divide() {divideButton.doClick();}
    private void sqrt() {squareRootButton.doClick();}
    private void result() {resultButton.doClick();}

    private void dot() {dotButton.doClick();}
    private void c() {cButton.doClick();}
    private void ce() {ceButton.doClick();}
    private void backspace() {backspaceButton.doClick();}

    private String getResult() { return resultField.getText();}
}
