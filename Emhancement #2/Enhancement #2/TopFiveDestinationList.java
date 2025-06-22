import java.awt.*;
import java.io.File;
import javax.swing.*;
import javax.swing.border.*;

/**
 * Application to display a numbered list of top 5 travel destinations,
 * each with an image and description
 */
public class TopFiveDestinationList {
    // Main class used to launch the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TopDestinationListFrame topDestinationListFrame = new TopDestinationListFrame();
            topDestinationListFrame.setTitle("Top 5 Destination List");
            topDestinationListFrame.setVisible(true);
        });
    }
}

/**
 * Subclass that displays the top destinations. Handles pages, UI layout, and user interactions.
 */
class TopDestinationListFrame extends JFrame {
    private DefaultListModel<TextAndIcon> listModel;
    // List of all destinations in an array for page numbers
    private java.util.List<TextAndIcon> allDestinations = new java.util.ArrayList<>();
    private int currentPage = 0;
    private final int pageSize = 1;
    private JLabel pageLabel;

    public TopDestinationListFrame() {
        super("Top Five Destination List");

        // Initializes window and adds a border
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(900, 750);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(230, 240, 250)); 

        listModel = new DefaultListModel<>();

        // Adds destination with HTML formatting
        allDestinations.add(new TextAndIcon("<html><span style='font-size:16pt;'><b>1. Sydney Opera House</b></span><br/>An architectural marvel located on the Sydney Harbour...</html>", new ImageIcon(getClass().getResource("/resources/Sydney.jpg"))));
        allDestinations.add(new TextAndIcon("<html><span style='font-size:16pt;'><b>2. Auckland Skyline</b></span><br/>A bustling cityscape with volcanic hills and harbor views...</html>", new ImageIcon(getClass().getResource("/resources/Auckland.jpg"))));
        allDestinations.add(new TextAndIcon("<html><span style='font-size:16pt;'><b>3. Downtown Austin</b></span><br/>The vibrant capital of Texas known for music, food, and culture...</html>", new ImageIcon(getClass().getResource("/resources/Austin.jpg"))));
        allDestinations.add(new TextAndIcon("<html><span style='font-size:16pt;'><b>4. San Francisco Golden Gate Bridge</b></span><br/>An iconic red suspension bridge spanning the Golden Gate...</html>", new ImageIcon(getClass().getResource("/resources/San Francisco.jpg"))));
        allDestinations.add(new TextAndIcon("<html><span style='font-size:16pt;'><b>5. Downtown Houston</b></span><br/>A sprawling metropolis with museums, cuisine, and green spaces...</html>", new ImageIcon(getClass().getResource("/resources/Houston.jpg"))));

        // Initializes page
        loadPage();

        JList<TextAndIcon> list = new JList<>(listModel);
        list.setBackground(Color.WHITE);
        list.setSelectionBackground(Color.GREEN);
        TextAndIconListCellRenderer renderer = new TextAndIconListCellRenderer(50);
        list.setCellRenderer(renderer);

