package bigwork;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class BigPicture extends JLabel {
	protected ImageIcon view(String path) {

		System.out.println(path);
		ImageIcon icon = new ImageIcon();
		icon = new ImageIcon(path);
		int w = icon.getIconWidth();
		int h = icon.getIconHeight();
		int screenwidht = (int) (java.awt.Toolkit.getDefaultToolkit().getScreenSize().width * 0.8);
		int screenheight = (int) (java.awt.Toolkit.getDefaultToolkit().getScreenSize().height * 0.8);
		// 如果图片原尺寸大于屏幕就调整，否则就按原尺寸
		if (w > screenwidht || h > screenheight) {
			if (w / h > screenwidht / screenheight) {
				h = screenwidht * h / w;
				w = screenwidht;
			} else {
				w = screenheight * w / h;
				h = screenheight;
			}
		}
		icon.setImage(icon.getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT));
		return icon;
	}

	public BigPicture(String path) {
		setVerticalTextPosition(JLabel.CENTER);
		setHorizontalTextPosition(JLabel.CENTER);
		this.setIcon(view(path));
	}

}
