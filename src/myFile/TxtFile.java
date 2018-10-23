package myFile;

import java.io.Serializable;
import disk.MyDisk;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TxtFile extends MyFile implements Serializable{

	//-----------------------------数据域----------------------
	/**
	 * 文本内容
	 */
	private String text;

	//-----------------------------构造方法------------------------
	/**
	 * 复制时所用的构造方法
	 * @param name文件名
	 */
	public TxtFile(String name, String text) {
		super(name);
		this.text = text;
		//拓展名为t
		this.extensionName = 't';
		this.attribute = 'o';

	}

	/**
	 * 新建是所用的构造方法
	 * @param parent
	 */
	public TxtFile(MyFile parent){
		super(parent);
		this.name = "新建文件";
		this.extensionName = 't';
		this.attribute = 'o';
	}

	//--------------------------功能类方法-----------------------
	/**
	 *文件复制
	 *返回TxtFile对象，未指定父目录
	 */
	@Override
	public MyFile copy() {
		//新建文件
		TxtFile file = new TxtFile(this.name, this.text);
		//复制文本内容
		file.setSize(this.size);
		return file;
	}

	//-----------------------------set方法-------------------------
	/**
	 * 修改文本内容
	 * @param text
	 */
	public void setText(String text){
		this.text = text;
		//原本的文件大小
		int oldSize = this.size;
		//文件大小=内容大小+属性大小
		this.size = text.length() + 8;
		//文件大小的改变
		int changeSize  = this.size - oldSize;
		//循环改变父目录的大小
		MyFile parent = this.parent;
		while (parent != null) {
			parent.setSize(parent.getSize() + changeSize);
			parent = parent.getParent();
		}
		//重新该文件的分配FAT
		MyDisk.getDisk().getFat().changeByTextSize(this.size, this.originNum);
	}

	//--------------------------------get方法----------------------------
	public String getText(){
		return text;
	}
}
