package bigwork;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class readPicture extends JPanel {
	SmallPicture[] jls = null;
	List<String> filenames = null;

	// 获得文件夹下的图片名称
	protected List<String> getFileList(String path) {
		File f = new File(path);
		String[] files; // 当前文件夹下的文件名称
		List<String> pfiles = new LinkedList<>();

		if (!f.exists()) {
			JOptionPane.showMessageDialog(this, path + "不存在");
		}

		files = f.list();
		if(files==null)return pfiles;
		for (int i = 0; i < files.length; i++) {
			if (files[i].indexOf(".") == -1) // 如果是文件夹
				continue;
			if (files[i].split("\\.")[1].equals("png") || files[i].split("\\.")[1].equals("jpg")) {
				pfiles.add(files[i]);
			}
		}
		return pfiles;
	}

	// 多线程给Label添加图标
	private SmallPicture[] getLabel(List<String> filenames, String path) {
		int size = filenames.size();
		SmallPicture[] jls = new SmallPicture[size];
		final Executor executor = Executors.newCachedThreadPool();// 启用多线程
		final CountDownLatch count = new CountDownLatch(size);
		long begin = System.currentTimeMillis();
		for (int n = 0; n < size; n++) {
			final int i = n;
			executor.execute(new Runnable() {
				@Override
				public void run() {
					try {
						jls[i] = new SmallPicture(path + filenames.get(i), i);
						count.countDown();
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
					}
				}
			});
		}

		try {
			count.await();
			long end = System.currentTimeMillis();
			System.out.println("运行时间：" + (end - begin));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return jls;
	}

	public readPicture(String path) {
		filenames = getFileList(path);
		if (filenames.size() == 0) {
			JLabel jl = new JLabel("该文件夹下没有图片");
			this.add(jl);
			return;
		}
		jls = getLabel(filenames, path);

		setLayout(new GridLayout(jls.length / 3 + 1, 3, 10, 10));
		System.out.println(jls.length / 3);
		for (int i = 0; i < jls.length; i++) {
			this.add(jls[i]);
		}

	}
}
