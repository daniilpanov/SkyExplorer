import com.originalstudio.skyexplorer.Img;
import com.originalstudio.skyexplorer.Sprite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class TestForSprite extends JFrame {
    
    private JPanel panel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            sprite.render(g);
        }
    };
    
    private Sprite sprite =
            new Sprite(100, 200, Img.starship_r, Img.starship_r, Img.starship_r, Img.starship_r, panel) {
        @Override
        protected void checkingKey(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                this.moving(new Image[]{Img.starship_l, Img.starship_r, Img.starship_t, Img.starship_r}, 5, 1000, 100, 5, 1, X, 5);
            }
        }
    };
    
    private TestForSprite() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        getContentPane().add(panel);
        sprite.addControl(this);
        setVisible(true);
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(TestForSprite::new);
    }
}
