package disk;

import java.io.Serializable;

import myFile.ExeFile;
import myFile.Folder;

/**
 * 界面可调用的方法</br>
 * getFat()获取FAT表</br>
 * getRootFolder()获取根目录</br>
 * getDiskSize()获取磁盘已使用大小</br>
 * @author 风信子
 *
 */
public class Disk implements Serializable{

	//-------------------------------------数据域--------------------------------
	/**
	 * FAT表
	 */
	private Fat fat;
	/**
	 * 根目录
	 */
	private Folder rootFolder;

	//-----------------------------------构造方法------------------------------------
	/**
	 * 磁盘构造方法
	 */
	public Disk(){
		fat = new Fat();
		rootFolder = new Folder();
	}

	//-----------------------------------get方法-------------------------------------
	/**
	 * 获取FAT表
	 * @return
	 */
	public Fat getFat(){
		return fat;
	}

	/**
	 * 获取根目录
	 * @return根目录
	 */
	public Folder getRootFolder(){
		return rootFolder;
	}

	/**
	 * 获取磁盘占用空间
	 * @return磁盘已使用空间大小
	 */
	public int getDiskSize(){
		return 256 + rootFolder.getSize();
	}

}
