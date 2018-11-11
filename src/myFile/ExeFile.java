package myFile;

import java.io.Serializable;

import disk.MyDisk;

public class ExeFile extends MyFile implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	//--------------------------------数据域----------------------------
	/**
	 * 文本内容
	 */
	private String text;
	/**
	 * 指令
	 */
	private String[] orders;

	//-------------------------------构造方法----------------------------
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
		this.name = "EXE";
		this.extensionName = 'e';
		this.attribute = 'o';
	}

	/**
	 * 实现创建的可执行文件构造函数
	 * @param name
	 * @param orders
	 */
	public ExeFile(String name, String[] orders, MyFile parent){
		super(name);
		this.orders = orders;
		this.deletable = false;
		this.extensionName = 'e';
		this.attribute = 'o';
		this.size += orders.length;
		String orderText = "";
		for (int i = 0; i < orders.length; i++) {
			orderText = orderText + orders[i] + '\n';
		}
		this.text = orderText;
	}

	//----------------------------------功能类方法-----------------------------
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

	//-----------------------------------set方法------------------------------
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
		MyDisk.getDisk().getFat().changeByTextSize(this.size, this.originNum);
	}


	//----------------------------------get方法-------------------------------
	/**
	 *返回命令数据
	 * @return
	 */
	public String[] getOrders(){
		return orders;
	}

	/**
	 * 返回文本内容
	 * @return
	 */

	public String getText(){
		return text;
	}
}
