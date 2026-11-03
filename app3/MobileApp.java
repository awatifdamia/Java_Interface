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

public class MobileGuiApp extends JFrame {

    // Device 1 Controls
    private final JTextField txtM1Brand = new JTextField("Samsung");
    private final JTextField txtM1Model = new JTextField("S20");
    private final JTextField txtM1Type = new JTextField("Galaxy");
    private final JTextField txtM1Station = new JTextField("Hot FM");
    private final JTextField txtM1Freq = new JTextField("97.60");

    // Device 2 Controls
    private final JTextField txtM2Brand = new JTextField("Sonny Ericson");
    private final JTextField txtM2Model = new JTextField("Walkman");
    private final JTextField txtM2Station = new JTextField("Fly FM");
    private final JTextField txtM2Freq = new JTextField("109.80");

    // Display Labels
    private final JLabel lblM1Details = new JLabel();
    private final JLabel lblM1Radio = new JLabel();

    private final JLabel lblM2Details = new JLabel();
    private final JLabel lblM2Radio = new JLabel();

    private final DecimalFormat df = new DecimalFormat("0.00");

    public MobileGuiApp() {
        super("Mobile & Radio Tuner App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel root = new JPanel(new GridBagLayout());
        root.setBackground(new Color(244, 245, 249));

        RoundedPanel card = new RoundedPanel(32, Color.WHITE);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(new EmptyBorder(28, 28, 28, 28));
        card.setPreferredSize(new Dimension(500, 720));

        // Header
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

        // Device 1 Section (SmartPhone)
        card.add(buildDevice1Section());
        card.add(Box.createRigidArea(new Dimension(0, 12)));

        // Device 2 Section (BasicPhone)
        card.add(buildDevice2Section());
        card.add(Box.createRigidArea(new Dimension(0, 16)));

        // Action Button
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

        // Results Stack
        card.add(buildResultCard("SmartPhone Details", lblM1Details, lblM1Radio, new Color(217, 249, 157)));
        card.add(Box.createRigidArea(new Dimension(0, 10)));
        card.add(buildResultCard("BasicPhone Details", lblM2Details, lblM2Radio, new Color(191, 219, 254)));

        root.add(card);
        setContentPane(root);
        pack();
        setLocationRelativeTo(null);

        runSimulation();
    }

    private JPanel build
