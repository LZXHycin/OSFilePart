package myFile;

import java.io.Serializable;
import java.util.ArrayList;
import disk.MyDisk;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Folder extends MyFile implements Serializable{

	//-------------------------数据域-----------------------------------
	/**
	 * 目录下的子文件表
	 */
	private ArrayList<MyFile> childrenFiles = new ArrayList<MyFile>();

	//------------------------------构造方法-----------------------------
	/**
	 * root根目录构造方法
	 */
	public Folder(){
		super();
		this.originNum = 4;
		this.name = "root";
		this.attribute = 'f';
	}

	/**
	 * 文件复制所用构造方法
	 * @param name文件名
	 */
	public Folder(String name){
		super(name);
		this.attribute = 'f';
	}

	/**
	 * 新建文件所用构造方法
	 * @param parent父目录
	 */
	public Folder(MyFile parent){
		super(parent);
		this.name = "新建文件夹";
		this.attribute = 'f';
	}

	//-----------------------------功能类的方法------------------------
	/*
	 * 粘贴
	 * file:所要粘贴的文件
	 */
	public void paste(MyFile file){
		//目录总大小增加
//		System.out.println("粘贴文件的大小" + file.getSize());
		//循环改变父目录的大小
		MyFile parent = this;
		while (parent != null) {
			parent.setSize(parent.getSize() + file.getSize());
			parent = parent.getParent();
		}
		//子文件列表添加该文件
//		System.out.println("pre:"+childrenFiles.size());
		this.childrenFiles.add(file);
//		System.out.println("cur:"+childrenFiles.size());
		//指定文件的父目录
		file.setParent(this);
		file.setOriginNum(MyDisk.getDisk().getFat().changeByFileCopy(file.getSize()));
	}

	/**
	 * 删除
	 */
	public boolean delete(){
		Folder parent = (Folder) this.parent;
		parent.setSize(parent.getSize() - this.size);
		System.out.println(this.name + "有" + childrenFiles.size());
		while (this.childrenFiles.size() != 0) {
			childrenFiles.get(0).delete();
		}
		MyDisk.getDisk().getFat().printFat();
		System.out.println("首磁盘号" + this.originNum);
		System.out.println("删除" + this.name);
		MyDisk.getDisk().getFat().release(this.originNum);
		parent.removeFile(this);
		return true;
	}

	/**
	 * 新建
	 * @param choose选择的文件类型：1为目录，2为普通文本文件，3为可执行文件
	 * @return
	 */
	public MyFile newFile(int choose){
		if (choose == 1) {
			Folder folder = new Folder(this);
			this.paste(folder);
			return folder;
		}else if (choose == 2) {
			TxtFile txtFile = new TxtFile(this);
			this.paste(txtFile);
			return txtFile;
		}else {
			ExeFile exeFile = new ExeFile(this);
			this.paste(exeFile);
			return exeFile;
		}
	}

	/**
	 * 寻找指定文件名的文件
	 * @param name文件名
	 * @return MyFile
	 */
	public MyFile seekFile(String name){
		for (int index = 0; index < childrenFiles.size(); index++) {
			if (name.equals(childrenFiles.get(index).getName())) {
				return childrenFiles.get(index);
			}
		}
		return null;
	}

	//--------------------------外部可使用方法----------------------------
	/**
	 * 移除文件，用于文件删除
	 * @param file
	 */
	public void removeFile(MyFile file){
		this.childrenFiles.remove(file);
	}

	/**
	 * 是否为文件夹
	 */
	public boolean isFolder(){
		return true;
	}

	//--------------------------get方法------------------------------------
	/**
	 * 获取子文件
	 * @return
	 */
	public ArrayList<MyFile> getChildrenFiles(){
		return this.childrenFiles;
	}
}
