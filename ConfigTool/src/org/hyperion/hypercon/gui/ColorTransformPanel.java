package org.hyperion.hypercon.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.beans.Transient;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.hyperion.hypercon.spec.TransformConfigModel;

/**
 * Configuration panel for the ColorConfig.
 *  
 * NB This has not been integrated in the GUI jet!
 */
public class ColorTransformPanel extends JPanel {
	
	private final Dimension maxDim = new Dimension(1024, 20);
	
	private final TransformConfigModel mColorConfig;
	
	private JPanel mIndexPanel;
	private JLabel mIndexLabel;
	private JTextField mIndexField;
	
	private JPanel mRgbTransformPanel;
	private JLabel mThresholdLabel;
	private JLabel mGammaLabel;
	private JLabel mBlacklevelLabel;
	private JLabel mWhitelevelLabel;
	private JLabel mRedTransformLabel;
	private JSpinner mRedThresholdSpinner;
	private JSpinner mRedGammaSpinner;
	private JSpinner mRedBlacklevelSpinner;
	private JSpinner mRedWhitelevelSpinner;
	private JLabel mGreenTransformLabel;
	private JSpinner mGreenThresholdSpinner;
	private JSpinner mGreenGammaSpinner;
	private JSpinner mGreenBlacklevelSpinner;
	private JSpinner mGreenWhitelevelSpinner;
	private JLabel mBlueTransformLabel;
	private JSpinner mBlueThresholdSpinner;
	private JSpinner mBlueGammaSpinner;
	private JSpinner mBlueBlacklevelSpinner;
	private JSpinner mBlueWhitelevelSpinner;

	private JPanel mHsvTransformPanel;
	private JLabel mSaturationAdjustLabel;
	private JSpinner mSaturationAdjustSpinner;
	private JLabel mValueAdjustLabel;
	private JSpinner mValueAdjustSpinner;
	
	public ColorTransformPanel(TransformConfigModel pTransformConfig) {
		super();
		
		mColorConfig = pTransformConfig;
		
		initialise();
	}
	
	@Override
	@Transient
	public Dimension getMaximumSize() {
		Dimension maxSize = super.getMaximumSize();
		Dimension prefSize = super.getPreferredSize();
		return new Dimension(maxSize.width, prefSize.height);
	}

	private void initialise() {
		setBorder(BorderFactory.createTitledBorder("Transform [" + mColorConfig.id.getValue() + "]"));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		add(getIndexPanel());
		add(Box.createVerticalStrut(10));
		add(getRgbPanel());
		add(Box.createVerticalStrut(10));
		add(getHsvPanel());
		add(Box.createVerticalGlue());
	}
	
	private JPanel getIndexPanel() {
		if (mIndexPanel == null) {
			mIndexPanel = new JPanel();
			mIndexPanel.setMaximumSize(new Dimension(1024, 25));
			mIndexPanel.setLayout(new BorderLayout(10,10));
			
			mIndexLabel = new JLabel("Indices:");
			mIndexPanel.add(mIndexLabel, BorderLayout.WEST);
			
			mIndexField = new JTextField(mColorConfig.ledIndexString.getValue());
			mIndexField.setToolTipText("Comma seperated indices or index ranges (eg '1-10, 13, 14, 17-19'); Special case '*', which means all leds");
			mIndexField.getDocument().addDocumentListener(mDocumentListener);
			mIndexPanel.add(mIndexField, BorderLayout.CENTER);
		}
		return mIndexPanel;
	}
	
