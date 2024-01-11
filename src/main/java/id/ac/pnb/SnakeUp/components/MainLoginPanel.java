package id.ac.pnb.SnakeUp.components;

import id.ac.pnb.SnakeUp.models.ModelLogin;
import id.ac.pnb.SnakeUp.models.ModelUser;
import id.ac.pnb.SnakeUp.services.game.LoginManager;
import id.ac.pnb.SnakeUp.utils.GlobalVars;

import net.miginfocom.swing.MigLayout;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class MainLoginPanel extends GamePanel {

    private final DecimalFormat df = new DecimalFormat("##0.###", DecimalFormatSymbols.getInstance(Locale.US));
    private MigLayout layout;
    private PanelCover cover;
    private PanelLoginAndRegister loginAndRegister;
    private boolean isLogin = false;
    private final double addSize = 30;
    private final double coverSize = 40;
    private final double loginSize = 60;
    private LoginManager service;
    private boolean loggedIn = false;

    public MainLoginPanel() {
        init();
    }

    private void init() {

        service = new LoginManager();
        layout = new MigLayout("fill, insets 0");
        cover = new PanelCover();
        ActionListener eventRegister = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                register();
            }
        };
        ActionListener eventLogin = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                login();
            }
        };
        loginAndRegister = new PanelLoginAndRegister(eventRegister, eventLogin);
        TimingTarget target = new TimingTargetAdapter() {
            public void timingEvent(float fraction) {
                double fractionCover;
                double fractionLogin;
                double size = coverSize;
                if (fraction <= 0.5f) {
                    size += fraction * addSize;
                } else {
                    size += addSize - fraction * addSize;
                }
                if (isLogin) {
                    fractionCover = 1f - fraction;
                    fractionLogin = fraction;
                    if (fraction >= 0.5f) {
                        cover.registerRight(fractionCover * 100);
                    } else {
                        cover.loginRight(fractionLogin * 100);
                    }
                } else {
                    fractionCover = fraction;
                    fractionLogin = 1f - fraction;
                    if (fraction <= 0.5f) {
                        cover.registerLeft(fraction * 100);
                    } else {
                        cover.loginLeft((1f - fraction) * 100);
                    }
                }
                if (fraction >= 0.5f) {
                    loginAndRegister.showRegister(isLogin);
                }
                fractionCover = Double.valueOf(df.format(fractionCover));
                fractionLogin = Double.valueOf(df.format(fractionLogin));
                layout.setComponentConstraints(cover, "width " + size + "%, pos " + fractionCover + "al 0 n 100%");
                layout.setComponentConstraints(loginAndRegister, "width " + loginSize + "%, pos " + fractionLogin + "al 0 n 100%");
                revalidate();
            }

            @Override
            public void end() {
                isLogin = !isLogin;
            }
        };
        Animator animator = new Animator(800, target);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        animator.setResolution(0);
        setLayout(layout);
        this.add(cover, "width " + coverSize + "%, pos " + (isLogin ? "1al" : "0al") + " 0 n 100%");
        this.add(loginAndRegister, "width " + loginSize + "%, pos " + (isLogin ? "0al" : "1al") + " 0 n 100%");
        loginAndRegister.showRegister(!isLogin);
        cover.login(isLogin);
        cover.addEvent(ae -> {
            if (!animator.isRunning()) {
                animator.start();
            }
        });
    }

    private void register() {

        ModelUser user = loginAndRegister.getUser();
        try {
            if (service.checkDuplicateUser(user.getUserName())) {
                showMessage(Message.MessageType.ERROR, "User name already exists");
            } else {
                service.insertUser(user);
                service.insertWinrate(user);
                showMessage(Message.MessageType.SUCCESS, "Your account has been registered, please log in");

            }
        } catch (Exception e) {
            showMessage(Message.MessageType.ERROR, "Error Register");
        }

    }

    private void login() {
        ModelLogin data = loginAndRegister.getDataLogin();
        try {
            ModelUser user = service.login(data);
            if (user == null) {
                showMessage(Message.MessageType.ERROR, "Email and Password incorrect");
                return;
            }
       
            if (GlobalVars.setLogin) {
                   System.out.println(GlobalVars.userID);
                if (GlobalVars.userID.size() >= 2) {
                    
                  
                    loggedIn = true;

                } else {

                    showMessage(Message.MessageType.ERROR, "You need at least 2 players to play the game");
                
                }
            } else {
                loggedIn = true;
            }

        } catch (Exception e) {
            showMessage(Message.MessageType.ERROR, "Error Login");
        }

    }

    private void showMessage(Message.MessageType messageType, String message) {
        Message ms = new Message();
        ms.showMessage(messageType, message);
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void begin() {
                if (!ms.isShow()) {
                    add(ms, "pos 0.5al -30", 0); //  Insert to bg first index 0
                    ms.setVisible(true);
                    repaint();
                }
            }

            @Override
            public void timingEvent(float fraction) {
                float f;
                if (ms.isShow()) {
                    f = 40 * (1f - fraction);
                } else {
                    f = 40 * fraction;
                }
                layout.setComponentConstraints(ms, "pos 0.5al " + (int) (f - 30));
                repaint();
                revalidate();
            }

            @Override
            public void end() {
                if (ms.isShow()) {
                    remove(ms);
                    repaint();
                    revalidate();
                } else {
                    ms.setShow(true);
                }
            }
        };
        Animator animator = new Animator(300, target);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        animator.start();
        new Thread(() -> {
            try {
                Thread.sleep(2000);
                animator.start();
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }).start();
    }

    private void initComponents() {

    }

    @Override
    public void updateGame() {
//        throw new UnsupportedOperationException("Not supported yet.");
    }
  
    public boolean isLoggedIn() {
        return loggedIn;
    }
}
