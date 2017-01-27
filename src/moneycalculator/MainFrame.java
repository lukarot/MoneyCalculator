package moneycalculator;

import java.awt.GridBagConstraints;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import moneycalculator.control.CalculateCommand;
import moneycalculator.control.Command;
import moneycalculator.model.Currency;
import moneycalculator.ui.MoneyDialog;
import moneycalculator.ui.MoneyDisplay;
import moneycalculator.ui.swing.SwingMoneyDialog;
import moneycalculator.ui.swing.SwingMoneyDisplay;

public class MainFrame extends JFrame{

    private final Currency[] currencies;
    private final Map<String,Command> commands = new HashMap<>();
    private MoneyDialog moneyDialog;
    private MoneyDisplay moneyDisplay;
    
    public MainFrame(Currency[] currencies) {
        this.currencies=currencies;
        this.setTitle("Money Calculator");
        this.setSize(400, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        moneyDialog();
        moneyDisplay();
        toolbar();
        this.setVisible(true);
    }

    private void moneyDialog() {
        SwingMoneyDialog dialog = new SwingMoneyDialog(currencies);
        moneyDialog=dialog;
        GridBagConstraints c=new GridBagConstraints();
        c.gridx=0;
        c.gridy=0;
        c.anchor=GridBagConstraints.NORTH;
        this.add(dialog,c);
    }
    
    private void moneyDisplay() {
        
        SwingMoneyDisplay display = new SwingMoneyDisplay();
        moneyDisplay=display;
        GridBagConstraints c=new GridBagConstraints();
        c.gridx=0;
        c.gridy=1;
        c.anchor=GridBagConstraints.CENTER;
        this.add(display,c);
    }

    private void toolbar() {
        JPanel jPanel = new JPanel();
        jPanel.add(CalculateButton());
        GridBagConstraints c=new GridBagConstraints();
        c.gridx=0;
        c.gridy=1;
        c.anchor=GridBagConstraints.CENTER;
        this.add(jPanel,c);
    }

    public MoneyDialog getMoneyDialog() {
        return moneyDialog;
    }

    public MoneyDisplay getMoneyDisplay() {
        return moneyDisplay;
    }
    public void add(Command command) {
        commands.put(command.name(),command);
    }
    void add(CalculateCommand calculateCommand) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

      private JButton CalculateButton() {
        JButton button = new JButton("Calculate");
        button.addActionListener(calculate());
        return button;
    }

    private ActionListener calculate() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commands.get("calculate").execute();
            }
        };
    }
   
}