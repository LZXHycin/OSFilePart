package myFile;

import java.io.Serializable;

import disk.MyDisk;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ExeFile extends MyFile implements Serializable{

	//文本内容
	private String text;
	private String[] orders;

	/**
	 * 复制时所用的构造方法
	 * @param name文件名
	 */
	public ExeFile(String name, String text) {
		super(name);
		this.text = text;
		//拓展名为e
		this.extensionName = 'e';
		this.attribute = 'o';
	}

	/**
	 * 新建时所用的构造方法
	 * @param parent父目录
	 */
	public ExeFile(MyFile parent){
		super(parent);
		this.name = "新建文件";
		this.extensionName = 'e';
		this.attribute = 'o';
	}


	/**
	 * 文件复制
	 * 返回ExeFile对象，未指定父目录
	 */
	@Override
	public MyFile copy() {
		ExeFile file = new ExeFile(this.name, this.text);
		file.setText(this.text);
		file.setSize(this.size);
		return file;
	}



	/**
	 * 修改文本内容
	 * @param text
	 */
	public void setText(String text){
		this.text = text;
		int oldSize = this.size;
		//文件大小=命令数+属性大小
		orders = text.split("\\n+");
		this.size = orders.length + 8;
		//文件大小的改变
		int changeSize  = this.size - oldSize;
		//循环改变父目录的大小
		MyFile parent = this.parent;
		while (parent != null) {
			parent.setSize(parent.getSize() + changeSize);
			parent = parent.getParent();
		}
		MyDisk.disk.getFat().changeByTextSize(this.size, this.originNum);
	}

	/**
	 *返回命令数据
	 * @return
	 */
	public String[] getOrders(){
		return orders;
	}
}
