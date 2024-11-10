package com.it.ui;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class loginJflame extends JFrame implements MouseListener {
    //添加注册按钮
    JButton register = new JButton();
    //添加登录按钮
    JButton login = new JButton();
    //用户名输入
    JTextField nameinput = new JTextField();
    //密码输入
    JPasswordField pwinput = new JPasswordField();
    //验证码输入
    JTextField codeinput = new JTextField();

    String loginpath = "image/login/登录按钮.png";
    //正确的验证码
    String rightcode;
    //正确的账号
    String rightAcc;
    //正确的密码
    String rightpassw;

    public loginJflame(){
        //设置界面
        InitJflame();
        //初始化数据
        Initdata();
        //初始化
        InitImg();

        //界面显示
        this.setVisible(true);
    }

    private void Initdata() {

        rightAcc = "woshidashuaige";
        rightpassw = "123456";
        rightcode = createCode();
        //去除按钮边框
        login.setBorderPainted(false);
        //去除按钮背景
        login.setContentAreaFilled(false);


    }

    private void InitImg() {
        this.getContentPane().removeAll();
        //用户名文本
        JLabel username =new JLabel(new ImageIcon("image/login/用户名.png"));
        username.setBounds(116,135,47,17);
        this.getContentPane().add(username);
        //用户名输入框
        nameinput.setBounds(195,130,200,30);
        this.getContentPane().add(nameinput);

        //密码文本
        JLabel passward =new JLabel(new ImageIcon("image/login/密码.png"));
        passward.setBounds(130,195,30,16);
        this.getContentPane().add(passward);
        //密码输入框
        pwinput.setBounds(195,190,200,30);
        this.getContentPane().add(pwinput);

        //验证码文本
        JLabel yzcode =new JLabel(new ImageIcon("image/login/验证码.png"));
        yzcode.setBounds(115,256,50,30);
        this.getContentPane().add(yzcode);
        //验证码输入框
        codeinput.setBounds(195,256,100,30);
        this.getContentPane().add(codeinput);


        JLabel rcode = new JLabel();
        rcode.setText(rightcode);

        rcode.setBounds(300,256,50,30);
        this.getContentPane().add(rcode);


        login.setBounds(100,310,128,47);
        //按钮图片
        login.setIcon(new ImageIcon(loginpath));

        this.getContentPane().add(login);


        register.setBounds(250,310,128,47);
        //按钮图片
        register.setIcon(new ImageIcon("image/login/注册按钮.png"));
        //去除按钮边框
        register.setBorderPainted(false);
        //去除按钮背景
        register.setContentAreaFilled(false);
        this.getContentPane().add(register);

        JLabel bg =new JLabel(new ImageIcon("image/login/background.png"));
        bg.setBounds(0,0,470,390);
        this.getContentPane().add(bg);
        this.getContentPane().repaint();
    }

    private void showError(String text){
        //创建弹框对象
        JDialog Jd = new JDialog();

        JLabel jl = new JLabel();
        jl.setText(text);
        Jd.getContentPane().add(jl);
        //设置弹框大小
        Jd.setSize(200,150);
        //弹框置顶
        Jd.setAlwaysOnTop(true);
        //居中
        Jd.setLocationRelativeTo(null);
        //弹框不关闭无法操作游戏
        Jd.setModal(true);

        Jd.setVisible(true);
    }
    private String createCode() {
        Random r= new Random();
        String ans= "";
        for(int i=0;i<5;i++){
            int idx = r.nextInt(3);
            switch (idx){
                case 0:
                    char ch1 = (char)('a'+r.nextInt(26));
                    ans+=ch1;
                    break;
                case 1:
                    char ch2 = (char)('A'+r.nextInt(26));
                    ans+=ch2;
                    break;
                case 2:
                    int ch3 = r.nextInt(10);
                    ans+=ch3;
                    break;

            }
        }
        return ans;
    }

    private void InitJflame() {
        //设置界面宽高
        this.setSize(488,430);
        //设置界面标题
        this.setTitle("GameLogin");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //取消默认居中放置
        this.setLayout(null);
        //设置关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //绑定监听事件
        login.addMouseListener(this);

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
       Object com = e.getSource();
       if(com == login){
           loginpath="image/login/登录按下.png";

           InitImg();
       }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Object com = e.getSource();
        if(com == login){
            loginpath="image/login/登录按钮.png";
            String inputcode = codeinput.getText();
            String inputAcc = nameinput.getText();
            String inputpw = pwinput.getText();

            if(inputcode.equals(rightcode) && inputpw.equals(rightpassw) && inputAcc.equals(rightAcc)){
                this.setVisible(false);
                new gameJflame();
            }else{
                showError("！！！！输入有错误！！！！");
                rightcode = createCode();
                codeinput.setText("");
                nameinput.setText("");
                pwinput.setText("");
                InitImg();
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