	private JPanel getRgbPanel() {
		if (mRgbTransformPanel == null) {
			mRgbTransformPanel = new JPanel();
			
			GridLayout layout = new GridLayout(0, 5);
//			GroupLayout layout = new GroupLayout(mRgbTransformPanel);
			mRgbTransformPanel.setLayout(layout);
			
			mRgbTransformPanel.add(Box.createHorizontalBox());
			
			mThresholdLabel = new JLabel("Thres.");
			mRgbTransformPanel.add(mThresholdLabel);
			
			mGammaLabel = new JLabel("Gamma");
			mRgbTransformPanel.add(mGammaLabel);
			
			mBlacklevelLabel = new JLabel("Blacklvl");
			mRgbTransformPanel.add(mBlacklevelLabel);
			
			mWhitelevelLabel = new JLabel("Whitelvl");
			mRgbTransformPanel.add(mWhitelevelLabel);
			
			mRedTransformLabel = new JLabel("RED");
			mRgbTransformPanel.add(mRedTransformLabel);
			mRedThresholdSpinner = new JSpinner(new SpinnerNumberModel(mColorConfig.red.threshold.getValue(), 0.0, 1.0, 0.1));
			mRedThresholdSpinner.setMaximumSize(maxDim);
			mRedThresholdSpinner.addChangeListener(mChangeListener);
			mRgbTransformPanel.add(mRedThresholdSpinner);
			mRedGammaSpinner = new JSpinner(new SpinnerNumberModel(mColorConfig.red.gamma.getValue(), 0.0, 100.0, 0.1));
			mRedGammaSpinner.setMaximumSize(maxDim);
			mRedGammaSpinner.addChangeListener(mChangeListener);
			mRgbTransformPanel.add(mRedGammaSpinner);
			mRedBlacklevelSpinner = new JSpinner(new SpinnerNumberModel(mColorConfig.red.blacklevel.getValue(), 0.0, 1.0, 0.1));
			mRedBlacklevelSpinner.setMaximumSize(maxDim);
			mRedBlacklevelSpinner.addChangeListener(mChangeListener);
			mRgbTransformPanel.add(mRedBlacklevelSpinner);
			mRedWhitelevelSpinner = new JSpinner(new SpinnerNumberModel(mColorConfig.red.whitelevel.getValue(), 0.0, 1.0, 0.1));
			mRedWhitelevelSpinner.setMaximumSize(maxDim);
			mRedWhitelevelSpinner.addChangeListener(mChangeListener);
			mRgbTransformPanel.add(mRedWhitelevelSpinner);
			
			mGreenTransformLabel = new JLabel("GREEN");
			mRgbTransformPanel.add(mGreenTransformLabel);
			mGreenThresholdSpinner = new JSpinner(new SpinnerNumberModel(mColorConfig.green.threshold.getValue(), 0.0, 1.0, 0.1));
			mGreenThresholdSpinner.setMaximumSize(maxDim);
			mGreenThresholdSpinner.addChangeListener(mChangeListener);
			mRgbTransformPanel.add(mGreenThresholdSpinner);
			mGreenGammaSpinner = new JSpinner(new SpinnerNumberModel(mColorConfig.green.gamma.getValue(), 0.0, 100.0, 0.1));
			mGreenGammaSpinner.setMaximumSize(maxDim);
			mGreenGammaSpinner.addChangeListener(mChangeListener);
			mRgbTransformPanel.add(mGreenGammaSpinner);
			mGreenBlacklevelSpinner = new JSpinner(new SpinnerNumberModel(mColorConfig.green.blacklevel.getValue(), 0.0, 1.0, 0.1));
			mGreenBlacklevelSpinner.setMaximumSize(maxDim);
			mGreenBlacklevelSpinner.addChangeListener(mChangeListener);
			mRgbTransformPanel.add(mGreenBlacklevelSpinner);
			mGreenWhitelevelSpinner = new JSpinner(new SpinnerNumberModel(mColorConfig.green.whitelevel.getValue(), 0.0, 1.0, 0.1));
			mGreenWhitelevelSpinner.setMaximumSize(maxDim);
			mGreenWhitelevelSpinner.addChangeListener(mChangeListener);
			mRgbTransformPanel.add(mGreenWhitelevelSpinner);

			mBlueTransformLabel = new JLabel("BLUE");
			mRgbTransformPanel.add(mBlueTransformLabel);
			mBlueThresholdSpinner = new JSpinner(new SpinnerNumberModel(mColorConfig.blue.threshold.getValue(), 0.0, 1.0, 0.1));
			mBlueThresholdSpinner.setMaximumSize(maxDim);
			mBlueThresholdSpinner.addChangeListener(mChangeListener);
			mRgbTransformPanel.add(mBlueThresholdSpinner);
			mBlueGammaSpinner = new JSpinner(new SpinnerNumberModel(mColorConfig.blue.gamma.getValue(), 0.0, 100.0, 0.1));
			mBlueGammaSpinner.setMaximumSize(maxDim);
			mBlueGammaSpinner.addChangeListener(mChangeListener);
			mRgbTransformPanel.add(mBlueGammaSpinner);
			mBlueBlacklevelSpinner = new JSpinner(new SpinnerNumberModel(mColorConfig.blue.blacklevel.getValue(), 0.0, 1.0, 0.1));
			mBlueBlacklevelSpinner.setMaximumSize(maxDim);
			mBlueBlacklevelSpinner.addChangeListener(mChangeListener);
			mRgbTransformPanel.add(mBlueBlacklevelSpinner);
			mBlueWhitelevelSpinner = new JSpinner(new SpinnerNumberModel(mColorConfig.blue.whitelevel.getValue(), 0.0, 1.0, 0.1));
			mBlueWhitelevelSpinner.setMaximumSize(maxDim);
			mBlueWhitelevelSpinner.addChangeListener(mChangeListener);
			mRgbTransformPanel.add(mBlueWhitelevelSpinner);
		}
		return mRgbTransformPanel;
	}
	
