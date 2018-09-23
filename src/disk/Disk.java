package disk;

import java.io.Serializable;

import myFile.Folder;

public class Disk implements Serializable{

	//FAT表
	private Fat fat;
	//根目录
	private Folder rootFolder;

	public Disk(){
		fat = new Fat();
		rootFolder = new Folder();
	}

	/**
	 * 获取FAT表
	 * @return
	 */
	public Fat getFat(){
		return fat;
	}

	/**
	 * 获取根目录
	 * @return
	 */
	public Folder getRootFolder(){
		return rootFolder;
	}

	/**
	 * 获取磁盘占用空间
	 * @return
	 */
	public int getDiskSize(){
		return 256 + rootFolder.getSize();
	}

}
