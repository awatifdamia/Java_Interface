import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.text.DecimalFormat;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

interface RadioTuner {
    void setStation(String station);
    void setFrequency(double frequency);
}

class MobileDevice implements RadioTuner {
    protected String manufacturer;
    protected String model;
    public String station;
    public double frequency;

    public MobileDevice(String manufacturer, String model) {
        this.manufacturer = manufacturer;
        this.model = model;
    }

    public void printDetails() {
        System.out.println("Manufacturer: " + manufacturer);
        System.out.println("Model: " + model);
    }

    @Override
    public void setStation(String station) {
        this.station = station;
    }

    @Override
    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }
}

class SmartPhone extends MobileDevice {
    public String type;

    public SmartPhone(String manufacturer, String model, String type) {
        super(manufacturer, model);
        this.type = type;
    }

    @Override
    public void printDetails() {
        System.out.println("Smart Phone Detail:");
        super.printDetails();
        System.out.println("Type: " + type);
    }
}

class BasicPhone extends MobileDevice {

    public BasicPhone(String manufacturer, String model) {
        super(manufacturer, model);
    }

    @Override
    public void printDetails() {
        System.out.println("Basic Phone Detail:");
        super.printDetails();
    }
}

public class MobileApp extends JFrame {

    private final JTextField txtM1Brand = new JTextField("Samsung");
    private final JTextField txtM1Model = new JTextField("S20");
    private final JTextField txtM1Type = new JTextField("Galaxy");
    private final JTextField txtM1Station = new JTextField("Hot FM");
    private final JTextField txtM1Freq = new JTextField("97.60");

    private final JTextField txtM2Brand = new JTextField("Sonny Ericson");
    private final JTextField txtM2Model = new JTextField("Walkman");
    private final JTextField txtM2Station = new JTextField("Fly FM");
    private final JTextField txtM2Freq = new JTextField("109.80");

    private final JLabel lblM1Details = new JLabel();
    private final JLabel lblM1Radio = new JLabel();

    private final JLabel lblM2Details = new JLabel();
    private final JLabel lblM2Radio = new JLabel();

    private final DecimalFormat df = new DecimalFormat("0.00");

    public MobileApp() {
        super("Mobile & Radio Tuner App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel root = new JPanel(new GridBagLayout());
        root.setBackground(new Color(244, 245, 249));

        RoundedPanel card = new RoundedPanel(32, Color.WHITE);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(new EmptyBorder(28, 28, 28, 28));
        card.setPreferredSize(new Dimension(500, 720));

        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);

        JPanel headerTitles = new JPanel();
        headerTitles.setOpaque(false);
        headerTitles.setLayout(new BoxLayout(headerTitles, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Mobile & Radio Tuner");
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        title.setForeground(new Color(15, 23, 42));

        JLabel sub = new JLabel("Manages device specs and RadioTuner interfaces");
        sub.setFont(new Font("SansSerif", Font.BOLD, 12));
        sub.setForeground(new Color(100, 116, 139));

        headerTitles.add(title);
        headerTitles.add(sub);

        JLabel badge = new RoundedLabel("Mobile App", new Color(224, 231, 255), new Color(55, 48, 163));
        badge.setFont(new Font("SansSerif", Font.BOLD, 12));

        header.add(headerTitles, BorderLayout.WEST);
        header.add(badge, BorderLayout.EAST);
        card.add(header);
        card.add(Box.createRigidArea(new Dimension(0, 16)));

        card.add(buildDevice1Section());
        card.add(Box.createRigidArea(new Dimension(0, 12)));

        card.add(buildDevice2Section());
        card.add(Box.createRigidArea(new Dimension(0, 16)));

        JButton btnRun = new JButton("📻 Tune & Sync Devices");
        btnRun.setFont(new Font("SansSerif", Font.BOLD, 15));
        btnRun.setForeground(Color.WHITE);
        btnRun.setBackground(new Color(30, 41, 59));
        btnRun.setFocusPainted(false);
        btnRun.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));
        btnRun.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        btnRun.addActionListener(e -> runSimulation());
        card.add(btnRun);
        card.add(Box.createRigidArea(new Dimension(0, 16)));

        card.add(buildResultCard("SmartPhone Details", lblM1Details, lblM1Radio, new Color(217, 249, 157)));
        card.add(Box.createRigidArea(new Dimension(0, 10)));
        card.add(buildResultCard("BasicPhone Details", lblM2Details, lblM2Radio, new Color(191, 219, 254)));

        root.add(card);
        setContentPane(root);
        pack();
        setLocationRelativeTo(null);

        runSimulation();
    }

