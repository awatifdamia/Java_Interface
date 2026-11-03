import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

// a) Interface Measurable
interface Measurable {
    double getMeasure();
    String getName();
}

// b) BankAccount Class
class BankAccount implements Measurable {
    private int accNumber;
    private double balance;
    private static int LastAssignedNumber = 1000;

    public BankAccount() {
        this(0);
    }

    public BankAccount(double balance) {
        LastAssignedNumber++;
        this.accNumber = LastAssignedNumber;
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accNumber;
    }

    @Override
    public double getMeasure() {
        return balance;
    }

    @Override
    public String getName() {
        return "Account " + accNumber;
    }
}

// c) Coin Class
class Coin implements Measurable {
    private double value;
    private String name;

    public Coin(double value, String name) {
        this.value = value;
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    @Override
    public double getMeasure() {
        return value;
    }

    @Override
    public String getName() {
        return name;
    }
}

// d) DataSet Class
class DataSet implements Measurable {
    private double sum;
    private Measurable max;
    private int count;
    private String name;

    public DataSet() {
        this.sum = 0;
        this.max = null;
        this.count = 0;
        this.name = "DataSet";
    }

    public void add(Measurable x) {
        sum += x.getMeasure();
        if (count == 0 || x.getMeasure() > max.getMeasure()) {
            max = x;
        }
        count++;
    }

    public Measurable getMax() {
        return max;
    }

    public double getAverage() {
        return (count == 0) ? 0 : sum / count;
    }

    public int getCount() {
        return count;
    }

    @Override
    public double getMeasure() {
        return getAverage();
    }

    @Override
    public String getName() {
        return name;
    }
}

// e) Student Class
class Student implements Measurable {
    private String name;
    private double height;

    public Student(String name, double height) {
        this.name = name;
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public double getMeasure() {
        return height;
    }

    @Override
    public String getName() {
        return name;
    }
}

public class DataSetApp extends JFrame {

    // Palette setup based on UI Mockup
    private static final Color BG_DARK = new Color(245, 247, 250);
    private static final Color CARD_BG = Color.WHITE;
    private static final Color NAVY_DARK = new Color(28, 37, 54);
    private static final Color BADGE_YELLOW = new Color(254, 238, 161);
    private static final Color BADGE_TEXT = new Color(138, 102, 0);

    private static final Color METRIC_GREEN_BG = new Color(223, 246, 172);
    private static final Color METRIC_PINK_BG = new Color(254, 218, 233);
    private static final Color METRIC_BLUE_BG = new Color(207, 230, 254);

    private JComboBox<String> domainSelector;
    private JTextField txtValue;
    private JTextField txtName;
    private JLabel lblNameTag;

    private JLabel lblAvgValue;
    private JLabel lblAvgSub;
    private JLabel lblMaxValue;
    private JLabel lblMaxSub;
    private JLabel lblCountValue;

    private DataSet currentDataSet = new DataSet();
    private List<Measurable> currentList = new ArrayList<>();
    private final DecimalFormat df = new DecimalFormat("#,##0.00");

    public DataSetApp() {
        super("DataSet Java Lab");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Container Panel
        JPanel background = new JPanel(new GridBagLayout());
        background.setBackground(BG_DARK);

        // Rounded Card Window
        RoundedPanel card = new RoundedPanel(28, CARD_BG);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(new EmptyBorder(32, 32, 32, 32));
        card.setPreferredSize(new Dimension(480, 620));

        // Top Header
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        JLabel title = new JLabel("DATASET JAVA LAB");
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setForeground(NAVY_DARK);

        JLabel badge = new RoundedLabel("Measurable OOP", BADGE_YELLOW, BADGE_TEXT);
        badge.setFont(new Font("SansSerif", Font.BOLD, 12));

        header.add(title, BorderLayout.WEST);
        header.add(badge, BorderLayout.EAST);
        card.add(header);
        card.add(Box.createRigidArea(new Dimension(0, 20)));

        // Domain Selector
        JLabel lblSelect = new JLabel("SELECT DATASET DOMAIN");
        lblSelect.setFont(new Font("SansSerif", Font.BOLD, 11));
        lblSelect.setForeground(new Color(130, 140, 155));
        lblSelect.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(lblSelect);
        card.add(Box.createRigidArea(new Dimension(0, 6)));

        domainSelector = new JComboBox<>(new String[]{
            "BankAccount Class (RM Balance)",
            "Coin Class (USD Value)",
            "Student Class (Height in cm)"
        });
        domainSelector.setFont(new Font("SansSerif", Font.BOLD, 14));
        domainSelector.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));
        domainSelector.addActionListener(e -> onDomainChanged());
        card.add(domainSelector);
        card.add(Box.createRigidArea(new Dimension(0, 18)));