	private JPanel getHsvPanel() {
		if (mHsvTransformPanel == null) {
			mHsvTransformPanel = new JPanel();
			
			GroupLayout layout = new GroupLayout(mHsvTransformPanel);
			mHsvTransformPanel.setLayout(layout);
			
			mSaturationAdjustLabel = new JLabel("HSV Saturation gain");
			mHsvTransformPanel.add(mSaturationAdjustLabel);
			
			mSaturationAdjustSpinner = new JSpinner(new SpinnerNumberModel(mColorConfig.saturationGain.getValue(), 0.0, 1024.0, 0.01));
			mSaturationAdjustSpinner.setMaximumSize(maxDim);
			mSaturationAdjustSpinner.addChangeListener(mChangeListener);
			mHsvTransformPanel.add(mSaturationAdjustSpinner);
			
			mValueAdjustLabel = new JLabel("HSV Value gain");
			mHsvTransformPanel.add(mValueAdjustLabel);
			
			mValueAdjustSpinner = new JSpinner(new SpinnerNumberModel(mColorConfig.valueGain.getValue(), 0.0, 1024.0, 0.01));
			mValueAdjustSpinner.setMaximumSize(maxDim);
			mValueAdjustSpinner.addChangeListener(mChangeListener);
			mHsvTransformPanel.add(mValueAdjustSpinner);

			layout.setHorizontalGroup(layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup()
							.addComponent(mSaturationAdjustLabel)
							.addComponent(mValueAdjustLabel)
							)
					.addGroup(layout.createParallelGroup()
							.addComponent(mSaturationAdjustSpinner)
							.addComponent(mValueAdjustSpinner)
							)
					);
			
			layout.setVerticalGroup(layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup()
							.addComponent(mSaturationAdjustLabel)
							.addComponent(mSaturationAdjustSpinner)
							)
					.addGroup(layout.createParallelGroup()
							.addComponent(mValueAdjustLabel)
							.addComponent(mValueAdjustSpinner)
							)
					);
		}
		return mHsvTransformPanel;
	}
	
	private final ChangeListener mChangeListener = new ChangeListener() {
		@Override
		public void stateChanged(ChangeEvent e) {
			mColorConfig.red.threshold.setValue((Double)mRedThresholdSpinner.getValue());
			mColorConfig.red.gamma.setValue((Double)mRedGammaSpinner.getValue());
			mColorConfig.red.blacklevel.setValue((Double)mRedBlacklevelSpinner.getValue());
			mColorConfig.red.whitelevel.setValue((Double)mRedWhitelevelSpinner.getValue());

			mColorConfig.green.threshold.setValue((Double)mGreenThresholdSpinner.getValue());
			mColorConfig.green.gamma.setValue((Double)mGreenGammaSpinner.getValue());
			mColorConfig.green.blacklevel.setValue((Double)mGreenBlacklevelSpinner.getValue());
			mColorConfig.green.whitelevel.setValue((Double)mGreenWhitelevelSpinner.getValue());

			mColorConfig.blue.threshold.setValue((Double)mBlueThresholdSpinner.getValue());
			mColorConfig.blue.gamma.setValue((Double)mBlueGammaSpinner.getValue());
			mColorConfig.blue.blacklevel.setValue((Double)mBlueBlacklevelSpinner.getValue());
			mColorConfig.blue.whitelevel.setValue((Double)mBlueWhitelevelSpinner.getValue());
			
			mColorConfig.saturationGain.setValue((Double)mSaturationAdjustSpinner.getValue());
			mColorConfig.valueGain.setValue((Double)mValueAdjustSpinner.getValue());
		}
	};
	private final DocumentListener mDocumentListener = new DocumentListener() {
		@Override
		public void removeUpdate(DocumentEvent e) {
			mColorConfig.ledIndexString.setValue(mIndexField.getText());
		}
		@Override
		public void insertUpdate(DocumentEvent e) {
			mColorConfig.ledIndexString.setValue(mIndexField.getText());
		}
		@Override
		public void changedUpdate(DocumentEvent e) {
			mColorConfig.ledIndexString.setValue(mIndexField.getText());
		}
	};
}
