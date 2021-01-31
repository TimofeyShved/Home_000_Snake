import javax.swing.*;

public class Snake extends JFrame {

    public Snake()
    {
        setTitle("Snake");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(360,385);
        setLocation(400,400);
        add(new GameField());
        setVisible(true);
    }

    public static void main(String[] args)
    {
        Snake snake = new Snake();
    }
}
