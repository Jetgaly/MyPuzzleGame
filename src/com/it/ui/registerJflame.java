package com.it.ui;

import javax.swing.*;

public class registerJflame extends JFrame {
    public registerJflame(){

        //设置界面宽高
        this.setSize(488,500);
        //设置界面标题
        this.setTitle("GameRegister");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //界面显示
        this.setVisible(true);

    }
}
