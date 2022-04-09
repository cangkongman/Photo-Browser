package bigwork;

import java.io.File;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class FileTree{
	JTree fileBrowserTree;
	public FileTree(File rootFile) {
		super();
		// 递归构建文件树的根节点
		DefaultMutableTreeNode rootNode = buildFileTree(rootFile);

		// 使用根节点去初始化一颗树
        fileBrowserTree = new JTree(rootNode);
		fileBrowserTree.setVisible(true);
		
	}

	public DefaultMutableTreeNode buildFileTree(File rootFile) {
		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(rootFile.getName());

		// 如果是文件，即没有子文件
		if (rootFile.isFile()) {
			return rootNode;
		}

		File[] subFiles = rootFile.listFiles();

		// 列出文件出错，一般不会发生
		if (subFiles == null) {
			return rootNode;
		}

		for (File subFile : subFiles) {
			// 如果 subFile 是文件，则新建一个树节点
			if (subFile.isFile()) {
				DefaultMutableTreeNode subNode = new DefaultMutableTreeNode(subFile.getName());
				rootNode.add(subNode);
			}

			// 如果 subFile 是目录，那么构建一颗子树
			if (subFile.isDirectory()) {
				DefaultMutableTreeNode subNode = buildFileTree(subFile);
				rootNode.add(subNode);
			}
		}

		return rootNode;
	}

}
