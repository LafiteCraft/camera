package com.lafite.demo.controller;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.*;
import org.springframework.context.annotation.Scope;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author LafiteHao
 * @create 2017-05-24 11:25
 **/
@Namespace("/daily")
@ParentPackage("json-default")
@Scope("singleton")
@Results({@Result(name = "error", location = "error/error.jsp"),
        @Result(name = "swing_success", location = "/")})
public class SwingAction {

    private ServerSocket ss;

    @Action("swing")
    public String start () {
        String result = "swing_success";
        try {
            this.ss = new ServerSocket(6000);
            ImageFrame frame = new ImageFrame(ss);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            while(true){
                frame.panel.getimage();
                frame.repaint();
            }
        } catch (IOException e) {
            result = "error";
        }
        return result;
    }

    /**
     A frame with an image panel
     */
    @SuppressWarnings("serial")
    class ImageFrame extends JFrame{
        public ImagePanel panel;
        public JButton jb;

        public ImageFrame(ServerSocket ss){
            // get screen dimensions
            Toolkit kit = Toolkit.getDefaultToolkit();
            Dimension screenSize = kit.getScreenSize();
            int screenHeight = screenSize.height;
            int screenWidth = screenSize.width;

            // center frame in screen
            setTitle("ImageTest");
            setLocation((screenWidth - DEFAULT_WIDTH) / 2, (screenHeight - DEFAULT_HEIGHT) / 2);
            setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

            // add panel to frame
            this.getContentPane().setLayout(null);
            panel = new ImagePanel(ss);
            panel.setSize(640,480);
            panel.setLocation(0, 0);
            add(panel);
            jb = new JButton("按钮");
            jb.setBounds(0,480,640,50);
            add(jb);
            saveimage saveaction = new saveimage(ss);
            jb.addActionListener(saveaction);
        }

        public static final int DEFAULT_WIDTH = 640;
        public static final int DEFAULT_HEIGHT = 560;
    }

    /**
     A panel that displays a tiled image
     */
    @SuppressWarnings("serial")
    class ImagePanel extends JPanel {
        private ServerSocket ss;
        private BufferedImage image;
        private InputStream ins;

        public ImagePanel(ServerSocket ss) {
            this.ss = ss;
        }

        public void getimage() throws IOException{
            Socket s = this.ss.accept();
            System.out.println("���ӳɹ�!");
            this.ins = s.getInputStream();
            this.image = ImageIO.read(ins);
            this.ins.close();
        }

        public void paintComponent(Graphics g){
            super.paintComponent(g);
            if (image == null) return;
            g.drawImage(changeImg(image), 0, 0, null);
        }
        public BufferedImage changeImg(BufferedImage bufferedimage) {
            int w = bufferedimage.getWidth();
            int h = bufferedimage.getHeight();
            int type = bufferedimage.getColorModel().getTransparency();
            BufferedImage img = new BufferedImage(h, w, type);
            Graphics2D graphics2d;
            graphics2d=img.createGraphics();
            graphics2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            graphics2d.rotate(Math.toRadians(90), h / 2, h / 2);
            graphics2d.drawImage(bufferedimage, 0, 0, null);
            graphics2d.dispose();
            return img;
        }
    }

    class saveimage implements ActionListener {
        RandomAccessFile inFile = null;
        byte byteBuffer[] = new byte[1024];
        InputStream ins;
        private ServerSocket ss;

        public saveimage(ServerSocket ss){
            this.ss = ss;
        }

        public void actionPerformed(ActionEvent event){
            try {
                Socket s = ss.accept();
                ins = s.getInputStream();

                // �ļ�ѡ�����Ե�ǰ��Ŀ¼��
                JFileChooser jfc = new JFileChooser(".");
                jfc.showSaveDialog(new javax.swing.JFrame());
                // ��ȡ��ǰ��ѡ���ļ�����
                File savedFile = jfc.getSelectedFile();

                // �Ѿ�ѡ�����ļ�
                if (savedFile != null) {
                    // ��ȡ�ļ������ݣ�����ÿ���Կ�ķ�ʽ��ȡ����
                    try {
                        inFile = new RandomAccessFile(savedFile, "rw");
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }

                int amount;
                while ((amount = ins.read(byteBuffer)) != -1) {
                    inFile.write(byteBuffer, 0, amount);
                }
                inFile.close();
                ins.close();
                s.close();
                javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(),
                        "�ѽӱ���ɹ�", "��ʾ!", javax.swing.JOptionPane.PLAIN_MESSAGE);
            } catch (IOException e) {

                e.printStackTrace();
            }
        }

    }}
