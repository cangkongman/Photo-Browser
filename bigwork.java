package bigwork;

import java.io.File;
import java.io.IOException;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

public class bigwork {
	String rootPath = "src/"; // 文件目录树的根节点
	String Path = "src/picture/"; // 默认文件

	// 构造函数
	public bigwork() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			System.out.println("设置系统界面风格时出错");
		}

		JFrame mainFrame = new JFrame("图片浏览器");

		JTree f1 = new FileTree(new File(rootPath)).fileBrowserTree; // 文件目录树
		JScrollPane jsp1 = new JScrollPane();// 文件目录树的滚动条

		readPicture r1 = new readPicture(Path); // 图片浏览的面板
		JScrollPane jsp = new JScrollPane(); // 图片浏览面板的滚动条

		JButton b1 = new JButton("返回上一级");
		b1.setBounds(10, 0, 100, 30);
		b1.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				File file = new File(rootPath);
				String file_;
				try {
					// 获得绝对路径
					file_ = file.getCanonicalPath();
					System.out.println(file_);
					String[] str = file_.split("\\\\");
					rootPath = "";
					// 去掉最后一个\之后的字符，也就是获得上一级目录
					for (int i = 0; i < str.length - 1; i++) {
						rootPath += str[i];
						rootPath += "\\";
					}
					System.out.println(rootPath);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JTree _f1 = new FileTree(new File(rootPath)).fileBrowserTree;
				jsp1.setViewportView(_f1);
				mainFrame.repaint();
				TreeSelectionListener treeSelectionListener = new TreeSelectionListener() {
					@Override
					public void valueChanged(TreeSelectionEvent treeSelectionEvent) {
						DefaultMutableTreeNode note = (DefaultMutableTreeNode) _f1.getLastSelectedPathComponent();
						TreeNode[] tree = note.getPath();

						Path = rootPath;
						for (int i = 1; i < tree.length; i++) {
							Path += tree[i].toString();
							Path += "\\";
						}

						readPicture _r1 = new readPicture(Path); // 图片浏览的面板
						jsp.setViewportView(_r1);

						mainFrame.repaint();
						System.out.println(Path);
					}
				};

				// 添加文件树的监听事件
				_f1.addTreeSelectionListener(treeSelectionListener);
			}
		});

		jsp1.setBounds(10, 30, 100, 500);

		jsp.setBounds(120, 0, 660, 500);
		jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		mainFrame.add(jsp);
		jsp1.setViewportView(f1);
		jsp.setViewportView(r1);
		mainFrame.add(jsp1);
		mainFrame.add(b1);

		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLayout(null);
		mainFrame.setBounds(100, 100, 800, 600);
		mainFrame.setVisible(true);

		TreeSelectionListener treeSelectionListener = new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent treeSelectionEvent) {
				DefaultMutableTreeNode note = (DefaultMutableTreeNode) f1.getLastSelectedPathComponent();
				TreeNode[] tree = note.getPath();

				Path = "";
				for (TreeNode i : tree) {
					Path += i.toString();
					Path += '/';
				}

				readPicture _r1 = new readPicture(Path); // 图片浏览的面板
				jsp.setViewportView(_r1);

				mainFrame.repaint();
				System.out.println(Path);
			}
		};

		// 添加文件树的监听事件
		f1.addTreeSelectionListener(treeSelectionListener);
	}

	public static void main(String[] args) {
		new bigwork();
	}
}
