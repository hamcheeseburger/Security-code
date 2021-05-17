package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.Label;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import exceptions.NotSatisfiedException;
import exceptions.ObjNullException;

import javax.swing.JSeparator;
import javax.swing.JTextField;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.awt.event.ActionEvent;

public class mainView {
	private ViewController viewController;
	private JFrame frame;
	private Button btnSelectFileForSign;
	private JTextField routeFileForSign;
	private Button btnGenerateKey;
	private JTextField routePrivateKey;
	private Button btnSelectPrivateKey;
	private JTextField routeFileForVerify;
	private Button btnSelectFileForVerify;
	private JTextField routePublicKey;
	private Button btnSelectPublicKey;
	private JTextField routeOriginFile;
	private Button btnSelectOriginFile;
	private Button btnSign;
	private Button btnVerify;
	private Label resultVerifying;
	private Label resultSigning;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainView window = new mainView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public mainView() {
		initialize();
		viewController = new ViewController();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 929, 498);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		resultVerifying = new Label("");
		resultVerifying.setAlignment(Label.CENTER);
		resultVerifying.setBounds(588, 398, 189, 23);
		frame.getContentPane().add(resultVerifying);
		
		resultSigning = new Label("");
		resultSigning.setAlignment(Label.CENTER);
		resultSigning.setBounds(132, 398, 189, 23);
		frame.getContentPane().add(resultSigning);
		
