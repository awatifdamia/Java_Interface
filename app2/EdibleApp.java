import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class EdibleGuiApp extends JFrame {

    private final JTextField txtPerson1 = new JTextField("Ali");
    private final JTextField txtPerson2 = new JTextField("Sarah");
    private final JTextField txtAnimal = new JTextField("Chicken");
    private final JTextField txtApple = new JTextField("Red Apple");
    private final JTextField txtOrange = new JTextField("Navel Orange");

    private final JLabel lblOut1 = new JLabel("Awaiting input...");
    private final JLabel lblOut2 = new JLabel("Awaiting input...");
    private final JLabel lblOut3 = new JLabel("Awaiting input...");

    public EdibleGuiApp() {
        super("Edible Food & Animals App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel root = new JPanel(new GridBagLayout());
        root.setBackground(new Color(244, 245, 249));

        // App Card Container
        RoundedPanel card = new RoundedPanel(32, Color.WHITE);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(new EmptyBorder(28, 28, 28, 28));
        card.setPreferredSize(new Dimension(480, 640));

        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        
        JPanel headerTitles = new JPanel();
        headerTitles.setOpaque(false);
        headerTitles.setLayout(new BoxLayout(headerTitles, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Edible Food & Animals");
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        title.setForeground(new Color(15, 23, 42));

        JLabel sub = new JLabel("Evaluates how people eat different food objects");
        sub.setFont(new Font("SansSerif", Font.BOLD, 12));
        sub.setForeground(new Color(100, 116, 139));

        headerTitles.add(title);
        headerTitles.add(sub);

        JLabel badge = new RoundedLabel("Edible App", new Color(220, 252, 231), new Color(22, 101, 52));
        badge.setFont(new Font("SansSerif", Font.BOLD, 12));

        header.add(headerTitles, BorderLayout.WEST);
        header.add(badge, BorderLayout.EAST);
        card.add(header);
        card.add(Box.createRigidArea(new Dimension(0, 16)));

        // People Section
        card.add(buildFormSection("People Setup", new String[]{"Person 1 Name", "Person 2 Name"}, new JTextField[]{txtPerson1, txtPerson2}));
        card.add(Box.createRigidArea(new Dimension(0, 12)));

        // Items Section
        card.add(buildFormSection("Edible Array Objects", new String[]{"Animal Name", "Apple Variety", "Orange Variety"}, new JTextField[]{txtAnimal, txtApple, txtOrange}));
        card.add(Box.createRigidArea(new Dimension(0, 16)));

        // Action Button
        JButton btnRun = new JButton("🍽️ Serve & Taste Menu");
        btnRun.setFont(new Font("SansSerif", Font.BOLD, 15));
        btnRun.setForeground(Color.WHITE);
        btnRun.setBackground(new Color(30, 41, 59));
        btnRun.setFocusPainted(false);
        btnRun.setMaximumSize(new Dimension(Integer.MAX_VALUE, 46));
        btnRun.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        btnRun.addActionListener(e -> runSimulation());
        card.add(btnRun);
        card.add(Box.createRigidArea(new Dimension(0, 16)));

        // Results Stack
        card.add(buildResultCard("Item 1 (Animal Class)", lblOut1, new Color(217, 249, 157)));
        card.add(Box.createRigidArea(new Dimension(0, 10)));
        card.add(buildResultCard("Item 2 (Apple Class)", lblOut2, new Color(251, 207, 232)));
        card.add(Box.createRigidArea(new Dimension(0, 10)));
        card.add(buildResultCard("Item 3 (Orange Class)", lblOut3, new Color(191, 219, 254)));

        root.add(card);
        setContentPane(root);
        pack();
        setLocationRelativeTo(null);

        runSimulation();
    }

    private JPanel buildFormSection(String sectionTitle, String[] labels, JTextField[] fields) {
        RoundedPanel section = new RoundedPanel(22, new Color(248, 250, 252));
        section.setLayout(new BoxLayout(section, BoxLayout.Y_AXIS));
        section.setBorder(new EmptyBorder(14, 16, 14, 16));

        JLabel title = new JLabel(sectionTitle.toUpperCase());
        title.setFont(new Font("SansSerif", Font.BOLD, 12));
        title.setForeground(new Color(15, 23, 42));
        section.add(title);
        section.add(Box.createRigidArea(new Dimension(0, 8)));

        for (int i = 0; i < labels.length; i++) {
            JLabel lbl = new JLabel(labels[i]);
            lbl.setFont(new Font("SansSerif", Font.BOLD, 11));
            lbl.setForeground(new Color(100, 116, 139));
            fields[i].setFont(new Font("SansSerif", Font.BOLD, 13));
            fields[i].setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));

            section.add(lbl);
            section.add(Box.createRigidArea(new Dimension(0, 3)));
            section.add(fields[i]);
            if (i < labels.length - 1) section.add(Box.createRigidArea(new Dimension(0, 6)));
        }

        return section;
    }

    private JPanel buildResultCard(String tag, JLabel label, Color bg) {
        RoundedPanel card = new RoundedPanel(18, bg);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(new EmptyBorder(12, 16, 12, 16));

        JLabel tagLbl = new JLabel(tag.toUpperCase());
        tagLbl.setFont(new Font("SansSerif", Font.BOLD, 11));
        tagLbl.setForeground(new Color(30, 41, 59));

        label.setFont(new Font("SansSerif", Font.BOLD, 13));
        label.setForeground(new Color(15, 23, 42));

        card.add(tagLbl);
        card.add(Box.createRigidArea(new Dimension(0, 4)));
        card.add(label);

        return card;
    }

    private void runSimulation() {
        String p1Name = txtPerson1.getText().trim().isEmpty() ? "Person 1" : txtPerson1.getText().trim();
        String p2Name = txtPerson2.getText().trim().isEmpty() ? "Person 2" : txtPerson2.getText().trim();

        Person p1 = new Person(p1Name);
        Person p2 = new Person(p2Name);

        Edible[] objects = {
            new Animal(txtAnimal.getText().trim().isEmpty() ? "Animal" : txtAnimal.getText().trim()),
            new Apple(txtApple.getText().trim().isEmpty() ? "Apple" : txtApple.getText().trim()),
            new Orange(txtOrange.getText().trim().isEmpty() ? "Orange" : txtOrange.getText().trim())
        };

        lblOut1.setText("<html>" + p1.eating(objects[0]) + "</html>");
        lblOut2.setText("<html>" + p2.eating(objects[1]) + "</html>");
        lblOut3.setText("<html>" + p2.eating(objects[2]) + "</html>");
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
        SwingUtilities.invokeLater(() -> new EdibleGuiApp().setVisible(true));
    }
}
