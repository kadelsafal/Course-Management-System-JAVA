package system;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

// Interface for button customization and click handling
interface ButtonClickListener {
    void customizeButton(JButton button);
    void onClick(JButton button);
}

// Implementation of the interface
class hover_btn  implements ButtonClickListener {


	@Override
    public void customizeButton(JButton button) {
        button.setForeground(Color.WHITE);
        button.setOpaque(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setMargin(new Insets(0, 0, 0, 0));
        button.setAlignmentY(0.0f);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setForeground(Color.RED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setForeground(Color.WHITE);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                onClick(button);
            }
        });
    }

    @Override
    public void onClick(JButton button) {
        if (button.getText().equals("X")) {
            System.exit(0);
        } else if (button.getText().equals("-")) {
        	 Frame frame = (Frame) SwingUtilities.getRoot(button);
             frame.setExtendedState(JFrame.ICONIFIED);
        }
    }
}
