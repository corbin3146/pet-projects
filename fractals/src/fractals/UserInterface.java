package fractals;

import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.WindowConstants;

public class UserInterface {
	public UserInterface() {
		int resolution = 1000;
		JFrame frame = new JFrame();

		JPanel controlPanel = new JPanel();
		JPanel everythingPanel = new JPanel();
		everythingPanel.setLayout(new BoxLayout(everythingPanel, BoxLayout.Y_AXIS));
		JTextField foo = new JTextField(1);
		JLabel resolutionLabel = new JLabel("Resolution:");
		JTextField resolutionText = new JTextField("1000", 4);
		JLabel complexLabel = new JLabel("c:");
		JLabel xLabel = new JLabel("x=");
		JSpinner xSpin = new JSpinner(new SpinnerNumberModel(.0, -10, 10, 0.1));
		JLabel iLabel = new JLabel("i=");
		JSpinner iSpin = new JSpinner(new SpinnerNumberModel(.0, -10, 10, 0.1));
		JLabel shiftLabel = new JLabel("Color shift:");
		JTextField shiftText = new JTextField("5");
		JLabel offsetLabel = new JLabel("Offset:");
		JLabel xOffsetLabel = new JLabel("x=");
		JSpinner xOffsetSpin = new JSpinner(new SpinnerNumberModel(.0, -1, 1, 0.2));
		JLabel iOffsetLabel = new JLabel("i=");
		JSpinner iOffsetSpin = new JSpinner(new SpinnerNumberModel(.0, -1, 1, 0.2));
		JLabel zoomLabel = new JLabel("zoom:");
		JSpinner zoomSpin = new JSpinner(new SpinnerNumberModel(0, -100.0, 100.0, 1.0));
		JLabel powerLabel = new JLabel("Power:");
		JSpinner powerSpinner = new JSpinner(new SpinnerNumberModel(2,2,99,1));
		JLabel maxKLabel = new JLabel("Max K:");
		JSpinner maxKSpinner = new JSpinner(new SpinnerNumberModel(200,0,100000,100));
		JButton reDraw = new JButton("Draw");
		JCheckBox mendelCheck = new JCheckBox("mandelbrot");
		mendelCheck.setSelected(true);

		controlPanel.add(foo);
		controlPanel.add(resolutionLabel);
		controlPanel.add(resolutionText);
		controlPanel.add(complexLabel);
		controlPanel.add(xLabel);
		controlPanel.add(xSpin);
		controlPanel.add(iLabel);
		controlPanel.add(iSpin);
		controlPanel.add(shiftLabel);
		controlPanel.add(shiftText);
		controlPanel.add(offsetLabel);
		controlPanel.add(xOffsetLabel);
		controlPanel.add(xOffsetSpin);
		controlPanel.add(iOffsetLabel);
		controlPanel.add(iOffsetSpin);
		controlPanel.add(zoomLabel);
		controlPanel.add(zoomSpin);
		controlPanel.add(powerLabel);
		controlPanel.add(powerSpinner);
		controlPanel.add(maxKLabel);
		controlPanel.add(maxKSpinner);
		controlPanel.add(mendelCheck);
		controlPanel.add(reDraw);

		reDraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImageGeneration generator = new ImageGeneration();
				if (!mendelCheck.isSelected()) {
					BufferedImage image = generator.generateImageZoom(
							new ComplexNumber((double) xSpin.getValue(), (double) iSpin.getValue()),(int) powerSpinner.getValue(), (int)maxKSpinner.getValue(),
							Integer.parseInt(resolutionText.getText()),
							(1.5 * Math.pow(0.9, (double) zoomSpin.getValue())), Integer.parseInt(shiftText.getText()),
							(double) xOffsetSpin.getValue(), -1 * (double) iOffsetSpin.getValue());
					everythingPanel.removeAll();
					everythingPanel.add(controlPanel);
					everythingPanel.add(new JLabel(new ImageIcon(image)));
					frame.invalidate();
					frame.validate();
					frame.repaint();
				} else {
					BufferedImage image = generator.mendelbroth((int)powerSpinner.getValue(),(int) maxKSpinner.getValue(),Integer.parseInt(resolutionText.getText()),
							(1.5 * Math.pow(0.9, (double) zoomSpin.getValue())), Integer.parseInt(shiftText.getText()),
							(double) xOffsetSpin.getValue(), -1 * (double) iOffsetSpin.getValue());
					everythingPanel.removeAll();
					everythingPanel.add(controlPanel);
					everythingPanel.add(new JLabel(new ImageIcon(image)));
					frame.invalidate();
					frame.validate();
					frame.repaint();
				}
			}
		});

		foo.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
			}

			public void keyReleased(KeyEvent e) {
				if (e.getKeyChar() == 'w') {
					// System.out.println("up");
					iOffsetSpin.setValue(
							(double) iOffsetSpin.getValue() + (0.1 * Math.pow(0.9, (double) zoomSpin.getValue())));
				} else if (e.getKeyChar() == 'a') {
					// System.out.println("left");
					xOffsetSpin.setValue(
							(double) xOffsetSpin.getValue() + (-0.1 * Math.pow(0.9, (double) zoomSpin.getValue())));
				} else if (e.getKeyChar() == 's') {
					// System.out.println("down");
					iOffsetSpin.setValue(
							(double) iOffsetSpin.getValue() + (-0.1 * Math.pow(0.9, (double) zoomSpin.getValue())));
				} else if (e.getKeyChar() == 'd') {
					// System.out.println("right");
					xOffsetSpin.setValue(
							(double) xOffsetSpin.getValue() + (0.1 * Math.pow(0.9, (double) zoomSpin.getValue())));
				} else if (e.getKeyChar() == 'e') {
					zoomSpin.setValue(((double) zoomSpin.getValue()) + 1);
				} else if (e.getKeyChar() == 'q') {
					zoomSpin.setValue(((double) zoomSpin.getValue()) - 1);
					// }else if(e.getKeyChar() == 'r') {
					// shiftText.setText((Double.parseDouble(shiftText.getText())+5 )+ "");
					// }else if(e.getKeyChar() == 'f') {
					// shiftText.setText((int)(Double.parseDouble(shiftText.getText())-5 )+ "");
				}

				reDraw.doClick();
				foo.requestFocus();
			}

			public void keyPressed(KeyEvent e) {
			}
		});

		ImageGeneration generator = new ImageGeneration();
		BufferedImage image;

		if (!mendelCheck.isSelected()) {
			image = generator.generateImageZoom(
					new ComplexNumber((double) xSpin.getValue(), (double) iSpin.getValue()),(int) powerSpinner.getValue(), (int) maxKSpinner.getValue(),
					Integer.parseInt(resolutionText.getText()),
					(1.5 * Math.pow(0.9, (double) zoomSpin.getValue())), Integer.parseInt(shiftText.getText()),
					(double) xOffsetSpin.getValue(), -1 * (double) iOffsetSpin.getValue());
		} else {
			image = generator.mendelbroth((int) powerSpinner.getValue(),(int)maxKSpinner.getValue(),Integer.parseInt(resolutionText.getText()),
					(1.5 * Math.pow(0.9, (double) zoomSpin.getValue())), Integer.parseInt(shiftText.getText()),
					(double) xOffsetSpin.getValue(), -1 * (double) iOffsetSpin.getValue());
		}
		
		everythingPanel.add(controlPanel);
		everythingPanel.add(new JLabel(new ImageIcon(image)));

		frame.add(everythingPanel);
		frame.setVisible(true);
		frame.setBounds(0, 0, resolution + 50, resolution + 50);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

	}
}