        // Code for refresh button
        JButton refreshButton = new JButton("Refresh List");
        refreshButton.addActionListener(e -> updateList());
        // Apply styling to refresh button
        refreshButton.setBackground(new Color(0, 102, 204)); 
        refreshButton.setForeground(new Color(30, 30, 30)); 
        refreshButton.setFont(new Font("SansSerif", Font.BOLD, 12));
        refreshButton.setFocusPainted(false);
        refreshButton.setBorder(BorderFactory.createLineBorder(new Color(30, 30, 30), 2));
        refreshButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Code for next button
        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(e -> {
            if ((currentPage + 1) * pageSize < allDestinations.size()) {
                currentPage++;
                loadPage();
                pageLabel.setText("Page " + (currentPage + 1) + " of " + ((allDestinations.size() + pageSize - 1) / pageSize));
            }
        });
        // Apply styling to next button
        nextButton.setBackground(new Color(0, 102, 204));
        nextButton.setForeground(new Color(30, 30, 30)); 
        nextButton.setFont(new Font("SansSerif", Font.BOLD, 12));
        nextButton.setFocusPainted(false);
        nextButton.setBorder(BorderFactory.createLineBorder(new Color(30, 30, 30), 2));
        nextButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Code for previous button
        JButton prevButton = new JButton("Previous");
        prevButton.addActionListener(e -> {
            if (currentPage > 0) {
                currentPage--;
                loadPage();
                pageLabel.setText("Page " + (currentPage + 1) + " of " + ((allDestinations.size() + pageSize - 1) / pageSize));
            }
        });
        // Apply styling to previous button
        prevButton.setBackground(new Color(0, 102, 204)); 
        prevButton.setForeground(new Color(30, 30, 30)); 
        prevButton.setFont(new Font("SansSerif", Font.BOLD, 12));
        prevButton.setFocusPainted(false);
        prevButton.setBorder(BorderFactory.createLineBorder(new Color(30, 30, 30), 2));
        prevButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Bar for buttons at the bottom
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(refreshButton);
        buttonPanel.add(prevButton);
        buttonPanel.add(nextButton);
        buttonPanel.setBackground(new Color(210, 225, 245)); 
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Page label showing current page info
        JLabel pageLabel = new JLabel("Page " + (currentPage + 1) + " of " + ((allDestinations.size() + pageSize - 1) / pageSize));
        pageLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        pageLabel.setForeground(new Color(0, 102, 204)); 
        pageLabel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(0, 102, 204)), 
            BorderFactory.createEmptyBorder(10, 0, 10, 0) 
        ));
        pageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.pageLabel = pageLabel;
        add(pageLabel, BorderLayout.NORTH);
        
        add(buttonPanel, BorderLayout.SOUTH);

        JScrollPane scrollPane = new JScrollPane(list);
        add(scrollPane, BorderLayout.CENTER);
        scrollPane.getViewport().setBackground(new Color(245, 250, 255)); 
        scrollPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }

    private void loadPage() {
        listModel.clear();
        int start = currentPage * pageSize;
        int end = Math.min(start + pageSize, allDestinations.size());
        for (int i = start; i < end; i++) {
            listModel.addElement(allDestinations.get(i));
        }
    }

    private void updateList() {
        // Resets all destinations to original 5 and show first page
        allDestinations.clear();
        allDestinations.add(new TextAndIcon("<html><span style='font-size:16pt;'><b>1. Sydney Opera House</b></span><br/>An architectural marvel located on the Sydney Harbour...</html>", new ImageIcon(getClass().getResource("/resources/Sydney.jpg"))));
        allDestinations.add(new TextAndIcon("<html><span style='font-size:16pt;'><b>2. Auckland Skyline</b></span><br/>A bustling cityscape with volcanic hills and harbor views...</html>", new ImageIcon(getClass().getResource("/resources/Auckland.jpg"))));
        allDestinations.add(new TextAndIcon("<html><span style='font-size:16pt;'><b>3. Downtown Austin</b></span><br/>The vibrant capital of Texas known for music, food, and culture...</html>", new ImageIcon(getClass().getResource("/resources/Austin.jpg"))));
        allDestinations.add(new TextAndIcon("<html><span style='font-size:16pt;'><b>4. San Francisco Golden Gate Bridge</b></span><br/>An iconic red suspension bridge spanning the Golden Gate...</html>", new ImageIcon(getClass().getResource("/resources/San Francisco.jpg"))));
        allDestinations.add(new TextAndIcon("<html><span style='font-size:16pt;'><b>5. Downtown Houston</b></span><br/>A sprawling metropolis with museums, cuisine, and green spaces...</html>", new ImageIcon(getClass().getResource("/resources/Houston.jpg"))));
        currentPage = 0;
        loadPage();
        pageLabel.setText("Page " + (currentPage + 1) + " of " + ((allDestinations.size() + pageSize - 1) / pageSize));
    }
}

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
        String labelText = value.getText();
        if (labelText.startsWith("<html>")) {
        }
        setText(labelText);

        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 200, 230), 2),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        setBackground(new Color(250, 255, 255));
        setIconTextGap(40);

        setVerticalTextPosition(JLabel.BOTTOM);
        setHorizontalTextPosition(JLabel.CENTER);

        Icon rawIcon = value.getIcon();
        if (rawIcon instanceof ImageIcon) {
            Image img = ((ImageIcon) rawIcon).getImage();
            Image scaled = img.getScaledInstance(600, 450, Image.SCALE_AREA_AVERAGING);
            setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0, 102, 204), 3),
                BorderFactory.createEmptyBorder(15, 15, 15, 15) 
            ));
            setBackground(new Color(245, 250, 255)); 
            setOpaque(true);
            setIcon(new ImageIcon(scaled));
        } else {
            setIcon(rawIcon);
        }

        setHorizontalAlignment(JLabel.CENTER);
        setVerticalAlignment(JLabel.CENTER);

        setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());
        setForeground(isSelected ? list.getSelectionForeground() : list.getForeground());

        Border outsideBorder = hasFocus
            ? UIManager.getBorder("List.focusCellHighlightBorder")
            : NO_FOCUS_BORDER;
        setComponentOrientation(list.getComponentOrientation());
        setEnabled(list.isEnabled());
        setFont(list.getFont());

        return this;
    }

    @Override public void validate() {}
    @Override public void invalidate() {}
    @Override public void repaint() {}
    @Override public void revalidate() {}
    @Override public void repaint(long tm, int x, int y, int width, int height) {}
    @Override public void repaint(Rectangle r) {}
}