        // Dynamic Form Box
        RoundedPanel formBox = new RoundedPanel(18, new Color(248, 249, 252));
        formBox.setLayout(new BoxLayout(formBox, BoxLayout.Y_AXIS));
        formBox.setBorder(new EmptyBorder(16, 16, 16, 16));

        lblNameTag = new JLabel("Account Balance (RM)");
        lblNameTag.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblNameTag.setForeground(new Color(100, 110, 125));

        txtValue = new JTextField("1500");
        txtValue.setFont(new Font("SansSerif", Font.BOLD, 15));
        txtValue.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        txtName = new JTextField();
        txtName.setFont(new Font("SansSerif", Font.BOLD, 15));
        txtName.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        txtName.setVisible(false);

        JPanel btnRow = new JPanel(new GridLayout(1, 2, 10, 0));
        btnRow.setOpaque(false);
        btnRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));

        JButton btnAdd = new JButton("+ Add Item");
        styleButton(btnAdd, NAVY_DARK, Color.WHITE);
        btnAdd.addActionListener(e -> addItem());

        JButton btnReset = new JButton("Reset");
        styleButton(btnReset, new Color(228, 233, 242), NAVY_DARK);
        btnReset.addActionListener(e -> loadDefaults());

        btnRow.add(btnAdd);
        btnRow.add(btnReset);

        formBox.add(lblNameTag);
        formBox.add(Box.createRigidArea(new Dimension(0, 6)));
        formBox.add(txtValue);
        formBox.add(txtName);
        formBox.add(Box.createRigidArea(new Dimension(0, 12)));
        formBox.add(btnRow);
        card.add(formBox);
        card.add(Box.createRigidArea(new Dimension(0, 16)));

        // Metric Card 1: Average
        lblAvgValue = new JLabel("RM 0.00");
        lblAvgSub = new JLabel("Average Balance");
        card.add(buildMetricCard("Average Measure", lblAvgSub, lblAvgValue, METRIC_GREEN_BG));
        card.add(Box.createRigidArea(new Dimension(0, 10)));

        // Metric Card 2: Highest
        lblMaxValue = new JLabel("RM 0.00");
        lblMaxSub = new JLabel("No records yet");
        card.add(buildMetricCard("Highest Record", lblMaxSub, lblMaxValue, METRIC_PINK_BG));
        card.add(Box.createRigidArea(new Dimension(0, 10)));

        // Metric Card 3: Count
        lblCountValue = new JLabel("0");
        JLabel lblCountSub = new JLabel("Items in Dataset");
        card.add(buildMetricCard("Total Elements", lblCountSub, lblCountValue, METRIC_BLUE_BG));

        background.add(card);
        setContentPane(background);

        pack();
        setSize(540, 700);
        setLocationRelativeTo(null);

        // Load Default Sample State
        loadDefaults();
    }

    private JPanel buildMetricCard(String title, JLabel subText, JLabel mainVal, Color bg) {
        RoundedPanel pnl = new RoundedPanel(18, bg);
        pnl.setLayout(new BorderLayout());
        pnl.setBorder(new EmptyBorder(12, 16, 12, 16));

        JPanel left = new JPanel();
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
        left.setOpaque(false);

        JLabel lblT = new JLabel(title);
        lblT.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblT.setForeground(NAVY_DARK);

        subText.setFont(new Font("SansSerif", Font.PLAIN, 12));
        subText.setForeground(new Color(90, 100, 115));

        left.add(lblT);
        left.add(subText);

        mainVal.setFont(new Font("Monospaced", Font.BOLD, 18));
        mainVal.setForeground(NAVY_DARK);

        pnl.add(left, BorderLayout.WEST);
        pnl.add(mainVal, BorderLayout.EAST);
        return pnl;
    }

    private void onDomainChanged() {
        int index = domainSelector.getSelectedIndex();
        if (index == 0) {
            lblNameTag.setText("Account Balance (RM)");
            txtName.setVisible(false);
            lblAvgSub.setText("Average Balance");
        } else if (index == 1) {
            lblNameTag.setText("Coin Value ($) & Coin Name");
            txtName.setVisible(true);
            txtName.setText("Quarter");
            lblAvgSub.setText("Average Coin Value");
        } else {
            lblNameTag.setText("Student Height (cm) & Name");
            txtName.setVisible(true);
            txtName.setText("Ali");
            lblAvgSub.setText("Average Student Height");
        }
        loadDefaults();
    }

    private void loadDefaults() {
        currentDataSet = new DataSet();
        currentList.clear();

        int index = domainSelector.getSelectedIndex();
        if (index == 0) {
            addItemDirect(new BankAccount(0));
            addItemDirect(new BankAccount(10000));
            addItemDirect(new BankAccount(2000));
        } else if (index == 1) {
            addItemDirect(new Coin(0.25, "Quarter"));
            addItemDirect(new Coin(0.10, "Dime"));
            addItemDirect(new Coin(0.05, "Nickel"));
        } else {
            addItemDirect(new Student("Ali", 157.50));
            addItemDirect(new Student("Alex", 165.00));
            addItemDirect(new Student("Maria", 147.00));
        }
        updateUIResults();
    }

    private void addItem() {
        try {
            double val = Double.parseDouble(txtValue.getText().trim());
            int index = domainSelector.getSelectedIndex();

            if (index == 0) {
                addItemDirect(new BankAccount(val));
            } else if (index == 1) {
                String name = txtName.getText().trim();
                addItemDirect(new Coin(val, name.isEmpty() ? "Coin" : name));
            } else {
                String name = txtName.getText().trim();
                addItemDirect(new Student(name.isEmpty() ? "Student" : name, val));
            }
            updateUIResults();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid numeric value.", "Input Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void addItemDirect(Measurable item) {
        currentDataSet.add(item);
        currentList.add(item);
    }

    private void updateUIResults() {
        int index = domainSelector.getSelectedIndex();
        String prefix = index == 0 ? "RM " : (index == 1 ? "$ " : "");
        String suffix = index == 2 ? " cm" : "";

        lblAvgValue.setText(prefix + df.format(currentDataSet.getAverage()) + suffix);
        lblCountValue.setText(String.valueOf(currentDataSet.getCount()));

        Measurable max = currentDataSet.getMax();
        if (max != null) {
            lblMaxValue.setText(prefix + df.format(max.getMeasure()) + suffix);
            lblMaxSub.setText(max.getName());
        } else {
            lblMaxValue.setText("-");
            lblMaxSub.setText("No records");
        }
    }

    private void styleButton(JButton btn, Color bg, Color fg) {
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFocusPainted(false);
        btn.setFont(new Font("SansSerif", Font.BOLD, 13));
        btn.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
    }

    // Helper Rounded UI Components
    static class RoundedPanel extends JPanel {
        private int radius;
        private Color bgColor;

        RoundedPanel(int radius, Color bg) {
            this.radius = radius;
            this.bgColor = bg;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(bgColor);
            g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), radius, radius));
            g2.dispose();
        }
    }

    static class RoundedLabel extends JLabel {
        private Color bgColor;
        private Color fgColor;

        RoundedLabel(String text, Color bg, Color fg) {
            super(text);
            this.bgColor = bg;
            this.fgColor = fg;
            setForeground(fg);
            setBorder(BorderFactory.createEmptyBorder(6, 12, 6, 12));
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(bgColor);
            g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 16, 16));
            g2.dispose();
            super.paintComponent(g);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DataSetApp().setVisible(true));
    }
}
