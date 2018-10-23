package myFile;

import java.io.Serializable;
import java.util.ArrayList;
import disk.MyDisk;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Folder extends MyFile implements Serializable{

	//-------------------------������-----------------------------------
	/**
	 * Ŀ¼�µ����ļ���
	 */
	private ArrayList<MyFile> childrenFiles = new ArrayList<MyFile>();

	//------------------------------���췽��-----------------------------
	/**
	 * root��Ŀ¼���췽��
	 */
	public Folder(){
		super();
		this.originNum = 4;
		this.name = "root";
		this.attribute = 'f';
	}

	/**
	 * �ļ��������ù��췽��
	 * @param name�ļ���
	 */
	public Folder(String name){
		super(name);
		this.attribute = 'f';
	}

	/**
	 * �½��ļ����ù��췽��
	 * @param parent��Ŀ¼
	 */
	public Folder(MyFile parent){
		super(parent);
		this.name = "�½��ļ���";
		this.attribute = 'f';
	}

	//-----------------------------������ķ���------------------------
	/*
	 * ճ��
	 * file:��Ҫճ�����ļ�
	 */
	public void paste(MyFile file){
		//Ŀ¼�ܴ�С����
//		System.out.println("ճ���ļ��Ĵ�С" + file.getSize());
		//ѭ���ı丸Ŀ¼�Ĵ�С
		MyFile parent = this;
		while (parent != null) {
			parent.setSize(parent.getSize() + file.getSize());
			parent = parent.getParent();
		}
		//���ļ��б���Ӹ��ļ�
//		System.out.println("pre:"+childrenFiles.size());
		this.childrenFiles.add(file);
//		System.out.println("cur:"+childrenFiles.size());
		//ָ���ļ��ĸ�Ŀ¼
		file.setParent(this);
		file.setOriginNum(MyDisk.getDisk().getFat().changeByFileCopy(file.getSize()));
	}

	/**
	 * ɾ��
	 */
	public boolean delete(){
		Folder parent = (Folder) this.parent;
		parent.setSize(parent.getSize() - this.size);
		System.out.println(this.name + "��" + childrenFiles.size());
		while (this.childrenFiles.size() != 0) {
			childrenFiles.get(0).delete();
		}
		MyDisk.getDisk().getFat().printFat();
		System.out.println("�״��̺�" + this.originNum);
		System.out.println("ɾ��" + this.name);
		MyDisk.getDisk().getFat().release(this.originNum);
		parent.removeFile(this);
		return true;
	}

	/**
	 * �½�
	 * @param chooseѡ����ļ����ͣ�1ΪĿ¼��2Ϊ��ͨ�ı��ļ���3Ϊ��ִ���ļ�
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
	 * Ѱ��ָ���ļ������ļ�
	 * @param name�ļ���
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

	//--------------------------�ⲿ��ʹ�÷���----------------------------
	/**
	 * �Ƴ��ļ��������ļ�ɾ��
	 * @param file
	 */
	public void removeFile(MyFile file){
		this.childrenFiles.remove(file);
	}

	/**
	 * �Ƿ�Ϊ�ļ���
	 */
	public boolean isFolder(){
		return true;
	}

	//--------------------------get����------------------------------------
	/**
	 * ��ȡ���ļ�
	 * @return
	 */
	public ArrayList<MyFile> getChildrenFiles(){
		return this.childrenFiles;
	}
}
