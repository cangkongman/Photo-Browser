package bigwork;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class SmallPicture extends JLabel {
	int smallwidth = 200, smallheight = 120;

	// 获取图片,转为图标
	protected ImageIcon view(String path) {

		ImageIcon icon;
		icon = new ImageIcon(path);
		int w = icon.getIconWidth();
		int h = icon.getIconHeight();
		if (w < h) {
			smallwidth = smallheight * w / h;
		} else {
			smallheight = smallwidth * h / w;
		}
		icon.setImage(icon.getImage().getScaledInstance(smallwidth, smallheight, Image.SCALE_DEFAULT));
		return icon;
	}

	public void setClick(String path) {
		BigPicture jl = new BigPicture(path);
		this.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, jl, "", JOptionPane.PLAIN_MESSAGE);
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
	}

	public SmallPicture(String path, int i) {
		setVerticalTextPosition(JLabel.CENTER);
		setHorizontalTextPosition(JLabel.CENTER);

		
		this.setIcon(view(path));
		setClick(path);
	}
}
