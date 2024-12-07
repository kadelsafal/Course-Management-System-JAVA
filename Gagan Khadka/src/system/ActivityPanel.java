package system;

import java.awt.Color;
import java.awt.Font;
import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ActivityPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private static final int MAX_NOTIFICATIONS = 4;
    private LinkedList<JLabel> notificationLabels;

    public ActivityPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(true);
        setBackground(Color.WHITE);
        setForeground(Color.BLACK);
        notificationLabels = new LinkedList<>();
    }

    public void addNotification(String notification) {
        JLabel label = new JLabel(notification);
        label.setFont(new Font("Tahoma", Font.PLAIN, 19));

        notificationLabels.addFirst(label);
        add(label);

        if (notificationLabels.size() > MAX_NOTIFICATIONS) {
            JLabel removedLabel = notificationLabels.removeLast(); // Change here to remove the last label
            remove(removedLabel);
        }

        revalidate();
        repaint();
    }
    public  void clearnotifications() {
    	int notificationsToRemove = Math.min(notificationLabels.size(), 2);

        for (int i = 0; i < notificationsToRemove; i++) {
            JLabel removedLabel = notificationLabels.removeFirst();
            remove(removedLabel);
        }

        revalidate();
        repaint();
    }
    }

