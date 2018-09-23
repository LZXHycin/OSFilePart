package myFile;

import java.io.Serializable;

import disk.MyDisk;
import javafx.scene.image.ImageView;

public class MyFile implements Serializable{

	//文件名,3B
	protected String name = null;
	/**
	 * 文件拓展名：'e'可执行文件，'t'普通文本文件，' '目录
	 */
	protected char extensionName = ' ';
	/**
	 * 文件属性:'r'只读, 's'系统文件, 'o'普通文件, 'f'目录
	 */
	protected char attribute;
	//初始盘号,1B
	protected int originNum;
	//文件大小(单位为B),2B
	protected int size = 0;
	//父目录
	protected MyFile parent = null;

	/**
	 * 无参构造方法：只用于根目录创建
	 */
	public MyFile(){
		this.size = 8;
	}

	/**
	 * 复制时所用的构造方法
	 * @param name文件名
	 */
	public MyFile(String name){
		this();
		this.name = name;
	}

	/**
	 * 新建文件时所用的构造方法
	 * @param parent父目录
	 */
	public MyFile(MyFile parent){
		this();
		this.parent = parent;
	}



	/**
	 * 复制
	 * @return Myfile对象
	 */
	public MyFile copy() {
		return null;
	}

	/**
	 * 删除
	 */
	public void delete(){
		Folder parent = (Folder) this.parent;
		parent.setSize(parent.getSize() - this.size);
		MyDisk.disk.getFat().printFat();
//		System.out.println("首磁盘号" + this.originNum);
//		System.out.println("删除" + this.name);
//		Disk.fat.release(this.originNum);
		MyDisk.disk.getFat().release(this.originNum);
//		Disk.fat.printFat();
		parent.removeFile(this);
	}

	/**
	 * 重命名
	 * @param name 文件名
	 */
	public void rename(String name){
		this.name = name;
	}




	/**
	 * 获取文件的首磁盘块号
	 * @return
	 */
	public int getOriginNum(){
		return originNum;
	}

	/**
	 * 设置占用的首盘块，多用于文件粘贴时使用
	 * @param num 首盘块号
	 */
	protected void setOriginNum(int num){
		this.originNum = num;
	}

	/**
	 * 获取文件名
	 * @return 文件名
	 */
	public String getName(){
		return name;
	}

	/**
	 * 获取父目录
	 * @return Myfile对象
	 */
	public MyFile getParent(){
		return parent;
	}

	/**
	 * 设置父目录
	 * @param parent 父目录
	 */
	public void setParent(MyFile parent){
		this.parent = parent;
	}

	/**
	 * 获取文件大小
	 * @return 文件大小
	 */
	public int getSize(){
		return size;
	}

	/**
	 * 设置文件的大小，多用于复制文件时使用
	 * @param size 文件大小
	 */
	protected void setSize(int size){
		this.size = size;
	}



	/**
	 * 是否为文件夹
	 * @return
	 */
	public boolean isFolder(){
		return false;
	}

}
