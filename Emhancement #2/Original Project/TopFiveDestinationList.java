import java.awt.*;
import java.io.File;
import javax.swing.*;
import javax.swing.border.*;

public class TopFiveDestinationList {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TopDestinationListFrame topDestinationListFrame = new TopDestinationListFrame();
            topDestinationListFrame.setTitle("Top 5 Destination List");
            topDestinationListFrame.setVisible(true);
        });
    }
}

class TopDestinationListFrame extends JFrame {
    private DefaultListModel<TextAndIcon> listModel;

    public TopDestinationListFrame() {
        super("Top Five Destination List");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(900, 750);
        setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();

        // Add top 5 destinations
        addDestinationNameAndPicture("1. Sydney Opera House (...)", new ImageIcon(new ImageIcon(getClass().getResource("/resources/Sydney.jpg")).getImage().getScaledInstance(600, 450, Image.SCALE_SMOOTH)));
        addDestinationNameAndPicture("2. Auckland Skyline (...)", new ImageIcon(new ImageIcon(getClass().getResource("/resources/Auckland.jpg")).getImage().getScaledInstance(600, 450, Image.SCALE_SMOOTH)));
        addDestinationNameAndPicture("3. Downtown Austin (...)", new ImageIcon(new ImageIcon(getClass().getResource("/resources/Austin.jpg")).getImage().getScaledInstance(600, 450, Image.SCALE_SMOOTH)));
        addDestinationNameAndPicture("4. San Francisco Golden Gate Bridge (....)", new ImageIcon(new ImageIcon(getClass().getResource("/resources/San Francisco.jpg")).getImage().getScaledInstance(600, 450, Image.SCALE_SMOOTH)));
        addDestinationNameAndPicture("5. Downtown Houston (...)", new ImageIcon(new ImageIcon(getClass().getResource("/resources/Houston.jpg")).getImage().getScaledInstance(600, 450, Image.SCALE_SMOOTH)));

        JList<TextAndIcon> list = new JList<>(listModel);
        list.setBackground(Color.YELLOW);
        list.setSelectionBackground(Color.GREEN);
        list.setCellRenderer(new TextAndIconListCellRenderer(2));

        JButton refreshButton = new JButton("Refresh List");
        refreshButton.addActionListener(e -> updateList());
        add(refreshButton, BorderLayout.SOUTH);

        JScrollPane scrollPane = new JScrollPane(list);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void addDestinationNameAndPicture(String text, Icon icon) {
        TextAndIcon tai = new TextAndIcon(text, icon);
        listModel.addElement(tai);
    }

    private void updateList() {
        listModel.clear();
        addDestinationNameAndPicture("1. Sydney Opera House (...)", new ImageIcon(new ImageIcon(getClass().getResource("/resources/Sydney.jpg")).getImage().getScaledInstance(600, 450, Image.SCALE_SMOOTH)));
        addDestinationNameAndPicture("2. Auckland Skyline (...)", new ImageIcon(new ImageIcon(getClass().getResource("/resources/Auckland.jpg")).getImage().getScaledInstance(600, 450, Image.SCALE_SMOOTH)));
        addDestinationNameAndPicture("3. Downtown Austin (...)", new ImageIcon(new ImageIcon(getClass().getResource("/resources/Austin.jpg")).getImage().getScaledInstance(600, 450, Image.SCALE_SMOOTH)));
        addDestinationNameAndPicture("4. San Francisco Golden Gate Bridge (....)", new ImageIcon(new ImageIcon(getClass().getResource("/resources/San Francisco.jpg")).getImage().getScaledInstance(600, 450, Image.SCALE_SMOOTH)));
        addDestinationNameAndPicture("5. Downtown Houston (...)", new ImageIcon(new ImageIcon(getClass().getResource("/resources/Houston.jpg")).getImage().getScaledInstance(600, 450, Image.SCALE_SMOOTH)));
    }
} // Proper closure of TopDestinationListFrame class

class TextAndIcon {
    private String text;
    private Icon icon;

    public TextAndIcon(String text, Icon icon) {
        this.text = text;
        this.icon = icon;
    }

    public String getText() {
        return text;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }
}

class TextAndIconListCellRenderer extends JLabel implements ListCellRenderer<TextAndIcon> {
    private static final Border NO_FOCUS_BORDER = new EmptyBorder(1, 1, 1, 1);
    private final Border insideBorder;

    public TextAndIconListCellRenderer() {
        this(0, 0, 0, 0);
    }

    public TextAndIconListCellRenderer(int padding) {
        this(padding, padding, padding, padding);
    }

    public TextAndIconListCellRenderer(int topPadding, int rightPadding, int bottomPadding, int leftPadding) {
        insideBorder = BorderFactory.createEmptyBorder(topPadding, leftPadding, bottomPadding, rightPadding);
        setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends TextAndIcon> list, TextAndIcon value, int index,
                                                boolean isSelected, boolean hasFocus) {
        setText(value.getText());
        setIcon(value.getIcon());

        setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());
        setForeground(isSelected ? list.getSelectionForeground() : list.getForeground());

        Border outsideBorder = hasFocus
            ? UIManager.getBorder("List.focusCellHighlightBorder")
            : NO_FOCUS_BORDER;

        setBorder(BorderFactory.createCompoundBorder(outsideBorder, insideBorder));
        setComponentOrientation(list.getComponentOrientation());
        setEnabled(list.isEnabled());
        setFont(list.getFont());

        return this;
    }

    // Empty overrides for performance
    @Override public void validate() {}
    @Override public void invalidate() {}
    @Override public void repaint() {}
    @Override public void revalidate() {}
    @Override public void repaint(long tm, int x, int y, int width, int height) {}
    @Override public void repaint(Rectangle r) {}
}