    private JPanel buildDevice1Section() {
        RoundedPanel section = new RoundedPanel(22, new Color(248, 250, 252));
        section.setLayout(new BoxLayout(section, BoxLayout.Y_AXIS));
        section.setBorder(new EmptyBorder(12, 14, 12, 14));

        JLabel title = new JLabel("DEVICE 1 (SMARTPHONE CLASS)");
        title.setFont(new Font("SansSerif", Font.BOLD, 11));
        title.setForeground(new Color(15, 23, 42));
        section.add(title);
        section.add(Box.createRigidArea(new Dimension(0, 6)));

        JPanel grid = new JPanel(new GridLayout(2, 2, 8, 4));
        grid.setOpaque(false);
        grid.add(buildInput("Manufacturer", txtM1Brand));
        grid.add(buildInput("Model", txtM1Model));
        grid.add(buildInput("Type", txtM1Type));
        grid.add(buildInput("Station", txtM1Station));

        section.add(grid);
        section.add(Box.createRigidArea(new Dimension(0, 4)));
        section.add(buildInput("Frequency (MHz)", txtM1Freq));

        return section;
    }

    private JPanel buildDevice2Section() {
        RoundedPanel section = new RoundedPanel(22, new Color(248, 250, 252));
        section.setLayout(new BoxLayout(section, BoxLayout.Y_AXIS));
        section.setBorder(new EmptyBorder(12, 14, 12, 14));

        JLabel title = new JLabel("DEVICE 2 (BASICPHONE CLASS)");
        title.setFont(new Font("SansSerif", Font.BOLD, 11));
        title.setForeground(new Color(15, 23, 42));
        section.add(title);
        section.add(Box.createRigidArea(new Dimension(0, 6)));

        JPanel grid = new JPanel(new GridLayout(2, 2, 8, 4));
        grid.setOpaque(false);
        grid.add(buildInput("Manufacturer", txtM2Brand));
        grid.add(buildInput("Model", txtM2Model));
        grid.add(buildInput("Radio Station", txtM2Station));
        grid.add(buildInput("Frequency (MHz)", txtM2Freq));

        section.add(grid);

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
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        box.add(lbl);
        box.add(field);
        return box;
    }

    private JPanel buildResultCard(String tag, JLabel mainLabel, JLabel radioLabel, Color bg) {
        RoundedPanel card = new RoundedPanel(18, bg);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(new EmptyBorder(10, 14, 10, 14));

        JLabel tagLbl = new JLabel(tag.toUpperCase());
        tagLbl.setFont(new Font("SansSerif", Font.BOLD, 10));
        tagLbl.setForeground(new Color(30, 41, 59));

        mainLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        mainLabel.setForeground(new Color(15, 23, 42));

        radioLabel.setFont(new Font("Monospaced", Font.BOLD, 11));
        radioLabel.setForeground(new Color(15, 23, 42));

        card.add(tagLbl);
        card.add(Box.createRigidArea(new Dimension(0, 4)));
        card.add(mainLabel);
        card.add(Box.createRigidArea(new Dimension(0, 4)));
        card.add(radioLabel);

        return card;
    }

    private void runSimulation() {
        MobileDevice m1 = new SmartPhone(txtM1Brand.getText().trim(), txtM1Model.getText().trim(), txtM1Type.getText().trim());
        MobileDevice m2 = new BasicPhone(txtM2Brand.getText().trim(), txtM2Model.getText().trim());

        try {
            m1.setStation(txtM1Station.getText().trim());
            m1.setFrequency(Double.parseDouble(txtM1Freq.getText().trim()));

            m2.setStation(txtM2Station.getText().trim());
            m2.setFrequency(Double.parseDouble(txtM2Freq.getText().trim()));
        } catch (NumberFormatException ex) {
            // Ignore format error
        }

        lblM1Details.setText("<html>Smart Phone Detail:<br>Manufacturer: " + txtM1Brand.getText() + " | Model: " + txtM1Model.getText() + " | Type: " + txtM1Type.getText() + "</html>");
        lblM1Radio.setText("Station: " + m1.station + " | Frequency: " + df.format(m1.frequency) + " MHz");

        lblM2Details.setText("<html>Basic Phone Detail:<br>Manufacturer: " + txtM2Brand.getText() + " | Model: " + txtM2Model.getText() + "</html>");
        lblM2Radio.setText("Station: " + m2.station + " | Frequency: " + df.format(m2.frequency) + " MHz");
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
        SwingUtilities.invokeLater(() -> new MobileApp().setVisible(true));
    }
}
