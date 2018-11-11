package myFile;

import java.io.Serializable;
import java.util.ArrayList;

import disk.MyDisk;

public class MyFile implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	//------------------------------------数据域-----------------------------------
	/**
	 * 文件是否可删除
	 */
	protected boolean deletable = true;
	/**
	 * 文件名
	 */
	protected String name = null;
	/**
	 * 文件拓展名：'e'可执行文件，'t'普通文本文件，' '目录
	 */
	protected char extensionName = ' ';
	/**
	 * 文件属性:'r'只读, 's'系统文件, 'o'普通文件, 'f'目录
	 */
	protected char attribute;
	/**
	 * 文件占用磁盘的初始磁盘块号
	 */
	protected int originNum;
	/**
	 * 文件大小
	 */
	protected int size = 0;
	/**
	 * 文件父目录
	 */
	protected MyFile parent = null;

	//---------------------------------构造方法---------------------------------
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

	//----------------------------------功能类的方法---------------------------
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
	public boolean delete(){
		if (deletable) {
			Folder parent = (Folder) this.parent;
			parent.setSize(parent.getSize() - this.size);
			MyDisk.getDisk().getFat().printFat();
//		System.out.println("首磁盘号" + this.originNum);
//		System.out.println("删除" + this.name);
//		Disk.fat.release(this.originNum);
			MyDisk.getDisk().getFat().release(this.originNum);
//		Disk.fat.printFat();
			parent.removeFile(this);
			return true;
		}else {
			return false;
		}
	}

	/**
	 * 重命名
	 * @param name 文件名
	 */
	public boolean rename(String name){
		if (name.length() > 3) {
			return false;
		}else {
			this.name = name;
			return true;
		}
	}

	//---------------------------------外部可使用的方法------------------------
	/**
	 * 是否为文件夹
	 * @return
	 */
	public boolean isFolder(){
		return false;
	}

	//--------------------------------get方法----------------------------------
	/**
	 * @return文件的首磁盘块号
	 */
	public int getOriginNum(){
		return originNum;
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
	 * 获取文件大小
	 * @return 文件大小
	 */
	public int getSize(){
		return size;
	}

	//-----------------------------------set方法-----------------------------------
	/**
	 * 设置占用的首盘块，多用于文件粘贴时使用
	 * @param num 首盘块号
	 */
	protected void setOriginNum(int num){
		this.originNum = num;
	}

	/**
	 * 设置父目录
	 * @param parent 父目录
	 */
	public void setParent(MyFile parent){
		this.parent = parent;
	}

	/**
	 * 设置文件的大小，多用于复制文件时使用
	 * @param size 文件大小
	 */
	protected void setSize(int size){
		this.size = size;
	}

	public boolean isFile() {
		return true;
	}

	public ArrayList<MyFile> getChildrenFiles() {
		return null;
	}
}
