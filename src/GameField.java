import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GameField extends JPanel implements ActionListener
{

    private final int SIZE = 320;
    private final int dot_size = 16;
    private final int all_dots = 400;
    private Image dot;
    private Image apple;
    private int appleX;
    private int appleY;
    private int[] x = new int[all_dots];
    private int[] y = new int[all_dots];
    private int dots;
    private Timer timer;
    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
    private boolean inGame = true;
    private int z=0;

    public GameField()
    {
        setBackground(Color.BLACK);
        loadImage();
        initGame();
        addKeyListener(new FieldKey());
        setFocusable(true);
    }

    public void initGame()
    {
        dots = 3;
        for (int i=0;i<dots;i++)
        {
            x[i]=48-i*dot_size;
            y[i]=48;
        }
        timer = new Timer(250,this);
        timer.start();
        createApple();
    }



    private void createApple()
    {
        appleX = new Random().nextInt(20)*dot_size;
        appleY = new Random().nextInt(20)*dot_size;
    }

    public  void loadImage()
    {
        //String fileName = "img/" + "Sapple"+ ".png"; // создание ссылки
        //ImageIcon icon = new ImageIcon (getClass().getResource(fileName)); // создание картинки
        //return  icon.getImage();
        ImageIcon imgg_sapple = new ImageIcon("src/sapple.png");
        apple = imgg_sapple.getImage();
        ImageIcon imgg_Shhead = new ImageIcon("src/shead.png");
        dot = imgg_Shhead.getImage();
    }

    /*
    private void setImages (){
        for (BoxImage box: BoxImage.values() ){
            box.image = getImage(box.name());
        }
    }

    private  Image getImage (String name) //создание метода для вывода картинок
    {
        String fileName = "img/" + name.toLowerCase() + ".png"; // создание ссылки
        ImageIcon icon = new ImageIcon (); // создание картинки
        return  icon.getImage();
    }
    */

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (inGame){
            g.drawImage(apple,appleX,appleY,this);
            for (int i=0;i<dots;i++){
                g.drawImage(dot,x[i],y[i],this);
            }
        }
        else {
            String str = "Game over";
            g.setColor(Color.WHITE);
            g.drawString(str,125,SIZE/2);
        }
    }

    public void move()
    {
        for (int i=dots;i>0;i--)
        {
            x[i]=x[i-1];
            y[i]=y[i-1];
        }
        if (left){
            x[0]-=dot_size;
        }
        if (right){
            x[0]+=dot_size;
        }
        if (up){
            y[0]-=dot_size;
        }
        if (down){
            y[0]+=dot_size;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(inGame)
        {
            checkApple();
            checkCollision();
            move();
        }
        else
        {
            z++;
            if (z>10){
            timer.stop();
            inGame=true;
            initGame();
            z=0;
            }
        }
        repaint();
    }

    private void checkCollision() {
        for (int i=dots;i>0;i--){
            if(i>4&&x[0]==x[i]&&y[0]==y[i]){
                inGame=false;
            }
        }
        if (x[0]>SIZE){
            inGame=false;
        }
        if (x[0]<0){
            inGame=false;
        }
        if (y[0]>SIZE){
            inGame=false;
        }
        if (y[0]<0){
            inGame=false;
        }
    }

    private void checkApple() {
        if (x[0]==appleX && y[0]==appleY){
            dots++;
            createApple();
        }
    }

    class FieldKey extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if (key==KeyEvent.VK_LEFT&&!right){
                left=true;
                up=false;
                down=false;
                right=false;
            }
            if (key==KeyEvent.VK_RIGHT&&!left){
                left=false;
                up=false;
                down=false;
                right=true;
            }
            if (key==KeyEvent.VK_UP&&!down){
                left=false;
                up=true;
                down=false;
                right=false;
            }
            if (key==KeyEvent.VK_DOWN&&!up){
                left=false;
                up=false;
                down=true;
                right=false;
            }
        }
    }

}