		JLabel lblNewLabel = new JLabel("\uC804\uC790\uC11C\uBA85 \uD504\uB85C\uADF8\uB7A8");
		lblNewLabel.setFont(new Font("KoPub돋움체 Medium", Font.BOLD, 22));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 913, 46);
		frame.getContentPane().add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(273, 351, 48, -294);
		frame.getContentPane().add(separator);
		
		btnSelectFileForSign = new Button("\uD30C\uC77C\uC120\uD0DD");
		btnSelectFileForSign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String path = viewController.btnSelectFileForSignHandler();
				if(path != null) {
					routeFileForSign.setText(path);
				}
			}
		});
		btnSelectFileForSign.setBounds(348, 281, 76, 23);
		frame.getContentPane().add(btnSelectFileForSign);
		
		routeFileForSign = new JTextField();
		routeFileForSign.setEditable(false);
		routeFileForSign.setBounds(35, 281, 307, 23);
		frame.getContentPane().add(routeFileForSign);
		routeFileForSign.setDragEnabled(true);
		routeFileForSign.setDropTarget(getDropTarget(routeFileForSign));
		routeFileForSign.getDocument().addDocumentListener(setChanged(resultSigning, "서명할 파일이 선택되었습니다."));
		
		Label label = new Label("Signing");
		label.setFont(new Font("KoPub돋움체 Medium", Font.BOLD, 14));
		label.setAlignment(Label.CENTER);
		label.setBounds(173, 52, 69, 23);
		frame.getContentPane().add(label);
		
		Label label_1 = new Label("\uC11C\uBA85\uD560 \uD30C\uC77C \uC120\uD0DD");
		label_1.setBounds(35, 252, 116, 23);
		frame.getContentPane().add(label_1);
		
		btnGenerateKey = new Button("\uD0A4\uC0DD\uC131\uD558\uAE30");
		btnGenerateKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					viewController.btnGenerateKeyHandler();
					resultSigning.setText("키가 생성되었습니다.");
				} catch (ObjNullException e1) {
					// TODO Auto-generated catch block
					System.err.println("Key가 생성되지 않음");
				}
			}
		});
		btnGenerateKey.setBounds(35, 127, 76, 23);
		frame.getContentPane().add(btnGenerateKey);
		
		Label label_2 = new Label("\uD0A4\uAC00 \uC5C6\uB2E4\uBA74 \uC544\uB798 \uBC84\uD2BC\uC744 \uB20C\uB7EC \uC0DD\uC131\uD558\uC138\uC694.");
		label_2.setBounds(35, 98, 259, 23);
		frame.getContentPane().add(label_2);
		
		Label label_3 = new Label("\uC11C\uBA85\uC5D0 \uC0AC\uC6A9\uD560 PrivateKey \uC120\uD0DD");
		label_3.setBounds(35, 171, 189, 23);
		frame.getContentPane().add(label_3);
		
		routePrivateKey = new JTextField();
		routePrivateKey.setEditable(false);
		routePrivateKey.setBounds(35, 200, 307, 23);
		routePrivateKey.setDragEnabled(true);
		routePrivateKey.setDropTarget(getDropTarget(routePrivateKey));
		routePrivateKey.getDocument().addDocumentListener(setChanged(resultSigning, "Private Key가 선택되었습니다."));
		
		frame.getContentPane().add(routePrivateKey);
		routePrivateKey.addPropertyChangeListener(null);
		btnSelectPrivateKey = new Button("\uD0A4 \uC120\uD0DD");
		btnSelectPrivateKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String path = viewController.btnSelectPrivateKeyHandler();
				if(path != null) {
					routePrivateKey.setText(path);
				}
			}
		});
		btnSelectPrivateKey.setBounds(348, 200, 76, 23);
		frame.getContentPane().add(btnSelectPrivateKey);
		
		Label label_4 = new Label("Verifying");
		label_4.setFont(new Font("KoPub돋움체 Medium", Font.BOLD, 14));
		label_4.setAlignment(Label.CENTER);
		label_4.setBounds(649, 52, 69, 23);
		frame.getContentPane().add(label_4);
		
		Label label_5 = new Label("\uC11C\uBA85\uB41C \uD30C\uC77C\uC744 \uC120\uD0DD");
		label_5.setBounds(488, 179, 164, 23);
		frame.getContentPane().add(label_5);
		
		routeFileForVerify = new JTextField();
		routeFileForVerify.setEditable(false);
		routeFileForVerify.setBounds(488, 208, 307, 23);
		routeFileForVerify.setDragEnabled(true);
		routeFileForVerify.setDropTarget(getDropTarget(routeFileForVerify));
		routeFileForVerify.getDocument().addDocumentListener(setChanged(resultVerifying, "검증할 파일이 선택되었습니다."));
		frame.getContentPane().add(routeFileForVerify);
		
		btnSelectFileForVerify = new Button("\uD30C\uC77C\uC120\uD0DD");
		btnSelectFileForVerify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String path = viewController.btnSelectFileForVerifyHandler();
				if(path != null) {
					routeFileForVerify.setText(path);
				}
			}
		});
		btnSelectFileForVerify.setBounds(801, 208, 76, 23);
		frame.getContentPane().add(btnSelectFileForVerify);
		
		Label label_3_1 = new Label("\uAC80\uC99D\uC5D0 \uC0AC\uC6A9\uD560 PublicKey \uC120\uD0DD");
		label_3_1.setBounds(488, 252, 189, 23);
		frame.getContentPane().add(label_3_1);
		
		routePublicKey = new JTextField();
		routePublicKey.setEditable(false);
		routePublicKey.setBounds(488, 281, 300, 23);
		routePublicKey.setDragEnabled(true);
		routePublicKey.setDropTarget(getDropTarget(routePublicKey));
		routePublicKey.getDocument().addDocumentListener(setChanged(resultVerifying, "PublicKey가 선택되었습니다."));
		frame.getContentPane().add(routePublicKey);
		
		btnSelectPublicKey = new Button("\uD0A4 \uC120\uD0DD");
		btnSelectPublicKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String path = viewController.btnSelectPublicKeyHandler();
				if(path != null) {
					routePublicKey.setText(path);
				}
			}
		});
		btnSelectPublicKey.setBounds(801, 281, 76, 23);
		frame.getContentPane().add(btnSelectPublicKey);
		
		Label label_5_1 = new Label("\uC6D0\uBCF8 \uD30C\uC77C\uC744 \uC120\uD0DD");
		label_5_1.setBounds(488, 98, 164, 23);
		frame.getContentPane().add(label_5_1);
		
		routeOriginFile = new JTextField();
		routeOriginFile.setDragEnabled(true);
		routeOriginFile.setEditable(false);
		routeOriginFile.setBounds(488, 127, 307, 23);
		routeOriginFile.setDropTarget(getDropTarget(routeOriginFile));
		routeOriginFile.getDocument().addDocumentListener(setChanged(resultVerifying, "원본 파일이 선택되었습니다."));
		frame.getContentPane().add(routeOriginFile);
		
		btnSelectOriginFile = new Button("\uD30C\uC77C\uC120\uD0DD");
		btnSelectOriginFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String path = viewController.btnSelectOriginFileHandler();
				if(path != null) {
					routeOriginFile.setText(path);
				}
			}
		});
		
		btnSelectOriginFile.setBounds(801, 127, 76, 23);
		frame.getContentPane().add(btnSelectOriginFile);
		
		btnSign = new Button("\uC11C\uBA85\uD558\uAE30");
		btnSign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					viewController.btnSignHandler(routePrivateKey.getText(), routeFileForSign.getText());
					resultSigning.setText("서명파일이 생성되었습니다.");
				} catch (ObjNullException e1) {
					// TODO Auto-generated catch block
					System.err.println("디렉토리 선택 안함!!!");
				} catch (NotSatisfiedException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "필수 내용을 입력하세요.", "주의", 0);
					System.err.println("내용을 모두 입력하지 않음!");
				}
			}
		});
		
		btnSign.setBounds(173, 351, 76, 23);
		frame.getContentPane().add(btnSign);
		
		btnVerify = new Button("\uAC80\uC99D\uD558\uAE30");
		btnVerify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					boolean result = viewController.btnVerifyHandler(routeOriginFile.getText(), routeFileForVerify.getText(), routePublicKey.getText());
					
					if(result) {
						resultVerifying.setText("원본파일과 일치합니다.");
					} else {
						resultVerifying.setText("원본파일과 불일치합니다.");
					}
				} catch (NotSatisfiedException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "필수 내용을 입력하세요.", "주의", 0);
					System.err.println("내용을 모두 입력하지 않음!");
				}
			}
		});
		
		btnVerify.setBounds(649, 351, 76, 23);
		frame.getContentPane().add(btnVerify);
	}
	
	public DocumentListener setChanged(Label textField, String text) {
		return new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				textField.setText(text);
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
			}
			
		};
	}
	
	public DropTarget getDropTarget(JTextField textField) {
		return new DropTarget() {
			 @SuppressWarnings("unchecked")
				public synchronized void drop(DropTargetDropEvent evt) {
			            try {
			                evt.acceptDrop(DnDConstants.ACTION_COPY);
			                List<File> droppedFiles = (List<File>) evt
			                        .getTransferable().getTransferData(
			                                DataFlavor.javaFileListFlavor);
			                for (File file : droppedFiles) {
			                    /*
			                     * NOTE:
			                     *  When I change this to a println,
			                     *  it prints the correct path
			                     */
			                	textField.setText(file.getAbsolutePath());
			                }
			            } catch (Exception ex) {
			                ex.printStackTrace();
			            }
		}};
	}
}
