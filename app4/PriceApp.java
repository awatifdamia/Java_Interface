import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.text.DecimalFormat;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class PriceGuiApp extends JFrame {

    // Customer 1 Fields (SmartPhone)
    private final JTextField txtC1Name = new JTextField("Sally");
    private final JTextField txtC1Salary = new JTextField("3000.00");
    private final JTextField txtSpName = new JTextField("Iphone 14 ProMax");

    // Customer 2 Fields (Laptop)
    private final JTextField txtC2Name = new JTextField("Aaron");
    private final JTextField txtC2Salary = new JTextField("8000.00");
    private final JTextField txtLpName = new JTextField("Lenovo Yoga");

    // Customer 3 Fields (Car)
    private final JTextField txtC3Name = new JTextField("Simon");
    private final JTextField txtC3Salary = new JTextField("10000.00");
    private final JTextField txtCarBrand = new JTextField("BMW");

    // Display Labels
    private final JLabel lblTag1 = new JLabel("SMARTPHONE CHECK");
    private final JLabel lblOut1 = new JLabel("Awaiting check...");

    private final JLabel lblTag2 = new JLabel("LAPTOP CHECK");
    private final JLabel lblOut2 = new JLabel("Awaiting check...");

    private final JLabel lblTag3 = new JLabel("CAR CHECK");
    private final JLabel lblOut3 = new JLabel("Awaiting check...");

    private final DecimalFormat df = new DecimalFormat("0.0");

    public PriceGuiApp() {
        super("Price & Affordability App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel root = new JPanel(new GridBagLayout());
        root.setBackground(new Color(244, 245, 249));

        RoundedPanel card = new RoundedPanel(32, Color.WHITE);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(new EmptyBorder(24, 24, 24, 24));
        card.setPreferredSize(new Dimension(520, 760));

        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);

        JPanel headerTitles = new JPanel();
        headerTitles.setOpaque(false);
        headerTitles.setLayout(new BoxLayout(headerTitles, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Price & Affordability");
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        title.setForeground(new Color(15, 23, 42));

        JLabel sub = new JLabel("Evaluates purchase eligibility via Price Interface");
        sub.setFont(new Font("SansSerif", Font.BOLD, 12));
        sub.setForeground(new Color(100, 116, 139));

        headerTitles.add(title);
        headerTitles.add(sub);

        JLabel badge = new RoundedLabel("Price App", new Color(254, 240, 138), new Color(113, 63, 18));
        badge.setFont(new Font("SansSerif", Font.BOLD, 12));

        header.add(headerTitles, BorderLayout.WEST);
        header.add(badge, BorderLayout.EAST);
        card.add(header);
        card.add(Box.createRigidArea(new Dimension(0, 14)));

        // Customer 1 Section
        card.add(buildFormSection("Customer 1 (SmartPhone Check)", txtC1Name, txtC1Salary, "SmartPhone Model", txtSpName));
        card.add(Box.createRigidArea(new Dimension(0, 10)));

        // Customer 2 Section
        card.add(buildFormSection("Customer 2 (Laptop Check)", txtC2Name, txtC2Salary, "Laptop Model", txtLpName));
        card.add(Box.createRigidArea(new Dimension(0, 10)));

        // Customer 3 Section
        card.add(buildFormSection("Customer 3 (Car Check)", txtC3Name, txtC3Salary, "Car Brand", txtCarBrand));
        card.add(Box.createRigidArea(new Dimension(0, 14)));

        // Action Button
        JButton btnRun = new JButton("💳 Check Affordability");
        btnRun.setFont(new Font("SansSerif", Font.BOLD, 15));
        btnRun.setForeground(Color.WHITE);
        btnRun.setBackground(new Color(30, 41, 59));
        btnRun.setFocusPainted(false);
        btnRun.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));
        btnRun.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        btnRun.addActionListener(e -> runCheck());
        card.add(btnRun);
        card.add(Box.createRigidArea(new Dimension(0, 14)));

        // Results Stack
        card.add(buildResultCard(lblTag1, lblOut1, new Color(251, 207, 232)));
        card.add(Box.createRigidArea(new Dimension(0, 8)));
        card.add(buildResultCard(lblTag2, lblOut2, new Color(191, 219, 254)));
        card.add(Box.createRigidArea(new Dimension(0, 8)));
        card.add(buildResultCard(lblTag3, lblOut3, new Color(217, 249, 157)));

        root.add(card);
        setContentPane(root);
        pack();
        setLocationRelativeTo(null);

        runCheck();
    }

    private JPanel buildFormSection(String titleText, JTextField nameField, JTextField salaryField, String modelLabelText, JTextField modelField) {
        RoundedPanel section = new RoundedPanel(20, new Color(248, 250, 252));
        section.setLayout(new BoxLayout(section, BoxLayout.Y_AXIS));
        section.setBorder(new EmptyBorder(10, 12, 10, 12));

        JLabel title = new JLabel(titleText.toUpperCase());
        title.setFont(new Font("SansSerif", Font.BOLD, 10));
        title.setForeground(new Color(15, 23, 42));
        section.add(title);
        section.add(Box.createRigidArea(new Dimension(0, 4)));

        JPanel grid = new JPanel(new GridLayout(1, 2, 8, 0));
        grid.setOpaque(false);
        grid.add(buildInput("Customer Name", nameField));
        grid.add(buildInput("Salary (RM)", salaryField));

        section.add(grid);
        section.add(Box.createRigidArea(new Dimension(0, 4)));
        section.add(buildInput(modelLabelText, modelField));

        return section;
    }

    private JPanel buildInput(String labelText, JTextField field) {
        JPanel box = new JPanel();
        box.setOpaque(false);
        box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));

        JLabel lbl = new JLabel(labelText);
        lbl.setFont(new Font("SansSerif", Font.BOLD, 10));
        lbl.setForeground(new Color(100, 116, 139));

        field.setFont(new Font("SansSerif", Font.BOLD, 12));
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 28));

        box.add(lbl);
        box.add(field);
        return box;
    }

    private JPanel buildResultCard(JLabel tagLabel, JLabel outLabel, Color bg) {
        RoundedPanel card = new RoundedPanel(16, bg);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(new EmptyBorder(8, 12, 8, 12));

        tagLabel.setFont(new Font("SansSerif", Font.BOLD, 10));
        tagLabel.setForeground(new Color(30, 41, 59));

        outLabel.setFont(new Font("SansSerif", Font.BOLD, 11));
        outLabel.setForeground(new Color(15, 23, 42));

        card.add(tagLabel);
        card.add(Box.createRigidArea(new Dimension(0, 2)));
        card.add(outLabel);

        return card;
    }

    private void runCheck() {
        String c1Name = txtC1Name.getText().trim();
        double c1Sal = parseDouble(txtC1Salary.getText(), 3000.0);
        String spName = txtSpName.getText().trim();

        String c2Name = txtC2Name.getText().trim();
        double c2Sal = parseDouble(txtC2Salary.getText(), 8000.0);
        String lpName = txtLpName.getText().trim();

        String c3Name = txtC3Name.getText().trim();
        double c3Sal = parseDouble(txtC3Salary.getText(), 10000.0);
        String carBrand = txtCarBrand.getText().trim();

        lblTag1.setText("SMARTPHONE CHECK (" + c1Name.toUpperCase() + ")");
        lblTag2.setText("LAPTOP CHECK (" + c2Name.toUpperCase() + ")");
        lblTag3.setText("CAR CHECK (" + c3Name.toUpperCase() + ")");

        // 1. SmartPhone Check
        String out1 = "<html>The product is " + spName + ".<br>";
        if (c1Sal > 4000) {
            out1 += "The customer's name is " + c1Name + " with salary RM" + df.format(c1Sal) + " can buy this product. Your salary is higher than the price's product.</html>";
        } else {
            out1 += "The customer's name is " + c1Name + " with salary RM" + df.format(c1Sal) + " cannot buy this product. The product price is more than your salary.</html>";
        }

        // 2. Laptop Check
        String out2 = "<html>The product is " + lpName + ".<br>";
        if (c2Sal > 4000) {
            out2 += "The customer's name is " + c2Name + " with salary RM" + df.format(c2Sal) + " can buy this product. Your salary is higher than the price's product.</html>";
        } else {
            out2 += "The customer's name is " + c2Name + " with salary RM" + df.format(c2Sal) + " cannot buy this product. The product price is more than your salary.</html>";
        }

        // 3. Car Check
        String out3 = "<html>The car brand is " + carBrand + " with price RM200000.0.<br>";
        if (c3Sal > 8000) {
            out3 += "The customer's name is " + c3Name + " with salary RM" + df.format(c3Sal) + " can buy this car. Your salary is higher than the price's car.</html>";
        } else {
            out3 += "The customer's name is " + c3Name + " with salary RM" + df.format(c3Sal) + " cannot buy this car. The car price is more than your salary.</html>";
        }

        lblOut1.setText(out1);
        lblOut2.setText(out2);
        lblOut3.setText(out3);
    }

    private double parseDouble(String str, double defaultVal) {
        try {
            return Double.parseDouble(str.trim());
        } catch (Exception e) {
            return defaultVal;
        }
    }

    static class RoundedPanel extends JPanel {
        private final int radius;
        private final Color bgColor;

        RoundedPanel(int radius, Color bg) {
            this.radius = radius;
            this.bgColor = bg;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(bgColor);
            g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), radius, radius));
            g2.dispose();
            super.paintComponent(g);
        }
    }

    static class RoundedLabel extends JLabel {
        private final Color bgColor;

        RoundedLabel(String text, Color bg, Color fg) {
            super(text);
            this.bgColor = bg;
            setForeground(fg);
            setBorder(BorderFactory.createEmptyBorder(4, 10, 4, 10));
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(bgColor);
            g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 14, 14));
            g2.dispose();
            super.paintComponent(g);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PriceGuiApp().setVisible(true));
    }
}
