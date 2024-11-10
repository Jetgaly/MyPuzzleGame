package com.it.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class gameJflame extends JFrame implements KeyListener, ActionListener {
    int [][]data = new int[4][4];//打乱后的照片编号
    int x=0,y=0;//记录空白方块位置

    int step = 0;
    //条目对象
    JMenuItem girls = new JMenuItem("美女");
    JMenuItem animals = new JMenuItem("动物");
    JMenuItem sports = new JMenuItem("运动");
    JMenuItem replayItem = new JMenuItem("重新游戏");
    JMenuItem reroginItem = new JMenuItem("重新登陆");
    JMenuItem closeItem = new JMenuItem("关闭游戏");

    JMenuItem ifmItem = new JMenuItem("二维码");

    String path = "";
     public gameJflame(){
         //初始化界面
         InitJflame();

         //初始化菜单
         InitJmenubar();
         //初始化path
         InitPath();

         //初始化数据（打乱）
         InitData();

         //初始化图片
         InitImage();


         //界面显示
         this.setVisible(true);

     }

    private void InitPath() {
        String []name = {"animal/animal","girl/girl","sport/sport"};
        Random r = new Random();
        int idx = r.nextInt(name.length);
        path = name[idx];
        if(path == "animal/animal"){
           path+= (r.nextInt(8)+1);
        } else if( path == "girl/girl"){
            path+= (r.nextInt(13)+1);
        } else if (path == "sport/sport") {
            path+= (r.nextInt(10)+1);
        }
    }

    private void InitData() {

        int []temp = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,0};
        Random r = new Random();
        for (int i = 0; i < temp.length; i++) {
            int idx = r.nextInt(temp.length);
            int tnum = temp[idx];
            temp[idx] = temp[i];
            temp[i] = tnum;
        }
        for(int i=0;i<temp.length;i++){
            if (temp[i]==0){
                x = i/4;
                y = i%4;
            }
            data[i/4][i%4]=temp[i];
        }

    }

    private void InitImage() {
        //清空界面
        this.getContentPane().removeAll();

        //detail：先加载图片的在上面
        //判断游戏结束
        if(checkVictory()){
            JLabel Bgjlabel = new JLabel(new ImageIcon("image/win.png"));
            this.getContentPane().add(Bgjlabel);
            Bgjlabel.setBounds(40,40,508,560);
        }

         //步数
        JLabel stepcount = new JLabel("步数："+step);
        stepcount.setBounds(50,30,100,20);
        this.getContentPane().add(stepcount);

        //指定图片位置
        for (int i = 0; i < 4; i++) {
            for(int j=0;j<4;j++){
                //创建图片对象
                //创建管理容器
                JLabel jlabel = new JLabel(new ImageIcon("image/"+path+"/"+data[i][j]+".jpg"));
                //指定图片位置
                jlabel.setBounds(j*105+83,i*105+134,105,105);
                //加边框
                jlabel.setBorder(new BevelBorder(BevelBorder.LOWERED));
                //加载到界面中
                // this.add(jlabel);
                this.getContentPane().add(jlabel);

            }
        }
        //加载背景图片
        JLabel Bgjlabel = new JLabel(new ImageIcon("image/background.png"));
        Bgjlabel.setBounds(40,40,508,560);
        this.getContentPane().add(Bgjlabel);

        //刷新界面  使用双缓存机制，即在内存中预先绘制好画面，然后再一次性绘制到屏幕上，可以有效避免画面闪烁
        this.getContentPane().repaint();
    }
    private boolean checkVictory(){
        for (int i = 0; i < 4; i++) {
            if(i<3){
            for (int j=0;j<4;j++){
                if(data[i][j]!=(i*4+j+1))return false;
            }
            }else {
                for (int j=0;j<3;j++){
                    if(data[i][j]!=(i*4+j+1))return false;}
            }

        }
        return true;
    }
    private void InitJmenubar() {
        //工具栏
        JMenuBar bar = new JMenuBar();
        //选项
        JMenu func1 = new JMenu("功能");
        JMenu func2 = new JMenu("关于我们");
        JMenu func3 = new JMenu("更换图片");

        //绑定事件
        replayItem.addActionListener(this);
        reroginItem.addActionListener(this);
        closeItem.addActionListener(this);
        ifmItem.addActionListener(this);
        girls.addActionListener(this);
        animals.addActionListener(this);
        sports.addActionListener(this);
        //组合
        func3.add(girls);
        func3.add(sports);
        func3.add(animals);
        func1.add(func3);
        func1.add(replayItem);
        func1.add(reroginItem);
        func1.add(closeItem);

        func2.add(ifmItem);

        bar.add(func1);
        bar.add(func2);
        //设置菜单
        this.setJMenuBar(bar);
    }

    private void InitJflame() {
        //设置界面宽高
        this.setSize(603,680);
        //设置界面标题
        this.setTitle("MyPuzzleGame v1.0");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //取消默认居中放置
        this.setLayout(null);
        //给界面添加键盘监听事件
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(checkVictory())return;
        int code = e.getKeyCode();
        if (code == 32) {
            //space
            this.getContentPane().removeAll();
            JLabel alljlabel = new JLabel(new ImageIcon("image/"+path+"/all.jpg"));

            alljlabel.setBounds(83,134,420,420);
            this.getContentPane().add(alljlabel);
            //加载背景图片
            JLabel Bgjlabel = new JLabel(new ImageIcon("image/background.png"));
            Bgjlabel.setBounds(40,40,508,560);
            this.getContentPane().add(Bgjlabel);

            this.getContentPane().repaint();

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
         if(checkVictory())return;

         int code = e.getKeyCode();
        System.out.println(code);

        if(code == 87){
            //w
            if(x!=0){
                step++;
                data[x][y] = data[--x][y];
                data[x][y] = 0;
                InitImage();

            }
        } else if (code == 83) {
            //s
            if(x!=3){
                step++;
                data[x][y] = data[++x][y];
                data[x][y] = 0;
                InitImage();
            }

        } else if (code == 65) {
            //a
            if(y!=0){
                step++;
                data[x][y] = data[x][--y];
                data[x][y] = 0;
                InitImage();
            }
        } else if (code == 68) {
            //d
            if(y!=3){
                step++;
                data[x][y] = data[x][++y];
                data[x][y] = 0;
                InitImage();
            }
        } else if (code == 32) {
            //space
            InitImage();
        } else if (code == 86) {
            //v 一键通关
            for (int i = 0; i < 4; i++) {
                for(int j=0;j<4;j++){
                    data[i][j] = i*4+j+1;
                }
            }
            data[3][3]=0;
               InitImage();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
          Object com = e.getSource();
          if(com==replayItem){
            step = 0;
            InitData();
            InitImage();
          } else if (com==reroginItem) {
              this.setVisible(false);
              new loginJflame();
          } else if (com==closeItem) {
              //直接关闭虚拟机
              System.exit(0);
          } else if (com==ifmItem) {
               //创建弹框对象
              JDialog Jd = new JDialog();

              JLabel jl = new JLabel(new ImageIcon("image/damie.jpg"));
              //设置图片相对弹框的位置和  宽高
              jl.setBounds(0,0,258,258);

              Jd.getContentPane().add(jl);
              //设置弹框大小
              Jd.setSize(344,344);
              //弹框置顶
              Jd.setAlwaysOnTop(true);
              //居中
              Jd.setLocationRelativeTo(null);
              //弹框不关闭无法操作游戏
              Jd.setModal(true);

              Jd.setVisible(true);
          } else if (com == girls) {
              Random r = new Random();
              path = "girl/girl";
              path+= (r.nextInt(13)+1);
              step = 0;
              InitData();
              InitImage();
          } else if (com == sports) {
              Random r = new Random();
               path = "sport/sport";
               path+= (r.nextInt(10)+1);
              step = 0;
              InitData();
              InitImage();
          } else if (com == animals) {
              Random r = new Random();
              path = "animal/animal";
              path += (r.nextInt(8)+1);
              step = 0;
              InitData();
              InitImage();
          }
    }
}
